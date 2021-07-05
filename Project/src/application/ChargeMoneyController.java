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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ChargeMoneyController implements Initializable{
	@FXML Label money;
	@FXML TextField chargeMoney;
	@FXML Button chargeButton;
	FXMLLoader loader;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		money.setText(LoginController.log_in_list.get(0).getMoney());// 현재 로그인 한 Person 객체에서 money를 얻어와 Label에 출력
		chargeMoney.setText("0");
	}
	
	public void handlerChargeButtonAction(ActionEvent e) {// '충전하기 버튼' 이벤트 할당
		orderSysController.payData.clear();// 결제창의 결제 정보 초기화
		int leftMoney = Integer.parseInt(LoginController.log_in_list.get(0).getMoney());
		int cgMoney = Integer.parseInt(chargeMoney.getText());//TextField에서 충전하고자 하는 금액 얻어옴
		int tot = leftMoney + cgMoney;// 잔액에 충전금액 합산
		String totMoney = Integer.toString(tot);
		LoginController.log_in_list.get(0).setMoney(totMoney);// 현재 로그인 한 Person 객체의 money 정보 업데이트
		money.setText(LoginController.log_in_list.get(0).getMoney());// 현재 로그인 한 Person 객체에서 money를 얻어와 Label에 출력
		
		//"orderSys.fxml" Stage를 다시 표시할 때 로그아웃 가능 하게 구현
		loader=new FXMLLoader(getClass().getResource("orderSys.fxml"));
		try {
			Stage charge_stage=(Stage)chargeButton.getScene().getWindow();// 현재 창을 얻어옴
			charge_stage.close();// 현재 창을 닫음
			Parent p=loader.load();// "orderSys.fxml" 파일을 얻어와 Stage 생성
			Stage main_stage=new Stage();
			main_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {// "orderSys.fxml" Stage를 닫았을 때 이벤트 할당
				
				@Override
				public void handle(WindowEvent event) {
					Alert alert=new Alert(AlertType.CONFIRMATION);// 새로운 CONFIRMATION 타입 알림창 생성
					alert.setTitle("로그아웃");
					alert.setHeaderText("정말 로그아웃 하시겠어요?");
					alert.setContentText("Ok 버튼 클릭시 로그아웃 됩니다.");
					Optional<ButtonType> result= alert.showAndWait();// 알림창을 띄운 뒤, 사용자의 선택을 기다림
					if(result.get()==ButtonType.OK)// OK 버튼을 눌렀을 때
					{
						Person person=LoginController.log_in_list.get(0);
						orderSysController.save_money.put(person.getId(),person.getMoney());
						LoginController.log_in_list.remove(0);// 주문 정보를 지움
						main_stage.close();// "orderSys.fxml" Stage를 닫음
					}else
					{
						event.consume();// OK 버튼 이외의 버튼을 눌렀을 때 if문을 빠져나옴
					}
				}
			});
			main_stage.setScene(new Scene(p));
			main_stage.show();// "orderSys.fxml" Stage를 새로 엶
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
