package Observer;

public class Marketing implements Observer{

	@Override
	public void atualizar(String notificacao) {
		System.out.println("Departamento de marketing! ");
		System.out.println("Você tem uma nova notificação: ");
		System.out.println("Notificação: " + notificacao + "\n");
	}

}
