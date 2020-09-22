package com.fraserco.controllers;

import com.fraserco.App;
import com.fraserco.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectorController {



    private int count = 0;

    private Map<Integer, String> colourMap = new HashMap<>();

    private List<Button> buttons = new ArrayList<>();

    @FXML
    private AnchorPane parent;

    private void onClick(ActionEvent e, Button b) {
        if (count == 10) return;

        count++;
        int index = buttons.indexOf(b);
        colourMap.put(index, Model.colours[index]);

        ColorAdjust darken = new ColorAdjust();
        darken.setBrightness(-0.2);

        Image image = new Image(App.class.getResourceAsStream("img/selected.png"));
        ImageView iv = new ImageView(image);
        iv.setFitHeight(75);
        iv.setFitWidth(75);
        b.setGraphic(iv);


        b.setEffect(darken);
        b.setOnAction(_e -> onUnClick(_e, b));

    }

    private void onUnClick(ActionEvent e, Button b) {
        count--;
        int index = buttons.indexOf(b);
        colourMap.remove(index);

        ColorAdjust regular = new ColorAdjust();
        regular.setBrightness(0);

        b.setEffect(regular);
        b.setGraphic(null);

        b.setOnAction(_e -> onClick(_e, b));
    }

    @FXML
    void initialize() {
        for (int i = 0; i < 12; i++) {
            double x = 75 + 110 * (i % 6);
            double y = 165 + 110 * (i > 5 ? 1 : 0);

            Button b = new Button();
            b.setStyle("-fx-background-color: " + Model.colours[i] + "; -fx-background-radius: 5");

            b.setPrefHeight(100);
            b.setPrefWidth(100);

            b.setLayoutX(x);
            b.setLayoutY(y);


            b.setOnAction(e -> onClick(e, b));

            parent.getChildren().add(b);
            buttons.add(b);

        }
    }

    @FXML
    void onContinueClick(ActionEvent event) throws IOException {
        Model.get().uploadMap(this.colourMap);
        App.setRoot("game");
    }
}
