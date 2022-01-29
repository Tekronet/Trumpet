package com.tekronet.backend;

import java.util.*;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;

import com.tekronet.ui.MainView;

public class Sliders {
	
	//progress bar
	public static void initListeners(Slider progressBar, Slider volumeSlider, ListView<String> songList, Label l) {
	
		Control.mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
				progressBar.setValue(newValue.toSeconds());
				int max = (int) Math.round(progressBar.getMax());
				int current = (int) Math.round(progressBar.getValue());

				MainView.timeLabel.setText(
					(current / 60) + ":" + String.format("%02d", (current % 60)) 
					+ "/" + (max / 60) + ":" + String.format("%02d", (max % 60)));

				if (current == max)
					Control.nextSong(songList, progressBar, volumeSlider, l);
			}
		});
		
		progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Control.mediaPlayer.pause();
				Control.mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
			}
		});
		
		progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Control.mediaPlayer.pause();
				Control.mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
			}
		});
		
		progressBar.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Control.mediaPlayer.play();
			}
		});
		
		volumeSlider.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Control.mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
			}
		});
		volumeSlider.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Control.mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
			}
		});
		
		Control.mediaPlayer.setOnReady(new Runnable() {
			@Override
			public void run() {
				progressBar.setMax(Control.media.getDuration().toSeconds());
			}
		});
	}
}
