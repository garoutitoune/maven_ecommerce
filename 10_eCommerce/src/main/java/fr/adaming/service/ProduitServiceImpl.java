package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Service("proService")
@Transactional
public class ProduitServiceImpl implements IProduitService{
	
	@Autowired
	private IProduitDao produitDao;

	@Override
	public List<Produit> getAllProduit() {
		
		return produitDao.getAllProduit();
	}

	@Override
	public Produit addProduit(Produit pro, Categorie ca) {
		pro.setCategorie(ca);
		return produitDao.addProduit(pro);
	}

	@Override
	public int deleteProduit(Produit pro) {
		
		return produitDao.deleteProduit(pro);
	}

	@Override
	public int modifierProduit(Produit pro, Categorie ca) {
		pro.setCategorie(ca);
		//si le tableau est différent de 0 (si il a une photo) 
		if(pro.getPhoto().length!=0) {
			System.out.println("je lance la methode modif photo");
			//modifier l'ancienne photo
			return produitDao.modifierProduit(pro);
		}else {
			System.out.println("je lance la methode modif sans photo");
			// garde l'ancienne photo
			return produitDao.modifierProduitSansPhoto(pro);
		}
		
		
	}

	@Override
	public List<Produit> getAllProduitByCat(Produit pro, Categorie ca) {
		pro.setCategorie(ca);
		return produitDao.getAllProduitCat(pro);
	}

	@Override
	public Produit getProduitById(Produit pro) {
		
		return produitDao.getProduitById(pro);
	}

	@Override
	public List<Produit> getProduitByDes(Produit pro, Categorie ca) {
		pro.setCategorie(ca);
		return produitDao.getProduitByDescr(pro);
	}
	
}
