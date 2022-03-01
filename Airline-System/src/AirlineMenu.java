package com.learning.learningjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class AirlineMenu extends Application {
    private final Pane mainPane = new Pane();
    @Override
    public void start(Stage primaryStage) {
        ImageView imageBackground = new ImageView("D:\\Java\\LearningJavaFX\\src\\main\\PictureLib\\BkInterface2.jpg");
        MenuButton flightsMenu= new MenuButton("Flight");
    /*    MenuItem flightsMenu1=new MenuItem("Searching Airline");*/
        MenuItem flightsMenu2=new MenuItem("Searching Flights");
        flightsMenu.relocate(560,10);
        flightsMenu.setMinWidth(130);

        MenuButton userMenu= new MenuButton("User");
        MenuItem userMenu1 =new MenuItem("User Information");
        MenuItem userMenu2 =new MenuItem("Your Ticket");
        userMenu.relocate(725,10);
        userMenu.setMinWidth(130);

        //Set action
        /*flightsMenu1.setOnAction(e->{
            new SearchingAirline().start(new Stage());
        });*/
        flightsMenu2.setOnAction(e->{
            new SearchingFlights().start(new Stage());
        });
        userMenu1.setOnAction(e->{
            new Information().start(new Stage());
        });
        userMenu2.setOnAction(e->{
            new Ticket().start(new Stage());
        });

        flightsMenu.getItems().addAll(/*flightsMenu1,*/flightsMenu2);
        userMenu.getItems().addAll(userMenu1,userMenu2);

        mainPane.getChildren().add(imageBackground);
        mainPane.getChildren().addAll(flightsMenu,userMenu);


        Scene scene = new Scene(mainPane);
        primaryStage.setTitle("AIRLINE MANAGEMENT SYSTEM");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}