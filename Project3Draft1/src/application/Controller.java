package application;

import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import fontastic.*;
import processing.core.PApplet;
import processing.core.PVector;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Controller extends PApplet implements Initializable {
	private PApplet applet;
	@FXML
	TextField fntnm;
	@FXML
	Button add = new Button();
	@FXML
	Pane p = new Pane();
	@FXML
	Canvas c = new Canvas();
	@FXML
	Button clear = new Button();
	@FXML
	Button save = new Button();
	@FXML
	ImageView imgview;
	@FXML
	TextField txt = new TextField();
	@FXML
	Group grp = new Group();
	Rectangle[][] grid = new Rectangle[8][8];
	int[][] bm = new int[8][8];
	ArrayList<FontChar> list = new ArrayList<>();
	GraphicsContext g = c.getGraphicsContext2D();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//	Fontastic f = new Fontastic(this, "MyFont");
//		f.setAuthor("Musab Abuasi");
//		PVector[] points = new PVector[64];
		// Characters
		String[] chars = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "!", "@", "#",
				"$", "%", "^", "&", "*", "(", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
				"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
//Add characters to arraylist and create FontChars 
		for (int i = 0; i < 255; i++) {
			list.add(new FontChar("" + (char) i, null));
		}
//		for (int i = 0; i < chars.length; i++) {
//			list.add(new FontChar("" + chars[i], null));
//		}
		GraphicsContext g = c.getGraphicsContext2D();
		int width = (int) c.getWidth();
		int height = (int) c.getHeight();
		int divisionX = width / 8;
		int divisionY = height / 8;
		// Initialize Grid
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				grid[i - 1][j - 1] = new Rectangle((j - 1) * (divisionX), (i - 1) * (divisionY), (divisionX),
						(divisionY));
				grid[i - 1][j - 1].setFill(Color.WHITE);
				grid[i - 1][j - 1].setStroke(Color.BLACK);
			}
		}
		// add rectangles to group
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grp.getChildren().add(grid[i][j]);
			}
		}
//		g.setStroke(Color.BLACK);
//		g.setFill(Color.BLACK);
//		g.strokeRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
//Mouse Drag Drawing Event
		grp.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
			g.setStroke(Color.BLACK);
			g.setFill(Color.BLACK);
			double x = e.getX();
			double y = e.getY();
			for (int i = 0, z = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++, z++) {
					if (grid[i][j].contains(new Point2D(x, y))) {
						grid[i][j].setFill(Color.BLACK);
						bm[i][j] = 1;
						// points[z] = new PVector((int) grid[i][j].getX(), (int) grid[i][j].getY());
					}
				}
			}

