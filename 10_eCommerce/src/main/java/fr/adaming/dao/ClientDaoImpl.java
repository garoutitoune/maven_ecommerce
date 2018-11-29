package fr.adaming.dao;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;

@Repository
public class ClientDaoImpl implements IClientDao{

	//asso uml java
	@Autowired
	private SessionFactory sf;
	//setter pour injection

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public Client isExist(Client cl) {
		Session s=sf.getCurrentSession();
		//req hql
		String req="FROM Client cl WHERE cl.email=:pmail AND cl.mdp=:pmdp";
		Query q=s.createQuery(req);
		q.setParameter("pmail", cl.getEmail());
		q.setParameter("pmdp", cl.getMdp());
		return (Client) q.uniqueResult();
	}


	@Override
	public Client addClient(Client cl) {
		Session s=sf.getCurrentSession();
		s.save(cl);
		return cl;
	}

	@Override
	public Client modifClient(Client cl) {
		Session s=sf.getCurrentSession();
		s.saveOrUpdate(cl);
		return cl;
	}

	@Override
	public void delClient(Client cl) {
		
		Session s=sf.getCurrentSession();
		s.delete(cl);
		
	}

	@Override
	public Client searchById(Client cl) {
		Session s=sf.getCurrentSession();
		return (Client) s.get(Client.class, cl.getId());
	}

	
	
	
	

}
