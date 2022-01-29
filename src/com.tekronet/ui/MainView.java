package com.tekronet.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import java.io.*;
import java.util.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.scene.layout.Region;
import javafx.scene.control.*;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;
import javafx.geometry.Orientation;

import com.tekronet.backend.Control;
import com.tekronet.backend.Sliders;
import com.tekronet.ui.Icons;
import com.tekronet.ui.HelpView;

public class MainView {	
	
	ListView<String> songList;
	public static Label timeLabel;
	public void createUi(Stage stage) {
		stage.setTitle("Trumpet");
		AnchorPane root = new AnchorPane();
		Scene mainScene = new Scene(root, 800, 500);
		String stylesheet = this.getClass().getResource("main.css").toExternalForm();
		mainScene.getStylesheets().add(stylesheet);
		stage.setScene(mainScene);
		stage.setMinWidth(550);
		stage.setMinHeight(250);
		stage.show();

		Control control = new Control();
		Font.loadFont(MainView.class.getResource("res/Lato-Regular.ttf").toExternalForm(), 10);

		Menu menuPlayback = new Menu("Playback");
		MenuItem menuPlaybackPlay = new MenuItem("Play/Pause");
		MenuItem menuPlaybackStop = new MenuItem("Stop");
		MenuItem menuPlaybackNextSong = new MenuItem("Next song");
		MenuItem menuPlaybackPrevSong = new MenuItem("Previous song");
		
		menuPlayback.getItems().addAll(menuPlaybackPlay, menuPlaybackStop, menuPlaybackNextSong, menuPlaybackPrevSong);
		
		//menu help
		Menu menuHelp = new Menu("Help");
		MenuItem menuHelpAbout = new MenuItem("About Trumpet");
		menuHelp.getItems().addAll(menuHelpAbout);
		
		//menu file
		Menu menuFile = new Menu("File");
		MenuItem menuFileOpen = new MenuItem("Open");
		MenuItem menuFileClose = new MenuItem("Close");
		MenuItem menuFileClear = new MenuItem("Clear playlist");
		menuFile.getItems().addAll(menuFileOpen, menuFileClose, menuFileClear);

		Menu menuAudio = new Menu("Audio");
		MenuItem menuAudioVolumeUp = new MenuItem("Volume +10%");
		MenuItem menuAudioVolumeDown = new MenuItem("Volume -10%");
		SeparatorMenuItem menuAudioSep = new SeparatorMenuItem();
		MenuItem menuAudioSpeedUp = new MenuItem("Speed +10%");
		MenuItem menuAudioSpeedDown = new MenuItem("Speed -10%");
		MenuItem menuAudioSpeedReset = new MenuItem("Reset speed");
		menuAudio.getItems().addAll(
		menuAudioVolumeUp, menuAudioVolumeDown, menuAudioSep, menuAudioSpeedUp, menuAudioSpeedDown, menuAudioSpeedReset);
		
		//menubar
		MenuBar menubar = new MenuBar();
		menubar.getStyleClass().add("menubar");

		menubar.getMenus().addAll(menuFile, menuPlayback, menuAudio, menuHelp);
		AnchorPane.setLeftAnchor(menubar, 0.0);
		AnchorPane.setRightAnchor(menubar, 0.0);
		
		//quick control bar
		AnchorPane quickControlMenu = new AnchorPane();
		AnchorPane.setBottomAnchor(quickControlMenu, 0.0);
		AnchorPane.setRightAnchor(quickControlMenu, 0.0);
		AnchorPane.setLeftAnchor(quickControlMenu, 0.0);
		quickControlMenu.setMinHeight(80);
		
		//icons
		HBox quickControl = new HBox();
		quickControl.setSpacing(20.0);
		AnchorPane.setTopAnchor(quickControl, 10.0);
		AnchorPane.setLeftAnchor(quickControl, 12.0);

		SVGPath pplay = Icons.createPath(Icons.PLAY);
		SVGPath ppause = Icons.createPath(Icons.PAUSE);
		SVGPath pnext = Icons.createPath(Icons.NEXT);
		SVGPath pprev = Icons.createPath(Icons.PREVIOUS);
		SVGPath pstop = Icons.createPath(Icons.STOP);

		Region play = Icons.createRegion(pplay, 25, 30);
		Region next = Icons.createRegion(pnext, 32, 30);
		Region prev = Icons.createRegion(pprev, 32, 30);
		Region stop = Icons.createRegion(pstop, 30, 30);

		quickControl.getChildren().addAll(play, stop, prev, next);

		//sliders
		Slider progressBar = new Slider();
		AnchorPane.setLeftAnchor(progressBar, 10.0);
		AnchorPane.setRightAnchor(progressBar, 10.0);
		AnchorPane.setBottomAnchor(progressBar, 10.0);
		
		Slider volumeSlider = new Slider();
		volumeSlider.setMin(0);
		volumeSlider.setMax(100);
		volumeSlider.setValue(100);
		AnchorPane.setLeftAnchor(volumeSlider, 215.0);
		AnchorPane.setTopAnchor(volumeSlider, 30.0);

		Label volumeLabel = new Label("Volume");
		volumeLabel.getStyleClass().add("label");
		AnchorPane.setLeftAnchor(volumeLabel, 220.0);
		AnchorPane.setTopAnchor(volumeLabel, 8.0);

		
		AnchorPane statusPanel = new AnchorPane();
		statusPanel.setMinWidth(265);
		AnchorPane.setRightAnchor(statusPanel, 0.0);
		AnchorPane.setLeftAnchor(statusPanel, 365.0);

		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		separator.setPrefHeight(50);
		AnchorPane.setLeftAnchor(separator, 0.0);

		Label filenameLabel = new Label("Filename");
		filenameLabel.getStyleClass().add("label");
		AnchorPane.setLeftAnchor(filenameLabel, 10.0);
		AnchorPane.setTopAnchor(filenameLabel, 4.0);

		timeLabel = new Label("0:00/0:00");
		timeLabel.getStyleClass().add("label");
		AnchorPane.setLeftAnchor(timeLabel, 10.0);
		AnchorPane.setTopAnchor(timeLabel, 25.0);

		statusPanel.getChildren().addAll(separator, filenameLabel, timeLabel);

		quickControlMenu.getChildren().addAll(
		progressBar, volumeSlider, quickControl, volumeLabel, statusPanel);
				
		//song list
		songList = new ListView<String>();
		songList.getStyleClass().add("song-list");
		AnchorPane.setLeftAnchor(songList, 0.0);
		AnchorPane.setRightAnchor(songList, 0.0);
		AnchorPane.setTopAnchor(songList, 27.0);
		AnchorPane.setBottomAnchor(songList, 90.0);

		root.getChildren().addAll(menubar, quickControlMenu, songList);

		//events
		menuFileOpen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {control.openFiles(songList);}
		});

		menuFileClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {control.closeSong(songList); play.setShape(pplay);}
		});
		menuFileClear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {control.clearPlaylist(songList);}
		});
		menuPlaybackPlay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				control.playPause();
				if (control.playing) 
					play.setShape(ppause);
				else
					play.setShape(pplay);
			}
		});
		menuPlaybackStop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				control.stop();
				play.setShape(pplay);
			}
		});
		
		songList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				try {
					control.createMediaPlayer(songList, progressBar, volumeSlider, filenameLabel);
					play.setShape(ppause);
				}
				catch (Exception ex) {
					System.out.println("Debug: clicked on listview but no files opened");
				}
			}
		});
		menuPlaybackNextSong.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {control.nextSong(songList, progressBar, volumeSlider, filenameLabel);}
		});
		menuPlaybackPrevSong.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {control.prevSong(songList, progressBar, volumeSlider, filenameLabel);}
		});

		play.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent e) {
				if (control.selected)
					control.playPause();
				else 
					control.createMediaPlayer(songList, progressBar, volumeSlider, filenameLabel);

				if (control.playing)
					play.setShape(ppause);
				else
					play.setShape(pplay);
			}
		});

		stop.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				control.stop();
				play.setShape(pplay);
			}
		});

		next.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				control.nextSong(songList, progressBar, volumeSlider, filenameLabel);
			}
		});

		prev.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				control.prevSong(songList, progressBar, volumeSlider, filenameLabel);
			}
		});

		menuAudioVolumeUp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (Control.mediaPlayer.getVolume() < 100) {
					Control.mediaPlayer.setVolume(Control.mediaPlayer.getVolume() + 0.1);
					volumeSlider.setValue(volumeSlider.getValue() + 10);
				} 
			}
		});
		menuAudioVolumeDown.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (Control.mediaPlayer.getVolume() > 0) {
					Control.mediaPlayer.setVolume(Control.mediaPlayer.getVolume() - 0.1);
					volumeSlider.setValue(volumeSlider.getValue() - 10);
				} 
			}
		});
		menuAudioSpeedUp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Control.mediaPlayer.setRate(Control.mediaPlayer.getRate() + 0.1);
			}
		});
		menuAudioSpeedDown.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Control.mediaPlayer.setRate(Control.mediaPlayer.getRate() - 0.1);
			}
		});
		menuAudioSpeedReset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Control.mediaPlayer.setRate(1);
			}
		});
		menuHelpAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				HelpView h = new HelpView();
				h.createUi();
			}
		});
	}
}