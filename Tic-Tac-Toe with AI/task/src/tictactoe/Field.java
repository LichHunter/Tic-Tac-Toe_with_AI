package tictactoe;


/**
 * Class contains game field and methods to interact with it
 */
public class Field {
	private final String[][] field; // game field must be square matrix
	private final Checker CHECKER;

	Field(Checker checker) {
		this.field = new String[3][3];
		this.CHECKER = checker;
	}

	public Field(String[][] field, Checker checker) {
		this.field = field;
		this.CHECKER = checker;
	}

	public Field(int size, Checker checker) {
		this.field = new String[size][size];
		this.CHECKER = checker;
	}

	/**
	 * Method will count number of each player chars and determine who should go next
	 *
	 * @return "X" - number of "O" chars bigger or lover then number of "X" chars
	 */
	public String determineWhoMakeNextMove() {
		int x = 0;
		int o = 0;

		//count number of each char
		for (String[] row : field) {
			for (String cell : row) {
				if ("X".equals(cell)) {
					x++;
				} else if ("O".equals(cell)) {
					o++;
				}
			}
		}

		return x <= o ? "X" : "O";
	}

	/**
	 * Method will fill field with given input
	 *
	 * @param input string with similar insides - ___XXOO_X, "_" - nothing
	 * @throws IllegalArgumentException if length of input is not equal to size of field
	 */
	public void fillField(String input) throws IllegalArgumentException {
		if (input.length() != Math.pow(field.length, 2)) {
			throw new IllegalArgumentException("Length of input ("
					+ input + ") is not equal to number of cells in field (" + Math.pow(field.length, 2) + ")");
		}

		String[] signs = input.split("");
		int counter = 0;

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				if ("_".equals(signs[counter])) {
					field[i][j] = " ";
				} else {
					field[i][j] = signs[counter];
				}
				counter++;
			}
		}
	}

	public void putOnField(String input, String coordinates) throws IllegalArgumentException {
		try {
			CHECKER.checkCoordinates(coordinates, field);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		String[] splitCoordinates = coordinates.split("[,.\\s]+");
		String cell = field[Integer.parseInt(splitCoordinates[0]) - 1][Integer.parseInt(splitCoordinates[1]) - 1];

		if (!"X".equals(cell) && !"O".equals(cell)) {
			field[Integer.parseInt(splitCoordinates[0]) - 1][Integer.parseInt(splitCoordinates[1]) - 1] = input;
		} else {
			throw new IllegalArgumentException("This cell is occupied! Choose another one!");
		}
	}

	/**
	 * Show field
	 */
	public void showField() {
		for (int i = 0; i < field[0].length * 2 + 2; i++) {
			System.out.print("-");
		}
		System.out.println();

		for (String[] row : field) {
			System.out.print("| ");
			for (String cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println("|");
		}

		for (int i = 0; i < field[0].length * 2 + 2; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public String[][] getField() {
		return field;
	}
}
