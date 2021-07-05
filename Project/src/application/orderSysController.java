package application;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class orderSysController implements Initializable{
	@FXML private HBox menuBox; //왼쪽편 pane
	@FXML private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, payBtn, delete;
	@FXML private TextArea total;
	@FXML private TableView<TableRowData> tableView;
	@FXML private TableColumn<TableRowData, String> food;
	@FXML private TableColumn<TableRowData, Integer> foodPrice;
	@FXML private Button id,money;
	
	
	public static LinkedList<TableRowData> payData = new LinkedList<TableRowData>();// 결제창에서 목록을 띄워주기 위한 LinkedList 컬랙션 생성
	static int totalPrice =0;
	Menu[] menu = new Menu[9];
	public String[] foodName ={"김밥", "김치", "냉면", "떡", "떡볶이", "보쌈", "불고기", "비빔밥", "잡채"};
	public int[] Price = {2500, 1500, 5000, 3500, 3000, 20000, 8000, 6500, 7000};
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//사람의 이름과 금액을 id,money 버튼에 출력
		Person p = LoginController.log_in_list.get(0);
		id.setText(p.getId());
		money.setText(p.getMoney());
				
		// 음식버튼 초기화 및 클릭했을 경우 음식이름과 가격 tableView에 추가 payData에 음식이름과 가격을 삽입하여 결제창에서 출력용으로 사용
		Button[] btn = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
		for(int i=0; i<9; i++) {// 반복문으로 각 버튼에 ActionEvent 할당
			Menu menu = new Menu(foodName[i], Price[i]);// 각 버튼에 해당하는 메뉴의 이름과 가격 가져옴
			btn[i].setOnAction(e-> {// 각 버튼에 ActionEvent 할당
				tableView.getItems().add(new TableRowData(new SimpleStringProperty(menu.getFoodName()),
						new SimpleIntegerProperty(menu.getPrice())));// tableView의 각 TableColumn에 각 버튼에 해당하는 메뉴의 이름과 가격 출력
				payData.add(new TableRowData(new SimpleStringProperty(menu.getFoodName()), 
						new SimpleIntegerProperty(menu.getPrice())));// LinkedList 컬랙션에 각 버튼에 해당하는 메뉴의 이름과 가격 저장
				totalPrice += menu.getPrice();// 버튼을 누를 때 마다 totalPrice 값 누적
				String sum = Integer.toString(totalPrice);
				total.setText(sum);// TextArea에 totalPrice 값 출력
				tableView.scrollTo(payData.size());// 이용자가 누른 버튼에 해당하는 메뉴의 tableView로 스크롤 이동
			});
		}
		// CellValueFactorty를 매개값으로 받아 TableView의 각 TableColumn값을 세팅
		food.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		foodPrice.setCellValueFactory(cellData->cellData.getValue().priceProperty().asObject());
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {// tableView의 각 항목에 마우스를 클릭했을 때 이벤트 할당

			@Override
			public void handle(MouseEvent event) {//EventHandler 인터페이스의 handle 메소드 재정의
				
				int slctPoint = tableView.getSelectionModel().getSelectedIndex(); //클릭한 인덱스
				TableRowData slctData= tableView.getSelectionModel().getSelectedItem(); //클릭한 테이블
				if (slctData != null) {
					int dltPrice = slctData.priceProperty().getValue(); //클릭한 테이블의 가격데이터 
					tableView.getItems().remove(slctPoint); //테이블에서 클릭한 인덱스를 삭제
					totalPrice -= dltPrice;	//삭제한 인덱스의 가격만큼 결제금액 차감
					String sum = Integer.toString(totalPrice); //결제금액 String캐스팅	
					payData.remove(slctPoint);	//결제창 메뉴 삭제
					if (totalPrice == 0) {
						total.clear();	//총 금액 0을 지우기 위해서 TextArea 비우기
					}
					else {
						total.setText(sum);	//결제금액 출력	
					}				
				}
				tableView.getSelectionModel().clearSelection();
							
			}	
			
		});
		
		
		delete.setOnAction(new EventHandler<ActionEvent>() {// '전체 삭제 버튼'이벤트
			@Override
			public void handle(ActionEvent event) {// EventHandler 인터페이스의 handle 메소드 재정의
				
				payData.clear();// 결제창 메뉴 삭제
				tableView.getItems().remove(0, tableView.getItems().size());// tableView의 처음부터 tableView의 크기에 해당하는 tableView 항목 삭제
				totalPrice = 0;// 총 금액 0원으로 초기화
				total.setText("");			
			}	
			
		});
		
	}
	
	public void log_out_handler(Event e)// 로그아웃 버튼(id가 표시되는 버튼)에 이벤트 할당
	{
		
		Stage stage=(Stage)id.getScene().getWindow();// 현재 창을 얻어옴
		Alert alert=new Alert(AlertType.CONFIRMATION);// 새로운 CONFIRMATION 타입 알림창 생성
		alert.setTitle("로그아웃");
		alert.setHeaderText("정말 로그아웃 하시겠어요?");
		alert.setContentText("Ok 버튼 클릭시 로그아웃 됩니다.");
		Optional<ButtonType> result= alert.showAndWait();// 알림창을 띄운 뒤, 사용자의 선택을 기다림
		
		if(result.get()==ButtonType.OK)// OK 버튼을 눌렀을 때
		{
			orderSysController.payData.clear();// 주문 정보를 지움
			LoginController.log_in_list.remove(0);// log_in_list에 저장된 Person 객체 삭제
			stage.close();// 현재 창을 닫음
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));// "Login.fxml" 창 불러와서 loader 객체 생성
			try {
				Parent p=loader.load();
				Stage main_stage=new Stage();
				main_stage.setScene(new Scene(p));
				main_stage.show();// "Login.fxml" 창 띄우기
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		}	
		
				
	}
	
	
	public void handleChargeBtnAtcion(ActionEvent e) {// 충전버튼(잔액이 표시되는 버튼)에 이벤트 할당
		FXMLLoader loader=new FXMLLoader(getClass().getResource("ChargeMoney.fxml"));// "ChargeMoney.fxml" 파일 불러와서 loader 객체 생성
	 Stage charge_stage=(Stage)money.getScene().getWindow();// 현재 창을 얻어옴
		try {
			charge_stage.close();// 화면 전환을 위해 현재 창을 닫음
			Parent root = loader.load();
			Stage stage=new Stage(); 
			stage.setTitle("잔액충전");
			stage.setScene(new Scene(root));
			stage.show();// "ChargeMoney.fxml" 창 띄우기
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void handlePayBtnAction(Event e)//결제 버튼(현금 일러스트가 표시되는 버튼)에 이벤트 할당
	{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("pay.fxml"));// "pay.fxml" 파일 불러와서 loader 객체 생성
		Parent p;
		Stage main_stage=(Stage)payBtn.getScene().getWindow();// 현재 창을 얻어옴
		try {
			p = loader.load();
			Stage stage=new Stage();
			stage.setTitle("결제");
			stage.setScene(new Scene(p));
			stage.show();// "pay.fxml" 창 띄우기
			main_stage.close();// 화면 전환을 위해 현재 창을 닫음
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}


}

	
