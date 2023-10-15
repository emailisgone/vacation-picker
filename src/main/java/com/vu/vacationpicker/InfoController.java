package com.vu.vacationpicker;

import com.vu.vacationdata.UserVacChoice;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InfoController {
    private final UserVacChoice userData;
    @FXML
    private Label nameLabel;
    @FXML
    private Label continentLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label transMethLabel;
    @FXML
    private ImageView flagImage;
    private Stage stage;
    private BaseController.DataTransferMethod method;

    public InfoController(BaseController.DataTransferMethod method, Stage stage){
        this.stage = stage;
        this.method = BaseController.DataTransferMethod.USERDATA;
        if (method == BaseController.DataTransferMethod.USERDATA){
            this.userData = (UserVacChoice) stage.getUserData();
        }
        else{
            this.userData = null;
        }
    }
    public InfoController(UserVacChoice userData){
        this.userData = userData;
        this.method = BaseController.DataTransferMethod.CONTROLLER;
    }
    public void initialize(){
        nameLabel.setText(userData.getUserName());
        continentLabel.setText(userData.getContinent());
        countryLabel.setText(userData.getCountry());
        switch (method){
            case CONTROLLER -> transMethLabel.setText("CONTROLLER");
            case USERDATA -> transMethLabel.setText("USERDATA");
        }

    }
}
