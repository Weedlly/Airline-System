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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Information extends Application {
    ConnectionDB connectionDB = new ConnectionDB();
    private final Pane mainPane = new Pane();
    GridPane gridPane = new GridPane();

    Label labelName = new Label("Full name");
    Label labelPhone = new Label("Phone number");
    Label labelPassport = new Label("Passport");
    Label labelAddress = new Label("Address");
    Label labelPaymentCardNumber = new Label("Payment card number");
    Label labelCVV = new Label("CVV");

    TextField textFieldName = new TextField();
    TextField textFieldPhone = new TextField();
    TextField textFieldPassport = new TextField();
    TextField textFieldAddress = new TextField();
    TextField textFieldPaymentCardNumber = new TextField();
    TextField textFieldCVV = new TextField();

    Button buttonSave = new Button("Save");

    @Override
    public void start(Stage stage) {
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(10);

        gridPane.add(labelName, 0, 0);
        gridPane.add(labelPhone, 0, 1);
        gridPane.add(labelPassport, 0, 2);
        gridPane.add(labelAddress, 0, 3);
        gridPane.add(labelPaymentCardNumber, 0, 4);
        gridPane.add(labelCVV, 0, 5);


        gridPane.add(textFieldName, 1, 0);
        gridPane.add(textFieldPhone, 1, 1);
        gridPane.add(textFieldPassport, 1, 2);
        gridPane.add(textFieldAddress, 1, 3);
        gridPane.add(textFieldPaymentCardNumber, 1, 4);
        gridPane.add(textFieldCVV, 1, 5);

        buttonSave.setMaxWidth(100);
        buttonSave.setMinWidth(100);
        gridPane.add(buttonSave, 1, 8);
        GridPane.setHalignment(buttonSave, HPos.CENTER);

        gridPane.setLayoutY(50);
        mainPane.getChildren().add(new ImageView("D:\\Java\\LearningJavaFX\\src\\main\\PictureLib\\UserBk2.jpg"));
        mainPane.getChildren().add(gridPane);

        Scene scene = new Scene(mainPane, 600, 450);
        stage.setTitle("User Information");
        stage.setScene(scene);

        textFieldAddress.focusedProperty();
        textFieldCVV.focusedProperty();
        textFieldName.focusedProperty();
        textFieldPassport.focusedProperty();
        textFieldPaymentCardNumber.focusedProperty();
        textFieldPhone.focusedProperty();

        textFieldName.setOnAction(actionEvent -> {
        });
        textFieldAddress.setOnAction(actionEvent -> {
        });
        textFieldPassport.setOnAction(actionEvent -> {
        });
        textFieldPhone.setOnAction(actionEvent -> {
        });
        textFieldPaymentCardNumber.setOnAction(actionEvent -> {
        });
        textFieldCVV.setOnAction(actionEvent -> {
        });

        buttonSave.setOnAction(new SaveHandle());
        stage.show();
    }

    class SaveHandle implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                ResultSet resultSet = connectionDB.statement.executeQuery("SELECT userID from userinformation ");
                String buffer = "";
                while (resultSet.next()){
                    buffer = resultSet.getString(1);
                }
                buffer =String.valueOf(Integer.parseInt(buffer) + 1);

                PreparedStatement preparedStatement = connectionDB.connection.prepareStatement("insert into userinformation(userId, userName, userPhone, userPassport, userAddress, userPaymentCardNumber, userCVV)  values('"+ buffer +"','" + textFieldName.getText() + "'" +
                        ",'" + textFieldPhone.getText() + "'" +
                        ",'" + textFieldPassport.getText() + "'" +
                        ",'" + textFieldAddress.getText() + "'" +
                        ",'" + textFieldPaymentCardNumber.getText() + "'" +
                        ",'" + textFieldCVV.getText() + "' ) " );
                preparedStatement.executeUpdate();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
