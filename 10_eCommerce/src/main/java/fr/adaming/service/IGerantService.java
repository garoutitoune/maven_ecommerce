package fr.adaming.service;



import fr.adaming.model.Gerant;


public interface IGerantService {
	
	public Gerant isExist(Gerant gIn);
	
	public void mailAjoutCl(String toMail, String sujet, String text);
	
	public void PDF();
	

}
