package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TileBoard {

	private StackPane pane;
	private InfoCenter infoCenter;
	private Tile[][] allTiles = new Tile[3][3];

	private char turn = 'X';
	private boolean isEndOfGame = false;

	public TileBoard(InfoCenter infoCenter) {
		this.infoCenter = infoCenter;
		pane = new StackPane();
		pane.setMinSize(UiConstants.APP_WIDTH, UiConstants.BORAD_HEIGHT);
		pane.setTranslateX(UiConstants.APP_WIDTH / 2);
		pane.setTranslateY((UiConstants.BORAD_HEIGHT / 2) + UiConstants.INFO_HEIGHT);
		addAllTiles();
	}

	private void addAllTiles() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				Tile tile = new Tile();
				tile.getStackPane().setTranslateX((col * 100) - 100);
				tile.getStackPane().setTranslateY((row * 100) - 100);
				pane.getChildren().add(tile.getStackPane());
				allTiles[row][col] = tile;
			}
		}

	}

	public void changePlayerTurn() {
		if (turn == 'X')
			turn = 'O';
		else
			turn = 'X';

		infoCenter.updateMessage("Player " + turn + "'s turn");
	}

	public String getPlayerTurn() {
		return String.valueOf(turn);
	}

	public StackPane getPane() {
		return pane;
	}

	private void checkForWinner() {
		checkRowsForWinner();
		checkColsForWinner();
		checkTopLeftToBottomRightForWinner();
		checkTopRightToBottomLeftForWinner();
		checkForDraw();
	}

	private void checkRowsForWinner() {
		if (!isEndOfGame) {
			for (int row = 0; row < 3; row++) {
				if (allTiles[row][0].getValue().equals(allTiles[row][1].getValue())
						&& allTiles[row][0].getValue().equals(allTiles[row][2].getValue())
						&& !allTiles[row][0].getValue().isEmpty()) {
					String winner = allTiles[row][0].getValue();
					endGame(winner);
					return;
				}
			}
		}
	}

	private void checkColsForWinner() {
		if (!isEndOfGame) {
			for (int col = 0; col < 3; col++) {
				if (allTiles[0][col].getValue().equals(allTiles[1][col].getValue())
						&& allTiles[0][col].getValue().equals(allTiles[2][col].getValue())
						&& !allTiles[0][col].getValue().isEmpty()) {
					String winner = allTiles[0][col].getValue();
					endGame(winner);
					return;
				}
			}
		}

	}

	private void checkTopLeftToBottomRightForWinner() {
		if (!isEndOfGame) {
			if (allTiles[0][0].getValue().equals(allTiles[1][1].getValue())
					&& allTiles[0][0].getValue().equals(allTiles[2][2].getValue())
					&& !allTiles[0][0].getValue().isEmpty()) {
				String winner = allTiles[0][0].getValue();
				endGame(winner);
				return;
			}
		}

	}

	private void checkTopRightToBottomLeftForWinner() {
		if (!isEndOfGame) {
			if (allTiles[0][2].getValue().equals(allTiles[1][1].getValue())
					&& allTiles[0][2].getValue().equals(allTiles[2][0].getValue())
					&& !allTiles[0][2].getValue().isEmpty()) {
				String winner = allTiles[0][2].getValue();
				endGame(winner);
				return;
			}
		}

	}

	private void checkForDraw() {
		if (!isEndOfGame) {
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (allTiles[row][col].getValue().isEmpty()) {
						return;
					}
				}
			}
			isEndOfGame = true;
			infoCenter.updateMessage("Draw ...");
			infoCenter.showButt();
		}
	}

	private void endGame(String winner) {
		isEndOfGame = true;
		infoCenter.updateMessage("Player "+winner+" wins!");
		infoCenter.showButt();
	}
	
	public void startNewGame() {
		isEndOfGame = false;
		turn = 'X';
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
					allTiles[row][col].setValue("");
				}
			}
		}
	

	private class Tile {
		private StackPane pane;
		private Label label;

		public Tile() {
			pane = new StackPane();
			pane.setMinSize(100, 100);

			Rectangle border = new Rectangle();
			border.setWidth(100);
			border.setHeight(100);
			border.setFill(Color.TRANSPARENT);
			border.setStroke(Color.BLACK);
			pane.getChildren().add(border);

			label = new Label("");
			label.setAlignment(Pos.CENTER);
			label.setFont(Font.font(24));
			pane.getChildren().add(label);

			pane.setOnMouseClicked(e -> {
				if (label.getText().isEmpty() && !isEndOfGame) {
					label.setText(getPlayerTurn());
					changePlayerTurn();
					checkForWinner();
				}
			});
		}

		public StackPane getStackPane() {
			return pane;
		}

		public String getValue() {
			return label.getText();
		}

		public void setValue(String value) {
			label.setText(value);
		}

	}

}
