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
		
		id.setText(LoginController.log_in_list.get(0).getId());// "log_in_list"에서 현재 로그인 중인 Person의 id와 money 얻어옴
		money.setText(LoginController.log_in_list.get(0).getMoney());
		
		// CellValueFactorty를 매개값으로 받아 TableView의 각 TableColumn값을 세팅
		payFood.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		payPrice.setCellValueFactory(cellData->cellData.getValue().priceProperty().asObject());
		
		for (TableRowData t: orderSysController.payData) {
			payTable.getItems().add(t);// "orderSysController"에서 저장된 payData에서 주문 목록을 가져옴
		}
		
		String sum = Integer.toString(orderSysController.totalPrice);// "orderSysController"에서 저장된 totalPrice를 가져옴
		if(orderSysController.totalPrice == 0) {// 총액이 0일 경우 TextField를 지움
			payTotal.clear();
		} 
		else {
			payTotal.setText(sum);// 총액이 0이 아닐 경우, 총액을 String으로 변환하여 TextField에 출력
		}
		
		payok.setOnAction(new EventHandler<ActionEvent>() {// 결제창의 '확인 버튼' 이벤트 처리
			
			@Override
			public void handle(ActionEvent event) {
				 user_money=Integer.parseInt(money.getText());
				 total_money=Integer.parseInt(payTotal.getText());// 로그인 된 Person의 Money와 주문 총액을 Integer로 변환
				if(user_money<total_money)// Person의 Money가 주문 총액보다 적을 경우
				{
					Alert alert=new Alert(AlertType.INFORMATION);// Alert 생성 후 출력
					alert.setHeaderText("잔액이 부족합니다");
					alert.show();
					
				}else
				{
					user_money-=total_money;// Person의 Money에서 총액만큼 차감
					LoginController.log_in_list.get(0).setMoney(String.valueOf(user_money));// Person의 Money 재조정하여 객체에 업데이트
					Stage stage=(Stage)payok.getScene().getWindow();// 현재 창을 얻어옴
					orderSysController.totalPrice=0;
					orderSysController.payData.clear();// 총액과 주문 목록을 지움
					payTable.getItems().remove(0, payTable.getItems().size());// tableView의 처음부터 tableView의 크기에 해당하는 tableView 항목 삭제
					payTotal.clear();
					stage.close();// 현재 창 닫음
					
					main_page_load();// "orderSys.fxml" 창을 띄운 뒤 로그아웃 기능 구현
				}
			}
			
		});
		
		
	}
	
	public void handle_cancel()// 결제창의 '취소 버튼' 이벤트 처리
	{
		Stage stage=(Stage)cancel.getScene().getWindow();// 현재 창을 얻어옴
		orderSysController.totalPrice=0;
		orderSysController.payData.clear();// 총액과 주문 목록을 지움
		payTable.getItems().remove(0, payTable.getItems().size());// tableView의 처음부터 tableView의 크기에 해당하는 tableView 항목 삭제
		payTotal.clear();
		stage.close();// 현재 창 닫음
		
		main_page_load();// "orderSys.fxml" 창을 띄운 뒤 로그아웃 기능 구현
	}
	
	//메인창을 띄우고 로그인 화면으로 돌아가는(로그아웃) 기능을 구현한 메소드
	public  void main_page_load()
	{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("orderSys.fxml"));// "orderSys.fxml" 파일 불러와서 loader 객체 생성
		try {
			Parent p=loader.load();//loader 객체로 컨테이너 생성 후 장면 생성
			Stage main_stage=new Stage();
			main_stage.setScene(new Scene(p));
			
			main_stage.setOnCloseRequest(e->{// "orderSys.fxml"창이 닫힐 때 이벤트 추가
				Alert alert=new Alert(AlertType.CONFIRMATION);// 새로운 CONFIRMATION 타입 알림창 생성
				alert.setTitle("로그아웃");
				alert.setHeaderText("정말 로그아웃 하시겠어요?");
				alert.setContentText("Ok 버튼 클릭시 로그아웃 됩니다.");
				Optional<ButtonType> result= alert.showAndWait();// 알림창을 띄운 뒤, 사용자의 선택을 기다림
				if(result.get()==ButtonType.OK)// OK 버튼을 눌렀을 때
				{

					Person person=LoginController.log_in_list.get(0);
					orderSysController.save_money.put(person.getId(),person.getMoney());
					LoginController.log_in_list.remove(0);// log_in_list에 저장된 Person 객체 삭제
					main_stage.close();// "orderSys.fxml"창 닫음
					FXMLLoader	login_page=new FXMLLoader(getClass().getResource("Login.fxml"));// "Login.fxml" 창 불러와서 loader 객체 생성

				try {
					Parent p1=login_page.load();
					Stage login_stage=new Stage();
					login_stage.setScene(new Scene(p1));
					login_stage.show();// "Login.fxml" 창 띄우기
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					
				}else
				{
					e.consume();// OK 버튼 이외의 버튼을 눌렀을 때 if문을 빠져나옴
				}
			});
			main_stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
