package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChargeMoneyController implements Initializable{
	@FXML Label money;
	@FXML TextField chargeMoney;

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
	}

}
