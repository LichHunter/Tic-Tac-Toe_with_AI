package tictactoe;

/**
 * Class contains methods to check given input for correctness
 */
public class Checker {
	/**
	 * Method will check if coordinates are numbers and they are pointing to anything in game field
	 *
	 * @param coordinates string contains two numbers (row, column) e.g. 1 2, 3 4, ...
	 * @param field       two dimensional game field
	 * @throws IllegalArgumentException is thrown if coordinates are not numbers or they don't point to anything
	 */
	public void checkCoordinates(String coordinates, String[][] field) throws IllegalArgumentException {
		//check if input is letters
		if (coordinates.matches("([a-zA-Z]+|[a-zA-Z]+ [a-zA-Z]+)+")) {
			throw new IllegalArgumentException("You should enter numbers!");
		}

		try {
			String[] input = coordinates.split("[,.\\s]+");

			//check if input is digits only
			if (input[0].matches("\\d+") && input[1].matches("\\d+")) {
				//check if coordinates point to anything in field
				try {
					String cell = field[Integer.parseInt(input[0]) - 1][Integer.parseInt(input[1]) - 1];
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Coordinates should contain two numbers separated by space");
		}
	}

	public void checkParameters(String input) throws IllegalArgumentException {
		IllegalArgumentException exception = new IllegalArgumentException("Bad parameters!");
		String[] parameters = input.split("\\s+");

		if ("exit".equals(parameters[0]))
			return;
		if (parameters.length != 3)
			throw exception;
		if (!parameters[0].matches("start|exit"))
			throw exception;
		if (!parameters[1].matches("user|easy|medium|hard") || !parameters[2].matches("user|easy|medium|hard"))
			throw exception;
	}

	/**
	 * Check game state
	 *
	 * @param field two dimensional array that contains game field
	 * @return State.X -> X has won, State.O - O has won, State.Draw - draw, State.Continue - game can be continued
	 */
	public State checkGameState(String[][] field) {
		State result = checkRows(field);
		if (result != null) return result;

		result = checkColumns(field);
		if (result != null) return result;

		result = checkDiagonals(field);
		if (result != null) return result;

		State draw = checkForDraw(field);
		if (draw != null) return draw;

		return State.Continue;
	}

	private State checkForDraw(String[][] field) {
		//if all cells are occupied and no one has won -> draw
		for (String[] row : field) {
			for (String cell : row) {
				if (" ".equals(cell)) {
					return null;
				}
			}
		}

		return State.Draw;
	}

	private State checkDiagonals(String[][] field) {
		StringBuilder builder = new StringBuilder();

		//check main diagonal
		for (int i = 0; i < field.length; i++) {
			builder.append(field[i][i]);
		}

		if (builder.toString().matches("XXX")) {
			return State.X;
		} else if (builder.toString().matches("OOO")) {
			return State.O;
		}

		builder = new StringBuilder();
		int num = field.length - 1;

		//check side diagonal
		for (String[] strings : field) {
			builder.append(strings[num]);
			num--;
		}

		if (builder.toString().matches("XXX")) {
			return State.X;
		} else if (builder.toString().matches("OOO")) {
			return State.O;
		}

		return null;
	}

	private State checkColumns(String[][] field) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < field[0].length; i++) {
			for (String[] strings : field) {
				builder.append(strings[i]);
			}
		}

		if (builder.toString().matches("XXX")) {
			return State.X;
		} else if (builder.toString().matches("OOO")) {
			return State.O;
		}

		return null;
	}

	private State checkRows(String[][] field) {
		for (String[] row : field) {
			StringBuilder builder = new StringBuilder();

			for (String cell : row) {
				builder.append(cell);
			}

			if (builder.toString().matches("XXX")) {
				return State.X;
			} else if (builder.toString().matches("OOO")) {
				return State.O;
			}
		}
		return null;
	}
}
