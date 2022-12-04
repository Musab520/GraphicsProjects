package application;

public class FontChar {
	private String character;
	private int[][] bitmap;
	private String uni;
	private char c;

	public String getUni() {
		return uni;
	}

	public void setUni(String uni) {
		this.uni = uni;
	}

	public FontChar(String character, int[][] bitmap) {
		this.character = character;
		this.bitmap = bitmap;
		this.c = character.charAt(0);
	}
	public FontChar(String character) {
		this.character = character;
		this.bitmap = null;
	}
	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public int[][] getBitmap() {
		return bitmap;
	}

	public void setBitmap(int[][] bitmap) {
		this.bitmap = bitmap;
	}

}
