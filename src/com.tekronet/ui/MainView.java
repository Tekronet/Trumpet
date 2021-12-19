package com.tekronet.ui;

//required imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//layouts
import javafx.scene.layout.AnchorPane;

//widgets
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainView {
	public void createUi(Stage stage) {
		stage.setTitle("Trumpet");
		
		AnchorPane root = new AnchorPane();
		Scene mainScene = new Scene(root, 800, 500);
		stage.setScene(mainScene);
		stage.show();
		
		MenuBar menubar = new MenuBar();
		
		Menu menuFile = new Menu("File");
		Menu menuPlayback = new Menu("Playback");
		Menu menuPlaylist = new Menu("Playlist");
		Menu menuHelp = new Menu("Help");
				
		menubar.getMenus().addAll(menuFile, menuPlayback, menuPlaylist, menuHelp);
		AnchorPane.setLeftAnchor(menubar, 0.0);
		AnchorPane.setRightAnchor(menubar, 0.0);
		
		root.getChildren().addAll(menubar);
	}
}
