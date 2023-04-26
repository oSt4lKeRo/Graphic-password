module com.example.di_lab7 {
	requires javafx.controls;
	requires javafx.fxml;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires org.kordamp.bootstrapfx.core;

	opens com.example.di_lab7 to javafx.fxml;
	exports com.example.di_lab7;
}