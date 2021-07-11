package PROJET;


import java.io.IOException;

/**
 * MAIN
 * la classe qui donne utilité  aux autre classes.
 * @author abjabja salah-eddine + boulanouar walid
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		//creation du reseau
		ReseauxDePetri RDP=new ReseauxDePetri();
		
		//creation des places et transitions
		Transition T1=new Transition("T1");	
		Transition T2=new Transition("T2");
		Place P1=new Place("P1",3);
		Place P2=new Place("P2",2);
		Place P3=new Place("P3",1);
		Place P4=new Place("P4",0);
		Place P5=new Place("P5",1);

		//l'ajout des places et transition aux reseau
		RDP.addplace(P1);
		RDP.addplace(P2);
		RDP.addplace(P3);
		RDP.addplace(P4);
		RDP.addplace(P5);	
		RDP.addTransition(T1);
		RDP.addTransition(T2);
		
		//lier les places avec les transitions
		P1.linkToTransition('s', T1);
		P2.linkToTransition('P', T1);
		P3.linkToTransition('P', T1);
		P2.linkToTransition('S', T2);
		P3.linkToTransition('S', T2);
		P4.linkToTransition('P', T2);
		P5.linkToTransition('p', T2);
		P5.linkToTransition('S', T1);

		//complete les laisons manquantes
		RDP.autofill();
		

		
		
		//affichage console simple
		RDP.affichePlaces();
		System.out.println("\n\n        *** ");
		RDP.afficheTransitions();
		
		//generation et affichage du graph sous format png
		
		RDP.toGraphPng();
		
		
	}

}
