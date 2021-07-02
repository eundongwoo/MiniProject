package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class payController implements Initializable{

	@FXML private TableView<TableRowData> payTable;
	@FXML private TableColumn<TableRowData, String> payFood;
	@FXML private TableColumn<TableRowData, Integer> payPrice;
	@FXML private Button cancel,payok;
	@FXML private ListView<String> listView;
	@FXML private TextField payTotal;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		payFood.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		payPrice.setCellValueFactory(cellData->cellData.getValue().priceProperty().asObject());
		
		for (TableRowData t: orderSysController.payData) {
			payTable.getItems().add(t);
		}
		
		String sum = Integer.toString(orderSysController.totalPrice);
		if(orderSysController.totalPrice == 0) {
			payTotal.clear();
		} 
		else {
			payTotal.setText(sum);
		}
		
		payok.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
			}
			
		});
		
		
	}
	
	public void handle_cancel()
	{
		Stage stage=(Stage)cancel.getScene().getWindow();
		stage.close();
	}
	
	

}
