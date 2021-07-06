package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

	@FXML private TextField id;
	@FXML private PasswordField pwd;
	@FXML private TextField name;
	@FXML private Button register_button;
	@FXML private Button check_button;
	Alert alert=new Alert(AlertType.INFORMATION); // 회원가입 안내 알림창 생성
	public static Set<Person> member=new HashSet<Person>(); //회원 목록을 담는 HashSet 생성
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { // Initializable 인터페이스의 initiazlize 메소드 재정의
		
	}
	
	public void register_action(Event e) // '회원가입 버튼' 이벤트 
	{
		
		Stage stage=(Stage)register_button.getScene().getWindow(); // 현재 열려있는 Stage를 얻어옴		
		if(!member.contains(id.getText()))
		{
			Person person=new Person(id.getText(), pwd.getText(), name.getText()); 
			member.add(person); //중복되는 id가 없을 경우 member HashSet에 Person 객체 추가
			stage.close();
			alert.setTitle("회원가입 성공");
			alert.setHeaderText("회원이 되신것을 축하합니다.");
			alert.show();
		}else
		{
			alert.setHeaderText("이미 있는 아이디 입니다.."); //중복되는 id가 있을 경우 경고창 출력
			alert.show();
		}
		
	}
	
	public void check_action(Event e)// '중복체크 버튼' 이벤트 
	{
		
		if(member.contains(new Person(id.getText())))
		{
			alert.setHeaderText("이미 있는 아이디 입니다.(다시 입력해주세요)");//중복되는 id가 있을 경우 경고창 출력
			alert.show();
		}else
		{
			alert.setHeaderText("사용가능한 아이디입니다.");//중복되는 id가 없을 경우 id 사용 가능 안내창 출력
			alert.show();
		}
	}
	
	}
	


