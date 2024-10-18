package Observer;

public class DepLogística implements Observer {

	@Override
	public void atualizar(String notificacao) {
		System.out.println("Departamento de logistica! ");
		System.out.println("Você tem uma nova notificação: ");
		System.out.println("Notificação: " + notificacao + "\n");
	}
}
