package fr.adaming.service;



import java.util.List;

import org.primefaces.model.TreeNode;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;


public interface ICommandeService {

	public Commande addCommande(Commande commande, Client client);
	public void delCommande(Commande commande);
	public Commande searchCommandeById(Commande commande);
	public List<Commande> searchCommandeByClId(Client client);
	public double prixCommande(Commande commande);
	public TreeNode liste2treenode(List<Commande> listeCommandes);
}
