package Factory;

import modulo.Pedido;

public class NotificacaoSMS implements Notificacao{

	@Override
	public void enviarNotificacao(Pedido pedido) {
		System.out.println("Enviando SMS " + pedido.getCliente() + " para o cliente sobre o pedido: " + pedido);
		
	}
}
