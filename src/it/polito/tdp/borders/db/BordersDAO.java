package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<String> loadAllCountries(Map<Integer,Country> mappa) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<String> result = new ArrayList<String>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country cx = new Country(rs.getInt("ccode"),rs.getString("StateAbb"), rs.getString("StateNme"));
				mappa.put(rs.getInt("ccode"),cx);
				result.add(cx.getStateName());
				System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno,Graph<Country,DefaultEdge> grafo,Map<Integer,Country> mappa) {
		
		String sql = "SELECT state1no, state2no, state1ab, state2ab, conttype, year FROM contiguity WHERE YEAR<=? AND conttype=1 AND state1no > state2no";
		List<Border> yearBorder = new ArrayList<Border>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				grafo.addEdge(mappa.get(rs.getInt("state1no")),mappa.get(rs.getInt("state2no")));
				Border bx = new Border( rs.getInt("state1no"),rs.getInt("state2no"),rs.getString("state1ab"),rs.getString("state2Ab"), rs.getInt("conttype"),rs.getInt("year"));
				yearBorder.add(bx);
				//System.out.format(" %s %s %d %d \n", rs.getString("state1ab"), rs.getString("state2Ab"), rs.getInt("conttype"));
			}
			
			conn.close();
			return yearBorder;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

	}
	
	public void setGradoCountry(int anno , Map<Integer,Country> mappa) {
		String sql = "SELECT state1no, COUNT(state1no) AS cnt FROM contiguity WHERE YEAR<= ? AND conttype=1 GROUP BY state1no";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				mappa.get(rs.getInt("state1no")).setGrado(rs.getInt("cnt"));
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

		
		
	}
}
