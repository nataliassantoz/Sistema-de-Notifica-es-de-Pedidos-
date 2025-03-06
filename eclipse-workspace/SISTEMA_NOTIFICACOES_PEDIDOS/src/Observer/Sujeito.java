package Observer;
// 						INTERFACE DO SUJEITO - PEDIDO

public interface Sujeito {
	public void adicionarObserver(Observer observador);
	public void removerObserver(Observer observador);
	public void notificar (String notificacao);
}
