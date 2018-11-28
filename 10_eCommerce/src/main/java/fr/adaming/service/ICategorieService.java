package fr.adaming.service;

import java.util.List;



import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;


public interface ICategorieService {
	
	public List<Categorie> getAllCategorie();
	
	public Categorie addCategorie(Categorie ca, Gerant g);
	
	public int modifierCategorie(Categorie ca);
	
	public int supprimerCategorie(Categorie ca);
	
	
}
