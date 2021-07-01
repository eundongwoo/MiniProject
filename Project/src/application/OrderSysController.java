package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class OrderSysController implements Initializable{
	@FXML private HBox menuBox; //¿ÞÂÊÆí pane
	@FXML private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, payBtn, delete;
	//@FXML private ImageView sign; //°£ÆÇ
	@FXML private ListView<String> listView;
	@FXML private Label nameLabel, gradeLabel;
	@FXML private TextArea total;
	@FXML private Button id;
	@FXML private Button money;
	
	int[] flag = new int[9];
	static int totalPrice =0;
	Menu kimbap = new Menu("±è¹ä", 2500);
	Menu kimchi = new Menu("±èÄ¡", 1000);
	Menu naengmyeon = new Menu("³Ã¸é", 5000);
	Menu tteok = new Menu("¶±", 3500);
	Menu tteokbokki = new Menu("¶±ººÀÌ", 3000);
	Menu bossam = new Menu("º¸½Ó", 20000);
	Menu bulgogi = new Menu("ºÒ°í±â", 8000);
	Menu bibimbap = new Menu("ºñºö¹ä", 6500);
	Menu japchae = new Menu("ÀâÃ¤", 7000);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
	
		Person p = LoginController.log_in_list.get(0);
		id.setText(p.getId());
		money.setText(p.getMoney());
		
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (listView.getItems().size() != 0) {
					listView.getItems().remove(listView.getItems().size()-1, listView.getItems().size());
				}
				listView.getItems().remove(0,listView.getItems().size());
				totalPrice = 0;
				total.setText("0");
				
			}
			
		});
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listView.getItems().add(kimbap.getFoodName().toString() +" \t\t\t\t\t\t" + kimbap.getPrice());
				kimbap.count += 1;
				totalPrice += kimbap.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
//				if (flag[0] == 0) {
//					
//					listView.getItems().add(kimbap.getFoodName());
//					flag[0] = 1;
//				}
				
				//¸Þ´º Áßº¹¾øÀ½
//				listView.getItems().remove("±è¹ä");
//				listView.getItems().remove("±è¹ä"+kimbap.count);
//				kimbap.count += 1;
//				String kb = kimbap.getFoodName().toString() + kimbap.count;
//				listView.getItems().add(kb);
//				System.out.println(listView.getItems().get(listView.getItems().size()));
				
				
				
			}
			
		});
		
		btn2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listView.getItems().add(kimchi.getFoodName().toString() +" \t\t\t\t\t\t" + kimchi.getPrice());
				kimchi.count += 1;
				totalPrice += kimchi.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
								
			}
			
		});
				
		btn3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listView.getItems().add(naengmyeon.getFoodName().toString() +" \t\t\t\t\t\t" + naengmyeon.getPrice());
				naengmyeon.count += 1;
				totalPrice += naengmyeon.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
	
			}
			
		});
		
		btn4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listView.getItems().add(tteok.getFoodName().toString() +"  \t\t\t\t\t\t\t" + tteok.getPrice());
				tteok.count += 1;
				totalPrice += tteok.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
				
			}
			
		});
		
		btn5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listView.getItems().add(tteokbokki.getFoodName().toString() +"\t\t\t\t\t\t" + tteokbokki.getPrice());
				tteokbokki.count += 1;
				totalPrice += tteokbokki.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
				
			}
			
		});
		
		btn6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listView.getItems().add(bossam.getFoodName().toString() +" \t\t\t\t\t\t" + bossam.getPrice());
				bossam.count += 1;
				totalPrice += bossam.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
				
			}
			
		});
		
		btn7.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listView.getItems().add(bulgogi.getFoodName().toString() +"\t\t\t\t\t\t" + bulgogi.getPrice());
				bulgogi.count += 1;
				totalPrice += bulgogi.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
				
			}
			
		});
		
		btn8.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listView.getItems().add(bibimbap.getFoodName().toString() +"\t\t\t\t\t\t" + bibimbap.getPrice());
				bibimbap.count += 1;
				totalPrice += bibimbap.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
				
			}
			
		});
		
		btn9.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listView.getItems().add(japchae.getFoodName().toString() +" \t\t\t\t\t\t" + japchae.getPrice());
				japchae.count += 1;
				totalPrice += japchae.getPrice();
				
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				
				
			}
			
		});
		
	}
	

	public void log_out_handler(Event e)
	{
		Stage stage=(Stage)id.getScene().getWindow();
		
				// TODO Auto-generated method stub
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle("·Î±×¾Æ¿ô");
				alert.setHeaderText("Á¤¸» ·Î±×¾Æ¿ô ÇÏ½Ã°Ú¾î¿ä?");
				alert.setContentText("Ok ¹öÆ° Å¬¸¯½Ã ·Î±×¾Æ¿ô µË´Ï´Ù.");
				Optional<ButtonType> result= alert.showAndWait();
				if(result.get()==ButtonType.OK)
				{
					LoginController.log_in_list.remove(0);
					stage.close();
					
					System.out.println(LoginController.log_in_list.size());
				}

				
			}

}


