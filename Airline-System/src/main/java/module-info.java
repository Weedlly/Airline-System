module com.learning.learningjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports com.learning.learningjavafx;
    opens com.learning.learningjavafx to javafx.fxml;

}