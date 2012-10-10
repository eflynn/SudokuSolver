
public final class Square {
	private final char letter;
	private final int digit;
	
	public Square(char letter, int digit) {
		this.letter = letter;
		this.digit = digit;
	}
	
	public char getLetter() {
		return letter;
	}
	public int getDigit() {
		return digit;
	}
	
	@Override
	public int hashCode() {
		return 31 * letter + digit;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Square)) {
			return false;
		}
		
		Square s = (Square) other;
		return letter == s.letter && digit == s.digit;  
	}
	
	@Override
	public String toString() {
		return "[" + Character.toString(letter) + "], [" + digit + "] ";
	}
	
}
