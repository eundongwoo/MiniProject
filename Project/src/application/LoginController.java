package application;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML private TextField id;
	@FXML private PasswordField pwd;
	@FXML private Button login_button;
	@FXML private Hyperlink register_button;
	Alert alert=new Alert(AlertType.INFORMATION);
	public static List<Person> log_in_list=new ArrayList<>(); //로그인을 한 사람을 담는 배열
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		login_button.setOnAction(e->btn_login_action(e));
		register_button.setOnAction(e->btn_register_action(e));
	}
	
	public void btn_login_action(Event e)
	{
		//System.out.println(RegisterController.member.contains(new Person(id.getText(),pwd.getText())));
		 FXMLLoader loader=new FXMLLoader(getClass().getResource("orderSys.fxml"));
		 Person person=new Person(id.getText(),pwd.getText());
		if(RegisterController.member.contains(person))
		{
			try {
				log_in_list.add(person);
				Parent p=loader.load();
				Stage stage=new Stage();
				stage.setTitle("주문");
				stage.setScene(new Scene(p));
				stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else
		{
			alert.setTitle("로그인 실패!!");
			alert.setHeaderText("다시 입력해주세요");
			alert.show();
		}
	}
	
	public void btn_register_action(Event e)
	{
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Register.fxml"));
		
		try {
			Parent p=loader.load();
			Stage stage=new Stage();
			stage.setTitle("회원가입");
			stage.setScene(new Scene(p)); 
			stage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
