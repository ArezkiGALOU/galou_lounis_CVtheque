package com.daar.controler;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daar.model.CV;
import com.daar.model.CVelastic;
import com.daar.repository.CVRepository;
import com.daar.repository.CVelasticRepository;
import com.daar.sevice.ElasticService;
import com.daar.sevice.JSONFromCV;
import com.google.gson.Gson;



@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class CVControler {

	@Autowired
	private CVRepository repository;
	@Autowired
	private CVelasticRepository elasticrepository;
	
	@Autowired
	private ElasticService elasticService;
	
	@PostMapping("CV/add")
	public String  add(@RequestParam("file") MultipartFile file) {
		JSONObject obj=null;
	
		if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
			try {
				obj=JSONFromCV.getObjectFromWord(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(file.getContentType().equals("application/pdf")){
			obj=JSONFromCV.getObjectFromPdf(file);
		}else {
			return "erreur";
		}

		try {
					CV mycv;
					mycv = new CV(file.getOriginalFilename(), file.getBytes());
					this.repository.save(mycv);
					CVelastic cvelastic=new CVelastic(mycv.getId(),mycv.getNom(), obj.toString());
				    this.elasticrepository.save(cvelastic);
					return "opération réussi";
					
			} catch (IOException e) {
					e.printStackTrace();
			}		
			return "erreur";
			
	}
	
@GetMapping("CV/search")
public String search(@RequestParam ("mots") String mots) {
	
	try {
		String json = new Gson().toJson(this.elasticService.search(mots));
		System.out.println(json);
		return json;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}

	
	
}
