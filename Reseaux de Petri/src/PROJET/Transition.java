package PROJET;

import java.util.ArrayList;

/**
 * <h1>Transition<h1>
 * Cette classe definit une transition d'un reseau de petri.
 * @author abjabja salah-eddine + boulanouar walid
 *
 */
public class Transition {
	String name;
	private ArrayList<Place> PlacesPredess;
	private ArrayList<Place> PlacesSuccess;
	
	/**
	 * constructer 1, permet de definir une transition nommee "s".
	 * @param s string
	 */
	public Transition(String s) {
		this.name=s;
		this.PlacesPredess=new ArrayList<Place>();
		this.PlacesSuccess=new ArrayList<Place>();
	}
	/**
	 * constructer 2, permet de definir une transition nommee "s" succédee par les element de S et precedee par les element de P.
	 * @param n string
	 * @param P list de places
	 * @param S list de places
	 */
	public Transition(String n,ArrayList<Place> P,ArrayList<Place> S) {
		this.name=n;
		this.PlacesPredess=P;
		this.PlacesSuccess=S;
	}
	/**
	  * constructer 3, permet de definir une transition nommee "s" succédee par S et precedee par P.
	 * @param n string
	 * @param P place
	 * @param S place
	 */
	public Transition(String s,Place P,Place S) {
		this.name=s;
		ArrayList<Place> Ps=new ArrayList<Place>();
		Ps.add(S);
		ArrayList<Place> Pp=new ArrayList<Place>();
		Ps.add(P);
		this.PlacesSuccess=Ps;
		this.PlacesPredess=Pp;
	}
	
	
	public ArrayList<Place> getPlacesPredess() {
		return PlacesPredess;
	}
	public void setPlacesPredess(ArrayList<Place> placesPredess) {
		PlacesPredess = placesPredess;
	}
	public ArrayList<Place> getPlacesSuccess() {
		return PlacesSuccess;
	}
	public void setPlacesSuccess(ArrayList<Place> placesSuccess) {
		PlacesSuccess = placesSuccess;
	}
	/**
	 * cette methode lie la transition a une place P.
	 * si c=S ou c=s la fonction ajoute P a la liste des successeure, si c=P ou c=p la fonction ajoute P a la liste des predecesseurs.
	 * @param c char
	 * @param p place
	 */
	public void linkToPlace(char c,Place p) {
		if(c=='P'||c=='p'){
			this.PlacesPredess.add(p);
		}
		if(c=='S'||c=='s'){
			this.PlacesSuccess.add(p);
		}
	}

}
