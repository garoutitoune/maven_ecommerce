package fr.adaming.dao;

import java.util.List;




import fr.adaming.model.Produit;


public interface IProduitDao {
	
	public List<Produit> getAllProduit();
	
	public Produit addProduit(Produit pro);
	
	public int deleteProduit(Produit pro);
	
	public int modifierProduit(Produit pro);
	
	public List<Produit> getAllProduitCat(Produit pro);
	
	public Produit getProduitById(Produit pro);
	
	public List<Produit> getProduitByDescr(Produit pro);
	
	public int modifierProduitSansPhoto(Produit pro);

}
