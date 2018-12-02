package fr.adaming.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

public class Pdf {
	
	@ManagedProperty(value="proService")
	private IProduitService produitService;
	
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	public static void pdf() {
	Document doc = new Document();

	try {
		PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\INTI0489\\Desktop\\pdftest\\pdf1.pdf"));
		
		doc.open();

		doc.add(new Paragraph(" "));
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph(" "));
		Paragraph para = new Paragraph("                     ----------------------- Liste des produits -----------------------");

		doc.add(para);

		doc.add(new Paragraph(" "));
		doc.add(new Paragraph(" "));
		

		PdfPTable table = new PdfPTable(6);

		PdfPCell c1 = new PdfPCell(new Phrase("Id du produit"));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c1);
		PdfPCell c2 = new PdfPCell(new Phrase("designation"));
		c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("description"));
		c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c3);
		PdfPCell c4 = new PdfPCell(new Phrase("image"));
		c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c4);
		PdfPCell c5 = new PdfPCell(new Phrase("prix"));
		c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c5);
		PdfPCell c6 = new PdfPCell(new Phrase("quantite"));
		c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c6);
		
		table.setHeaderRows(1);
		
		doc.add(table);
	
//		// List<Produit> liste=produitService.getAllProduit();
//		for(Produit pro:liste) {
//			table.addCell(Integer.toString(pro.getId()));
//			table.addCell(pro.getDesignation());
//			table.addCell(pro.getDescription());
//			try {
//				Image img=Image.getInstance(pro.getPhoto());
//				table.addCell(img);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		table.addCell(pro.getImage());
//			table.addCell(Double.toString(pro.getPrix()));
//			table.addCell(Integer.toString(pro.getQuantite()));			
//		}
        
		doc.add(table);
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph(" "));
	
		doc.close();
		
		
	} catch (FileNotFoundException | DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	public static void recapCommande(Commande commande) {
		
		Document doc = new Document();

		try {
			
			File yourFile=new File("C:\\Users\\INTI0489\\Desktop\\pdftest\\Recapitulatif.pdf");
//			yourFile.delete();
			yourFile.createNewFile();
			
//			Writer doc = new BufferedWriter(new FileWriter(yourFile));
			PdfWriter.getInstance(doc, new FileOutputStream(yourFile,false));
			
			
			doc.open();

			doc.add(new Paragraph("----------------------- Récapitulatif de commande -----------------------\n"));
			doc.add(new Paragraph("Commande N."+commande.getId()+"\n\n" ) );
			
			//construction de la table des produits
			PdfPTable table = new PdfPTable(4);
			
			table.addCell("Identifiant du produit");
			table.addCell("Désignation");
			table.addCell("Prix");
			table.addCell("Quantité");
			
			for (LigneCommande li : commande.getListeLignes()) {
				table.addCell(li.getProduit().getId()+"");
				table.addCell(li.getProduit().getDesignation()+"");
				table.addCell(li.getProduit().getPrix()+"");
				table.addCell(li.getQt()+"");
				
			}
			
//			PdfPCell c1 = new PdfPCell(new Phrase("Id du produit"));
//			c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//			table.addCell(c1);
//			PdfPCell c2 = new PdfPCell(new Phrase("designation"));
//			c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
//			table.addCell(c2);
//			PdfPCell c5 = new PdfPCell(new Phrase("prix"));
//			c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
//			table.addCell(c5);
//			PdfPCell c6 = new PdfPCell(new Phrase("quantite"));
//			c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
//			table.addCell(c6);
			
//			table.setHeaderRows(1);
			
			doc.add(table);
			
			
			//fin de la table
			//calcul du prix de la commande
			double prix=0;
			
			for (LigneCommande li : commande.getListeLignes()) {
				prix+=li.getPrix();
			}
			doc.add(new Paragraph("Prix total de la commande: "+prix+"€\n"));
			doc.add(new Paragraph("(A régler au moment de la récupération des produits.)"));
			doc.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	
}
