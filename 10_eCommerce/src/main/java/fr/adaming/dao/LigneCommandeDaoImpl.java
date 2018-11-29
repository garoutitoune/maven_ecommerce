package fr.adaming.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao{

	@Autowired
	private SessionFactory sf;
	
	//setter pour injection
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	
	@Override
	public LigneCommande addLigne(LigneCommande ligne) {
		Session s=sf.getCurrentSession();
		s.save(ligne);
		return ligne;
	}

	

	@Override
	public LigneCommande searchLigneById(LigneCommande ligne) {
		Session s=sf.getCurrentSession();
		return (LigneCommande) s.get(LigneCommande.class, ligne.getId());
	}

	@Override
	public void delLigne(LigneCommande ligne) {
		Session s=sf.getCurrentSession();
		s.delete(ligne);
		
	}

	@Override
	public void savePanier(Panier panier, Commande commande) {
		Session s=sf.getCurrentSession();
		//lier les objets en java, puis persister la classe maitre
		for (LigneCommande li : panier.getListeLignes()) {
			li.setCommande(commande);
			s.save(li);
		}
		//persister l'esclave
		s.save(commande);
	}


	@Override
	public List<LigneCommande> listeLignes(Commande commande) {
		Session s=sf.getCurrentSession();
		//requete HQL
		String req="FROM LigneCommande AS li WHERE li.commande.id=:pid";
		Query q=s.createQuery(req);
		q.setParameter("pid", commande.getId());
		return q.list();
	}

	

}
