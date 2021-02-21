package edu.eci.arsw.blueprints.test.persistence.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

public class FiltradoATest {
	@Test
    public void noExisteAutorGetPrint() {
    	//InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
    	ApplicationContext ac = new ClassPathXmlApplicationContext("aplicationContext.xml");
        BlueprintsServices gc = ac.getBean(BlueprintsServices.class);
    	
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10),new Point(10, 10),new Point(10, 10),new Point(40, 10),new Point(50, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        try {
        	gc.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        try {
			Blueprint b1=gc.getBlueprint("john", "thepaint");
			assertEquals(b1.getPoints().size(),4);
		} catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
