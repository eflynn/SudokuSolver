import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class to solve any Sudoku puzzle through recursion
 */
public class SudokuSolver {
	private static final int INPUT_DATA_LIST = 27;
	private static final int BOX_SIZE = 3;
	private static final int ROWS = 9;
	private static final int COLS = 9;
	private final String fileName;
	private int[][] grid = new int[ROWS][COLS];
	public int size;

	/**
	 * Constructor receives name of the file from command line and passes to
	 * readIn()
	 * 
	 * @param fileName
	 * 
	 */
	public SudokuSolver(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		readIn(fileName);
	}

	/**
	 * A method to read the numbers for the Sudoku game from an input file and
	 * populate the board
	 * 
	 * @throws FileNotFoundException
	 */
	private void readIn(String sudokuFile) throws FileNotFoundException {
		Scanner file = new Scanner(new File(sudokuFile));
		int xCoord = 0;
		int yCoord = 0;

		for (int i = 0; i < INPUT_DATA_LIST; i++) {
			String input = file.next();
			for (int n = 0; n < BOX_SIZE; n++) {
				grid[xCoord][yCoord] = Character.digit(input.charAt(n), 10);
				yCoord++;

				if (yCoord == ROWS) {
					yCoord = 0;
					xCoord++;
				}
			}
		}

		file.close();
	}

	/**
	 * A method to print the board as a String
	 * 
	 * @return the grid as a String
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder(fileName);

		builder.append("\n+--------------------+\n|");

		for (int xCoord = 0; xCoord < COLS; xCoord++) {
			for (int yCoord = 0; yCoord < ROWS; yCoord++) {
				if (xCoord == BOX_SIZE && yCoord == 0)
					builder.append("------+------+------|\n|");
				else if (xCoord == 2 * BOX_SIZE && yCoord == 0)
					builder.append("------+------+------|\n|");

				if (grid[xCoord][yCoord] == 0)
					builder.append(". ");
				else
					builder.append(grid[xCoord][yCoord] + " ");

				if ((yCoord + 1) % BOX_SIZE == 0)
					builder.append("|");
				if (yCoord == ROWS - 1 && xCoord != COLS - 1)
					builder.append("\n|");
			}
		}
		builder.append("\n+--------------------+\n|");

		return builder.toString();
	}

	/**
	 * A method to recursively solve Sudoku puzzles by testing a number and
	 * attempting to solve the rest of the board
	 * 
	 * @param xCoord
	 *            the starting x value of the grid
	 * @param yCoord
	 *            the starting y value of the grid
	 * @return true when board is complete
	 */

	private boolean puzzleSolver(int xCoord, int yCoord) {
		size++;
		if (xCoord == ROWS) {
			xCoord = 0;
			yCoord++;
			if (yCoord == COLS) {
				System.out.println(size);
				return true;
			}
		}
		if (grid[xCoord][yCoord] != 0) {
			return puzzleSolver(xCoord + 1, yCoord);
		}
		for (int solution = 1; solution <= ROWS; solution++) {
			if (cellIsValid(xCoord, yCoord, solution)) {
				grid[xCoord][yCoord] = solution;
				if (puzzleSolver(xCoord + 1, yCoord))
					return true;
			}
		}

		grid[xCoord][yCoord] = 0;

		return false;
	}

	/**
	 * A method to check the validity of a possible solution in each cell within
	 * the puzzle by checking the row, column, and box the cell lies in.
	 * 
	 * @param xCoord
	 *            x coordinate of the cell
	 * @param yCoord
	 *            y coordinate of the cell
	 * @param candidate
	 *            the value of the cell to check
	 * @return true if the entire puzzle remains valid with the solution in that
	 *         cell
	 */
	private boolean cellIsValid(int xCoord, int yCoord, int candidate) {

		for (int testPosition = 0; testPosition < ROWS; testPosition++) {
			if (grid[xCoord][testPosition] == candidate)
				return false;
		}
		for (int testPosition = 0; testPosition < COLS; testPosition++) {
			if (grid[testPosition][yCoord] == candidate)
				return false;
		}
		return boxIsValid(xCoord, yCoord, candidate);

	}

	/**
	 * Checks each potential answer in the 3 by 3 box to see if it invalidates
	 * the puzzle.
	 * 
	 * @param xCoord
	 * @param yCoord
	 * @param candidate
	 * @return
	 */
	private boolean boxIsValid(int xCoord, int yCoord, int candidate) {
		int startingRow = xCoord / 3 * 3;
		int startingColumn = yCoord / 3 * 3;
		for (int j = startingRow; j < startingRow + 3; j++) {
			for (int k = startingColumn; k < startingColumn + 3; k++) {
				if (grid[j][k] == candidate)
					return false;
			}
		}
		return true;
	}

	/**
	 * Implementation starts here.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner kbd = new Scanner(System.in);
		System.out
				.print("Enter the filename of the puzzle you would like to solve: ");
		String line = kbd.nextLine();
		while (line.length() == 0) {
			System.out.println("Please enter a valid puzzle filename");
			line = kbd.nextLine();
		}
		kbd.close();

		SudokuSolver newGame = new SudokuSolver(line);
		System.out.println(newGame);
		newGame.puzzleSolver(0, 0);
		System.out.println(newGame);

	}
}
