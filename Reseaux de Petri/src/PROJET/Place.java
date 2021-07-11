package PROJET;

import java.util.ArrayList;
/**
 * <h1>Place<h1>
 * Cette classe definit une Place d'un reseau de petri.
 * @author abjabja salah-eddine + boulanouar walid
 *
 */
public class Place {
	String name;
	private int nbrjetons;
	private ArrayList<Transition> TransPredess;
	private ArrayList<Transition> TransSuccess;
	
	/**
	 * constructer 1, permet de definir une place nommee "s" contenante n jetens.
	 * @param s string
	 * @param n int
	 */
	public Place(String s,int n) {
		this.name=s;
		this.nbrjetons=n;
		this.TransPredess=new ArrayList<Transition>();
		this.TransSuccess=new ArrayList<Transition>();
	}
	/**
	 * constructer 2, permet de definir une place nommee "s".
	 * @param s string
	 */
	public Place(String s) {
		this.name=s;
		this.TransPredess=new ArrayList<Transition>();
		this.TransSuccess=new ArrayList<Transition>();
	}
	/**
	 * constructer 3, permet de definir une place nommee "s" contenante n jetens succédee par les element de S et precedee par les element de P.
	 * @param s string
	 * @param n int
	 * @param P liste de transitions
	 * @param S liste de transitions
	 */
	public Place(String s,int n,ArrayList<Transition> P,ArrayList<Transition> S) {
		this.TransPredess=P;
		this.TransSuccess=S;
		this.name=s;
		this.nbrjetons=n;
	}
	/**
	 * constructer 4, permet de definir une place nommee "s" contenante n jetens succédee par S et precedee par P.
	 * @param s string
	 * @param n int
	 * @param P transition
	 * @param S transition
	 */
	public Place(String s,int n,Transition P,Transition S) {
		ArrayList<Transition> Ts=new ArrayList<Transition>();
		Ts.add(S);
		ArrayList<Transition> Tp=new ArrayList<Transition>();
		Ts.add(P);
		this.TransPredess=Ts;
		this.TransSuccess=Tp;
		this.name=s;
		this.nbrjetons=n;
	}


	/**
	 * cette methode lie la place a une transition T.
	 * si c=S ou c=s la fonction ajoute T a la liste des successeure, si c=P ou c=p la fonction ajoute T a la liste des predecesseurs.
	 * @param c char
	 * @param T TRANSITION
	 */
	public void linkToTransition(char c ,Transition T) {
		if(c=='P'||c=='p'){
			this.TransPredess.add(T);
		}
		if(c=='S'||c=='s'){
			this.TransSuccess.add(T);
		}
	}
	
	
	public ArrayList<Transition> getTransPredess() {
		return TransPredess;
	}
	public void setTransPredess(ArrayList<Transition> transPredess) {
		TransPredess = transPredess;
	}
	public ArrayList<Transition> getTransSuccess() {
		return TransSuccess;
	}
	public void setTransSuccess(ArrayList<Transition> transSuccess) {
		TransSuccess = transSuccess;
	}
	public int getNbrjetons() {
		return nbrjetons;
	}
	public void setNbrjetons(int nbrjetons) {
		this.nbrjetons = nbrjetons;
	}

}
