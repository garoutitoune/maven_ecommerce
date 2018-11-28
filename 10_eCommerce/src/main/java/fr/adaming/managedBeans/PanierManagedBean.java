package fr.adaming.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneService;
import fr.adaming.service.IPanierService;
import fr.adaming.service.IProduitService;
import fr.adaming.service.LigneServiceImpl;
import fr.adaming.service.ProduitServiceImpl;

@ManagedBean(name="paMB")
@SessionScoped
public class PanierManagedBean implements Serializable{

	//attributs
	private HttpSession maSession;
	private Panier panier;
	private Produit produit;
	private Client client;
	
	//asso uml java

	private IPanierService paservice;

	private ILigneService liservice;

	private ICommandeService coservice;
	
	private IProduitService proservice;

	private IClientService clservice;

	public PanierManagedBean() {
		super();
	}
	//init
	@PostConstruct
	public void init() {
		// créer/récupérer une session pour le panier
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(true);

//		 recuperer le panier de la session
		this.panier =(Panier) maSession.getAttribute("paSession");
		if(this.panier==null) { //au cas ou le panier n'etait pas cree
			this.panier =new Panier(); //ajouter les panier à la session
			maSession.setAttribute("paSession", this.panier);
		}
	}	

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public void addProd() throws IOException {
		this.panier=paservice.addProd(this.panier ,this.produit);
		//actualiser le panier de la session
		maSession.setAttribute("paSession", this.panier);
//		FacesContext.getCurrentInstance().getExternalContext().redirect("voirProds1Categ.xhtml");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","Le produit a été ajouté au panier."));
	}
	
	public void delProd() throws IOException {
		this.panier=paservice.delProd(this.panier ,this.produit);
		//actualiser le panier de la session
		maSession.setAttribute("paSession", this.panier);
//		FacesContext.getCurrentInstance().getExternalContext().redirect("voirProds1Categ.xhtml");
	}
	
	public void delProd2() {
		this.panier=paservice.delProd2(this.panier ,this.produit);
		//actualiser le panier de la session
		maSession.setAttribute("paSession", this.panier);
	}
	public void savePanier()
	{
		try {
			System.out.println("je save le panieer!");
			
			System.out.println("id produit:"+panier.getListeLignes().get(0).getProduit().getId());
			Client client=new Client();
			clservice.addClient(client);
			Commande commande=new Commande(new Date());
			coservice.addCommande(commande, client);
			System.out.println("apres enregistrement commande");
			
			for (LigneCommande li : this.panier.getListeLignes()) {
				liservice.addLigne(li, commande, li.getProduit());
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","La commande a été enregistrée."));
		} catch (Exception e) {
			System.out.println("error message:"+e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'enregistrement a échoué"));
		}
		
	}
	
}
