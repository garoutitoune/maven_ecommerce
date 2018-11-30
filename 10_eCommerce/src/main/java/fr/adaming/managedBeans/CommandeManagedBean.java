package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;

@ManagedBean(name="coMB")
public class CommandeManagedBean {
	
	//transfo uml java
	@ManagedProperty(value="#{coservice}")
	private ICommandeService coservice;
	//setter pour injection
	public void setCoservice(ICommandeService coservice) {
		this.coservice = coservice;
	}
	
	//attributs
	private Commande commande;
	private List<Commande> listeCommandesCl;
	private HttpSession maSession;
	private Client client;
	
	public CommandeManagedBean() {
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
		this.client=new Client();
	}		
	//getter setter
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	
	
	public List<Commande> getListeCommandesCl() {
		return listeCommandesCl;
	}
	public void setListeCommandesCl(List<Commande> listeCommandesCl) {
		this.listeCommandesCl = listeCommandesCl;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	//methodes (ajouter les try catch)
	public String addCommande() {
		coservice.addCommande(this.commande, this.client);
		return null;
	}
	
	public void delCommande() {
		coservice.delCommande(this.commande);
	}
	
	public void listeCommandesCl() {
		//recup le client de la session
		this.client=(Client) maSession.getAttribute("clSession");
		this.listeCommandesCl=coservice.searchCommandeByClId(this.client);
	}
	

}
