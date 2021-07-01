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
	Alert alert=new Alert(AlertType.INFORMATION);
	public static Set<Person> member=new HashSet<Person>(); //사람을 담는 HashSet
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void register_action(Event e)
	{
		
		Stage stage=(Stage)register_button.getScene().getWindow();
		for(Person p:member)
		{
			if(!p.getId().equals(id.getText()))
			{
				Person person=new Person(id.getText(), pwd.getText(), name.getText());
			//	person.print(); //지워야 하는 코드
				member.add(person);
				stage.close();
				alert.setTitle("회원가입 성공");
				alert.setHeaderText("회원이 되신것을 축하합니다.");
				alert.show();
				person.member_info();
				break;
			}
			else
			{
				alert.setHeaderText("이미 있는 아이디 입니다..");
				alert.show();
				break;
			}
		}
		
	}
	
	public void check_action(Event e)
	{
		for(Person p:member)
		{
			if(p.getId().equals(id.getText()))
			{
				alert.setHeaderText("이미 있는 아이디 입니다.(다시 입력해주세요)");
				alert.show();
				break;
			}else
			{
				alert.setHeaderText("사용가능한 아이디입니다.");
				alert.show();
				break;
			}
		}
	}
	
	
	

}
