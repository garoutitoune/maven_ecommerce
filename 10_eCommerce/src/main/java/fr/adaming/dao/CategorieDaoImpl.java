package fr.adaming.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;

@Repository
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		Session s = sf.getCurrentSession();

//		Criteria cr = s.createCriteria(Categorie.class);
//		List<Categorie> liste=cr.list();
		
		//req hql
		String req="FROM Categorie";
		Query q=s.createQuery(req);
		List<Categorie> liste=q.list();
		
		for(Categorie ca:liste) {
			ca.setImage("data:image/png);base64," + Base64.encodeBase64String(ca.getPhoto()));
		}
		return liste;
	}

	@Override
	public Categorie addCategorie(Categorie ca) {
		System.out.println("je suis dans catDao ajout");
		Session s = sf.getCurrentSession();

		s.save(ca);
		return ca;
	}

	@Override
	public int modifierCategorie(Categorie ca) {
		Session s = sf.getCurrentSession();

		String req = "UPDATE Categorie ca SET ca.description=:pDescrip, ca.nom=:pNom WHERE ca.id=:pIdCa";

		Query query = s.createQuery(req);

		// passage avec params
		query.setParameter("pDescrip", ca.getDescription());
		query.setParameter("pNom", ca.getNom());
		query.setParameter("pIdCa", ca.getId());
		System.out.println("je ne modifie pas l'image");

		return query.executeUpdate();
	}

	@Override
	public int supprimerCategorie(Categorie ca) {
		Session s=sf.getCurrentSession();
		
		String req="DELETE FROM Categorie ca WHERE ca.id=:pIdCa";
		Query query=s.createQuery(req);
		
		query.setParameter("pIdCa", ca.getId());
		
		return query.executeUpdate();
	}

	@Override
	public int modifierCategoriePhoto(Categorie ca) {
		Session s = sf.getCurrentSession();

		String req = "UPDATE Categorie ca SET ca.description=:pDescrip, ca.nom=:pNom, ca.photo=:pPhoto WHERE ca.id=:pIdCa";

		Query query = s.createQuery(req);

		// passage avec params
		query.setParameter("pDescrip", ca.getDescription());
		query.setParameter("pNom", ca.getNom());
		query.setParameter("pPhoto", ca.getPhoto());
		query.setParameter("pIdCa", ca.getId());
		
		return query.executeUpdate();
	}

}
