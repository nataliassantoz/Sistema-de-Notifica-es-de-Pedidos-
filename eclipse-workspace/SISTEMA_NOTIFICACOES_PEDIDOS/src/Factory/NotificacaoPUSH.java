package Factory;

import modulo.Pedido;

public class NotificacaoPUSH implements Notificacao {

	@Override
	public void enviarNotificacao(Pedido pedido) {
		System.out.println("Enviando PUSH para o cliente sobre o pedido: " + pedido);
		
	}

}
