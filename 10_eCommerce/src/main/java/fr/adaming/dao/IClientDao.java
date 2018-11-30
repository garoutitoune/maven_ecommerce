package fr.adaming.dao;



import fr.adaming.model.Client;


public interface IClientDao {
	
	public Client isExist(Client cl);
	public Client addClient(Client cl);
	public Client modifClient(Client cl);
	public void delClient(Client cl);
	public Client searchById(Client cl);
	public int modifMdp(Client cl);

}
