package fr.adaming.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "proMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	// transformation de l'association uml en JAVA

	@ManagedProperty(value = "#{proService}")
	private IProduitService produitService;

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	// declaration des attributs
	private Produit produit;
	private Categorie categorie;
	private Gerant gerant;
	private UploadedFile file;
	private List<Produit> listeProds;
	private boolean indice;

	HttpSession maSession;

	public ProduitManagedBean() {
		this.produit = new Produit();
		this.categorie = new Categorie();
	}

	// init
	@PostConstruct
	public void inti() {
		// recuperer la session en cours
		maSession = (HttpSession) FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext()
				.getSession(false);
		maSession.getAttribute("gConnexion");

		// recuperer le formateur de la session
		this.gerant = (Gerant) maSession.getAttribute("gSession");

	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Produit> getListeProds() {
		return listeProds;
	}

	public void setListeProds(List<Produit> listeProds) {
		this.listeProds = listeProds;
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}
	// m�thodes

	public void listeprodsmeth(int i) throws IOException {
//		FacesContext.getCurrentInstance().getExternalContext().redirect("voirProds1Categ.xhtml");
		// recup la liste des produits pour 1 cat�g
		this.categorie.setId(i);
		this.listeProds = produitService.getAllProduitByCat(new Produit(), this.categorie);
		maSession.setAttribute("listeProds1Categ", this.listeProds);
//		return "voirProds1Categ.xhtml";
		FacesContext.getCurrentInstance().getExternalContext().redirect("voirProds1Categ.xhtml");
	}

	public String produitByCat() {
		// recuperer la liste de produit selon la categorie
		List<Produit> listePro = produitService.getAllProduitByCat(produit, categorie);

		indice = true;

		maSession.setAttribute("listeProCatSession", listePro);
		return "accueil";
	}

	public String getProduitByCat() {
		// recuperer la liste de produit selon la categorie
		List<Produit> listePro = produitService.getAllProduitByCat(produit, categorie);
		
		maSession.setAttribute("listeProCatSession", listePro);
		return "listeProCat";
		
	}

	public String ajouterProduit() {
		this.produit.setPhoto(file.getContents());
		Produit proOut = produitService.addProduit(produit, categorie);

		if (proOut.getId() != 0) {
			// si tout c'est bien pass� recup�re la liste et je l'injecte
			List<Produit> liste = produitService.getAllProduit();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeProSession", liste);
			return "accueil";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�"));

			return "ajouterPro";
		}
	}

	public String supprimerProduit() {
		try {
		int verif = produitService.deleteProduit(produit);

		if (verif != 0) {
			// si tout c'est bien pass� recup�re la liste et je l'injecte
			List<Produit> liste = produitService.getAllProduit();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeProSession", liste);
			return "accueil";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�"));

			return "supprimerPro";
		}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit est enregistr� dans une commande!"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veuillez d'abord supprimer la commande"));

			return "supprimerPro";
		}
	}

	public String modifierProduit() {
		this.produit.setPhoto(file.getContents());
		int verif = produitService.modifierProduit(produit, categorie);
		if (verif != 0) {
			// si tout c'est bien pass� recup�re la liste et je l'injecte
			List<Produit> liste = produitService.getAllProduit();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeProSession", liste);
			return "accueil";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modification a �chou�"));

			return "modifierPro";
		}
	}

	public String modifierLienProduit() {
		return "modifierPro";
	}

	public String rechercherByDesignation() {
		List<Produit> listeOut = produitService.getProduitByDes(produit, categorie);
		if (listeOut != null) {
			maSession.setAttribute("listeProRechSession", listeOut);

			return "recherchePro";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a �chou�"));
			return "recherchePro";
		}
	}

	public String rechercher() {
		Produit pro = produitService.getProduitById(produit);
		maSession.setAttribute("proSession1", pro);

		return "recherche";
	}
}
