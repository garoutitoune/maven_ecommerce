package fr.adaming.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;


public class LigneCommandeDaoImpl implements ILigneCommandeDao{

	@PersistenceContext(unitName = "tp_ecommerce")
	private EntityManager em;
	
	@Override
	public LigneCommande addLigne(LigneCommande ligne) {
		
		
		em.persist(ligne);
		return ligne;
	}

	@Override
	public LigneCommande searchLigneById(LigneCommande ligne) {
		return em.find(LigneCommande.class, ligne.getId());
	}

	@Override
	public void delLigne(LigneCommande ligne) {
		em.remove(this.searchLigneById(ligne));
	}

	@Override
	public void savePanier(Panier panier, Commande commande) {
		//lier les objets en java
		for (LigneCommande li : panier.getListeLignes()) {
			li.setCommande(commande);
		}
		//persister la classe maitre
		for (LigneCommande li : panier.getListeLignes()) {
			em.persist(li);
		}
		//persister l'esclave
		em.persist(commande);		
		
	}

}
