package com.daar.sevice;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class JSONFromCV {
	
 public static JSONObject getObjectFromPdf(MultipartFile file) {
	 
	  PDDocument pdocument;
	 JSONObject obj = new JSONObject();
	 try {
		pdocument = PDDocument.load(file.getBytes());
		PDFTextStripper pdfStripper= new PDFTextStripper();
		String  text= pdfStripper.getText(pdocument);
		obj.put("text", text);
	 } catch (IOException e) {
		e.printStackTrace();
	 }
			
	 return obj;
 }
 
 
 public static JSONObject getObjectFromWord(MultipartFile file) throws IOException {

		XWPFDocument docx = new XWPFDocument(file.getInputStream());

		XWPFWordExtractor text = new XWPFWordExtractor(docx);

		JSONObject obj = new JSONObject();
		obj.put("text", text.getText());

		return obj;
	}
}
