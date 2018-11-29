package fr.adaming.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Service(value="clservice")
@Transactional
public class ClientServiceImpl implements IClientService{

	//asso uml java
	@Autowired
	IClientDao cldao;
	//setter pour injection

	public void setCldao(IClientDao cldao) {
		this.cldao = cldao;
	}
	
	
	@Override
	public Client isExist(Client cl) {
		return cldao.isExist(cl);
	}


	@Override
	public Client addClient(Client cl) {
		return cldao.addClient(cl);
	}

	@Override
	public Client modifClient(Client cl) {
		return cldao.modifClient(cl);
	}

	@Override
	public void delClient(Client cl) {
		cldao.delClient(cl);
		
	}

	@Override
	public Client searchById(Client cl) {
		return cldao.searchById(cl);
	}

	
}
