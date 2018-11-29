package fr.adaming.dao;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao{

	@Autowired
	private SessionFactory sf;
	
	//setter pour injection
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public Commande addCommande(Commande commande) {
		Session s=sf.getCurrentSession();
		s.save(commande);
		return commande;
	}

	@Override
	public void delCommande(Commande commande) {
		//supprimer les lignes de commande de la commande
		Session s=sf.getCurrentSession();
		//requete HQL
		String req="DELETE FROM LigneCommande AS li WHERE li.commande.id=:pid";
		Query q=s.createQuery(req);
		q.setParameter("pid", commande.getId());
		q.executeUpdate();
		//supprimer la commande
		s.delete(commande);
	}

	@Override
	public Commande searchCommandeById(Commande commande) {
		Session s=sf.getCurrentSession();
		return (Commande) s.get(Commande.class, commande.getId());
	}

	
}
