module com.charles.kataquiz {

    requires javafx.controls;
    requires javafx.fxml;

    requires java.net.http;

    requires com.google.gson;
    requires org.apache.commons.text;
    requires atlantafx.base;

    opens com.charles.kataquiz.controller to javafx.fxml;
    opens com.charles.kataquiz.model to javafx.fxml;

    exports com.charles.kataquiz;
}
