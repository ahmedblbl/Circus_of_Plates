package eg.edu.alexu.csd.oop.cs19;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartController {

	@FXML
	private Pane startPane;
	@FXML
	private VBox vBoxStartBtns;
	@FXML
	private Button newGameBtn;
	@FXML
	private Button optionsBtn;
	@FXML
	private Button exitBtn;

	private static Stage primaryStage;
	private static Scene scene;
	private OptionsController options;
	private DifficultyController difficulty;

	/**
	 * gets the primary stage to be used afterwards.
	 * 
	 * @param stage
	 *            the primary stage.
	 */
	public void startControl(Stage stage, Scene sc) {
		primaryStage = stage;
		scene = sc;
		this.options = new OptionsController();
		this.difficulty = new DifficultyController();
		this.options.startController(primaryStage, scene);
		this.difficulty.startController(primaryStage, scene);
	}

	public void optionsBtnPressed() {

		System.out.println("Otions button clicked !!");
		try {
			
			// loading the start.fxml file and attach it to the root
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("views" + System.getProperty("file.separator") + "options.fxml"));
			
			Parent root = loader.load();
			scene.setRoot(root);
			
		} catch (Exception e) {

		}
		
	}
	
	public void newGameBtnPressed() {
		
		System.out.println("new game button clicked !!");
		try {
			
			// loading the start.fxml file and attach it to the root
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("views" + System.getProperty("file.separator") + "difficulty.fxml"));
			
			Parent root = loader.load();
			scene.setRoot(root);
			
		} catch (Exception e) {

		}
		
	}
	
	public void exitBtnPressed() {
		
		primaryStage.close();
		
	}

}
