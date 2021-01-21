package tictactoe;

public enum State {
	X("X wins"),
	O("O wins"),
	Draw("Draw"),
	Continue("Game not finished");

	private final String message;

	State(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
