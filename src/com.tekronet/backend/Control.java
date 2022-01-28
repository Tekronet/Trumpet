package com.tekronet.backend;

import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.*;

import java.io.*;
import java.util.*;
import java.net.*;

import com.tekronet.ui.MainView;

public class Control {

	public static Media media;
	public static MediaPlayer mediaPlayer;
	
	boolean second = false;
	public boolean playing = false;
	int selectedIndex = 0;
	
	Playlist playlist = new Playlist();
	
	public void openFiles(ListView<String> songList) {
		playlist.addSongs(songList);
	}
	
	public void createMediaPlayer(ListView<String> songList, Slider progressBar, Slider volumeSlider, Label l) {
		if (second) {
			mediaPlayer.stop();
		}
	
		String selectedSong = songList.getSelectionModel().getSelectedItem();
		File file = playlist.getMap().get(selectedSong);
		
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		second = true;
		mediaPlayer.stop();
		mediaPlayer.play();
		playing = true;
		Sliders.initListeners(progressBar, volumeSlider);
		l.setText(file.getName());
	}
	
	public void closeSong(ListView<String> songList) {
		playlist.removeSong(songList);
		mediaPlayer.stop();
	}
	
	public void clearPlaylist(ListView<String> songList) {
		playlist.clearPlaylist(songList);		
	}
	
	
	public void playPause() {
		if (playing) {
			mediaPlayer.pause();
			playing = false;
		}
		else if (!playing) {
			mediaPlayer.play();
			playing = true;
		}
	}
	
	public void stop() {
		try {
			mediaPlayer.stop();
		}
		catch (Exception e) {
			System.out.println("Debug: Trying to invoke stop but mediaplayer not created yet");
		}
	}
	
	public void nextSong(ListView<String> songList, Slider progressBar, Slider volumeSlider) {
		if (songList.getSelectionModel().getSelectedIndex() + 1  < songList.getItems().size()) {
			mediaPlayer.stop();
			
			String selectedSong = songList.getItems().get(songList.getSelectionModel().getSelectedIndex() + 1);
			File file = playlist.getMap().get(selectedSong);
			media = new Media(file.toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			mediaPlayer.play();
			playing = true;
			songList.getSelectionModel().select(songList.getSelectionModel().getSelectedIndex() + 1);
			Sliders.initListeners(progressBar, volumeSlider);
		}
	}
	
	public void prevSong(ListView<String> songList, Slider progressBar, Slider volumeSlider) {
		if (songList.getSelectionModel().getSelectedIndex() > 0) {
			mediaPlayer.stop();
			
			String selectedSong = songList.getItems().get(songList.getSelectionModel().getSelectedIndex() - 1);
			File file = playlist.getMap().get(selectedSong);
			media = new Media(file.toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			mediaPlayer.play();
			playing = true;
			songList.getSelectionModel().select(songList.getSelectionModel().getSelectedIndex() - 1);
			Sliders.initListeners(progressBar, volumeSlider);
		}
	}
	public void plusVolume() {
		System.out.println(mediaPlayer.getVolume());
	}
}
