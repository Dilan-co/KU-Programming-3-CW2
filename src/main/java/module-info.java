//module com.main {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires javafx.web;
//
//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;
//
//    opens com to javafx.fxml;  // Open the com package for JavaFX reflection
//    opens com.Controllers to javafx.fxml;  // Open com.Controllers for JavaFX reflection
//
//    exports com.Controllers;  // Export the Controllers package to other modules if needed
//}


module com.main {
        requires javafx.graphics;
        requires javafx.controls;
        requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens com.Controllers to javafx.fxml;

        exports com;
        exports com.Controllers;
        }
