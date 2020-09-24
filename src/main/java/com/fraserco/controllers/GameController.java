// Copyright 2020 Fraser
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.fraserco.controllers;


import com.fraserco.App;
import com.fraserco.model.Model;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController {

    private final Map<Integer, Button> buttons = new HashMap<>();
    List<String> colours;
    @FXML
    private AnchorPane parent;


    @FXML
    void onFinishClick(ActionEvent event) throws IOException {
        App.setRoot("selector");
    }

    @FXML
    void onResetClick(ActionEvent event) {
        buttons.values().forEach(button -> {
            button.setGraphic(null);
            button.setOnAction(e -> clickOne(button));
        });
    }

    @FXML
    void initialize() throws NativeHookException {
        this.colours = Model.get().getColours();

        for (int i = 0; i < this.colours.size(); i++) {
            double x = 125 + 110 * (i % 5);
            double y = 165 + 110 * (i > 4 ? 1 : 0);

            Button b = new Button();
            b.setStyle("-fx-background-color: " + this.colours.get(i) + "; -fx-background-radius: 5");

            b.setPrefHeight(100);
            b.setPrefWidth(100);

            b.setLayoutX(x);
            b.setLayoutY(y);

            //b.setText((i + 1) % 10 + "");

            b.setOnAction(e -> clickOne(b));

            parent.getChildren().add(b);
            buttons.put(i, b);
        }

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        
        GlobalScreen.registerNativeHook();

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

            }

            @Override
            public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
                int code = nativeKeyEvent.getKeyCode() - 2;
                if (code >= 0 && code < buttons.size()) {
                    Button b = buttons.get(code);
                    Platform.runLater(b::fire);
                }
            }

            @Override
            public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

            }
        });

    }

    //todo: use a list to make this not suck
    
    private void clickOne(Button b) {
        b.setOnAction(_e -> clickTwo(b));

        cycleImage("tick", b);

    }

    private void clickTwo(Button b) {
        b.setOnAction(_e -> clickThree(b));
        cycleImage("question", b);

    }

    private void clickThree(Button b) {
        b.setOnAction(_e -> clickFour(b));
        cycleImage("mark", b);
    }

    private void clickFour(Button b) {
        b.setOnAction(_e -> clickOne(b));

        b.setGraphic(null);

    }

    private void cycleImage(String img, Button b) {
        Image image = new Image(App.class.getResourceAsStream("img/" + img + ".png"));
        ImageView iv = new ImageView(image);
        iv.setFitHeight(75);
        iv.setFitWidth(75);
        b.setGraphic(iv);
    }

}
