package fr.adaming.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Gerant;

@Repository
public class GerantDaoImpl implements IGerantDao {
	
	@Autowired
	private SessionFactory sf;
	
	// setter pour l'injection de dependance
		public void setSf(SessionFactory sf) {
			this.sf = sf;
		}


	@Override
	public Gerant isEsist(Gerant g) {
		Session s=sf.getCurrentSession();
		
		//requete HQL
		String req="FROM Gerant g WHERE g.mail=:pMail AND g.mdp=:pMdp";
		
		Query query=s.createQuery(req);
		
		//passage avec params
		query.setParameter("pMail", g.getMail());
		query.setParameter("pMdp", g.getMdp());
		
		return (Gerant) query.uniqueResult();
	}

}
