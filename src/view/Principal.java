package view;

import java.util.Random;

import controller.Chef;

public class Principal {
	
	public static final String chefs[] = {"Bruno", "Fogazza", "Jacquin", "Leonardo", "Clebão", "Ana Maria Brogui", "Paola", "Beca", "Amaury", "Mohammed" };
	
	public static void main(String[] args) {
		int qntChef = (int) ((Math.random() * 10)+ 1);
		int qntPratos = (int) ((Math.random()* 10)+ 5);
		
		Chef[] masterChefs = new Chef[qntChef];
		String buffer = "";
		
		for (int i = 0; i < qntChef; i++) {
			String nome = nomeRandom();
			masterChefs[i] = new Chef(nome);
			buffer += nome + " | ";
		}
		
		System.out.println("Chefs: " + buffer);
		
		for (int i = 0; i < qntPratos; i++) {
			for(Chef mchefs : masterChefs) {
				mchefs.novoPrato();
			}
		}

	}

	private static String nomeRandom() {
		int random = new Random().nextInt(chefs.length);
		return chefs[random];
	}

}
