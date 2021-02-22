/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
//import edu.eci.arsw.springdemo.GrammarChecker;

import java.util.Set;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        //InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

    	ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
        BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
    	
        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        gc.addNewBlueprint(bp0);
        
        //ibpp.saveBlueprint(bp0);
        
        /*Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);*/
        
        assertNotNull("Loading a previously stored blueprint returned null.",gc.getBlueprint(bp0.getAuthor(), bp0.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",gc.getBlueprint(bp0.getAuthor(), bp0.getName()), bp0);
        
    }


    @Test
    public void saveExistingBpTest() {
        //InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
    	ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
        BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
    	
    	
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
        	gc.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
        	gc.addNewBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }

    @Test
    public void noExisteAutorGetPrint() {
    	//InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
    	ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
        BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
    	
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
        	gc.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        try {
			gc.getBlueprint("cesar","thepaint");
			fail("not exist blueprint 2 ");
		} catch (BlueprintNotFoundException e) {
			
		}
        
    }
    
    @Test
    public void existeAutorGetPrint() {
    	//InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
    	ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
        BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
    	
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        try {
        	gc.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        try {
			Blueprint bp0=gc.getBlueprint("john","thepaint");
			assertEquals(bp,bp0);
			
		} catch (BlueprintNotFoundException e) {
			fail("not exist blueprint 2 ");
		}
        
    }
    
    @Test
    public void noexisteConjunto() {
    	//InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
    	ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
        BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
    	
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        try {
        	gc.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        try {
        	Set<Blueprint> conjunto = gc.getBlueprintsByAuthor("cesar");
        	fail("not exist conjunto");
		} catch (BlueprintNotFoundException e) {
		}
        
    }
  
    @Test
    public void existeConjunto() {
    	//InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
    	ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
        BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
    	
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        try {
        	gc.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        try {
        	Set<Blueprint> conjunto = gc.getBlueprintsByAuthor("john");
        	assertEquals(conjunto.size(),1);
		} catch (BlueprintNotFoundException e) {
		}
        
    }
    
    @Test
    public void todoslosprints() {
    	//InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
    	ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
        BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
    	
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        try {
        	gc.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
       assertEquals(gc.getAllBlueprints().size(),2);
    }
}
