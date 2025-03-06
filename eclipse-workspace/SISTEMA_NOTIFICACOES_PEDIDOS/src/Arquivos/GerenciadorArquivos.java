package Arquivos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GerenciadorArquivos<T> {

	    // Construtor vazio
	    public GerenciadorArquivos() {
	    }
	
	    // Método para ler um arquivo de texto e retornar o conteúdo como List<String>
	    public List<String> lerArquivoTexto(Path caminhoDoArquivo) throws IOException {
	    	
	        // Verifica se o arquivo existe antes de tentar ler
	        if (!Files.exists(caminhoDoArquivo)) {
	            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoDoArquivo);
	        }
	        return Files.readAllLines(caminhoDoArquivo, StandardCharsets.UTF_8);
	    }
	
	    // Método para escrever um conteúdo de texto (List<String>) no arquivo
	    public void escreverArquivoTexto(Path caminho, List<String> conteudo) throws IOException {
	        // Garante que o arquivo e seus diretórios existam
	        verificarOuCriarArquivo(caminho);
	        
	        // Escreve o conteúdo no arquivo
	        Files.write(caminho, conteudo, StandardCharsets.UTF_8);
	        System.out.println("Conteúdo escrito no arquivo.");
	    }
	
	    // Método genérico para salvar um objeto no arquivo (serialização)
	    public void salvarObjeto(Path caminho, T objeto) throws IOException {
	        // Garante que o arquivo e seus diretórios existam
	        verificarOuCriarArquivo(caminho);
	        
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho.toFile()))) {
	            oos.writeObject(objeto);
	            System.out.println("Objeto serializado e salvo no arquivo.");
	        }
	    }
	
	    // Método genérico para carregar um objeto do arquivo (desserialização)
	    public T carregarObjeto(Path caminho) throws IOException, ClassNotFoundException {
	        // Verifica se o arquivo existe antes de tentar carregar o objeto
	        if (!Files.exists(caminho)) {
	            throw new FileNotFoundException("Arquivo não encontrado: " + caminho);
	        }
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho.toFile()))) {
	            return (T) ois.readObject();
	        }
	    }
	
	    // Método para adicionar conteúdo textual ao arquivo sem sobrescrever
	    public void adicionarConteudoTexto(Path caminho, String conteudo) throws IOException {
	        // Garante que o arquivo e seus diretórios existam
	        verificarOuCriarArquivo(caminho);
	        
	        String conteudoExistente = Files.readString(caminho, StandardCharsets.UTF_8);
	        Files.writeString(caminho, conteudoExistente + conteudo, StandardCharsets.UTF_8);
	        System.out.println("Novo conteúdo adicionado ao arquivo.");
	    }
	
	    // Método para adicionar várias linhas ao arquivo sem sobrescrever
	    public void adicionarLinhasTexto(Path caminho, List<String> novasLinhas) throws IOException {
	        // Garante que o arquivo e seus diretórios existam
	        verificarOuCriarArquivo(caminho);
	        
	        List<String> conteudoExistente = Files.readAllLines(caminho, StandardCharsets.UTF_8);
	        conteudoExistente.addAll(novasLinhas);
	        Files.write(caminho, conteudoExistente, StandardCharsets.UTF_8);
	        System.out.println("Novas linhas adicionadas ao arquivo.");
	    }
	
	    // Método privado para verificar se o arquivo existe e, caso contrário, criá-lo junto com seus diretórios
	    private void verificarOuCriarArquivo(Path caminho) throws IOException {
	        if (!Files.exists(caminho.getParent())) {
	            Files.createDirectories(caminho.getParent()); // Cria diretórios, se necessário
	        }
	        if (!Files.exists(caminho)) {
	            Files.createFile(caminho); // Cria o arquivo, se não existir
	        }
	    }
}
