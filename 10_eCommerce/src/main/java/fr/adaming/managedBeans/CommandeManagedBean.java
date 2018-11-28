package fr.adaming.managedBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;

@ManagedBean(name="coMB")
public class CommandeManagedBean {
	
	//attributs
	private Commande commande;
	private HttpSession maSession;
	private Client client;
	
	//asso uml java
	@EJB
	private ICommandeService coservice;

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
	
	

}
