package fr.adaming.dao;




import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneService;
import fr.adaming.service.IProduitService;

@Repository
public class PanierDaoImpl implements IPanierDao{
	
	//asso uml java
	@Autowired
	ILigneService liservice;
	@Autowired
	ICommandeService coservice;
	@Autowired
	IProduitService proservice;
	@Autowired
	IProduitDao prodao;
	
	//setters pour injection
	public void setLiservice(ILigneService liservice) {
		this.liservice = liservice;
	}

	public void setCoservice(ICommandeService coservice) {
		this.coservice = coservice;
	}
	
	public void setProservice(IProduitService proservice) {
		this.proservice = proservice;
	}

	public void setProdao(IProduitDao prodao) {
		this.prodao = prodao;
	}

	@Override
	public int addProd(Panier panier, Produit produit) {
		//recuperer le nb de produit en stock
		Produit pstock=proservice.getProduitById(produit);
		//verifier si le produit est déjà dans le panier
//		int verif=0; 
		List<LigneCommande> liste=panier.getListeLignes();
		LigneCommande li=new LigneCommande();
		for (int i = 0; i < liste.size(); i++) {
			li=liste.get(i);
			if(li.getProduit().getDesignation()==produit.getDesignation()) {
				//si le produit est trouvé, verif=1, verifier que le stock est suffisant
				if((li.getQt()+1)<=pstock.getQuantite()) {
					li.setQt(li.getQt()+1); // ajouter 1 à la ligne de commande
					li.setPrix(produit.getPrix()*li.getQt()); //actualiser le prix de la ligne de commande
					return 1;
				}
//				verif=1;
				//produit trouvé mais stock insuffisant
				return 0;
			}
		}
		//(verif==0)&&
		if((pstock.getQuantite()>=1)) { //si le produit n'a pas été trouvé dans le panier, et que la qt en stock>=1
			li=new LigneCommande(1, produit.getPrix()); //le rajouter
			li.setProduit(produit);
			//ajouter la nouvelle ligne de commande au panier
			liste.add(li);
			return 1;
		}
		return 0;
	}

	

	@Override
	public Panier delProd(Panier panier, Produit produit) {
		//on suppose que le produit est dans le panier et qt>1
				List<LigneCommande> liste=panier.getListeLignes();
				
				LigneCommande li=new LigneCommande();
				
				for (int i = 0; i < liste.size(); i++) {
					li=liste.get(i);
					if(li.getProduit().getDesignation()==produit.getDesignation()) {
						li.setQt(li.getQt()-1); //si le produit est trouvé dans le panier, soustraire
						//si la qt est =0, retirer du panier
						if(li.getQt()==0) {
							liste.remove(li);
						}else {
							li.setPrix(produit.getPrix()*li.getQt()); //actualiser le prix de la ligne de commande
						}
						
					}
				}
				return panier;
	}

	@Override
	public Panier delProd2(Panier panier, Produit produit) {
		//on suppose que le produit est dans le panier et qt>1
				List<LigneCommande> liste=panier.getListeLignes();
				
				LigneCommande li=new LigneCommande();
				
				for (int i = 0; i < liste.size(); i++) {
					li=liste.get(i);
					if(li.getProduit().getDesignation()==produit.getDesignation()) {
						liste.remove(i);//si le produit est trouvé dans le panier, supprimer sa ligne de commande
					}
				}
				return panier;	
	}

	@Override
	public Commande savePanier(Panier panier, Client client) {
		
		
		Commande commande=new Commande(new Date());
		coservice.addCommande(commande, client);
		//ajouter les lignes de commande correspondant à cette commande
		for (LigneCommande li : panier.getListeLignes()) {
			Produit prod=li.getProduit();
			liservice.addLigne(li, commande, li.getProduit());
			//modifier les stocks
			prod.setQuantite(prod.getQuantite()-li.getQt());
			prodao.modifierProduitSansPhoto(prod);
//			proservice.modifierProduit(prod, prod.getCategorie());
		}
		
		return commande;
		
	}


	
	
	
	
	
	
}
