package it.polito.tdp.rivers.model;

import java.util.List;

public class Simulatore {
	//Modello
	private double caso;
	
	//Eventi
	
	
	//Input
	private double C;
	private double Q;
	private double flussoMedio;
	private double flussoMedioUscita;
	private double flussoMedioUscitaAltaStagione;
	private List<Flow> flussiIngressoGiornalieri;
	
	//Output
	int carenza;
	double media;
	
	public void init(double k, double flussoMedio, List<Flow> flussiIngressoGiornalieri) {
		
		this.flussoMedio = flussoMedio;
		this.Q = k*flussoMedio*30*3600*24;
		this.C = Q/2;
		this.flussoMedioUscita = 0.8*this.flussoMedio;
		this.flussoMedioUscitaAltaStagione = 10*this.flussoMedioUscita;
		this.carenza = 0;
		
		this.flussiIngressoGiornalieri = flussiIngressoGiornalieri;
		
	}
	
	public void run() {
		
		for (Flow f : this.flussiIngressoGiornalieri) {
			this.caso = Math.random();
			if (caso<=0.95) {	
				
				this.C += (f.getFlow()*3600*24-this.flussoMedioUscita*3600*24);
				
				if (C>Q) {
					this.C = this.Q;
				}
				
				if (C<0) {
					this.C = 0;
					this.carenza++;
				}
				
				this.media += this.C;
				
			} else {
				
				this.C += (f.getFlow()*3600*24-this.flussoMedioUscitaAltaStagione*3600*24);
				
				if (C>Q) {
					this.C = this.Q;
				}
				
				if (C<0) {
					this.C = 0;
					this.carenza++;
				}
				
				this.media += this.C;
				
			}
		}
		
		this.media = this.media / this.flussiIngressoGiornalieri.size();
		
	}

	public int getCarenza() {
		return carenza;
	}

	public double getMedia() {
		return media;
	}
	
}
