package it.polito.tdp.borders.model;

public class Border {
	private int state1no;
	private int state2no;
	private String state1ab; 
	private String state2ab;  
	private int conttype;
	private int anno;
	
	public Border(int state1no, int state2no, String state1ab, String state2ab, int conttype, int anno) {
		
		this.state1no = state1no;
		this.state2no = state2no;
		this.state1ab = state1ab;
		this.state2ab = state2ab;
		this.conttype = conttype;
		this.anno = anno;
	}
	

	public int getState1no() {
		return state1no;
	}



	public int getState2no() {
		return state2no;
	}



	public String getState1ab() {
		return state1ab;
	}

	public String getState2ab() {
		return state2ab;
	}

	public int getConttype() {
		return conttype;
	}

	public int getAnno() {
		return anno;
	}
	
	
	
	

}
