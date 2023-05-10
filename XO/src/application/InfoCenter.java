package application;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;

public class InfoCenter {
	private StackPane pane;
	private Label message;
	private Button startButton;

	public InfoCenter() {
		pane = new StackPane();
		pane.setMinSize(UiConstants.APP_WIDTH, UiConstants.INFO_HEIGHT);
		pane.setTranslateX(UiConstants.APP_WIDTH / 2);
		pane.setTranslateY(UiConstants.INFO_HEIGHT / 2);

		message = new Label("Tic Tac Toe");
		message.setMinSize(UiConstants.APP_WIDTH, UiConstants.INFO_HEIGHT);
		message.setFont(Font.font(24));
		message.setAlignment(Pos.CENTER);
		message.setTranslateY(-20);

		startButton = new Button("Start Game");
		startButton.setMinSize(135, 30);
		startButton.setTranslateY(20);
		pane.getChildren().addAll(message, startButton);

	}

	public StackPane getPane() {
		return pane;
	}

	public void updateMessage(String message) {
		this.message.setText(message);
	}

	public void showButt() {
		startButton.setVisible(true);
	}

	public void hideButt() {
		startButton.setVisible(false);
	}

	public void setStartButtonOnAction(EventHandler<ActionEvent> onAction) {
		startButton.setOnAction(onAction);
	}

}
