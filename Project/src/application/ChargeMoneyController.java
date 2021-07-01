package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChargeMoneyController implements Initializable{
	@FXML Label money;
	@FXML TextField chargeMoney;
	@FXML Button chargeButton;
	FXMLLoader loader;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		money.setText(LoginController.log_in_list.get(0).getMoney());
		
	}
	
	public void handlerChargeButtonAction(ActionEvent e) {
		int leftMoney = Integer.parseInt(LoginController.log_in_list.get(0).getMoney());
		int cgMoney = Integer.parseInt(chargeMoney.getText());
		int tot = leftMoney + cgMoney;
		String totMoney = Integer.toString(tot);
		LoginController.log_in_list.get(0).setMoney(totMoney);
		money.setText(LoginController.log_in_list.get(0).getMoney());
		System.out.println(totMoney);
		loader=new FXMLLoader(getClass().getResource("orderSys.fxml"));
		try {
			Stage charge_stage=(Stage)chargeButton.getScene().getWindow();
			charge_stage.close();
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
