package fr.adaming.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IGerantDao;
import fr.adaming.model.Gerant;


@Service("gService")
@Transactional
public class GerantServiceImpl implements IGerantService{
	
	@Autowired
	private IGerantDao gerantDao;
	
	

	public void setGerantDao(IGerantDao gerantDao) {
		this.gerantDao = gerantDao;
	}

	@Override
	public Gerant isExist(Gerant gIn) {
		
		return gerantDao.isEsist(gIn);
	}

	@Override
	public void mailAjoutCl(String toMail, String sujet, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PDF() {
		// TODO Auto-generated method stub
		
	}
	
}
