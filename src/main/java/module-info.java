module com.vu.vacationpicker {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens com.vu.vacationpicker to javafx.fxml;
    exports com.vu.vacationpicker;
}