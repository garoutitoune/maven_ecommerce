package fr.adaming.dao;



import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;


public interface ICommandeDao {

	
	public Commande addCommande(Commande commande);
	public void delCommande(Commande commande);
	public Commande searchCommandeById(Commande commande);
	public List<Commande> searchCommandeByClId(Client client);
	public double prixCommande(Commande commande);
	
	
}
