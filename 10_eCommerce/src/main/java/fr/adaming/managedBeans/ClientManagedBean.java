package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	//asso uml java

	private IClientService clService;
	
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
		
		
		try {
			this.cl=clService.isExist(this.cl);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clSession", this.cl);
			return "monCommerce.xhtml";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le login ou mdp n'est pas valide"));
		}
		
		return "logincl.xhtml";
		
	}
	
	public String addClient() {
		
		Client clout=clService.addClient(this.cl);
		if(clout.getId()!=0) {
			return "monCommerce.xhtml";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué"));
			return "ajoutercl.xhtml";
		}
	}
	
	public String delClient() {
		
		try {
			clService.delClient(this.cl);
			return "monCommerce.xhtml";
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a échoué"));
			return "delCl.xhtml";
		}
		
	}
	
	public String modifClient(){
		try {
			Client clin=(Client) maSession.getAttribute("clSession");
			System.out.println(clin);
			this.cl=clService.modifClient(clin);
			System.out.println(this.cl);
			return "monCommerce.xhtml";
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modif a échoué"));
			return "modifCl.xhtml";
		}
		
		
		
	}
	
	
	
	
	
	
	
}
