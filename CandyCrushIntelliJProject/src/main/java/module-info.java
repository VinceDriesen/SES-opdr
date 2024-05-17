module be.kuleuven.candycrush {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.common;
    requires org.checkerframework.checker.qual;

    opens be.kuleuven.candycrush to javafx.fxml;

    exports be.kuleuven.candycrush;
    opens be.kuleuven.candycrush.controller to javafx.fxml;
}