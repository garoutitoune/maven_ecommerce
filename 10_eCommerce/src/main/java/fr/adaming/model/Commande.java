package fr.adaming.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="commandes") 
public class Commande {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id_co")
	private int id;
	private Date date;
	
	//asso uml java
	@OneToMany(mappedBy="commande", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<LigneCommande> listeLignes;
	@ManyToOne
	@JoinColumn(name="cl_id", referencedColumnName="id_cl")
	private Client client;
	
	//constructeurs
	
	
	public Commande(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}
	public Commande(Date date) {
		super();
		this.date = date;
	}
	public Commande() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Commande [id=" + id + ", date=" + date + "]";
	}
	public List<LigneCommande> getListeLignes() {
		return listeLignes;
	}
	public void setListeLignes(List<LigneCommande> listeLignes) {
		this.listeLignes = listeLignes;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
}
