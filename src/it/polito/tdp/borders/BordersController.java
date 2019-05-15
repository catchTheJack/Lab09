/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class BordersController {

	Model model = new Model();
	
	@FXML
	private HBox hbox;	
	
	@FXML
	private ComboBox<String> comboBox;

	@FXML
	private Button btnPaesiRaggiungibili;
	
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		txtResult.clear();
		int anno = Integer.parseInt(txtAnno.getText());
		double start = System.nanoTime();
		model.creaGrafo(anno);
		double stop = System.nanoTime();
		txtResult.appendText("Tempo di esecuzione = " +(stop-start)+"\n");
		model.calcolaCollegamenti(anno);
		for(Country temp: model.getMappa().values()) {
			txtResult.appendText(temp.getInfo()+" componenti connesse: "+model.paesiRaggiungibili(temp)+"\n");
		}
		comboBox.getItems().addAll(model.getPaesi());
		hbox.setDisable(false);
	}
		
	@FXML
	void doCercaPaesiRaggiungibili(ActionEvent event) {
				txtResult.clear();
				String cercato = comboBox.getValue();
				txtResult.appendText("PUOI VISITARE UNA DELLE SEGUENTI NAZIONI \nsenza prendere la nave di schiettino \n RICORDA CHE NEI PAESI ARABI PRIMA DI PARLARE....STAI ZITTO! \n");
				for(String temp: model.quoVado(cercato)) {
					txtResult.appendText(temp+"\n");
				}
		    }	
		
	
	
	

    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Borders.fxml'.";
        assert btnPaesiRaggiungibili != null : "fx:id=\"btnPaesiRaggiungibili\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
        assert hbox != null : "fx:id=\"hbox\" was not injected: check your FXML file 'Borders.fxml'.";
    }

	public void setModel(Model model) {
		this.model = model;
		}
		
	
}
