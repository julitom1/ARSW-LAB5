/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */


public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();
    private FiltradoB b=null;
    
    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        pts=new Point[]{new Point(10, 140),new Point(15, 115),new Point(70, 70),new Point(80, 80)};
        bp=new Blueprint("_authorname_", "_bpname1_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        pts=new Point[]{new Point(90, 90),new Point(100, 115),new Point(120, 140),new Point(15, 11)};
        bp=new Blueprint("_Cesar_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }    
        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
    	
    	Blueprint blu=blueprints.get(new Tuple<>(author, bprintname));
    	if(blu==null) {
    		throw new BlueprintNotFoundException("The blueprint not exist");
    	}	
    	
    	b.getBlueprint(blu.getAuthor(), blu.getName());
    	return blu;
    }
    
    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
    	Set<Blueprint> conjunto=new HashSet<Blueprint>();
    	
    	for (Map.Entry<Tuple<String,String>, Blueprint> entry : blueprints.entrySet()) {
    		if(entry.getKey().getElem1() == author) {
    			
    			conjunto.add(blueprints.get(entry.getKey()));
    		}
    	    //System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
    	}
    	if(conjunto.size()==0) {
    		throw new BlueprintNotFoundException("The author not exist");
    	}
    	return conjunto;
    }		
    
    @Override
    public Set<Blueprint> getAllBlueprints(){
    	Set<Blueprint> lista=new HashSet<Blueprint>();
    	for (Map.Entry<Tuple<String,String>, Blueprint> entry : blueprints.entrySet()) {
    		lista.add(blueprints.get(entry.getKey()));
    	    //System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
    	}
    	return lista;
    }
    
}
