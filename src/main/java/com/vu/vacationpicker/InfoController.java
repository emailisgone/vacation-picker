package com.vu.vacationpicker;

import com.vu.vacationdata.UserVacChoice;
import com.vu.vacationpatterns.UVCRegistry;
import com.vu.vacationpatterns.UVCSingleton;
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
        UserVacChoice temp = null;
        this.stage = stage;
        switch (method){
            case USERDATA -> {
                temp = (UserVacChoice) stage.getUserData();

            }
            case SINGLETON -> {
                temp = new UserVacChoice(UVCSingleton.getInstance().getUserName(),
                        UVCSingleton.getInstance().getContinent(),
                        UVCSingleton.getInstance().getCountry());
                //this.method = method;
            }
            case REGISTRY -> {
                temp = UVCRegistry.getUVC("firstInstance");
                //this.method = method;
            }
        }
        if(temp == null) temp = new UserVacChoice();
        this.userData = temp;
        this.method = method;
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
            case SINGLETON -> transMethLabel.setText("SINGLETON");
            case REGISTRY -> transMethLabel.setText("REGISTRY");
        }
    }
}
