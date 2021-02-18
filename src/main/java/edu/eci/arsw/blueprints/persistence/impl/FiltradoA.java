package edu.eci.arsw.blueprints.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

@Service
public class FiltradoA extends Filtrados {
	
	public Point[] formaFiltrado(List<Point> puntos) {
		ArrayList<Point> puntosBuenos =new ArrayList<Point>();
		int puntox=-1;
		int puntoy=-1;
		for(int i=0;i< puntos.size();i++) {
			if(puntos.get(i).getX()!=puntox || puntos.get(i).getY()!=puntoy) {
				puntosBuenos.add(puntos.get(i));
				puntox=puntos.get(i).getX();
				puntoy=puntos.get(i).getY();
			}
		}
		Point[] puntonuevo= new Point[puntosBuenos.size()];
		for(int i=0;i<puntosBuenos.size();i++) {
			puntonuevo[i]=puntosBuenos.get(i);
		}
		return puntonuevo;
	}
}
