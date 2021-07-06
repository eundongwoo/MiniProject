package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminController implements Initializable {

	@FXML private Button search;
	@FXML private TableView<UserTableRowData> tableView;
	@FXML private TableColumn<UserTableRowData, String> id;
	@FXML private TableColumn<UserTableRowData, String> pwd;
	@FXML private TableColumn<UserTableRowData, String> name;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		id.setCellValueFactory(cellData->cellData.getValue().idProperty());
		pwd.setCellValueFactory(cellData->cellData.getValue().pwdProperty());
		name.setCellValueFactory(cellData->cellData.getValue().nameProperty());
		Iterator<Person> iter=RegisterController.member.iterator();
		while(iter.hasNext())
		{
			Person p=iter.next();
			tableView.getItems().add(new UserTableRowData(new SimpleStringProperty(p.getId()), 
					new SimpleStringProperty(p.getPwd()),new SimpleStringProperty(p.getName())));
		}
		
	}

}
