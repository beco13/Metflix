package com.metflix;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);//, 440, 440);
        
        primaryStage.setScene(scene);
        //primaryStage.getIcons().add(new Image("img/icon_magic.png"));
        primaryStage.setResizable(false);
        //primaryStage.minWidthProperty().bind(scene.heightProperty().multiply(2));
        //primaryStage.minHeightProperty().bind(scene.widthProperty().divide(2));
        
        primaryStage.show();
                
	}

	public static void main(String[] args) {
		launch(args);
	}
}
