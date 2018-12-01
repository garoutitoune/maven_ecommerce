package fr.adaming.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "categories")
public class Categorie {

	// declaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ca")
	private int id;
	private String nom;
	@Lob
	private byte[] photo;
	private String description;
	
	
	@Transient
	private String image;
	
	//transformation de l'association
	@OneToMany(mappedBy="categorie", cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<Produit> produit;
	
	

	// constructeur vide
	public Categorie() {
		super();
	}

	// constructeur sans id
	public Categorie(String nom, byte[] photo, String description) {
		super();
		this.nom = nom;
		this.photo = photo;
		this.description = description;
	}

	// constructeur avec id
	public Categorie(int id, String nom, byte[] photo, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.description = description;
	}

	// getters et setters
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<Produit> getProduit() {
		return produit;
	}

	public void setProduit(List<Produit> produit) {
		this.produit = produit;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + ", description=" + description + "]";
	}
	
	
	
	
	

}
