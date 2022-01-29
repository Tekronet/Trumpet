package com.tekronet.backend;
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.tekronet.ui.MainView;
 
public class Trumpet extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
       	MainView mv = new MainView();
       	mv.createUi(primaryStage);
    }

    public void openLink() {
        getHostServices().showDocument("https://tekronet.github.io");
    }
}
