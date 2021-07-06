package application;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController implements Initializable {

	@FXML private TextField id;
	@FXML private PasswordField pwd;
	@FXML private Button login_button;
	@FXML private Hyperlink register_button;
	Alert alert=new Alert(AlertType.INFORMATION);
	public static List<Person> log_in_list=new ArrayList<>(); //현재 로그인 한 사람 리스트 생성
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		login_button.setOnAction(e->btn_login_action(e));
		register_button.setOnAction(e->btn_register_action(e));
	}
	
	public void btn_login_action(Event e)// 'login 버튼'이벤트 
	{
		
		 Person person=new Person(id.getText(),pwd.getText());// id 텍스트 필드와 password 패스워드 필드에 입력된 정보로 Person 객체 생성
		if(RegisterController.member.contains(person))// member HashSet에 로그인 과정에서 생성된 Person 객체와 같은 객체가 있는지 확인
		{
			if(person.getId().equals("admin"))
			{
				try {
					FXMLLoader admin=new FXMLLoader(getClass().getResource("Admin.fxml"));
					
					Parent p = admin.load();
					Stage stage=new Stage();
					stage.setScene(new Scene(p));
					stage.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else
			{
				 FXMLLoader loader=new FXMLLoader(getClass().getResource("orderSys.fxml"));// "orderSys.fxml" 파일 불러와서 loader 객체 생성
				try { // 같은 객체가 있을 때, 아래의 동작 수행
					if(orderSysController.save_money.containsKey(person.getId()))
					{
						String save_money=orderSysController.save_money.get(person.getId()); 
						person.setMoney(save_money);					
					}
					log_in_list.add(person);// log_in_list에 현재 로그인중인 Person 객체 추가
					Parent p=loader.load();//loader 객체로 컨테이너 생성 후 장면 생성
					Stage stage=new Stage();
					stage.setTitle("주문");
					stage.setScene(new Scene(p));
					
					stage.setOnCloseRequest(new EventHandler<WindowEvent>() {// "orderSys.fxml"창이 닫힐 때 이벤트 추가
						
						@Override
						public void handle(WindowEvent event) {
							Alert alert=new Alert(AlertType.CONFIRMATION);// 새로운 CONFIRMATION 타입 알림창 생성
							alert.setTitle("로그아웃");
							alert.setHeaderText("정말 로그아웃 하시겠어요?");
							alert.setContentText("Ok 버튼 클릭시 로그아웃 됩니다.");
							Optional<ButtonType> result= alert.showAndWait();// 알림창을 띄운 뒤, 사용자의 선택을 기다림
							
							if(result.get()==ButtonType.OK)// OK 버튼을 눌렀을 때
							{
								orderSysController.payData.clear();// 주문 정보를 지움
								Person person=LoginController.log_in_list.get(0);
								orderSysController.save_money.put(person.getId(),person.getMoney());
								LoginController.log_in_list.remove(0);// log_in_list에 저장된 Person 객체 삭제
								stage.close();// "orderSys.fxml"창 닫음
								FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));// "Login.fxml" 창 불러와서 loader 객체 생성
								try {
									Parent p=loader.load();
									Stage main_stage=new Stage();
									main_stage.setScene(new Scene(p));
									main_stage.show();// "Login.fxml" 창 띄우기
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}else
							{
								event.consume();// OK 버튼 이외의 버튼을 눌렀을 때 if문을 빠져나옴
							}
							
						}
						
					});
					
					stage.show();// *"orderSys.fxml" 파일로 만든 Stage 열기
					Stage login_stage=(Stage)register_button.getScene().getWindow();//"register_button"에 연결된 창(로그인 창) 불러오기
					login_stage.close();// 로그인 창 닫기
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			
			
		}else // 같은 객체가 없을 때
		{
			alert.setTitle("로그인 실패!!");// 로그인 실패 제목, 텍스트 설정 후 알림창 출력
			alert.setHeaderText("다시 입력해주세요");
			alert.show();
		}
	}
	
	public void btn_register_action(Event e)//'가입하기 하이퍼텍스트' 이벤트
	{
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Register.fxml"));// "Register.fxml" 창 불러와서 loader 객체 생성
		
		try {
			Parent p=loader.load();//loader 객체로 컨테이너 생성 후 Stage 생성
			Stage stage=new Stage();
			stage.setTitle("회원가입");
			stage.setScene(new Scene(p)); 
			stage.show();//"Register.fxml" 창으로 생성된 Stage 출력
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
