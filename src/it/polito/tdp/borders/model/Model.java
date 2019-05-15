package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	BordersDAO bDAO ;
	Graph<Country,DefaultEdge> grafo;
	Map<Integer,Country> mappa;
	List<String> paesi;
	
	

	public Model() {
			this.bDAO = new BordersDAO();
			this.grafo = new SimpleGraph<>(DefaultEdge.class);
			this.mappa = new HashMap<Integer,Country>();
			this.paesi = new ArrayList<String>();
		}
	
	
	//metodo per creare il grafo
	public void creaGrafo(int anno) {
		//aggiungo vertici
		paesi = bDAO.loadAllCountries(mappa);
		Graphs.addAllVertices(grafo,mappa.values());
		
		//aggiungo archi direttamente nella query sql
		bDAO.getCountryPairs(anno,this.grafo,this.mappa);
		
		System.out.print(grafo);
	}
	
	
	public void calcolaCollegamenti(int anno) {
		bDAO.setGradoCountry(anno, mappa);
		System.out.println(mappa);
	}
	
	
	public List<String> quoVado(String stato){
		List<String> checco =new ArrayList<String>(); 
		Country source = cercaStato(stato);
		if(source!=null) {
		GraphIterator<Country, DefaultEdge> it = new BreadthFirstIterator<>(this.grafo, source);
		while (it.hasNext()) {
			checco.add(it.next().getStateName());
		}
		
		}
		return checco;
	}
	
	
	
	
	public int paesiRaggiungibili(Country source){
		ConnectivityInspector<Country, DefaultEdge> conIt = new ConnectivityInspector<Country,DefaultEdge>(grafo);
		List<Country> raggiunti = new ArrayList<Country>(conIt.connectedSetOf(source));
		return raggiunti.size();
	}


	public Map<Integer, Country> getMappa() {
		return mappa;
	}


	public List<String> getPaesi() {
		return paesi;
	}
	
	public Country cercaStato(String paese) {
		Country cercato = null;
		for(Country cx : mappa.values()) {
			if(cx.getStateName().compareTo(paese)==0) {
				cercato = cx;
				break;
			}
		}
		return cercato;
	}
	
	
	

	
	
}
