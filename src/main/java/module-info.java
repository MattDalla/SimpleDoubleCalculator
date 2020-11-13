module dev.matt.calculator {
    requires javafx.controls;
    requires javafx.fxml;
	requires commons.lang3;

    opens dev.matt.calculator to javafx.fxml;
    exports dev.matt.calculator;
}