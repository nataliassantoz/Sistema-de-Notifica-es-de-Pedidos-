package Main;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Arquivos.GerenciadorArquivos;
import Factory.FactoryNotificacoes;
import Observer.Cliente;
import Observer.Observer;
import Singleton.PedidoService;
import modulo.Pedido;



public class Main {
    
    static Scanner scanner = new Scanner(System.in);  
    static FactoryNotificacoes factory = new FactoryNotificacoes();
    static HashMap<String, Double> catalogo = new HashMap<>();
    static GerenciadorArquivos<String> gerenciadorArquivos = new GerenciadorArquivos<>();
    static Path caminhoDoArquivo = Paths.get("src/Arquivos/catalogo");
    static List<String> itensCliente = new ArrayList<>();
    static PedidoService  pedidoService = PedidoService.getInstance(); //pedidoService é o objeto
    static Observer cliente; 
    

    public static void main(String[] args) {
    	
    	lerProdutosDoArquivo(caminhoDoArquivo);
    	
    	while(true) {
	        System.out.println("             SEJA BEM VINDO         ");
	        System.out.println("             MENU DE OPÇÕES");
	        System.out.println();
	        System.out.println("1 - Cadastro");
	        System.out.println("2 - Realizar pedido");
	        System.out.println("3 - detalhar pedido");
	        System.out.println("4 - Finalizar pedido");
	        System.out.println("5 - Cancelar pedido");
	        System.out.println("7 - Sair");
	        
	        int opcao = scanner.nextInt();
	        scanner.nextLine();
	        
	        switch(opcao) {
	        
	            case 1:
	                cadastro();
	                break;
	            case 2:
	            	realizarPedido();
	            	break;
	            case 3:
	            	detalharPedido();
	            	break;
	            case 4:
	            	finalizarPedido();
	            	break;
	            	
	        }
    	}
    }
    
    public static void cadastro() {
    	
        System.out.println("Nome: ");
        String nome = scanner.nextLine();  // scanner estático pode ser acessado
        System.out.println("Numero telefone: ");
        String numero = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Cadastro realizado com sucesso.");
        
        cliente = new Cliente(nome, numero, email);
    }
   
    public static void realizarPedido() {
        exibirCatalogo(); 
        System.out.println();
        System.out.println("Digite os produtos escolhidos um por um (ou digite 'sair' para finalizar):");

        while (true) {
            String item = scanner.nextLine().trim(); // Lê a entrada do usuário e remove espaços em branco

            if (item.equalsIgnoreCase("sair")) {
                break; 
            }
            // Verifica se o produto está no catálogo
            if (catalogo.containsKey(item)) {
                itensCliente.add(item); 
            
            } else {
            	
                System.err.println("Produto não encontrado no catálogo: " + item);
            }
        }
        System.out.println("Pedido criado com sucesso!");

        // Calcula o valor total dos produtos escolhidos
        double valorTotal = calcularValorTotal(itensCliente);
        Pedido pedidoCliente = pedidoService.criarPedido(cliente, valorTotal);
        
     
        for (String item : itensCliente) {
            pedidoService.adicionarItemPedido(item); 
        }
       
    }

    public static void exibirCatalogo() {
    	
        for (Map.Entry<String, Double> entry :catalogo.entrySet()) {
            String produto = entry.getKey();
            Double valor = entry.getValue();
            System.out.println("Produto: " + produto + " | Preço: R$ " + valor);
        }
    }
    
    public static void lerProdutosDoArquivo(Path caminhoDoArquivo) {
        System.out.println("Iniciando...");
        try {
            // Lê o arquivo e obtém as linhas
            List<String> linhas = gerenciadorArquivos.lerArquivoTexto(caminhoDoArquivo);
            

            for (String linha : linhas) {
                // Remove espaços em branco no início e no fim da linha
                linha = linha.trim();

                // Divide a linha em partes
                String[] partes = linha.split(" "); // Divide a linha pelo espaço

                // Verifica se a linha contém pelo menos duas partes
                if (partes.length < 2) {
                    System.err.println("Linha inválida: " + linha); // Linha inválida
                    continue; // Ignora linhas inválidas
                }

                // O último elemento é o preço
                String precoStr = partes[partes.length - 1];
                Double precoProduto;

                // Tenta converter o preço para Double
                try {
                    precoProduto = Double.parseDouble(precoStr);
                } catch (NumberFormatException e) {
                    System.err.println("Preço inválido na linha: " + linha);
                    continue; // Ignora linhas com preço inválido
                }

                // O nome do produto é formado por todos os elementos, exceto o último
                String nomeProduto = String.join(" ", Arrays.copyOf(partes, partes.length - 1));

                // Adiciona ao HashMap
                catalogo.put(nomeProduto, precoProduto);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        
        
    }

    public static double calcularValorTotal(List<String> itens) {
        double total = 0.0;

        for (String item : itens) {
            // Remove espaços em branco do nome do item
            String nomeProduto = item.trim();

            // Verifica se o produto está no catálogo
            if (catalogo.containsKey(nomeProduto)) {
                total += catalogo.get(nomeProduto); // Adiciona o preço do produto ao total
            } else {
                System.err.println("Produto não encontrado no catálogo: " + nomeProduto);
            }
        }
		return total;
    } 
    public static void detalharPedido () {
    	pedidoService.exibirPedidos();
    }

    public static void finalizarPedido() {
    	System.out.println("Para finalizar o pedido, escolha por onde deseja receber a nota fiscal: ");
    	System.out.println();
    	System.out.println("1 - e-mail");
    	System.out.println("2 - SMSl");
    	System.out.println("3 - PUSH");
    	
    	String tipoNotificacao = null;
    	
    	int opcao = scanner.nextInt();
    	scanner.nextLine();
    	if(opcao == 1) {
    		tipoNotificacao = "email";
    	}
    	else if( opcao == 2) {
    		 tipoNotificacao = "sms";
    	}
    	else if(opcao == 3) {
    		 tipoNotificacao =" push";
    	}
    	else {
    		System.out.println("Opcao de confirmacao inválida senhor, digita essa bosta novamente!");
    		tipoNotificacao = "sms"; //supondo que o cliente sera um bocó
    	}
    
    	pedidoService.fecharPedidoNotificar(tipoNotificacao);
    }
}



