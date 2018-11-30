package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Gerant;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IGerantService;
import fr.adaming.service.IProduitService;
import fr.adaming.util.Mail;

@ManagedBean(name="mailMB")
@RequestScoped
public class MailManagedBean implements Serializable{
	
	// transformation de l'association UML en JAVA
		@ManagedProperty(value = "#{caService}")
		private ICategorieService categorieService;

		@ManagedProperty(value = "#{proService}")
		private IProduitService produitService;

		public void setCategorieService(ICategorieService categorieService) {
			this.categorieService = categorieService;
		}

		public void setProduitService(IProduitService produitService) {
			this.produitService = produitService;
		}
		
		// declaration des attributs
		private String test1;
		private String sujet;
		private String mail;
		private Gerant gerant;
		HttpSession maSession;

		public MailManagedBean() {
			
		}
		
		@PostConstruct
		public void inti() {
			// recuperer la session en cours
			maSession = (HttpSession) FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext()
					.getSession(false);
			maSession.getAttribute("gConnexion");

			// recuperer le formateur de la session
			this.gerant = (Gerant) maSession.getAttribute("gSession");
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

		public String contactGerant() {
			String toMail="proxibanque17@gmail.com";
			
			String text="la personne "+this.mail +" a essayée de vous joindre pour ce message : " +this.test1;
			
			Mail.sendMail(toMail, sujet, text);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le mail a bien été envoyé"));
			
			return "contactGerant";
			
			
		}

}
