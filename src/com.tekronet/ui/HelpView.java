package com.tekronet.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.tekronet.backend.Trumpet;

public class HelpView {
    public void createUi() {
        AnchorPane root = new AnchorPane();
        Stage stage = new Stage();
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("About Trumpet");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        String stylesheet = this.getClass().getResource("main.css").toExternalForm();
		scene.getStylesheets().add(stylesheet);
        Font.loadFont(MainView.class.getResource("res/Lato-Regular.ttf").toExternalForm(), 10);

        Label title = new Label("Trumpet 2.0");
        title.getStyleClass().add("title");
        AnchorPane.setTopAnchor(title, 10.0);
        AnchorPane.setLeftAnchor(title, 10.0);

        Label content = new Label(
        "Trumpet is a free simple audio player that supports playing\nmp3 and wav files. Released under Apache 2.0 license");
        content.getStyleClass().add("content");
        AnchorPane.setTopAnchor(content, 50.0);
        AnchorPane.setLeftAnchor(content, 10.0);
        
        Label content2 = new Label(
        "Source code and more info is avaliable here:");
        content2.getStyleClass().add("content");
        AnchorPane.setTopAnchor(content2, 100.0);
        AnchorPane.setLeftAnchor(content2, 10.0);

        Hyperlink link = new Hyperlink("https://tekronet.github.io");
        link.getStyleClass().add("content");
        AnchorPane.setTopAnchor(link, 120.0);
        AnchorPane.setLeftAnchor(link, 7.0);

        link.setOnAction(e -> {
            Trumpet t = new Trumpet();
            t.openLink();
        });

        Image logo = new Image(this.getClass().getResource("res/logo.png").toExternalForm().toString());
        ImageView img = new ImageView(logo);
        img.setFitWidth(120);
        img.setFitHeight(120);
        AnchorPane.setBottomAnchor(img, 10.0);
        AnchorPane.setLeftAnchor(img, 10.0);

        Label author = new Label("Tekronet 2022");
        author.getStyleClass().add("title");
        AnchorPane.setBottomAnchor(author, 60.0);
        AnchorPane.setLeftAnchor(author, 140.0);
        root.getChildren().addAll(title, content, content2, link, img, author);
    }
}