package fr.adaming.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.dao.IGerantDao;
import fr.adaming.model.Gerant;
import fr.adaming.model.Produit;


public class GerantServiceImpl implements IGerantService{

	@Override
	public Gerant isExist(Gerant gIn) {
		// TODO Auto-generated method stub
		return null;
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
