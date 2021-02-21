/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */

@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    
	
	
    
  
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetRecursoXX(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
	    BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
		
	    try {	
			//obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(gc.getAllBlueprints(),HttpStatus.ACCEPTED);
	    } catch (Exception ex){
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
	    }  
	}
	
	@GetMapping("/{author}")
	public ResponseEntity<?> leer(@PathVariable String author) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
	    BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
	    Set<Blueprint> conjunto;
		try {
			conjunto = gc.getBlueprintsByAuthor(author);
			return ResponseEntity.ok(conjunto);
		} catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			
			return ResponseEntity.notFound().build();
		}
	
	}
	
	@GetMapping("/{author}/{bname}")
	public ResponseEntity<?> leer(@PathVariable String author,@PathVariable String bname) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
	    BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
	    Blueprint blue;
		try {
			blue = gc.getBlueprint(author, bname);
			return ResponseEntity.ok(blue);
		} catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			
			return ResponseEntity.notFound().build();
		}
	
	}
    
    
}




    
 


