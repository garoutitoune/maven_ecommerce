package fr.adaming.managedBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneService;

@ManagedBean(name="liMB")
public class LigneManagedBean {

	//attributs
	private LigneCommande ligne;
	private Commande commande;
	private Produit produit;
	private HttpSession maSession;
	
	//asso uml java
	@EJB
	private ILigneService liservice;
	
	//construc
	public LigneManagedBean() {
		super();
	}
	
	//init
		@PostConstruct
		public void init() {
			// recuperer la session en cours
			maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);

			// initialiser la commande
			this.commande =new Commande();
			this.ligne=new LigneCommande();
			this.produit=new Produit();
		}		
	
	
	
	//getter setter
	public LigneCommande getLigne() {
		return ligne;
	}
	public void setLigne(LigneCommande ligne) {
		this.ligne = ligne;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	//methodes (ajouter les try catch)
	public void addLigne() {
		
		liservice.addLigne(this.ligne,this.commande, this.produit);
	}
	public void delLigne() {
		liservice.delLigne(ligne);
	}
	
	
	
	
}
