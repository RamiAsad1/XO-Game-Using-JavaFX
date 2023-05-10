package application;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {// TicTacToe
	private InfoCenter infoCenter;
	private TileBoard tileBoard;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		initLayout(root);

		Scene scene = new Scene(root, UiConstants.APP_WIDTH, UiConstants.APP_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initLayout(BorderPane root) {
		initInfoCenter(root);
		initTitleBoard(root);
	}
	
	private void initInfoCenter(BorderPane root) {
		infoCenter = new InfoCenter();
		infoCenter.setStartButtonOnAction(startGame());
		root.getChildren().add(infoCenter.getPane());
	}
	
	private EventHandler<ActionEvent> startGame(){
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				infoCenter.hideButt();
				infoCenter.updateMessage("Player X's Turn!");
				tileBoard.startNewGame();
			}
		};
	}

	private void initTitleBoard(BorderPane root) {
		tileBoard = new TileBoard(infoCenter);
		root.getChildren().add(tileBoard.getPane());

	}

	
}
