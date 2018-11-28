package fr.adaming.dao;

import java.util.List;



import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;


public interface ICategorieDao {
	
	public List<Categorie> getAllCategorie();
	
	public Categorie addCategorie(Categorie ca);
	
	public int modifierCategorie(Categorie ca);
	
	public int supprimerCategorie(Categorie ca);
	
	public int modifierCategoriePhoto(Categorie ca);

}
