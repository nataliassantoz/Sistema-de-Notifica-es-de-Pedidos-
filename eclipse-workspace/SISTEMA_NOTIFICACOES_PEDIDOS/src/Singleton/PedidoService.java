package Singleton;
import Factory.FactoryNotificacoes;
import Factory.Notificacao;
import Observer.DepLogística;
import Observer.Marketing;
import Observer.Observer;
import modulo.Pedido;
import modulo.StatusPedido;
// 				classe que regencia a classe pedido
//				nao chama a clase pelo construtor new


public class PedidoService {
	

	static Pedido pedidoUnico;	
	private static PedidoService instance;
	private int numeroPedido;
	
	
	public static PedidoService getInstance() {
        if (instance == null) {
        	instance = new PedidoService();
        }
        return instance;
    }
	
	private PedidoService() {
		this.numeroPedido = 1;
	}
	
    //  criacao do pedido - nesse momento todos os observers devem ser notificados 
    public Pedido criarPedido(Observer cliente, double valor) {
    	
    	String mensagemCriacaoPedido = "Pedido criado com sucesso! ";
    	pedidoUnico =  new Pedido(numeroPedido, cliente, valor);
    
    	pedidoUnico.adicionarObserver(cliente);
        
        //criando os departamentos usando injecao de dependencia 
        Observer marketing  = new Marketing();
        Observer depLogistica = new DepLogística();
        
        pedidoUnico.adicionarObserver(marketing);
        pedidoUnico.adicionarObserver(depLogistica);
        pedidoUnico.notificar(mensagemCriacaoPedido);
  
        return pedidoUnico;
    }
    
    public void adicionarItemPedido(String item) { 
        
    	pedidoUnico.adicionarItem(item);
    	System.out.println(item + " adicionado ao pedido!");
    }
  
    public void fecharPedidoNotificar(String tipoNotificacao) {
    	
    	pedidoUnico.fecharPedido();
    	FactoryNotificacoes  notificarfabrica = new FactoryNotificacoes();
    	 // n ==> é a intancia do tipo de notificacao 
    	 // n é do tipo notificacao, pois essa iterface referencia todos os tipos de notificacoes 
    	 //obj que chama metodo que passa tipoNotificacao
    	Notificacao n = notificarfabrica.notificarTipo(tipoNotificacao); //ira retornar a intancia da notificacao correta
    	n.enviarNotificacao(pedidoUnico);
    	
    }
    
    public void cancelarPedido() {
        if (pedidoUnico.getStatus().equals(StatusPedido.FINALIZADO)) {
            System.err.println("Não é possível cancelar o pedido, pois já foi finalizado!");
        } 
        else if (pedidoUnico.getStatus().equals(StatusPedido.CANCELADO)) {
            System.err.println("Não é possível cancelar o pedido, pois ele já está cancelado!");
        } 
        else {
            pedidoUnico.setStatus(StatusPedido.CANCELADO);
            System.out.println("Pedido cancelado com sucesso!");
        }
    }

    
    public void exibirPedidos() {
    	System.out.println("SEUS PEDIDOS: ");
    	pedidoUnico.consultarItens();
    }
}