package br.com.wrahgles.model;

import java.io.Serializable;

public class Bounds implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Point sul;
	
	private Point norte;

	public Point getSul() {
		return sul;
	}

	public void setSul(Point sul) {
		this.sul = sul;
	}

	public Point getNorte() {
		return norte;
	}

	public void setNorte(Point norte) {
		this.norte = norte;
	}
	
	
	
	

	

	
}
