package fr.adaming.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cl")
	private int id;
	private String nom;
	private String adresse;
	private String email;
	private String tel;
	private String mdp;
	//asso uml java
	@OneToMany(mappedBy="client",cascade=CascadeType.ALL)
	private List<Commande> listeCommande;
	
	//construc
	public Client() {
		super();
	}
	
	



	public Client(String nom, String adresse, String email, String tel, String mdp, List<Commande> listeCommande) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.mdp = mdp;
		this.listeCommande = listeCommande;
	}





	public Client(int id, String nom, String adresse, String email, String tel, String mdp,
			List<Commande> listeCommande) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.mdp = mdp;
		this.listeCommande = listeCommande;
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", email=" + email + ", tel=" + tel + "]";
	}

	public List<Commande> getListeCommande() {
		return listeCommande;
	}

	public void setListeCommande(List<Commande> listeCommande) {
		this.listeCommande = listeCommande;
	}





	public String getMdp() {
		return mdp;
	}





	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
	
	
	
	
	
}
