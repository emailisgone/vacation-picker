package com.vu.vacationpicker;

import com.vu.vacationdata.UserVacChoice;
import com.vu.vacationpatterns.UVCRegistry;
import com.vu.vacationpatterns.UVCSingleton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseController {
    private Stage stage;
    enum DataTransferMethod{
        USERDATA,
        CONTROLLER,
        SINGLETON,
        REGISTRY
    }
    @FXML
    private ChoiceBox<String> continentBox;
    @FXML
    private ChoiceBox<String> countryBox;
    @FXML
    private TextField nameField;
    @FXML
    private void sendData(ActionEvent event) {
        if(nameField.getText().isBlank()
                || nameField.getText().isEmpty()
                || continentBox.getValue().isBlank()
                || continentBox.getValue().isEmpty()
                || countryBox.getValue().isBlank()
                || countryBox.getValue().isEmpty()){
            showErrorAlert("Error!", "Please make sure to input your name and select the continent and the country of your choice.");
            return;
        }

        UserVacChoice userChoice = new UserVacChoice(nameField.getText(), continentBox.getValue(), countryBox.getValue());
        /*
        * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        *
        *                   CHANGE DATA TRANSFER METHOD HERE
        *
        * VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
        * */
        initTransfer(userChoice, DataTransferMethod.CONTROLLER);
        stage.close();
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }

    private void initTransfer(UserVacChoice userChoice, DataTransferMethod method){
        switch (method){
            case CONTROLLER:
                controllerTransfer(userChoice);
                System.out.println("initTransfer CONTROLLER - OK");
                break;
            case USERDATA:
                userDataTransfer(userChoice);
                System.out.println("initTransfer USERDATA - OK");
                break;
            case SINGLETON:
                singletonTransfer(userChoice);
                System.out.println("initTransfer SINGLETON - OK");
                break;
            case REGISTRY:
                registryTransfer(userChoice);
                System.out.println("initTransfer REGISTRY - OK");
                break;
        }
    }

    private void controllerTransfer(UserVacChoice userChoice) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BaseApplication.class.getResource("info.fxml"));
            InfoController controller = new InfoController(userChoice);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Information Window");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void userDataTransfer(UserVacChoice userChoice){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BaseApplication.class.getResource("info.fxml"));
            Stage stage = new Stage();
            stage.setUserData(userChoice);
            InfoController controller = new InfoController(DataTransferMethod.USERDATA, stage);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();

            stage.setTitle("Information Window");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void singletonTransfer(UserVacChoice userChoice){
        try {
            UVCSingleton.setUserVacChoice(userChoice.getUserName(), userChoice.getContinent(), userChoice.getCountry());

            FXMLLoader fxmlLoader = new FXMLLoader(BaseApplication.class.getResource("info.fxml"));
            Stage stage = new Stage();
            InfoController controller = new InfoController(DataTransferMethod.SINGLETON, stage);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();

            stage.setTitle("Information Window");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void registryTransfer(UserVacChoice userChoice){
        try {
            UVCRegistry.addUVC("firstInstance", userChoice);

            FXMLLoader fxmlLoader = new FXMLLoader(BaseApplication.class.getResource("info.fxml"));
            Stage stage = new Stage();
            InfoController controller = new InfoController(DataTransferMethod.REGISTRY, stage);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();

            stage.setTitle("Information Window");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<String> initCountries(String continent){
        ObservableList<String> countryList = switch (continent) {
            case "Africa" -> FXCollections.observableArrayList(
                    "Algeria", "Canary IslandsSpain", "Ceuta", "Egypt", "Libya", "Madeira", "Melilla", "Morocco", "Sudan", "Tunisia", "Western Sahara", "Burundi", "Comoros", "Djibouti", "Eritrea", "Ethiopia", "French Southern and Antarctic Lands", "Kenya", "Madagascar", "Malawi", "Mauritius", "Mayotte", "Mozambique", "Reunion", "Rwanda", "Seychelles", "Somalia", "Somaliland", "South Sudan", "Tanzania", "Uganda", "Zambia", "Zimbabwe", "Angola", "Cameroon", "Central African Republic", "Chad", "Democratic Republic of the Congo", "Republic of the Congo", "Equatorial Guinea", "Gabon", "São Tomé and Príncipe", "Botswana", "Eswatini", "Lesotho", "Namibia", "South Africa", "Benin", "Burkina Faso", "Cape Verde", "Ivory Coast", "Gambia, The", "Ghana", "Guinea", "Guinea-Bissau", "Liberia", "Mali", "Mauritania", "Niger", "Nigeria", "Senegal", "Sierra Leone", "Togo"
            );
            case "Asia" -> FXCollections.observableArrayList(
                    "Afghanistan", "Armenia", "Azerbaijan", "Bahrain", "Bangladesh", "Bhutan", "Brunei", "Cambodia", "China", "Cyprus", "Georgia", "India", "Indonesia", "Iran", "Iraq", "Israel", "Japan", "Jordan", "Kazakhstan", "Kuwait", "Kyrgyzstan", "Laos", "Lebanon", "Malaysia", "Maldives", "Mongolia", "Myanmar", "Nepal", "North Korea", "Oman", "Pakistan", "Philippines", "Qatar", "Saudi Arabia", "Singapore", "South Korea", "Sri Lanka", "State of Palestine", "Syria", "Taiwan", "Tajikistan", "Thailand", "Timor-Leste", "Turkey", "Turkmenistan", "United Arab Emirates", "Uzbekistan", "Vietnam", "Yemen"
            );
            case "Europe" -> FXCollections.observableArrayList(
                    "Albania", "Andorra", "Austria", "Belarus", "Belgium", "Bosnia and Herzegovina", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Iceland", "Ireland", "Italy", "Kazakhstan", "Latvia", "Liechtenstein", "Lithuania", "Luxembourg", "Malta", "Moldova", "Monaco", "Montenegro", "Netherlands", "North Macedonia", "Norway", "Poland", "Portugal", "Romania", "Russia", "San Marino", "Serbia", "Slovakia", "Slovenia", "Spain", "Sweden", "Switzerland", "Turkey", "Ukraine", "United Kingdom", "Vatican City"
            );
            case "North America" -> FXCollections.observableArrayList(
                    "Antigua and Barbuda", "Bahamas", "Barbados", "Belize", "Canada", "Costa Rica", "Cuba", "Dominica", "Dominican Republic", "El Salvador", "Grenada", "Guatemala", "Haiti", "Honduras", "Jamaica", "Mexico", "Nicaragua", "Panama", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Trinidad and Tobago", "United States"
            );
            case "South America" -> FXCollections.observableArrayList(
                    "Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "Guyana", "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela"
            );
            case "Australia" -> FXCollections.observableArrayList(
                    "Australia", "Fiji", "Kiribati", "Marshall Islands", "Micronesia", "Nauru", "New Zealand", "Palau", "Papua New Guinea", "Samoa", "Solomon Islands", "Tonga", "Tuvalu", "Vanuatu"
            );
            default -> null;
        };
        return countryList;
    }

    public static void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

    @FXML
    public void initialize(){
        ObservableList<String> continents = FXCollections.observableArrayList(
                "Africa", "Asia", "Europe", "North America", "South America", "Australia"
        );
        continentBox.setItems(continents);

        if(stage.getUserData() != null){
            UserVacChoice preset = (UserVacChoice)stage.getUserData();
            nameField.setText(preset.getUserName());
            continentBox.setValue(preset.getContinent());
            countryBox.setValue(preset.getCountry());
        }

        continentBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                ObservableList<String> countries = initCountries(observableValue.getValue());
                countryBox.setItems(countries);
                if(countryBox.isDisabled()) countryBox.setDisable(false);
            }
        });
    }
}