package fr.adaming.dao;



import fr.adaming.model.Commande;


public interface ICommandeDao {

	
	public Commande addCommande(Commande commande);
	public void delCommande(Commande commande);
	public Commande searchCommandeById(Commande commande);
	
	
	
}
