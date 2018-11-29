package fr.adaming.dao;



import java.util.List;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;


public interface ILigneCommandeDao {

	public LigneCommande addLigne(LigneCommande ligne);
	public LigneCommande searchLigneById(LigneCommande ligne);
	public void delLigne(LigneCommande ligne);
	public List<LigneCommande> listeLignes(Commande commande);
	public void savePanier(Panier panier, Commande commande);
	
	
}
