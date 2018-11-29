package fr.adaming.service;



import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;


public interface ILigneService {

	public LigneCommande addLigne(LigneCommande ligne, Commande commande, Produit produit);
	public LigneCommande searchLigneById(LigneCommande ligne);
	public void delLigne(LigneCommande ligne);
	public List<LigneCommande> listeLignes(Commande commande);
	public void savePanier(Panier panier,  Client client);
}
