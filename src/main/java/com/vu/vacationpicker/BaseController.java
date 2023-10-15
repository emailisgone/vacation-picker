package com.vu.vacationpicker;

import com.vu.vacationdata.UserVacChoice;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseController {
    private Stage stage;
    enum DataTransferMethod{
        USERDATA,
        CONTROLLER,
        SINGLETON,
        BUILDER
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
        // SWITCH DATA TRANSFER METHOD HERE
        initTransfer(userChoice, DataTransferMethod.USERDATA);
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
                System.out.println("initTransfer SINGLETON - OK");
                break;
            case BUILDER:
                System.out.println("initTransfer BUILDER - OK");
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

    private ObservableList<String> initCountries(String continent){
        ObservableList<String> countryList = switch (continent) {
            case "Africa" -> FXCollections.observableArrayList(
                    "Surprise Me", "Algeria", "Canary IslandsSpain", "Ceuta", "Egypt", "Libya", "Madeira", "Melilla", "Morocco", "Sudan", "Tunisia", "Western Sahara", "Burundi", "Comoros", "Djibouti", "Eritrea", "Ethiopia", "French Southern and Antarctic Lands", "Kenya", "Madagascar", "Malawi", "Mauritius", "Mayotte", "Mozambique", "Reunion", "Rwanda", "Seychelles", "Somalia", "Somaliland", "South Sudan", "Tanzania", "Uganda", "Zambia", "Zimbabwe", "Angola", "Cameroon", "Central African Republic", "Chad", "Democratic Republic of the Congo", "Republic of the Congo", "Equatorial Guinea", "Gabon", "São Tomé and Príncipe", "Botswana", "Eswatini", "Lesotho", "Namibia", "South Africa", "Benin", "Burkina Faso", "Cape Verde", "Ivory Coast", "Gambia, The", "Ghana", "Guinea", "Guinea-Bissau", "Liberia", "Mali", "Mauritania", "Niger", "Nigeria", "Senegal", "Sierra Leone", "Togo"
            );
            case "Asia" -> FXCollections.observableArrayList(
                    "Surprise Me", "Afghanistan", "Armenia", "Azerbaijan", "Bahrain", "Bangladesh", "Bhutan", "Brunei", "Cambodia", "China", "Cyprus", "Georgia", "India", "Indonesia", "Iran", "Iraq", "Israel", "Japan", "Jordan", "Kazakhstan", "Kuwait", "Kyrgyzstan", "Laos", "Lebanon", "Malaysia", "Maldives", "Mongolia", "Myanmar", "Nepal", "North Korea", "Oman", "Pakistan", "Philippines", "Qatar", "Saudi Arabia", "Singapore", "South Korea", "Sri Lanka", "State of Palestine", "Syria", "Taiwan", "Tajikistan", "Thailand", "Timor-Leste", "Turkey", "Turkmenistan", "United Arab Emirates", "Uzbekistan", "Vietnam", "Yemen"
            );
            case "Europe" -> FXCollections.observableArrayList(
                    "Surprise Me", "Albania", "Andorra", "Austria", "Belarus", "Belgium", "Bosnia and Herzegovina", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Iceland", "Ireland", "Italy", "Kazakhstan", "Latvia", "Liechtenstein", "Lithuania", "Luxembourg", "Malta", "Moldova", "Monaco", "Montenegro", "Netherlands", "North Macedonia", "Norway", "Poland", "Portugal", "Romania", "Russia", "San Marino", "Serbia", "Slovakia", "Slovenia", "Spain", "Sweden", "Switzerland", "Turkey", "Ukraine", "United Kingdom", "Vatican City"
            );
            case "North America" -> FXCollections.observableArrayList(
                    "Surprise Me", "Antigua and Barbuda", "Bahamas", "Barbados", "Belize", "Canada", "Costa Rica", "Cuba", "Dominica", "Dominican Republic", "El Salvador", "Grenada", "Guatemala", "Haiti", "Honduras", "Jamaica", "Mexico", "Nicaragua", "Panama", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Trinidad and Tobago", "United States"
            );
            case "South America" -> FXCollections.observableArrayList(
                    "Surprise Me", "Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "Guyana", "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela"
            );
            case "Antarctica" -> FXCollections.observableArrayList(
                    "Surprise Me", "Adélie Land (France)", "Argentine Antarctica (Argentina)", "Australian Antarctic Territory (Australia)", "British Antarctic Territory (United Kingdom)", "Chilean Antarctic Territory (Chile)", "Peter I Island and Queen Maud Land (Norway)", "Ross Dependency (New Zealand)"
            );
            case "Australia" -> FXCollections.observableArrayList(
                    "Surprise Me", "Australia", "Fiji", "Kiribati", "Marshall Islands", "Micronesia", "Nauru", "New Zealand", "Palau", "Papua New Guinea", "Samoa", "Solomon Islands", "Tonga", "Tuvalu", "Vanuatu"
            );
            case "Surprise Me" -> FXCollections.observableArrayList(
                    "Surprise Me"
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

    public void initialize(){
        ObservableList<String> continents = FXCollections.observableArrayList(
                "Africa", "Asia", "Europe", "North America", "South America", "Antarctica", "Australia", "Surprise Me"
        );
        continentBox.setItems(continents);

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