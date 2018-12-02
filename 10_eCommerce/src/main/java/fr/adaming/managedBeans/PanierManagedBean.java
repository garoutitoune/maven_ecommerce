package fr.adaming.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.CommandeTreenode;
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
@RequestScoped
public class PanierManagedBean implements Serializable{

	//attributs
	private HttpSession maSession;
	private Panier panier;
	private Produit produit;
	private Client client;
	
	//asso uml java
	@ManagedProperty(value="#{paservice}")
	private IPanierService paservice;
	@ManagedProperty(value="#{liservice}")
	private ILigneService liservice;
	@ManagedProperty(value="#{coservice}")
	private ICommandeService coservice;
	
	@ManagedProperty(value="#{clservice}")
	private IClientService clservice;
	
	//setters pour injection
	public void setPaservice(IPanierService paservice) {
		this.paservice = paservice;
	}
	public void setLiservice(ILigneService liservice) {
		this.liservice = liservice;
	}
	public void setCoservice(ICommandeService coservice) {
		this.coservice = coservice;
	}
	public void setClservice(IClientService clservice) {
		this.clservice = clservice;
	}
	//construc
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
	
	//methodes
	public void addProd() throws IOException {
		int verif=paservice.addProd(this.panier ,this.produit);
		//verifier que le panier a été modifié
		if(verif==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Echec","Stocks insuffisants"));
		}else {
			//actualiser le panier de la session
			maSession.setAttribute("paSession", this.panier);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","Le produit a été ajouté au panier."));
		}
	}
	
	public void delProd() throws IOException {
		this.panier=paservice.delProd(this.panier ,this.produit);
		//actualiser le panier de la session
		maSession.setAttribute("paSession", this.panier);
//		FacesContext.getCurrentInstance().getExternalContext().redirect("voirProds1Categ.xhtml");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","Le produit a été retiré du panier."));
	}
	
	public void delProd2() {
		this.panier=paservice.delProd2(this.panier ,this.produit);
		//actualiser le panier de la session
		maSession.setAttribute("paSession", this.panier);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","Le produit a été supprimé du panier."));
	}
	public void savePanier()
	{
		try {
		
			paservice.savePanier(panier, (Client) maSession.getAttribute("clSession"));
			//vider le panier
			maSession.setAttribute("paSession", new Panier());
			this.panier=new Panier();
			
			//actualiser la liste de commandes
			List<Commande> liste =coservice.searchCommandeByClId((Client)maSession.getAttribute("clSession"));
			//creer le treenode correspondant et l'enregistrer
			maSession.setAttribute("listeCommandesTree", coservice.liste2treenode(liste));
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","La commande a été enregistrée."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'enregistrement a échoué"));
		}
		
	}
	
}
