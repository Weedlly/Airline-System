package com.learning.learningjavafx;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Login extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Login background
        ImageView imageViewBk = new ImageView("D:\\Java\\LearningJavaFX\\src\\main\\PictureLib\\BgLogin.jfif");
        Pane paneBk = new Pane(imageViewBk);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5);
        pane.setVgap(5);

        Label labelUsername = new Label("Username :");
        labelUsername.setTextFill(Color.WHITE);
        Label labelPassword = new Label("Password :");
        labelPassword.setTextFill(Color.WHITE);
        TextField textFieldUser = new TextField("weed");

        PasswordField passwordField = new PasswordField();
        pane.addRow(0, labelUsername, textFieldUser);
        pane.addRow(1, labelPassword, passwordField);

        Button btSignIn = new Button("Sign in ");
        Button btSignUp = new Button("Sign up ");

        pane.add(btSignIn, 1, 3);
        pane.add(btSignUp, 1, 3);

        StackPane stackPane = new StackPane(paneBk, pane);
        GridPane.setHalignment(btSignUp, HPos.LEFT);
        GridPane.setHalignment(btSignIn, HPos.RIGHT);

        btSignIn.setOnMouseEntered(MouseEvent -> {
            btSignIn.setEffect(new Reflection());
        });
        btSignIn.setOnMouseExited(MouseEvent -> {
            btSignIn.setEffect(null);
        });
        btSignUp.setOnMouseEntered(MouseEvent -> {
            btSignUp.setEffect(new Reflection());
        });
        btSignUp.setOnMouseExited(MouseEvent -> {
            btSignUp.setEffect(null);
        });

        btSignIn.setOnAction(ActionEvent -> {
            if (primaryStage.isShowing())
                new AirlineMenu().start(new Stage());
            primaryStage.close();
        });
        btSignUp.setOnAction(ActionEvent -> {
            if (primaryStage.isShowing())
                new AirlineMenu().start(new Stage());
            primaryStage.close();
        });

        Scene scene = new Scene(stackPane);
        primaryStage.setTitle("Login"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false);
        btSignIn.requestFocus();
        btSignUp.requestFocus();
        primaryStage.show(); // Display the stage

    }
}


