package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class TextFile {
	private String Text = "";
	private String ColorText;
	private Color clr;
	private String FontText;
	private String Title;
	private String size;
	private boolean valid = true;

	public TextFile(String s) {
		String[] str = s.split("`");
		if (str.length == 3) {
			String colorInt = str[0];
			int red = Integer.valueOf(colorInt.substring(2, 4), 16)/255;
			int green = Integer.valueOf(colorInt.substring(4, 6), 16)/255;
			int blue = Integer.valueOf(colorInt.substring(6, 8), 16)/255;
			int alpha = Integer.valueOf(colorInt.substring(8, 10), 16)/255;
			clr = new Color(red, green, blue, alpha);
			ColorText = clr.toString();
			FontText = str[1];
			for (int i = 0; i < str[2].length(); i++) {
				Text += str[2].charAt(i) + ":";
			}
		}
	}

	public Color getClr() {
		return clr;
	}

	public void setClr(Color clr) {
		this.clr = clr;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public TextFile(String txt, String clr, String fnt) {
		Text = txt.replaceAll(":", "");
		ColorText = clr;
		FontText = fnt;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public String getColorText() {
		return ColorText;
	}

	public void setColorText(String colorText) {
		ColorText = colorText;
	}

	public String getFontText() {
		return FontText;
	}

	public void setFontText(String fontText) {
		FontText = fontText;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	@Override
	public String toString() {
		return "" + ColorText + "`" + FontText + "`" + Text;
	}

}
