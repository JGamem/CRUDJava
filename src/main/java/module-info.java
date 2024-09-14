module principal.umg.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;


    opens principal.umg.crud to javafx.fxml;
    exports principal.umg.crud;
}