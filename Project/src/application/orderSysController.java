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
	//@FXML private ImageView sign; //간판
	//@FXML private ListView<String> listView;
	@FXML private TextArea total;
	@FXML private TableView<TableRowData> tableView;
	@FXML private TableColumn<TableRowData, String> food;
	@FXML private TableColumn<TableRowData, Integer> foodPrice;
	@FXML private Button id,money;
	
	
	public static LinkedList<TableRowData> payData = new LinkedList<TableRowData>();
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
		
		//음식버튼 초기화 및 클릭했을 경우 음식이름과 가격 tableView에 추가 payData에 음식이름과 가격을 삽입하여 결제창에서 출력용으로 사용
		Button[] btn = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
		for(int i=0; i<9; i++) {
			Menu menu = new Menu(foodName[i], Price[i]);
			btn[i].setOnAction(e-> {
				tableView.getItems().add(new TableRowData(new SimpleStringProperty(menu.getFoodName()), 
						new SimpleIntegerProperty(menu.getPrice())));
				payData.add(new TableRowData(new SimpleStringProperty(menu.getFoodName()), 
						new SimpleIntegerProperty(menu.getPrice())));
				totalPrice += menu.getPrice();
				String sum = Integer.toString(totalPrice);
				total.setText(sum);
				tableView.scrollTo(payData.size());
			});
		}
		
		
		food.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		foodPrice.setCellValueFactory(cellData->cellData.getValue().priceProperty().asObject());
		
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
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
		
		
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				payData.clear();
				tableView.getItems().remove(0, tableView.getItems().size());
				totalPrice = 0;
				total.setText("");			
			}	
			
		});
		
	}
	
	public void log_out_handler(Event e)
	{
		
		Stage stage=(Stage)id.getScene().getWindow();
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("로그아웃");
		alert.setHeaderText("정말 로그아웃 하시겠어요?");
		alert.setContentText("Ok 버튼 클릭시 로그아웃 됩니다.");
		Optional<ButtonType> result= alert.showAndWait();
		if(result.get()==ButtonType.OK)
		{
			orderSysController.payData.clear();
			LoginController.log_in_list.remove(0);
			stage.close();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
			try {
				Parent p=loader.load();
				Stage main_stage=new Stage();
				main_stage.setScene(new Scene(p));
				main_stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}	
		
				
	}
	
	
	public void handleChargeBtnAtcion(ActionEvent e) {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("ChargeMoney.fxml"));
	 Stage charge_stage=(Stage)money.getScene().getWindow();
		try {
			charge_stage.close();
			Parent root = loader.load();
			Stage stage=new Stage(); 
			stage.setTitle("잔액충전");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void handlePayBtnAction(Event e)
	{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("pay.fxml"));
		Parent p;
		Stage main_stage=(Stage)payBtn.getScene().getWindow();
		try {
			p = loader.load();
			Stage stage=new Stage();
			stage.setTitle("결제");
			stage.setScene(new Scene(p));
			stage.show();
			main_stage.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}


}

	
