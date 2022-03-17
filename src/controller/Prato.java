package controller;

import java.util.concurrent.Semaphore;

public class Prato extends Thread{
	private String nomeChef;
	private String prato;
	private Semaphore filaPratos;
	private Semaphore filaEntrega;
	
	public Prato (String _nomeChef, Semaphore _filaPratos, Semaphore _filaEntrega) {
		nomeChef = _nomeChef;
		filaPratos = _filaPratos;
		filaEntrega = _filaEntrega;
	}
	
	@Override
	public void run() {
		switch (((int) this.getId()) % 2) {
		case 0:
			prato = "Lasanha";
			try {
				filaPratos.acquire();
				cLasanha();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				filaPratos.release();
			}
			entregarPrato();
			break;
		
		case 1:
			prato = "Sopa de Cebola";
			try {
				filaPratos.acquire();
				cSopa();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				filaPratos.release();
			}
			entregarPrato();
			break;
		}			
	}

	
	private void cLasanha() {
		System.out.println(nomeChef + " cozinhando " + prato);
		
		int tempoCozimento = (int) ((Math.random() * 601) +600);
		int contador = 0;
		while (contador < tempoCozimento) {
			try {
				if (contador + 100 <= tempoCozimento) {
					contador += 100;
				}else {
					contador += tempoCozimento - contador;
					System.out.println(nomeChef + ": " + prato + " " + (contador + 100) / tempoCozimento + "%");
					sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void cSopa() {
	System.out.println(nomeChef + " cozinhando " + prato);
		
		int tempoCozimento = (int) ((Math.random() * 301) +600);
		int contador = 0;
		while (contador < tempoCozimento) {
			try {
				if (contador + 100 <= tempoCozimento) {
					contador += 100;
				}else {
					contador += tempoCozimento - contador;
					System.out.println(nomeChef + ": " + prato + " " + (contador + 100) / tempoCozimento + "%");
					sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void entregarPrato() {
		try {
			System.out.println(nomeChef + ": Correndo para entregar o prato...");
			filaEntrega.acquire();
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			filaEntrega.release();
			System.out.println("O(a) chef " + nomeChef + " entregou o " + prato + " :D");
		}
	}

	
	
}
