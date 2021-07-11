package PROJET;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
/**
 * <h1>Reseaux de PETRI<h1>
 * Cette classe definit un reseau de petri.
 * @author abjabja salah-eddine + boulanouar walid
 * 
 */
public class ReseauxDePetri {
	private ArrayList<Place> places;
	private ArrayList<Transition> transitions;
	
	/**
	 * Constructeur 1 : fait la creation d'un reseaux de petri avec ses transitions et ses places.
	 * @param P liste de places
	 * @param T liste de transitions
	 */
	public ReseauxDePetri(ArrayList<Place> P,ArrayList<Transition> T) {
		this.places=P;
		this.transitions=T;
	}
	/**
	 * Constructeur 0 : initialise un reseaux de Petri vide.
	 */
	public ReseauxDePetri() {
		this.places=new ArrayList<Place>();
		this.transitions=new ArrayList<Transition>();
	}
	/**
	 * Ajout la place p a la liste places.
	 * @param p place
	 */
	public void addplace(Place p) {
		this.places.add(p);
	}
	/**
	 * Ajout la transition t a la liste transtions.
	 * @param t transition
	 */
	public void addTransition(Transition t) {
		this.transitions.add(t);
	}
	/**
	 * fait un affichage console simple des places/nbr jetons avec les transitions qui les entourent.
	 */
	public void affichePlaces() {
		for (Place p : places) {
			System.out.println();
			for (Transition t : p.getTransPredess()) {
				System.out.print(t.name+" , ");
			}
			System.out.print("--> ("+p.name+" / "+p.getNbrjetons()+" jetons) --> ");
			for (Transition t : p.getTransSuccess()) {
				System.out.print(t.name+" , ");
			}
		}
		
	}
	/**
	 * fait un affichage console simple des transitions avec les places qui les entourent.
	 */
	public void afficheTransitions() {
		for (Transition t : transitions) {
			System.out.println();
			for (Place p : t.getPlacesPredess()) {
				System.out.print(p.name+" , ");
			}
			System.out.print("--> ("+t.name+") --> ");
			for (Place p : t.getPlacesSuccess()) {
				System.out.print(p.name+" , ");
			}
		}
	}
	
	public Collection<Place> getPlaces() {
		return places;
	}public Collection<Transition> getTransitions() {
		return transitions;
	}public void setPlaces(Collection<Place> places) {
		this.places = (ArrayList<Place>) places;
	}public void setTransitions(Collection<Transition> transitions) {
		this.transitions = (ArrayList<Transition>) transitions;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * un remplissage automatique .
	 * exmpl: si une place a un predecesseur T cette fct l'ajoute a la liste des successeur de T si elle n'est pas encore ajouté.
	 */
	public void autofill() {
		for (Place p : places) {	
			for (Transition t : p.getTransSuccess()) {
				if (!t.getPlacesPredess().contains(p)) {
				    t.linkToPlace('p', p);
				}
			}
			for (Transition t : p.getTransPredess()) {
				if (!t.getPlacesSuccess().contains(p)) {
				    t.linkToPlace('s', p);
				}
			}
		}
		for (Transition t : transitions) {	
			for (Place p : t.getPlacesSuccess()) {
				if (!p.getTransPredess().contains(t)) {
				    p.linkToTransition('p', t);
				}
			}
			for (Place p : t.getPlacesPredess()) {
				if (!p.getTransSuccess().contains(t)) {
				    p.linkToTransition('s', t);
				}
			}
		}
	}
	
	

	/**
	 * cette fonction fait la redaction de dot qui va etre lu par graphviz apres.
	 * @return String
	 */
	public String toDot() {
		String D="digraph G {"
				+ "rankdir=LR;";
		for (Place p : places) {
			String s="";
			for (int i = 0; i < p.getNbrjetons(); i++) {
				s+="&bull;";
			}
			D+="\n	\""+p.name+"\n"+s+"\"[shape=circle];";
		}
		for (Transition t : transitions) {
			D+="\n	"+t.name+"[shape=box,color=black,style=filled,margin=\"0.1,0.66\",fontcolor=white];";
		}
		for (Place p : places) {
			String s="";
			for (int i = 0; i < p.getNbrjetons(); i++) {
				s+="&bull;";
			}
			
			for (Transition t : p.getTransSuccess()) {
				D+="\n	\""+p.name+"\n"+s+"\" -> "+t.name+";";
			}
			for (Transition t : p.getTransPredess()) {
				D+="\n	"+t.name+" -> \""+p.name+"\n"+s+"\";";
			}
		}
		D+="\n }";
		return D;
	}
	/**
	 * fait la representation graphique de Reseau de petri.
	 * a partir du retour de toDot, cette fonction cree un fichier dot.dot dans le bureau, 
	 * cree l'image graph.png dans le bureau a l'aide du l'invite de commande et a partir du fichier dot.dot, 
	 * ouvre l'image, et ferme l'invite de commande. //et supprime le fichier dot.
	 * @throws IOException
	 */
	public void toGraphPng() throws IOException {
		Runtime rt = Runtime.getRuntime();
		String s=this.toDot();
		PrintWriter writer = new PrintWriter("..\\..\\Desktop\\dot.dot", "UTF-8"); 		//creation du fichier dot
		writer.println(s);																//remplissage du dot
		writer.close();
		
		rt.exec("cmd.exe /c cd \""+"..\\..\\Desktop"+"\" & start cmd.exe "		//creation de png a partir du dot
				+ "/k \"dot -Tpng dot.dot -o graph.png && start graph.png && exit\"");
		
		//supression de fichier dot 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f=new File("..\\..\\Desktop\\dot.dot");
		f.delete();
	}

}
