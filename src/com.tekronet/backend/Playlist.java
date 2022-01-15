package com.tekronet.backend;

import java.util.*;
import java.io.*;
import java.net.*;

import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Playlist {

	public HashMap<String, File> songMap = new HashMap<>();
	
	public void addSongs(ListView<String> songList) {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Select audio files");
		
		List<File> audioFilesList = filechooser.showOpenMultipleDialog(null);
		
		if (audioFilesList != null) {
			for (int i = 0; i < audioFilesList.size(); i++) {
				if (audioFilesList.get(i).getName().toString().endsWith(".mp3") || 
				audioFilesList.get(i).getName().toString().endsWith(".wav") || 
				audioFilesList.get(i).getName().toString().endsWith(".mp4")) {	
					songList.getItems().add(audioFilesList.get(i).getName().toString());
					songMap.put(audioFilesList.get(i).getName().toString(), audioFilesList.get(i));		
				}
				else {
					System.out.println("Debug: Wrong file format");
					Alert alert = new Alert(AlertType.ERROR);
				 	alert.setTitle("File format error");
					alert.setHeaderText("Wrong file format");
					alert.setContentText("Only .wav and .mp3 files are supported");
					alert.show();
				}
			}
		}
		else {
			System.out.println("Debug: No files selected");
		}
	}
	
	public void removeSong(ListView<String> songList) {
		if (songList.getSelectionModel().getSelectedIndex() > -1) {
			songMap.remove(songList.getSelectionModel().getSelectedItem().toString());
			songList.getItems().remove(songList.getSelectionModel().getSelectedIndex());
		}
	}
	
	public void clearPlaylist(ListView<String> songList) {
		songMap.clear();
		songList.getItems().clear();
	}
	
		
	public void printHashMap() {
		System.out.println(Arrays.asList(songMap));
	}
	
	public HashMap<String, File> getMap() {
		return songMap;
	}
}
