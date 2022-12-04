package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller2 implements Initializable {
	@FXML
	Button change=new Button();
	@FXML
	Slider sliderR = new Slider();
	@FXML
	Slider sliderG = new Slider();
	@FXML
	Slider sliderB = new Slider();
	@FXML
	TextField TxtR = new TextField();
	@FXML
	TextField TxtG = new TextField();
	@FXML
	TextField TxtB = new TextField();
	@FXML
	Label lblH = new Label();
	@FXML
	Label lblS = new Label();
	@FXML
	Label lblV = new Label();
	@FXML
	Pane anc = new Pane();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}

}