//			g.moveTo(e.getX(), e.getY());
//			g.lineTo(e.getX(), e.getY());
//			g.stroke();
		});
		// Fill in on Mouse Pressed
		grp.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
			double x = e.getX();
			double y = e.getY();
			for (int i = 0, z = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++, z++) {
					if (grid[i][j].contains(new Point2D(x, y))) {
						if (bm[i][j] == 0) {
							grid[i][j].setFill(Color.BLACK);
							bm[i][j] = 1;
						} else {
							grid[i][j].setFill(Color.WHITE);
							bm[i][j] = 0;
						}
						// points[z] = new PVector((int) grid[i][j].getX(), (int) grid[i][j].getY());
					}
				}
			}
		});
		// Clear Grid
		clear.setOnAction(e -> {
			clears();
		});
		add.setOnAction(e -> {
			int index = -1;
			// Search Character List for Letter Key and set bitmap 
			if ((!txt.getText().isBlank())) {
				for (int i = 0; i < list.size(); i++) {
					FontChar font = new FontChar(txt.getText());
					if (Character.compare(list.get(i).getCharacter().charAt(0), font.getCharacter().charAt(0)) == 0) {
						index = i;
					}
				}
				if (index != -1) {
					int[][] bitmap = new int[8][8];
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {

							bitmap[i][j] = bm[i][j];
						}

					}
					list.get(index).setBitmap(bitmap);
					Alert a = new Alert(AlertType.CONFIRMATION, "Add Succesful");
					a.show();
					txt.clear();
					clears();
				} else {
					Alert a = new Alert(AlertType.CONFIRMATION, "Please Enter a Valid Character");
					a.show();
				}
			} else {
				Alert a = new Alert(AlertType.CONFIRMATION, "Please Enter a Valid Character");
				a.show();
			}
//			g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
//			g.beginPath();
//			for (int i = 0; i < 8; i++) {
//				for (int j = 0; j < 8; j++) {
//					if (bm[i][j] == 1) {
//						g.strokeRect(grid[i][j].getX(), grid[i][j].getY(), grid[i][j].getWidth(),
//								grid[i][j].getHeight());
//						g.fillRect(grid[i][j].getX(), grid[i][j].getY(), grid[i][j].getWidth(), grid[i][j].getHeight());
//					}
//				}
//			}
//			f.addGlyph(txt.getText().charAt(0)).addContour(points);
//			SnapshotParameters s = new SnapshotParameters();
//			WritableImage w = c.snapshot(s, null);
//			try {
//				ImageIO.write(SwingFXUtils.fromFXImage(w, null), "png",
//						new File("C://Users/Admin/Desktop/Birzeit Folder/Year 3 Semester 1/Graphics/fontimgs/char"
//								+ (int) txt.getText().charAt(0) + ".png"));
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			Image dest = new Image(
//					"file:C:\\Users\\Admin\\Desktop\\Birzeit Folder\\Year 3 Semester 1\\Graphics\\fontimgs\\char"
//							+ (int) txt.getText().charAt(0) + ".png",
//					50, 50, true, true);
//			WritableImage wrt = new WritableImage(50, 50);
//			PixelReader imageReader = dest.getPixelReader();
//			PixelWriter writer = wrt.getPixelWriter();
//			for (int x = 0; x < 45; x++) {
//				for (int y = 0; y < 50; y++) {
//
//					Color imageColor = imageReader.getColor(x, y);
//
//					if (imageColor.equals(Color.BLACK)) {
//						writer.setColor(x, y, Color.RED);
//					}
//
//				}
//			}
//			g.drawImage(wrt, 0, 0, 50, 50);

		});
		//Saving Font
		save.setOnAction(e -> {
			if (!fntnm.getText().isBlank()) {
				try {
					BufferedWriter out = new BufferedWriter(
							new FileWriter("/C:/Users/Admin/Graphics/Project3Draft1/" + fntnm.getText() + ".txt"));
					FileOutputStream outs = new FileOutputStream(
							"/C:/Users/Admin/Graphics/Project3Draft1/" + fntnm.getText() + ".fnt");
					StringBuilder builder = new StringBuilder();
//					builder.append("STARFONT 2.1" + "\n");
//					builder.append("FONT -gnu-unifont-medium-r-normal--8-80-75-75-c-80-iso10646-1" + "\n");
//					builder.append("SIZE 8 75 75" + "\n");
//					builder.append("FONTBOUNDINGBOX 8 8 0 -2" + "\n");
//					builder.append("STARTPROPERTIES 2" + "\n");
//					builder.append("FONT_ASCENT 14" + "\n");
//					builder.append("FONT_DESCENT 2" + "\n");
//					builder.append("ENDPROPERTIES" + "\n");
//					builder.append("CHARS 45" + "\n");

					for (int x = 0; x < list.size(); x++) {
//						builder.append("STARTCHAR U+" + list.get(x).getUni() + "\n");
//						builder.append("ENCODING " + (int) list.get(x).getC() + "\n");
//						builder.append("SWIDTH 500 0" + "\n");
//						builder.append("DWIDTH 8 0" + "\n");
//						builder.append("BBX 8 8 0 -2" + "\n");
//						builder.append("BITMAP" + "\n");
						builder.append("\n" + list.get(x).getCharacter().charAt(0) + ":" + "\n");
						outs.write((int) list.get(x).getCharacter().charAt(0));
						if (list.get(x).getBitmap() != null) {
							for (int i = 0; i < list.get(x).getBitmap().length; i++)// for each row
							{
								String st = "";
								for (int j = 0; j < list.get(x).getBitmap().length; j++)// for each column
								{
									st = st + list.get(x).getBitmap()[i][j];
								}
								int decimal = Integer.parseInt(st, 2);
								// byte decimal2 = Byte.parseByte(st, 2);
								outs.write(decimal);
								String str = "";
								if (decimal != 0)
									str = Integer.toString(decimal, 16);
								else
									str = "00";
								str = str.toUpperCase();
								builder.append(str);
								builder.append("\n");// append new line at the end of the row
							}
						} else {
							builder.append("FF");
							outs.write(Integer.parseInt("FF", 16));
							builder.append("\n");
							builder.append("C3");
							outs.write(Integer.parseInt("C3", 16));
							builder.append("\n");
							builder.append("A5");
							outs.write(Integer.parseInt("A5", 16));
							builder.append("\n");
							builder.append("99");
							outs.write(Integer.parseInt("99", 16));
							builder.append("\n");
							builder.append("A5");
							outs.write(Integer.parseInt("A5", 16));
							builder.append("\n");
							builder.append("C3");
							outs.write(Integer.parseInt("C3", 16));
							builder.append("\n");
							builder.append("FF");
							outs.write(Integer.parseInt("FF", 16));
							outs.write(Integer.parseInt("00", 16));
//							int[][] inv = { { 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 1 },
//									{ 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 1 },
//									{ 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 1 },
//									{ 0, 0, 0, 0, 0, 0, 0, 1 } };
//							for (int i = 0; i < inv.length; i++)// for each row
//							{
//								for (int j = 0; j < inv.length; j++)// for each column
//								{
//									builder.append(inv[i][j] + "");// append to the output string
//									if (j < inv.length - 1)// if this is not the last row element
//										builder.append(",");
//								}
//								builder.append("\n");
//							}
						}
						// builder.append("ENDCHAR" + "\n");
					}
					// builder.append("ENDFONT");
					out.write(builder.toString());// save the string representation of the board
					outs.close();
					out.close();
					Alert a = new Alert(AlertType.CONFIRMATION, "Font Saved Succesfully");
					a.show();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				Alert a = new Alert(AlertType.CONFIRMATION, "Please Enter a Valid Font Name");
				a.show();
			}
		});
	}

	public void clears() {
		g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
		g.beginPath();
//		g.strokeRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				grid[i][j].setFill(Color.WHITE);
				System.out.print("" + bm[i][j] + " ");
				bm[i][j] = 0;
			}
			System.out.println("");
		}
	}

}
