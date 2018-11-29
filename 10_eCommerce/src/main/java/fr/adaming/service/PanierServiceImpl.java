package fr.adaming.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IPanierDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

@Service(value="paservice")
@Transactional
public class PanierServiceImpl implements IPanierService{

	//asso uml java
	@Autowired
	IPanierDao padao;
	//setter pour injection

	public void setPadao(IPanierDao padao) {
		this.padao = padao;
	}
	
	@Override
	public Panier addProd(Panier panier, Produit produit) {
		return padao.addProd(panier, produit);
	}


	@Override
	public Panier delProd(Panier panier, Produit produit) {
		return padao.delProd(panier, produit);
	}

	@Override
	public Panier delProd2(Panier panier, Produit produit) {
		return padao.delProd2(panier, produit);
	}

	@Override
	public void savePanier(Panier panier, Client client) {
		Commande commande=new Commande(new Date());
		commande.setClient(client);
		padao.savePanier(panier, commande);
		
	}
	
	
}
