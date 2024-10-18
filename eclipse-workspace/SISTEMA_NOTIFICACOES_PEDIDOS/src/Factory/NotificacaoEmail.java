package Factory;

import modulo.Pedido;

public class NotificacaoEmail implements Notificacao {

	@Override
	public void enviarNotificacao(Pedido pedido) {
		System.out.println("Enviando E-MAIL " + pedido.getCliente() + " para o cliente sobre o pedido: " + pedido);
		
	}
}
