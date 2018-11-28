package fr.adaming.service;



import fr.adaming.model.Client;
import fr.adaming.model.Commande;


public interface ICommandeService {

	public Commande addCommande(Commande commande, Client client);
	public void delCommande(Commande commande);
	public Commande searchCommandeById(Commande commande);
}
