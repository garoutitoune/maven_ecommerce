package fr.adaming.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

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

	
}
