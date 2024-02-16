module JavaFX_practice {
	requires javafx.controls;
	requires java.desktop;
	requires java.sql;
	requires mysql.connector.j;
	opens application to javafx.graphics, javafx.fxml;
}
