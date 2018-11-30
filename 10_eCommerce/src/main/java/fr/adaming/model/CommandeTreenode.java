package fr.adaming.model;

public class CommandeTreenode {
	
	private String nom;
	private Object Description;
	
	public CommandeTreenode(String nom, Object description) {
		super();
		this.nom = nom;
		Description = description;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Object getDescription() {
		return Description;
	}
	public void setDescription(Object description) {
		Description = description;
	}
	
	
	
	
	
	

}
