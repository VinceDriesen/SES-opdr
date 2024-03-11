module be.kuleuven.candycrush {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires be.kuleuven.checkNeigboursInGrid;

    opens be.kuleuven.candycrush to javafx.fxml;

    exports be.kuleuven.candycrush;
    opens be.kuleuven.candycrush.controller to javafx.fxml;
}