package it.polito.tdp.borders.model;

public class Country {
	
	private int ccode;
	private String stateAbb;
	private String stateName;
	private int grado;
	
	public Country(int ccode, String stateAbb, String stateName) {
		
		this.ccode = ccode;
		this.stateAbb = stateAbb;
		this.stateName = stateName;
		this.grado = 0;
	}
	

	public int getGrado() {
		return grado;
	}


	public void setGrado(int grado) {
		this.grado = grado;
	}




	public int getCcode() {
		return ccode;
	}


	public String getStateAbb() {
		return stateAbb;
	}


	public String getStateName() {
		return stateName;
	}
	
	public String getInfo() {
		String res = stateName+" "+grado;
		return res;
	}
	
	
	

}
