package com.tekronet.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import java.io.*;

//layouts
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

//widgets
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.tekronet.backend.Playlist;

public class MainView {
	public void createUi(Stage stage) {
		stage.setTitle("Trumpet");
		AnchorPane root = new AnchorPane();
		Scene mainScene = new Scene(root, 800, 500);
		String stylesheet = this.getClass().getResource("main.css").toExternalForm();
		mainScene.getStylesheets().add(stylesheet);
		stage.setScene(mainScene);
		stage.show();
		
		Playlist playlist = new Playlist();	
		Font.loadFont(MainView.class.getResource("res/Lato-Regular.ttf").toExternalForm(), 10);
		
		//menuplayback
		Menu menuPlayback = new Menu("Playback");
		MenuItem menuPlaybackPlay = new MenuItem("Play");
		MenuItem menuPlaybackPause = new MenuItem("Pause");
		MenuItem menuPlaybackNextSong = new MenuItem("Next song");
		MenuItem menuPlaybackPrevSong = new MenuItem("Previous song");
		menuPlaybackPlay.getStyleClass().add("menuitem");
		menuPlaybackPause.getStyleClass().add("menuitem");
		menuPlaybackNextSong.getStyleClass().add("menuitem");
		menuPlaybackPrevSong.getStyleClass().add("menuitem");
		menuPlayback.getItems().addAll(menuPlaybackPlay, menuPlaybackPause, menuPlaybackNextSong, menuPlaybackPrevSong);
		
		//menu help
		Menu menuHelp = new Menu("Help");
		MenuItem menuHelpAbout = new MenuItem("About Trumpet");
		menuHelpAbout.getStyleClass().add("menuitem");
		menuHelp.getItems().addAll(menuHelpAbout);
		
		//menu file
		Menu menuFile = new Menu("File");
		MenuItem menuFileOpen = new MenuItem("Open");
		MenuItem menuFileClose = new MenuItem("Close");
		MenuItem menuFileClear = new MenuItem("Clear playlist");
		menuFile.getItems().addAll(menuFileOpen, menuFileClose, menuFileClear);
		
		//menubar
		MenuBar menubar = new MenuBar();
		menubar.getStyleClass().add("menubar");

		menubar.getMenus().addAll(menuFile, menuPlayback, menuHelp);
		AnchorPane.setLeftAnchor(menubar, 0.0);
		AnchorPane.setRightAnchor(menubar, 0.0);
		
		//quick control bar
		HBox quickMenu = new HBox();
		quickMenu.getStyleClass().add("quick-menu");
		
		AnchorPane.setLeftAnchor(quickMenu, 0.0);
		AnchorPane.setRightAnchor(quickMenu, 0.0);
		AnchorPane.setBottomAnchor(quickMenu, 0.0);
		
		//icons 
		Image iPlayImage = new Image(this.getClass().getResource("res/play.png").toExternalForm());
		ImageView vPlayImage = new ImageView(iPlayImage);
		vPlayImage.setFitHeight(65);
		vPlayImage.setFitWidth(65);
		
		Image iPauseImage = new Image(this.getClass().getResource("res/pause.png").toExternalForm());
		ImageView vPauseImage = new ImageView(iPauseImage);
		vPauseImage.setFitHeight(65);
		vPauseImage.setFitWidth(65);
		
		Image iPrevImage = new Image(this.getClass().getResource("res/skipBack.png").toExternalForm());
		ImageView vPrevImage = new ImageView(iPrevImage);
		vPrevImage.setFitHeight(65);
		vPrevImage.setFitWidth(65);
		
		Image iNextImage = new Image(this.getClass().getResource("res/skipForward.png").toExternalForm());
		ImageView vNextImage = new ImageView(iNextImage);
		vNextImage.setFitHeight(65);
		vNextImage.setFitWidth(65);
		
		
		quickMenu.getChildren().addAll(vPlayImage, vPauseImage, vNextImage, vPrevImage);
		
		//song list
		ListView<String> songList = new ListView<String>();
		songList.getStyleClass().add("song-list");
		AnchorPane.setLeftAnchor(songList, 0.0);
		AnchorPane.setRightAnchor(songList, 0.0);
		AnchorPane.setTopAnchor(songList, 27.0);
		AnchorPane.setBottomAnchor(songList, 70.0);
		
		root.getChildren().addAll(menubar, quickMenu, songList);
		
		//events
		menuFileOpen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {playlist.addSongs(songList); playlist.getHashMap();}
		});
		menuFileClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {playlist.removeSong(songList); playlist.getHashMap();}
		});
		menuFileClear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {playlist.clearPlaylist(songList);}
		});
		
	}
}
