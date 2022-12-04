package application;

public class FontChar {
	private String character;
	private int[][] bitmap = new int[8][8];
	private String uni;
	private char c;

	public String getUni() {
		return uni;
	}

	public void setUni(String uni) {
		this.uni = uni;
	}

	public FontChar(String s) {
		String str[] = s.split(":");
		this.character = "" + (char) Integer.parseInt(str[0]);
		for (int i = 1, z = 0; i < str.length; i++, z++) {
			String byte1 = str[i];
			int decimal = Integer.parseInt(byte1);
			String binary = Integer.toBinaryString(decimal);
			while (binary.length() < 8) {
				binary = "0" + binary;
			}
			for (int j = 0; j < binary.length(); j++) {
				if (binary.charAt(j) == '1') {
					bitmap[z][j] = 1;
				} else
					bitmap[z][j] = 0;
			}
		}
	}

	public FontChar(String character, int[][] bitmap) {
		this.character = character;
		this.bitmap = bitmap;
		String str[] = character.split(",");
		this.c = str[0].charAt(0);
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
