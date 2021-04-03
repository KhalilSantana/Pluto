module br.univali.comp.gui {
    requires org.apache.commons.io;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires org.fxmisc.richtext;
    opens br.univali.comp.gui to javafx.controls, javafx.fxml;
    exports br.univali.comp.gui;
}