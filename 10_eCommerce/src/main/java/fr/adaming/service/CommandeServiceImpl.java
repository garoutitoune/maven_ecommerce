package fr.adaming.service;


import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.CommandeTreenode;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Service(value="coservice")
@Transactional
public class CommandeServiceImpl implements ICommandeService{
	
	//asso uml java
	@Autowired
	ICommandeDao codao;
	
	
	//setter pour injection 
	public void setCodao(ICommandeDao codao) {
		this.codao = codao;
	}
	




	@Override
	public Commande addCommande(Commande commande, Client client) {
		commande.setClient(client);
		return codao.addCommande(commande);
	}

	@Override
	public void delCommande(Commande commande) {
		
		codao.delCommande(commande);
	}

	@Override
	public Commande searchCommandeById(Commande commande) {
		return codao.searchCommandeById(commande);
	}





	@Override
	public List<Commande> searchCommandeByClId(Client client) {
		return codao.searchCommandeByClId(client);
	}





	@Override
	public double prixCommande(Commande commande) {
		return codao.prixCommande(commande);
	}





	@Override
	public TreeNode liste2treenode(List<Commande> listeCommandes) {
		TreeNode root = new DefaultTreeNode("aller les enfants!", null);
		
		for (Commande commande : listeCommandes) {
			String s1="Commande N."+commande.getId()+"/ Prix: "+this.prixCommande(commande);
			Object s2= commande.getDate();
			TreeNode co=new DefaultTreeNode(new CommandeTreenode(s1,s2) ,root);
			
			for (LigneCommande li: commande.getListeLignes()) {
				Produit p=li.getProduit();
				s1="Produit: "+p.getId();
				s2="Désignation: "+p.getDesignation()//+" Description: "+p.getDescription()
				+"/ Quantité: "+li.getQt()+"/ Prix: "+li.getProduit().getPrix();
				TreeNode pr=new DefaultTreeNode(new CommandeTreenode(s1, s2),co);
			}
		}
		
		return root;
	}

	
}
