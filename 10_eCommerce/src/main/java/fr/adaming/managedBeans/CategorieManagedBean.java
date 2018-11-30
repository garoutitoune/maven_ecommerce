package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "caMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// transformation de l'association uml en java

	@ManagedProperty(value="#{caService}")
	private ICategorieService categorieService;
	
	@ManagedProperty(value="#{proService}")
	private IProduitService produitService;
	

	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	// declaration des attributs
	private Categorie categorie;
	private Gerant gerant;
	private UploadedFile file;

	HttpSession maSession;

	// constructeur vide
	public CategorieManagedBean() {
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

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String ajouterCategorie() {

		this.categorie.setPhoto(file.getContents());

		// j'ajoute la categorie et le gerant avec
		Categorie caOut = categorieService.addCategorie(categorie);
		if (caOut.getId() != 0) {
			// si tout c'est bien passé recupère la liste et je l'injecte
			List<Categorie> liste = categorieService.getAllCategorie();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeCaSession", liste);
			return "accueil";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué"));

			return "ajouter";
		}
	}

	public String modifierLienCategorie() {
		return "modifierCat";
	}

	public String modifierCategorie() {
		//injection de la photo
		this.categorie.setPhoto(file.getContents());
		
		int verif = categorieService.modifierCategorie(categorie);
		if (verif != 0) {
			// si tout c'est bien passé recupère la liste et je l'injecte
			List<Categorie> liste = categorieService.getAllCategorie();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeCaSession", liste);
			return "accueil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué"));

			return "modifierCat";
		}

	}

	public String supprimerCategorie() {
		int verif = categorieService.supprimerCategorie(categorie);
		if (verif != 0) {
			// si tout c'est bien passé recupère la liste et je l'injecte
			List<Categorie> liste = categorieService.getAllCategorie();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeCaSession", liste);

			// si tout c'est bien passé recupère la liste et je l'injecte
			List<Produit> liste2 = produitService.getAllProduit();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeProSession", liste2);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("success de la suppression"));
			return "accueil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a échoué"));

			return "accueil";
		}

	}
	public String getAllCategorie() {
		List<Categorie> listeCa=categorieService.getAllCategorie();
		maSession.setAttribute("listeCaSession", listeCa);
		
		return "listeCa";
	}

}
