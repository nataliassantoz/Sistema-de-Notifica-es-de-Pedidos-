package Factory;

//			 fabrica de notiificacoes
public class FactoryNotificacoes {
//	TIPO É O TIPO DA NOTIFICACAO QUE O CLIENTE DESEJA RECEBER
	public Notificacao notificarTipo(String tipo) {
		
		switch(tipo.toLowerCase()) {
		
		case "sms":
			return new NotificacaoSMS();
			
		case "email":
			return new NotificacaoEmail();
			
		case "push":
			return new NotificacaoPUSH();
			
		default:
			throw new IllegalArgumentException("Método de envio de notificação inválido: " + tipo);
	
	}
	}
}
