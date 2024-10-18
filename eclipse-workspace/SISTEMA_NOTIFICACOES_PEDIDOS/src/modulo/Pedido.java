package modulo;
import java.util.ArrayList;
import java.util.List;

import Observer.Cliente;
import Observer.Observer;
import Observer.Sujeito;

public class Pedido implements Sujeito{
	private int id;
	private Observer cliente;
	private double valor;
	private List<String> listaItens;
	private StatusPedido status;
	private List<Observer> listaObservadores;
	
	public Pedido(int id, Observer cliente, double valor) {
		
		this.id = id;
		this.cliente = cliente;
		this.valor = valor;
		this.listaObservadores = new ArrayList<>();
		this.listaItens =  new ArrayList<>();
		this.status = StatusPedido.PROCESSANDO;
		
	}

	String notificacaoFecharPedido = "cliente fechou o pedido 🎉";

	
	public int getId() {return id;}
	
	public void setId(int id) {this.id = id;}
	
	public Observer getCliente() {return cliente;}

	public void setCliente(Cliente cliente) {this.cliente = cliente;}
		
	public double getValor() {	return valor;}
	
	public void setValor(double valor) {this.valor = valor;}
		
	public List<String> getListaItens() {return listaItens;}
	
	public void setListaItens(List<String> listaItens) {this.listaItens = listaItens;}
		
	public StatusPedido getStatus() {return status;}
		
	public void setStatus(StatusPedido status) {this.status = status;}
	
   // Adiciona item e notifica
	public void adicionarItem(String itensPedido) {
		if(itensPedido == null || itensPedido.isEmpty()) {
			throw new IllegalArgumentException("O item do pedido não pode ser vazio ou nulo.");
		}
		listaItens.add(itensPedido);
	}
	
	 // Fecha pedido e altera status, notificando todos
	 public void fecharPedido() {
	        if (listaItens.isEmpty()) {
	            throw new IllegalArgumentException("O pedido não pode ser fechado.");
	        }
	        this.status = StatusPedido.FINALIZADO;
	        notificar("Cliente fechou o pedido.");
	    }
			
	public void removerItem(String item) {
		if(listaItens.isEmpty()) {
			throw new IllegalArgumentException("Sua sacola de compras esta vazia.");
		}
		if(!listaItens.contains(item)) {
			throw new IllegalArgumentException("Sua sacola de compras nao contem " + item);
		}
		listaItens.remove(item);
	}

	public void consultarItens() {
		if(listaItens.isEmpty()) {
			throw new IllegalArgumentException("Sua sacola de compras esta vazia.");
		}
		int i = 0;
		for(String item :  listaItens) {
			i++;
			System.out.println(i + " : " + item);
		}
	}
	@Override
	public void adicionarObserver(Observer observador) {
		if ( observador == null) {
			throw new IllegalArgumentException("Observador não pode ser nulo.");
		}
		listaObservadores.add(observador);
		
	}

	@Override
	public void removerObserver(Observer observador) {
		listaObservadores.remove(observador);
		
	}

	@Override
	public void notificar(String notificacao) {
		for(Observer observer :listaObservadores) {
			try {
				observer.atualizar(notificacao);
			}
			catch(Exception e) {
				 System.err.println("Erro ao notificar o observador: " + e.getMessage());}
			}
	}

	@Override
	public String toString() {
		return "Pedido [cliente=" + cliente + ", valor=" + valor + ", status=" + status + "]";
	}


	
	
}
