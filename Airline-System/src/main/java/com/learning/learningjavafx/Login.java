package com.learning.learningjavafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends Application {
    ConnectionDB connectionDB = new ConnectionDB();
    Stage primaryStage;

    TextField textFieldUser = new TextField();
    PasswordField passwordField = new PasswordField();

    @Override // Override the start method in the Application class
    public void start(Stage stage) {
        // Login background
        this.primaryStage = stage;
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

        pane.addRow(0, labelUsername, textFieldUser);
        pane.addRow(1, labelPassword, passwordField);

        Button btSignIn = new Button("Sign in ");
        Button btSignUp = new Button("Sign up ");

        pane.add(btSignIn, 1, 3);
        pane.add(btSignUp, 1, 3);

        StackPane stackPane = new StackPane(paneBk, pane);
        GridPane.setHalignment(btSignUp, HPos.LEFT);
        GridPane.setHalignment(btSignIn, HPos.RIGHT);

        btSignIn.setOnMouseEntered(MouseEvent -> btSignIn.setEffect(new Reflection()));
        btSignIn.setOnMouseExited(MouseEvent -> btSignIn.setEffect(null));
        btSignUp.setOnMouseEntered(MouseEvent -> btSignUp.setEffect(new Reflection()));
        btSignUp.setOnMouseExited(MouseEvent -> btSignUp.setEffect(null));

        btSignIn.setOnAction(new SignInHandle());
        btSignUp.setOnAction(new SignUpHandle());

        Scene scene = new Scene(stackPane);
        primaryStage.setTitle("Login"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false);
        btSignIn.requestFocus();
        btSignUp.requestFocus();
        primaryStage.show(); // Display the stage
    }
    class SignInHandle implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                if (primaryStage.isShowing()) {
                    String queryString;
                    queryString = "select userAccount,userPassword from userinformation where userAccount = '"+textFieldUser.getText()+"' and userPassword = '"+passwordField.getText()+"' ";
                    ResultSet resultSet = connectionDB.statement.executeQuery(queryString);
                    if (resultSet.next()){
                        new AirlineMenu().start(new Stage());
                        primaryStage.close();
                    }
                    else{
                        Stage subLogin = new Stage();
                        Pane pane = new Pane();
                        Button tryAgainButton = new Button("Try again");
                        tryAgainButton.setLayoutY(25);
                        tryAgainButton.setLayoutX(50);
                        Label wrongLabel = new Label("Wrong Account or Password");
                        pane.getChildren().addAll(wrongLabel,tryAgainButton);

                        tryAgainButton.setOnAction(actionEvent1 -> {
                            if (subLogin.isShowing())
                                subLogin.close();
                        });
                        subLogin.setFullScreen(false);
                        subLogin.setMaximized(false);
                        Scene scene = new Scene(pane,156,50);
                        subLogin.setScene(scene);
                        subLogin.show();
                    }
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
    class SignUpHandle implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                if (primaryStage.isShowing()) {
                    if (textFieldUser.getText() != "" && passwordField.getText() != ""){
                        String queryString;
                        queryString = "select userAccount,userPassword from userinformation where userAccount = '"+textFieldUser.getText()+"' and userPassword = '"+passwordField.getText()+"' ";
                        ResultSet resultSet = connectionDB.statement.executeQuery(queryString);
                        if (!resultSet.next()) {
                            //Add new user to database
                            resultSet = connectionDB.statement.executeQuery("SELECT userID from userinformation ");
                            String buffer = "";
                            while (resultSet.next()) {
                                buffer = resultSet.getString(1);
                            }
                            buffer =String.valueOf(Integer.parseInt(buffer) + 1);

                            PreparedStatement preparedStatement = connectionDB.connection.prepareStatement("insert into userinformation(userId,userAccount,userPassword)  values('"+buffer+"','"+ textFieldUser.getText() +"','" + passwordField.getText() + "')");
                            preparedStatement.executeUpdate();

                            Stage subLogin = new Stage();
                            Pane pane = new Pane();
                            Button okeButton = new Button("OK");
                            okeButton.setLayoutY(25);
                            okeButton.setLayoutX(65);
                            Label successLabel = new Label("You are success");
                            successLabel.setLayoutX(40);
                            pane.getChildren().addAll(successLabel, okeButton);

                            okeButton.setOnAction(actionEvent1 -> {
                                if (subLogin.isShowing())
                                    subLogin.close();
                            });
                            subLogin.setFullScreen(false);
                            subLogin.setMaximized(false);
                            Scene scene = new Scene(pane, 156, 50);
                            subLogin.setScene(scene);
                            subLogin.show();
                        }
                    }
                    else{
                        Stage subLogin = new Stage();
                        Pane pane = new Pane();
                        Button tryAgainButton = new Button("Try again");
                        tryAgainButton.setLayoutY(25);
                        tryAgainButton.setLayoutX(50);
                        Label wrongLabel = new Label("Account existed");
                        wrongLabel.setLayoutX(40);
                        pane.getChildren().addAll(wrongLabel,tryAgainButton);

                        tryAgainButton.setOnAction(actionEvent1 -> {
                            if (subLogin.isShowing())
                                subLogin.close();
                        });

                        subLogin.setFullScreen(false);
                        subLogin.setMaximized(false);
                        Scene scene = new Scene(pane,156,50);
                        subLogin.setScene(scene);
                        subLogin.show();
                    }
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
}


