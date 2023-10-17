package com.vu.vacationpicker;

import com.vu.vacationdata.UserVacChoice;
import com.vu.vacationdata.VacChoiceInfo;
import com.vu.vacationpatterns.UVCRegistry;
import com.vu.vacationpatterns.UVCSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
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
    @FXML
    private Button backButton;
    @FXML
    private TextArea flagDesc;
    @FXML
    private Label vacDurationLabel;
    @FXML
    private Label currenciesLabel;
    @FXML
    private Label capitalLabel;
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
            }
            case REGISTRY -> {
                temp = UVCRegistry.getUVC("firstInstance");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText("Welcome, " + userData.getUserName() + "!");
        continentLabel.setText("We hope you'll travel to "+userData.getContinent()+" again.");
        countryLabel.setText("Please enjoy your stay at "+userData.getCountry()+".");
        switch (method){
            case CONTROLLER -> transMethLabel.setText("CONTROLLER");
            case USERDATA -> transMethLabel.setText("USERDATA");
            case SINGLETON -> transMethLabel.setText("SINGLETON");
            case REGISTRY -> transMethLabel.setText("REGISTRY");
        }

        VacChoiceInfo info = new VacChoiceInfo();
        info.generateInfoData(userData);
        info.generateVacationPeriod();
        vacDurationLabel.setText("Your vacation duration is as follows: " +
                info.getFormattedDate(info.getVacationStart().toString()) +
                " - " +
                info.getFormattedDate(info.getVacationEnd().toString()) + ".");
        capitalLabel.setText("The capital is "+info.getCapital()+".");
        currenciesLabel.setText("List of local currencies:\n" +
                info.getCurrencies());


        flagImage.setImage(new Image(info.getFlagUrl()));
        flagDesc.setText(info.getFlagDescription());


        backButton.setOnAction(event -> {
            try {
                Stage newStage = new Stage();
                newStage.initModality(Modality.NONE);

                FXMLLoader fxmlLoader = new FXMLLoader(BaseApplication.class.getResource("base.fxml"));
                BaseController controller = new BaseController();
                controller.setStage(newStage);
                fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();

                Scene scene = new Scene(root);
                newStage.setTitle("Hello!");
                newStage.setScene(scene);
                newStage.setResizable(false);
                newStage.show();
                stage.close();
            } catch (RuntimeException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
