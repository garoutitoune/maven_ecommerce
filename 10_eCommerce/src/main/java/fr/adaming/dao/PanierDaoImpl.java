package fr.adaming.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.CommandeServiceImpl;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneService;
import fr.adaming.service.LigneServiceImpl;


public class PanierDaoImpl implements IPanierDao{

	@Override
	public Panier addProd(Panier panier, Produit produit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Panier delProd(Panier panier, Produit produit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Panier delProd2(Panier panier, Produit produit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePanier(Panier panier, Commande commande) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	
}
