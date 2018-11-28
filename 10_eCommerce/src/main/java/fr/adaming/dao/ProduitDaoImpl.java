package fr.adaming.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;


public class ProduitDaoImpl implements IProduitDao {

	@Override
	public List<Produit> getAllProduit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit addProduit(Produit pro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProduit(Produit pro) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifierProduit(Produit pro) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Produit> getAllProduitCat(Produit pro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit getProduitById(Produit pro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProduitByDescr(Produit pro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifierProduitSansPhoto(Produit pro) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
