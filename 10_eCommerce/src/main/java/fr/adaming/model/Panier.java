package fr.adaming.model;

import java.util.ArrayList;
import java.util.List;


public class Panier {

	
	//asso uml java
	private List<LigneCommande> listeLignes=new ArrayList<>();

	public List<LigneCommande> getListeLignes() {
		return listeLignes;
	}

	public void setListeLignes(List<LigneCommande> listeLignes) {
		this.listeLignes = listeLignes;
	}
	
	
	
	
	
}
