package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao;
	Map<River, List<Flow>> portate; 
	
	public Model() {
		this.dao = new RiversDAO();
		this.portate = new HashMap<>();
	}
	
	public List<River> getAllRivers() {
		return dao.getAllRivers();
	}

	public void flowByRiver(River r) {
		
		List<Flow> flussi = dao.getFlowByRiver(r);
		
		portate.put(r, flussi);
		
	}
	
	public double getAvgFlow(River r) {
		
		double somma = 0;
		
		for (Flow f : portate.get(r)) {
			
			somma += f.getFlow();
			
		}
		
		return somma/portate.get(r).size();
		
	}
	
	public int getSizeFlow(River r) {
		return portate.get(r).size();
	}
	
	public LocalDate getMinDate(River r) {
		
		LocalDate min = LocalDate.MAX;
		
		for (Flow f : portate.get(r)) {
			if (f.getDay().isBefore(min) ) {
				min = f.getDay();
			}
		}
		
		return min;
		
	}
	
	public LocalDate getMaxDate(River r) {
		
		LocalDate max = LocalDate.MIN;
		
		for (Flow f : portate.get(r)) {
			if (f.getDay().isAfter(max) ) {
				max = f.getDay();
			}
		}
		
		return max;
		
	}
	
	
	

}
