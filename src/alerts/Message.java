package alerts;

import javafx.scene.control.Alert;

public class Message {
	
	public void setMessage(String str) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Cannot Contain");
		alert.setContentText(str);
		alert.show();
	}

}
