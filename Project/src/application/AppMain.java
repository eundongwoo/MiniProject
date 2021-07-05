package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application{
  
	FXMLLoader loader;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		loader=new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent p=loader.load();
		primaryStage.setScene(new Scene(p));
		primaryStage.setTitle("로그인");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Person person=new Person("admin", "admin");
		RegisterController.member.add(person);// member HashSet에 admin 계정 추가
		launch(args);
		
	}

}
   