package com.fraserco.controllers;


import com.fraserco.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SplashController {

    @FXML
    void onLoadClick(ActionEvent event) throws IOException {
        App.setRoot("selector");
    }

}
