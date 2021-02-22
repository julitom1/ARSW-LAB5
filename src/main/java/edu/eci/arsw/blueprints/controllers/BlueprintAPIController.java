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


import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */


@Controller
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    
	
	@Autowired
	BlueprintsServices bs=null;
  
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetRecursoXX(){
		
		
	    try {	
			//obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(bs.getAllBlueprints(),HttpStatus.ACCEPTED);
	    } catch (Exception ex){
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
	    }  
	}
	
	@RequestMapping(value = "/prueba", method = RequestMethod.POST)
	public ResponseEntity<?> manejadorPostRecursoBlueprint(@RequestBody  String s){
	    try {
	        //registrar dato
	    	
	    	JSONObject obj = new JSONObject(s);
	    	
	    	 String jsonName = obj.getString("name");
	   
	    	 String jsonauthor = obj.getString("author");
	    
	         JSONArray pointsJson = obj.getJSONArray("points");
	      
	         Point[] points = new Point[pointsJson.length()];
	         
	         for(int i = 0; i < points.length; i++){
	             JSONObject point = pointsJson.getJSONObject(i);
	             points[i] = new Point(point.getInt("x"), point.getInt("y"));
	         }
	      
	         Blueprint bp = new Blueprint(jsonauthor, jsonName, points);
	         
	        bs.addNewBlueprint(bp);
	    	
	        return new ResponseEntity<>(obj,HttpStatus.CREATED);
	    } catch (Exception ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);            
	    }        

	}
	
	@GetMapping("/{author}")
	public ResponseEntity<?> leer(@PathVariable String author) {

	    Set<Blueprint> conjunto;
		try {
			conjunto = bs.getBlueprintsByAuthor(author);
			return ResponseEntity.ok(conjunto);
		} catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			
			return ResponseEntity.notFound().build();
		}
	
	}
	
	@GetMapping("/{author}/{bname}")
	public ResponseEntity<?> leer(@PathVariable String author,@PathVariable String bname) {

	    Blueprint blue;
		try {
			blue = bs.getBlueprint(author, bname);
			return ResponseEntity.ok(blue);
		} catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			
			return ResponseEntity.notFound().build();
		}
	
	}
	
	@RequestMapping(value = "/{author}/{bpname}", method = RequestMethod.PUT)
	public ResponseEntity<?> UpdateRessourceBlueprint(@PathVariable String author, @PathVariable String bpname, @RequestBody String puntos){
	    

	        JSONObject obj = new JSONObject(puntos);
	        

	        JSONArray pointsJson = obj.getJSONArray("puntos");
	        Point[] points = new Point[pointsJson.length()];
	        for(int i = 0; i < points.length; i++){
	             JSONObject point = pointsJson.getJSONObject(i);
	            points[i] = new Point(point.getInt("x"), point.getInt("y"));
	        }


	      
	       try {
	    	   bs.UpdateBlueprint(author, bpname, points);
	       } catch (BlueprintNotFoundException e) {
			
	    	   Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
	    	   return new ResponseEntity<>("error",HttpStatus.FORBIDDEN);   
	       }
	       
	        return new ResponseEntity<>(obj,HttpStatus.ACCEPTED);


	  
	                 
	    
	}
    
    
}




    
 


