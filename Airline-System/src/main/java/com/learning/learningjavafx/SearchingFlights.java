package com.learning.learningjavafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchingFlights extends Application {
    ConnectionDB connectionDB = new ConnectionDB();

    private final Pane mainPane = new Pane();
    GridPane resultGridPane = new GridPane();

    MenuButton flightsDestinationMenu = new MenuButton("Flight Destination");
    MenuButton flightsFromMenu = new MenuButton("Flight from");

    private final MenuItem[] menuFromItems = new MenuItem[3];
    private final MenuItem[] menuDestinationItems = new MenuItem[4];

    Button labelID = new Button("ID");
    Button labelFrom = new Button("From");
    Button labelDestination = new Button("Destination");
    Button labelDate = new Button("Date");
    Button labelLeave = new Button("Leave");
    Button labelArrived = new Button("Arrived");
    Button labelTimeMoving = new Button("Time moving");
    Button labelAmount = new Button("Amount");

    String keyWordFlightFrom = "";
    String keyWordFlightDes = "";

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        menuFromItems[0] = new MenuItem("DA LAT");
        menuFromItems[1] = new MenuItem("CAN THO");
        menuFromItems[2] = new MenuItem("HO CHI MINH");

        menuDestinationItems[0] = new MenuItem("DA LAT");
        menuDestinationItems[1] = new MenuItem("CAN THO");
        menuDestinationItems[2] = new MenuItem("HO CHI MINH");
        menuDestinationItems[3] = new MenuItem("HA NOI");

        flightsFromMenu.getItems().addAll(menuFromItems);
        flightsFromMenu.relocate(200, 75);
        flightsFromMenu.autosize();

        flightsDestinationMenu.getItems().addAll(menuDestinationItems);
        flightsDestinationMenu.relocate(400, 75);
        flightsDestinationMenu.autosize();

        for (MenuItem c : menuFromItems) {
            c.setOnAction(new FlightFromHandle());
        }
        for (MenuItem c : menuDestinationItems) {
            c.setOnAction(new FlightDesHandle());
        }


        resultGridPane.setPadding(new Insets(10, 10, 10, 10));
        resultGridPane.setAlignment(Pos.TOP_CENTER);
        resultGridPane.setHgap(5);
        resultGridPane.setVgap(10);


        labelID.setMinWidth(60);
        labelFrom.setMinWidth(80);
        labelDestination.setMinWidth(110);
        labelDate.setMinWidth(60);
        labelLeave.setMinWidth(60);
        labelArrived.setMinWidth(60);
        labelTimeMoving.setMinWidth(60);
        labelAmount.setMinWidth(80);

        resultGridPane.setLayoutY(200);
        resultGridPane.setMinWidth(700);
        resultGridPane.setGridLinesVisible(true);

        resultGridPane.add(labelID, 0, 0);
        resultGridPane.add(labelFrom, 1, 0);
        resultGridPane.add(labelDestination, 2, 0);
        resultGridPane.add(labelDate, 3, 0);
        resultGridPane.add(labelLeave, 4, 0);
        resultGridPane.add(labelArrived, 5, 0);
        resultGridPane.add(labelTimeMoving, 6, 0);
        resultGridPane.add(labelAmount, 7, 0);

        mainPane.getChildren().addAll(resultGridPane, flightsDestinationMenu, flightsFromMenu);

        Scene scene = new Scene(mainPane, 700, 500);
        primaryStage.setTitle("SEARCHING FLIGHT");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class FlightFromHandle implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            while (!resultGridPane.getChildren().isEmpty()) {
                resultGridPane.getChildren().remove(0);
            }
            resultGridPane.add(labelID, 0, 0);
            resultGridPane.add(labelFrom, 1, 0);
            resultGridPane.add(labelDestination, 2, 0);
            resultGridPane.add(labelDate, 3, 0);
            resultGridPane.add(labelLeave, 4, 0);
            resultGridPane.add(labelArrived, 5, 0);
            resultGridPane.add(labelTimeMoving, 6, 0);
            resultGridPane.add(labelAmount, 7, 0);

            for (MenuItem c : menuFromItems) {
                if (actionEvent.getSource().equals(c)) {
                    try {
                        keyWordFlightFrom = c.getText();
                        String queryString;
                        if (keyWordFlightDes.equals("")) {
                            queryString = "select * from flightinformation where flightFrom = '" + keyWordFlightFrom + "'";
                        } else if (keyWordFlightFrom.equals("")) {
                            queryString = "select * from flightinformation where flightDestination = " +
                                    " '+keyWordFlightDes+'";
                        } else {
                            queryString = "select * from flightinformation where flightFrom = '" + keyWordFlightFrom + "' and flightDestination = '" + keyWordFlightDes + "' ";
                        }

                        ResultSet resultSet = connectionDB.statement.executeQuery(queryString);
                        int line = 1;
                        while (resultSet.next()) {
                            int row = 0;
                            for (int i = 1; i < 9; i++) {
                                System.out.print(resultSet.getString(i));
                                Label label = new Label(resultSet.getString(i));
                                resultGridPane.add(label, row, line);
                                label.setAlignment(Pos.BASELINE_CENTER);
                                row++;
                            }
                            System.out.println();
                            line++;
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    break;
                }
            }
        }
    }

    class FlightDesHandle implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            while (!resultGridPane.getChildren().isEmpty()) {
                resultGridPane.getChildren().remove(0);
            }
            resultGridPane.add(labelID, 0, 0);
            resultGridPane.add(labelFrom, 1, 0);
            resultGridPane.add(labelDestination, 2, 0);
            resultGridPane.add(labelDate, 3, 0);
            resultGridPane.add(labelLeave, 4, 0);
            resultGridPane.add(labelArrived, 5, 0);
            resultGridPane.add(labelTimeMoving, 6, 0);
            resultGridPane.add(labelAmount, 7, 0);

            for (MenuItem c : menuDestinationItems) {
                if (actionEvent.getSource().equals(c)) {
                    try {
                        keyWordFlightDes = c.getText();
                        String queryString;
                        if (keyWordFlightDes.equals("")) {
                            queryString = "select * from flightinformation where flightFrom = '" + keyWordFlightFrom + "'";
                        } else if (keyWordFlightFrom.equals("")) {
                            queryString = "select * from flightinformation where flightDestination = '"+keyWordFlightDes+"'";
                        } else {
                            queryString = "select * from flightinformation where flightFrom = '" + keyWordFlightFrom + "' and flightDestination = '" + keyWordFlightDes + "' ";
                        }

                        ResultSet resultSet = connectionDB.statement.executeQuery(queryString);
                        int line = 1;
                        while (resultSet.next()) {
                            int row = 0;
                            for (int i = 1; i < 9; i++) {
                                System.out.print(resultSet.getString(i));
                                Label label = new Label(resultSet.getString(i));
                                resultGridPane.add(label, row, line);
                                label.setAlignment(Pos.BASELINE_CENTER);
                                row++;
                            }
                            System.out.println();
                            line++;
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    break;
                }
            }
        }
    }
}



