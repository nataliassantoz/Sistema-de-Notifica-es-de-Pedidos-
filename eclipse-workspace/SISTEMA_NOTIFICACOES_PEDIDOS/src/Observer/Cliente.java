package Observer;

import modulo.StatusPedido;

public class Cliente implements Observer{
	private String nome;
	private String numero;
	private String email;
	

	
	public Cliente(String nome, String numero, String email) {
		
		this.nome = nome;
		this.numero = numero;
		this.email = email;
	}

	@Override
	public void atualizar(String notificacao) {
		
		System.out.println("Olá querido Cliente!  ");
		System.out.println("VocÊ tem uma nova notificação!");
		System.out.println("Notificacao: "+ notificacao + "\n");
		if(notificacao == "cliente fechou o pedido 🎉") {
			System.out.println("Status atual do seu pedido: " + StatusPedido.FINALIZADO);
		}
		else if(notificacao == "Novo item adicionado a sua sacola!") {
			System.out.println("Status atual do seu pedido: " + StatusPedido.PROCESSANDO);
		}
		
	}

	@Override
	public String toString() {
		   return "Cliente: " + nome;
	}
	


}
