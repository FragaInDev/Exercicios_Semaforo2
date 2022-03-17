package controller;

import java.util.concurrent.Semaphore;

public class Chef {
	private String nome;
	private Semaphore filaPratos;
	private Semaphore filaEntrega;
	
	public Chef (String _nome) {
		nome = _nome;
		filaPratos = new Semaphore(5);
		filaEntrega = new Semaphore(1);
	}
	
	public void novoPrato() {
		new Prato(nome, filaPratos, filaEntrega).start();
	}
}
