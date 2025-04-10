module org.monopoly.monopolygameproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens org.monopoly.monopolygameproject to javafx.fxml;
    opens org.monopoly.View to javafx.fxml;
    exports org.monopoly.monopolygameproject;
    exports org.monopoly.View to javafx.fxml;
    opens org.monopoly.View.Controllers to javafx.fxml;
}