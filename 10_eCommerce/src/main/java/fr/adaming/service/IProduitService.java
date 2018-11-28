package fr.adaming.service;

import java.util.List;



import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;


public interface IProduitService {
	
	public List<Produit> getAllProduit();
	
	public Produit addProduit(Produit pro, Categorie ca);
	
	public int deleteProduit(Produit pro);
	
	public int modifierProduit(Produit pro, Categorie ca);
	
	public List<Produit> getAllProduitByCat(Produit pro, Categorie ca);
	
	public Produit getProduitById(Produit pro);
	
	public List<Produit> getProduitByDes(Produit pro, Categorie ca);

}
