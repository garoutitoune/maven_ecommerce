package fr.adaming.service;

import java.util.Date;


import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.dao.LigneCommandeDaoImpl;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;


public class LigneServiceImpl implements ILigneService{

	@Override
	public LigneCommande addLigne(LigneCommande ligne, Commande commande, Produit produit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LigneCommande searchLigneById(LigneCommande ligne) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delLigne(LigneCommande ligne) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePanier(Panier panier, Client client) {
		// TODO Auto-generated method stub
		
	}
	
	
}
