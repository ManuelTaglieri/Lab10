/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void caricaPortateFiume(ActionEvent event) {
    	
    	txtResult.clear();
    	txtStartDate.clear();
    	txtEndDate.clear();
    	txtFMed.clear();
    	txtNumMeasurements.clear();
    	
    	River r = boxRiver.getValue();
    	
    	model.flowByRiver(r);
    	
    	txtStartDate.setText(model.getMinDate(r).toString());
    	txtEndDate.setText(model.getMaxDate(r).toString());
    	txtFMed.setText(Double.toString(model.getAvgFlow(r)));
    	txtNumMeasurements.setText(Integer.toString(model.getSizeFlow(r)));
    	
    	this.btnSimula.setDisable(false);

    }
    
    @FXML
    void simula(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	if (txtK.getText()==null || txtK.getText()=="") {
    		txtResult.setText("Inserire un valore di k");
    		return;
    	}
    	
    	if (boxRiver.getValue()==null) {
    		txtResult.setText("Selezionare un fiume dal menù a tendina");
    		return;
    	}
    	
    	try {
    		double k = Double.parseDouble(txtK.getText());
    		this.model.simula(k, boxRiver.getValue());
    		
    		txtResult.setText("Il numero di giorni con carenza idrica è stato " + this.model.getCarenza() +".\n");
    		txtResult.appendText("L'occupazione media del bacino è stata di " + (int) this.model.getMedia() + " m^3.");
    	} catch (NumberFormatException e) {
    		txtResult.setText("Inserire un valore numerico come parametro k");
    		return;
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	this.boxRiver.getItems().addAll(model.getAllRivers());
    	
    }
}
