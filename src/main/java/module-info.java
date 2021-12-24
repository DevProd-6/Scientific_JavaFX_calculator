module com.devprod.scientific_javafx_calculator {
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.fxml;
    
    opens com.devprod.scientific_javafx_calculator to javafx.fxml;
    exports com.devprod.scientific_javafx_calculator;
}