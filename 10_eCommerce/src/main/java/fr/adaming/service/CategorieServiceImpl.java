package fr.adaming.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;


@Service("caService")
@Transactional
public class CategorieServiceImpl implements ICategorieService{

	@Autowired
	private ICategorieDao categorieDao;
	
	
	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		
		return categorieDao.getAllCategorie();
	}

	@Override
	public Categorie addCategorie(Categorie ca) {
		
		return categorieDao.addCategorie(ca);
	}

	@Override
	public int modifierCategorie(Categorie ca) {
		if(ca.getPhoto().length!=0) {
			
			return categorieDao.modifierCategoriePhoto(ca);
		}else {
		
			return categorieDao.modifierCategorie(ca);
			
		}
		
	}

	@Override
	public int supprimerCategorie(Categorie ca) {
		
		return categorieDao.supprimerCategorie(ca);
	}
	

	

}
