package fr.adaming.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

@Service(value="liservice")
@Transactional
public class LigneServiceImpl implements ILigneService{
	
	//asso uml java
	@Autowired
	ILigneCommandeDao lidao;
	
	//setter pour injection

	public void setLidao(ILigneCommandeDao lidao) {
		this.lidao = lidao;
	}

	@Override
	public LigneCommande addLigne(LigneCommande ligne, Commande commande, Produit produit) {
		ligne.setCommande(commande);
		ligne.setProduit(produit);
		return lidao.addLigne(ligne);
	}


	@Override
	public LigneCommande searchLigneById(LigneCommande ligne) {
		return lidao.searchLigneById(ligne);
	}

	@Override
	public void delLigne(LigneCommande ligne) {
		lidao.delLigne(ligne);
		
	}

	@Override
	public void savePanier(Panier panier, Client client) {
		Commande commande=new Commande(new Date());
		commande.setClient(client);
		lidao.savePanier(panier, commande);
		
	}

	@Override
	public List<LigneCommande> listeLignes(Commande commande) {
		return lidao.listeLignes(commande);
	}
	
	
}
