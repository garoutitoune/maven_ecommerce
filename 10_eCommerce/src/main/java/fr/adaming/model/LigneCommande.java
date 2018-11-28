package fr.adaming.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ligneCommandes")
public class LigneCommande {

	//attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="id_li")
	private int id;
	private int qt;
	private double prix;
	//assos uml java
	@ManyToOne
	@JoinColumn(name="p_id", referencedColumnName="id_p")
	private Produit produit;
	@ManyToOne
	@JoinColumn(name="co_id",referencedColumnName="id_co")
	private Commande commande;
	
	//contrucs
	public LigneCommande(int id, int qt, double prix) {
		super();
		this.id = id;
		this.qt = qt;
		this.prix = prix;
	}
	public LigneCommande(int qt, double prix) {
		super();
		this.qt = qt;
		this.prix = prix;
	}
	public LigneCommande() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQt() {
		return qt;
	}
	public void setQt(int qt) {
		this.qt = qt;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "LigneCommande [id=" + id + ", qt=" + qt + ", prix=" + prix + "]";
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	
	
	
	
}
