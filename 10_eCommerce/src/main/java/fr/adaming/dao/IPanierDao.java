package fr.adaming.dao;




import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;


public interface IPanierDao {

	public int addProd(Panier panier,Produit produit); // opération +1 =>opération =x
	public Panier delProd(Panier panier, Produit produit); //opération -1
	public Panier delProd2(Panier panier, Produit produit); //supprimer une ligne de commande
	public Commande savePanier(Panier panier, Client client);
	
	
}
