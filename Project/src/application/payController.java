package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class payController implements Initializable{

	@FXML private TableView<TableRowData> payTable;
	@FXML private TableColumn<TableRowData, String> payFood;
	@FXML private TableColumn<TableRowData, Integer> payPrice;
	@FXML private Button cancel,payok;
	@FXML private TextField payTotal,id,money;
	int user_money,total_money=0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		id.setText(LoginController.log_in_list.get(0).getId());
		money.setText(LoginController.log_in_list.get(0).getMoney());
		
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
				 user_money=Integer.parseInt(money.getText());
				 total_money=Integer.parseInt(payTotal.getText());
				if(user_money<total_money)
				{
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setHeaderText("잔액이 부족합니다");
					alert.show();
					
				}else
				{
					user_money-=total_money;
					LoginController.log_in_list.get(0).setMoney(String.valueOf(user_money));
					Stage stage=(Stage)payok.getScene().getWindow();
					orderSysController.totalPrice=0;
					orderSysController.payData.clear();
					payTable.getItems().remove(0, payTable.getItems().size());
					payTotal.clear();
					stage.close();
					//메인창을 띄우고 로그아웃기능(로그인 화면) 구현
					main_page_load();
				}
			}
			
		});
		
		
	}
	
	public void handle_cancel()
	{
		Stage stage=(Stage)cancel.getScene().getWindow();
		orderSysController.totalPrice=0;
		orderSysController.payData.clear();
		payTable.getItems().remove(0, payTable.getItems().size());
		payTotal.clear();
		stage.close();
		
		main_page_load();
	}
	
	//메인창을 띄우고 로그아웃기능(로그인 화면) 구현
	public  void main_page_load()
	{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("orderSys.fxml"));
		try {
			Parent p=loader.load();
			Stage main_stage=new Stage();
			main_stage.setScene(new Scene(p));
			
			main_stage.setOnCloseRequest(e->{
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle("로그아웃");
				alert.setHeaderText("정말 로그아웃 하시겠어요?");
				alert.setContentText("Ok 버튼 클릭시 로그아웃 됩니다.");
				Optional<ButtonType> result= alert.showAndWait();
				if(result.get()==ButtonType.OK)
				{
					Person person=LoginController.log_in_list.get(0);
					orderSysController.save_money.put(person.getId(),person.getMoney());
					LoginController.log_in_list.remove(0);
					main_stage.close();
					FXMLLoader	login_page=new FXMLLoader(getClass().getResource("Login.fxml"));
				try {
					Parent p1=login_page.load();
					Stage login_stage=new Stage();
					login_stage.setScene(new Scene(p1));
					login_stage.show();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}else
				{
					e.consume();
				}
			});
			main_stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
