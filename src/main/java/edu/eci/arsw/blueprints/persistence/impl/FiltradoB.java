package edu.eci.arsw.blueprints.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

//@Service
public class FiltradoB extends Filtrados{
	
	public Point[] formaFiltrado(List<Point> puntos) {
		ArrayList<Point> puntosBuenos =new ArrayList<Point>();
		for(int i=0;i< puntos.size();i++) {
			if(((i+1) % 3)!=0) {
				puntosBuenos.add(puntos.get(i));
			}
		}
		
		Point[] puntonuevo= new Point[puntosBuenos.size()];
		for(int i=0;i<puntosBuenos.size();i++) {
			puntonuevo[i]=puntosBuenos.get(i);
		}
		return puntonuevo;
	}
}
