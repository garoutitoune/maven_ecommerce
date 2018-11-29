package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IGerantService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "gMB")
@RequestScoped
public class GerantManagedBean implements Serializable {

	// transformation de l'association UML en JAVA

	@ManagedProperty(value="#{gService}")
	private IGerantService gerantService;


	@ManagedProperty(value="#{caService}")
	private ICategorieService categorieService;

	@ManagedProperty(value="#{proService}")
	private IProduitService produitService;
	
	

	public void setGerantService(IGerantService gerantService) {
		this.gerantService = gerantService;
	}

	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	// declaration des attributs
	private Gerant gerant;
	private List<Categorie> listeCategorie;
	private List<Produit> listeProduit;
	private String test1;
	private String sujet;
	private String mail;

	// constructeur vide
	public GerantManagedBean() {
		this.gerant = new Gerant();

	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}

	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	 
	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	// les autres methodes
	public String seConnecter() {

		try {
			// aller chercher le formateur dans la bd
			Gerant gOut = gerantService.isExist(gerant);

			// recuperer les categories
			this.listeCategorie = categorieService.getAllCategorie();

			// recuperer les produits
			this.listeProduit = produitService.getAllProduit();

			// ajouter le formateur dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("gSession", gOut);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCaSession",
					this.listeCategorie);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProSession",
					this.listeProduit);

			return "accueil";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le login ou mdp n'est pas valide"));

		}

		return "login";
	}

	public String seDeconnecter() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "login";
	}

	public String accessSite() {
		// recuperer les categories
		this.listeCategorie = categorieService.getAllCategorie();

		// recuperer les produits
		this.listeProduit = produitService.getAllProduit();

		// ajouter la liste de categorie et de produit à la session
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCaSession",
				this.listeCategorie);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProSession",
				this.listeProduit);
		return "accueilSite";
	}
	
	

}
