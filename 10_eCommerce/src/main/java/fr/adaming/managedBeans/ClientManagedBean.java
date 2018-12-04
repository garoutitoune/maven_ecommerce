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

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.CommandeTreenode;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;

@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	//asso uml java
	@ManagedProperty(value="#{clservice}")
	private IClientService clService;
	@ManagedProperty(value="#{coservice}")
	private ICommandeService coService;
	//setter pour injection
	public void setClService(IClientService clService) {
		this.clService = clService;
	}
	
	
	public void setCoService(ICommandeService coService) {
		this.coService = coService;
	}


	//attributs
	private HttpSession maSession;
	private Client cl;
	private boolean connecte=false;

	
	//construc
	public ClientManagedBean() {
		super();
	}
	
	//init
	@PostConstruct
	public void init() {
		// recuperer la session en cours
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);

		// recuperer le client de la session
		this.cl =(Client) maSession.getAttribute("clSession");
		if(this.cl==null) { //null dans le cas du login
			this.cl =new Client();
		}
	}

	public Client getCl() {
		return cl;
	}

	public void setCl(Client cl) {
		this.cl = cl;
	}
	
	public boolean isConnecte() {
		return connecte;
	}

	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}

	public String seConnecter() {
		
		this.cl=clService.isExist(this.cl);
		if(this.cl!=null) {
			maSession.setAttribute("clSession", this.cl);
			this.connecte=true;
			maSession.setAttribute("connexion", this.connecte);
			
			//charger la liste des commandes
			//recup le client de la session
			List<Commande> liste =coService.searchCommandeByClId(this.cl);
//			maSession.setAttribute("listeCommandes", liste);
			//creer le treenode correspondant
			maSession.setAttribute("listeCommandesTree", coService.liste2treenode(liste));
			
			return "accueilSite.xhtml";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le login ou mdp n'est pas valide"));
		}
		
		return "logincl.xhtml";
		
	}
	
	public String addClient() {
		
		Client clout=clService.addClient(this.cl);
		if(clout.getId()!=0) {
			return "accueilSite.xhtml";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué"));
			return "ajoutercl.xhtml";
		}
	}
	
	public String delClient() {
		
		try {
			clService.delClient(this.cl);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//			FacesContext.getCurrentInstance().getExternalContext().redirect("accueilSite.xhtml");
			FacesContext.getCurrentInstance().getExternalContext().redirect( "logincl.xhtml");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","Votre compte a été éradiqué de la base de données."));
			
			return "logincl.xhtml";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a échoué"));
			return "modifCl.xhtml";
		}
		
	}
	
	public String modifClient(){
		try {
			Client clin=(Client) maSession.getAttribute("clSession");
			this.cl=clService.modifClient(clin);
			maSession.setAttribute("clSession", this.cl);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","Vos informations ont été modifiées"));
			return "modifCl.xhtml";
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modif a échoué"));
			return "modifCl.xhtml";
		}
		
		
		
	}
	
	public String deconnexion() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("accueilSite.xhtml");
		return "accueilSite.xhtml";
	}
	
	public void modifMdp() {
		int verif=clService.modifMdp(cl);
		if(verif!=0) {
			maSession.setAttribute("clSession", this.cl);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","Votre mot de passe a été modifié"));
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Echec","La modification n'a pas pu être effectuée"));
		}
	}
	
	
	
}
