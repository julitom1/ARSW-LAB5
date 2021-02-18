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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */

@RestController
@RequestMapping(value = "/url-raiz-recurso")
public class BlueprintAPIController {
    
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetRecursoXX(){
	    try {
	        
			//obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    } catch (Exception ex){
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
	    }  
	}
    
    
}




    
 


