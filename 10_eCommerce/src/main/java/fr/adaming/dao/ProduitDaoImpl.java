package fr.adaming.dao;

import java.util.List;



import org.apache.commons.codec.binary.Base64;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Produit> getAllProduit() {
		Session s = sf.getCurrentSession();
		
		String req="FROM Produit pr";

		Query query=s.createQuery(req);
		
		List<Produit> liste = query.list();

		for (Produit pr : liste) {
			System.out.println("je suis dans le recuperateur d'image");
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}
		return liste;
	}

	@Override
	public Produit addProduit(Produit pro) {
		Session s = sf.getCurrentSession();

		s.save(pro);

		return pro;
	}

	@Override
	public int deleteProduit(Produit pro) {
		Session s = sf.getCurrentSession();

		String req = "DELETE FROM Produit p WHERE p.id=:pIdPr";

		Query query = s.createQuery(req);

		query.setParameter("pIdPr", pro.getId());

		return query.executeUpdate();
	}

	@Override
	public int modifierProduit(Produit pro) {
		Session s = sf.getCurrentSession();

		String req = "UPDATE Produit p SET p.description=:pDescrip, p.designation=:pDesign, p.photo=:pPhoto, p.prix=:pPrix, p.quantite=:pQtite WHERE p.id=:pIdPr AND p.categorie.id=:pIdCa";

		Query query = s.createQuery(req);

		// passage avec params
		query.setParameter("pDescrip", pro.getDescription());
		query.setParameter("pDesign", pro.getDesignation());
		query.setParameter("pPhoto", pro.getPhoto());
		query.setParameter("pPrix", pro.getPrix());
		query.setParameter("pQtite", pro.getQuantite());
		query.setParameter("pIdPr", pro.getId());
		query.setParameter("pIdCa", pro.getCategorie().getId());

		return query.executeUpdate();
	}

	@Override
	public List<Produit> getAllProduitCat(Produit pro) {
		Session s = sf.getCurrentSession();

		String req="FROM Produit pr WHERE pr.categorie.id=:pIdCa";

		Query query=s.createQuery(req);
		
		query.setParameter("pIdCa", pro.getCategorie().getId());

		List<Produit> liste = query.list();

		for (Produit pr : liste) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}
		return liste;
	}

	@Override
	public Produit getProduitById(Produit pro) {
		Session s = sf.getCurrentSession();

		String req="FROM Produit pr WHERE pr.categorie.id=:pIdPr";

		Query query=s.createQuery(req);
		
		query.setParameter("pIdPr", pro.getId());

		Produit proOut = (Produit) query.uniqueResult();
		proOut.setImage("data:image/png);base64," + Base64.encodeBase64String(proOut.getPhoto()));

		return proOut;
	}

	@Override
	public List<Produit> getProduitByDescr(Produit pro) {
		Session s = sf.getCurrentSession();

		// ecrire la requete en SQL
		String req = "FROM Produit p WHERE p.categorie.id=:pIdCa AND designation LIKE :pDesign";

		Query query = s.createQuery(req);

		query.setParameter("pIdCa", pro.getCategorie().getId());
		query.setParameter("pDesign", "%"+pro.getDesignation()+"%");

		List<Produit> liste = query.list();

		for (Produit pr1 : liste) {
			pr1.setImage("data:image/png);base64," + Base64.encodeBase64String(pr1.getPhoto()));
		}
		return liste;
	}

	@Override
	public int modifierProduitSansPhoto(Produit pro) {
		Session s = sf.getCurrentSession();

		String req = "UPDATE Produit p SET p.description=:pDescrip, p.designation=:pDesign, p.prix=:pPrix, p.quantite=:pQtite WHERE p.id=:pIdPr AND p.categorie.id=:pIdCa";

		Query query = s.createQuery(req);

		// passage avec params
		query.setParameter("pDescrip", pro.getDescription());
		query.setParameter("pDesign", pro.getDesignation());
		query.setParameter("pPrix", pro.getPrix());
		query.setParameter("pQtite", pro.getQuantite());
		query.setParameter("pIdPr", pro.getId());
		query.setParameter("pIdCa", pro.getCategorie().getId());

		return query.executeUpdate();
	}

}
