package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class orderSysController implements Initializable{
	@FXML private HBox menuBox; //¿ÞÂÊÆí pane
	@FXML private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, payBtn, delete;
	//@FXML private ImageView sign; //°£ÆÇ
	//@FXML private ListView<String> listView;
	@FXML private Label nameLabel, gradeLabel;
	@FXML private TextArea total;
	@FXML private TableView<TableRowData> tableView;
	@FXML private TableColumn<TableRowData, String> food;
	@FXML private TableColumn<TableRowData, Integer> foodPrice;
	
	
	//int[] flag = new int[9];
	static int totalPrice =0;
	Menu kimbap = new Menu("±è¹ä", 2500);
	Menu kimchi = new Menu("±èÄ¡", 1500);
	Menu naengmyeon = new Menu("³Ã¸é", 5000);
	Menu tteok = new Menu("¶±", 3500);
	Menu tteokbokki = new Menu("¶±ººÀÌ", 3000);
	Menu bossam = new Menu("º¸½Ó", 20000);
	Menu bulgogi = new Menu("ºÒ°í±â", 8000);
	Menu bibimbap = new Menu("ºñºö¹ä", 6500);
	Menu japchae = new Menu("ÀâÃ¤", 7000);
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		food.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		foodPrice.setCellValueFactory(cellData->cellData.getValue().priceProperty().asObject());
		
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				int slctPoint = tableView.getSelectionModel().getSelectedIndex();
				TableRowData slctData= tableView.getSelectionModel().getSelectedItem();
				int dltPrice = slctData.priceProperty().getValue();
				tableView.getItems().remove(slctPoint);
				totalPrice -= dltPrice;
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
							
			}
			
		});
		
		
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				tableView.getItems().remove(0, tableView.getItems().size());
				totalPrice = 0;
				total.setText("");
				
			}
			
		});
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				tableView.getItems().add(new TableRowData(new SimpleStringProperty("±è¹ä"), 
						new SimpleIntegerProperty(2500)));
				kimbap.count += 1;
				totalPrice += kimbap.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
			}
			
		});
		
		
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				tableView.getItems().add(new TableRowData(new SimpleStringProperty("±èÄ¡"), 
						new SimpleIntegerProperty(1500)));
				kimchi.count += 1;
				totalPrice += kimchi.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
								
			}
			
		});
				
		btn3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableView.getItems().add(new TableRowData(new SimpleStringProperty("³Ã¸é"), 
						new SimpleIntegerProperty(5000)));
				naengmyeon.count += 1;
				totalPrice += naengmyeon.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
			}
			
		});
		
		btn4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableView.getItems().add(new TableRowData(new SimpleStringProperty("¶±"), 
						new SimpleIntegerProperty(3500)));
				tteok.count += 1;
				totalPrice += tteok.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
					
			}
			
		});
		
		btn5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableView.getItems().add(new TableRowData(new SimpleStringProperty("¶±ººÀÌ"), 
						new SimpleIntegerProperty(3000)));
				tteokbokki.count += 1;
				totalPrice += tteokbokki.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
			}
			
		});
		
		btn6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableView.getItems().add(new TableRowData(new SimpleStringProperty("º¸½Ó"), 
						new SimpleIntegerProperty(20000)));
				bossam.count += 1;
				totalPrice += bossam.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
			}
			
		});
		
		btn7.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//listView.getItems().add(bulgogi.getFoodName().toString() +"\t\t\t\t\t\t" + bulgogi.getPrice());
				tableView.getItems().add(new TableRowData(new SimpleStringProperty("ºÒ°í±â"), 
						new SimpleIntegerProperty(8000)));
				bulgogi.count += 1;
				totalPrice += bulgogi.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
			}
			
		});
		
		btn8.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableView.getItems().add(new TableRowData(new SimpleStringProperty("ºñºö¹ä"), 
						new SimpleIntegerProperty(6500)));
				bibimbap.count += 1;
				totalPrice += bibimbap.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
			}		
		});
		
		btn9.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableView.getItems().add(new TableRowData(new SimpleStringProperty("ÀâÃ¤"), 
						new SimpleIntegerProperty(7000)));
				japchae.count += 1;
				totalPrice += japchae.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);	
				
			}
			
		});
		
	}
	

}
