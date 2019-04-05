package eg.edu.alexu.csd.oop.cs19;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class OptionsController {
	
	@FXML
	private Button applyBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private CheckBox noMusic;
	@FXML
	private ComboBox<String> musicCombo;

	private static Stage primaryStage;
	private static Scene scene;
	private StartController start;
	
	public void startController(Stage stage, Scene sc) {
		primaryStage = stage;
		scene = sc;
		this.start = new StartController();
	}
	
	public void applyBtnPressed() {
		
	}
	
	public void cancelBtnPressed() {
		
		System.out.println("Cancel button clicked !!");
		try {
			
			// loading the start.fxml file and attach it to the root
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("views" + System.getProperty("file.separator") + "start.fxml"));
			
			Parent root = loader.load();
			scene.setRoot(root);
			
			this.start.startControl(primaryStage, scene);
			
		} catch (Exception e) {

		}
		
	}
}
