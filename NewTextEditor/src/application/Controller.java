package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {
	@FXML
	private ScrollPane scroll = new ScrollPane();
	@FXML
	private AnchorPane anc = new AnchorPane();
	@FXML
	private Group grp = new Group();
	@FXML
	MenuItem Choose = new MenuItem("Choose");
	@FXML
	MenuItem ColorChange = new MenuItem("Change Color");
	@FXML
	MenuItem Save = new MenuItem("Save File");
	@FXML
	MenuItem Open = new MenuItem("Open File");
	@FXML
	MenuItem Add = new MenuItem("Add");
	@FXML
	MenuItem Minus = new MenuItem("Minus");
	@FXML
	MenuItem Product = new MenuItem("Product");
	@FXML
	MenuItem Divide = new MenuItem("Divide");
	@FXML
	MenuItem eql = new MenuItem("Equal");
	@FXML
	MenuItem sqrt = new MenuItem("Square Root");
	@FXML
	MenuItem grt = new MenuItem("Greater");
	@FXML
	MenuItem less = new MenuItem("Less");
	@FXML
	MenuItem PI = new MenuItem("PI");
	@FXML
	Canvas c = new Canvas();
	static HashMap<String, FontChar> list = new HashMap<>();
	static int GridSize = 240;
	static Rectangle[][] grid = new Rectangle[GridSize][GridSize];
	static int countI = 0;
	static int countJ = 0;
	static int CursorX = 0;
	static int CursorY = 0;
	static int prevCursorX = 0;
	static int prevCursorY = 0;
	static Stack<Integer> previous = new Stack<>();
	static Color fontclr = Color.BLACK;
	static String fontstring = "C:\\Users\\Admin\\Graphics\\Project3Draft1\\myfnt.fnt";
	static String TEXT = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Initialize GRID
		scroll.setMaxHeight(1000);
		scroll.setMaxWidth(1000);
		int width = (int) scroll.getMaxWidth();
		int height = (int) scroll.getMaxHeight();
		int divisionX = width / GridSize;
		int divisionY = height / GridSize;
		for (int i = 1; i <= grid.length; i++) {
			for (int j = 1; j <= grid.length; j++) {
				grid[i - 1][j - 1] = new Rectangle((j - 1) * (divisionX), (i - 1) * (divisionY), (divisionX),
						(divisionY));
				grid[i - 1][j - 1].setFill(Color.WHITE);
				// grid[i - 1][j - 1].setStroke(Color.BLACK);
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grp.getChildren().add(grid[i][j]);
			}
		}
//read initial File
		File init = new File(fontstring);
		try {
			FileInputStream in = new FileInputStream(init);
			while (in.available() != 0) {
				String st = "";
				for (int i = 0; i < 9; i++) {
					st += in.read() + ":";
				}
				FontChar f = new FontChar(st);
				list.put(f.getCharacter(), f);
			}
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//put backspace
		list.put("\b", new FontChar("\b", new int[8][8]));
		//put enter
		list.put("\r", new FontChar("\r", new int[8][8]));
		//put space
		list.put(" ", new FontChar(" ", new int[8][8]));
		scroll.addEventFilter(KeyEvent.KEY_TYPED, e -> {
			String s = e.getCharacter();
			//check for backspace
			if (s.compareTo("\b") == 0 && CursorX > 0) {
				ClearSquare();
				CursorX -= 8;
			} else if (s.compareTo("\b") == 0 && CursorX == 0 && CursorY != 0) {

				ClearSquare();
				CursorY -= 8;
				CursorX = previous.pop();
			}
			//check for enter
			if (s.compareTo("\r") == 0 && CursorY+8<GridSize) {

				ClearSquare();
				CursorY += 8;
				previous.push(CursorX);
				CursorX = 0;
			}
			// Normal Characters
			if (list.get(e.getCharacter()) != null) {
				FontChar f = list.get(e.getCharacter());
				TEXT += e.getCharacter() + ":";
				if (CursorY < GridSize) {
					for (int i = CursorY, x = 0; i < 8 + CursorY; i++, x++) {
						for (int j = CursorX, z = 0; j < 8 + CursorX; j++, z++) {
							if (f.getBitmap()[x][z] == 1)
								grid[i][j].setFill(fontclr);
							else
								grid[i][j].setFill(Color.WHITE);
						}
					}
					if (CursorX + 8 < GridSize && !(s.compareTo("\b") == 0) && !(s.compareTo("\r") == 0)) {
						// prevCursorX = CursorX;
						CursorX += 8;
					} else if (CursorY + 8 < GridSize && !(s.compareTo("\b") == 0) && !(s.compareTo("\r") == 0)) {

						previous.push(CursorX);
						CursorX = 0;
						CursorY += 8;
					}
					if (CursorX < GridSize && CursorY < GridSize)
						DrawCursor(e.getCharacter());
				}
			}
		});
//		try {
//		Scanner in = new Scanner(init);
//		in.useDelimiter("\n");
//		while (in.hasNext()) {
//			String st = "";
//			for (int i = 0; i < 9; i++) {
//				st += in.next();
//			}
//			FontChar f = new FontChar(st);
//			list.put(f.getCharacter(), f);
//		}
//		in.close();
//	} catch (FileNotFoundException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
		ColorChange.setOnAction(e -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/smfxml.fxml"));
				Parent root2 = loader.load();
				Controller2 Controller2 = loader.getController();
				Stage color = new Stage();
				Scene scene = new Scene(root2);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				color.setScene(scene);
				color.show();
				Controller2.sliderR.valueProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observableValue, Number oldValue,
							Number newValue) {
						Controller2.TxtR.textProperty().setValue(String.valueOf(newValue.intValue()));
						float r = Float.parseFloat(Controller2.TxtR.getText());
						float g = Float.parseFloat(Controller2.TxtG.getText());
						float b = Float.parseFloat(Controller2.TxtB.getText());
						HSV hsv = new HSV();
						RGBtoHSV(r, g, b, hsv);
						Controller2.anc.setBackground(new Background(new BackgroundFill(
								new Color(r / 255, g / 255, b / 255, 1), CornerRadii.EMPTY, Insets.EMPTY)));
						DecimalFormat df = new DecimalFormat("###.#");
						Controller2.lblH.setText("H:" + String.valueOf(df.format(hsv.getH())));
						Controller2.lblS.setText("S:" + String.valueOf(df.format(hsv.getS())));
						Controller2.lblV.setText("V:" + String.valueOf(df.format(hsv.getV() / 255)));
					}
				});
				Controller2.sliderG.valueProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observableValue, Number oldValue,
							Number newValue) {
						Controller2.TxtG.textProperty().setValue(String.valueOf(newValue.intValue()));
						float r = Float.parseFloat(Controller2.TxtR.getText());
						float g = Float.parseFloat(Controller2.TxtG.getText());
						float b = Float.parseFloat(Controller2.TxtB.getText());
						HSV hsv = new HSV();
						RGBtoHSV(r, g, b, hsv);
						Controller2.anc.setBackground(new Background(new BackgroundFill(
								new Color(r / 255, g / 255, b / 255, 1), CornerRadii.EMPTY, Insets.EMPTY)));
						DecimalFormat df = new DecimalFormat("###.#");
						Controller2.lblH.setText("H:" + String.valueOf(df.format(hsv.getH())));
						Controller2.lblS.setText("S:" + String.valueOf(df.format(hsv.getS())));
						Controller2.lblV.setText("V:" + String.valueOf(df.format(hsv.getV() / 255)));
					}
				});
				Controller2.sliderB.valueProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observableValue, Number oldValue,
							Number newValue) {
						Controller2.TxtB.textProperty().setValue(String.valueOf(newValue.intValue()));
						float r = Float.parseFloat(Controller2.TxtR.getText());
						float g = Float.parseFloat(Controller2.TxtG.getText());
						float b = Float.parseFloat(Controller2.TxtB.getText());
						HSV hsv = new HSV();
						RGBtoHSV(r, g, b, hsv);
						Controller2.anc.setBackground(new Background(new BackgroundFill(
								new Color(r / 255, g / 255, b / 255, 1), CornerRadii.EMPTY, Insets.EMPTY)));
						DecimalFormat df = new DecimalFormat("###.#");
						Controller2.lblH.setText("H:" + String.valueOf(df.format(hsv.getH())));
						Controller2.lblS.setText("S:" + String.valueOf(df.format(hsv.getS())));
						Controller2.lblV.setText("V:" + String.valueOf(df.format(hsv.getV() / 255)));
					}
				});
				Controller2.change.setOnAction(g -> {
					BackgroundFill clr = Controller2.anc.getBackground().getFills().get(0);
					Color clrs = (Color) clr.getFill();
					for (int i = 0; i < grid.length; i++) {
						for (int j = 0; j < grid.length; j++) {
							if (grid[i][j].getFill() == fontclr) {
								grid[i][j].setFill(clrs);
							}
						}
					}
					fontclr = clrs;
				});
			} catch (Exception b) {
				b.printStackTrace();
			}
		});
		Choose.setOnAction(e -> {
			FileChooser chooser = new FileChooser();
			chooser.setInitialDirectory(new File("C:/Users/Admin/Graphics/Project3Draft1"));
			chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Font (*.fnt)", "*.fnt"));
			File selected = chooser.showOpenDialog(new Stage());
			if (selected != null) {
				fontstring = selected.getPath();
				list = new HashMap<>();
				try {
					FileInputStream in = new FileInputStream(selected);
					while (in.available() != 0) {
						String st = "";
						for (int i = 0; i < 9; i++) {
							st += in.read() + ":";
						}
						FontChar f = new FontChar(st);
						list.put(f.getCharacter(), f);
					}
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				list.put("\b", new FontChar("\b", new int[8][8]));
				list.put("\r", new FontChar("\r", new int[8][8]));
				list.put(" ", new FontChar(" ", new int[8][8]));
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid.length; j++) {
						grid[i][j].setFill(Color.WHITE);
					}
				}
				Rewrite();
			}
		});
		Save.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();

			// Set extension filter for text files
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			// Show save file dialog
			File file = fileChooser.showSaveDialog(new Stage());

			if (file != null) {
				try {
					PrintWriter writer;
					writer = new PrintWriter(file);
					TextFile txt = new TextFile(TEXT, fontclr.toString().toUpperCase(), fontstring);
					writer.write(txt.toString());
					writer.close();
				} catch (IOException ex) {

				}
			}
		});
		Open.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();

			// Set extension filter for text files
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			// Show save file dialog
			File file = fileChooser.showOpenDialog(new Stage());
			if (file != null) {
				try {
					Scanner in = new Scanner(file);
					String st = "";
					in.useDelimiter("`");
					while (in.hasNext()) {
						st += in.next() + "`";
					}
					TextFile txt = new TextFile(st);
					TEXT = txt.getText();
					fontclr = txt.getClr();
					fontstring = txt.getFontText();
					list = new HashMap<>();
					try {
						FileInputStream in2 = new FileInputStream(fontstring);
						while (in2.available() != 0) {
							String st2 = "";
							for (int i = 0; i < 9; i++) {
								st2 += in2.read() + ":";
							}
							FontChar f = new FontChar(st2);
							list.put(f.getCharacter(), f);
						}
						in2.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list.put("\b", new FontChar("\b", new int[8][8]));
					list.put("\r", new FontChar("\r", new int[8][8]));
					list.put(" ", new FontChar(" ", new int[8][8]));
					Rewrite();
					in.close();
				} catch (IOException ex) {

				}
			}
		});
		Add.setGraphic(new ImageView(drawOnImg(list.get("!").getBitmap())));
		Add.setOnAction(e -> {
			Insert(list.get("!"));
		});
		Minus.setGraphic(new ImageView(drawOnImg(list.get("@").getBitmap())));
		Minus.setOnAction(e -> {
			Insert(list.get("@"));
		});
		Product.setGraphic(new ImageView(drawOnImg(list.get("#").getBitmap())));
		Product.setOnAction(e -> {
			Insert(list.get("#"));
		});
		Divide.setGraphic(new ImageView(drawOnImg(list.get("$").getBitmap())));
		Divide.setOnAction(e -> {
			Insert(list.get("$"));
		});
		eql.setGraphic(new ImageView(drawOnImg(list.get("%").getBitmap())));
		eql.setOnAction(e -> {
			Insert(list.get("%"));
		});
		sqrt.setGraphic(new ImageView(drawOnImg(list.get("^").getBitmap())));
		sqrt.setOnAction(e -> {
			Insert(list.get("^"));
		});
		grt.setGraphic(new ImageView(drawOnImg(list.get("&").getBitmap())));
		grt.setOnAction(e -> {
			Insert(list.get("&"));
		});
		less.setGraphic(new ImageView(drawOnImg(list.get("*").getBitmap())));
		less.setOnAction(e -> {
			Insert(list.get("*"));
		});
		PI.setGraphic(new ImageView(drawOnImg(list.get("(").getBitmap())));
		PI.setOnAction(e -> {
			Insert(list.get("("));
		});
	}

	public void Insert(FontChar f) {
		if (f != null) {
			TEXT += f.getCharacter() + ":";
			if (CursorY < GridSize) {
				for (int i = CursorY, x = 0; i < 8 + CursorY; i++, x++) {
					for (int j = CursorX, z = 0; j < 8 + CursorX; j++, z++) {
						if (f.getBitmap()[x][z] == 1)
							grid[i][j].setFill(fontclr);
						else
							grid[i][j].setFill(Color.WHITE);
					}
				}
				if (CursorX + 8 < GridSize) {
					// prevCursorX = CursorX;
					CursorX += 8;
				} else if (CursorY + 8 < GridSize) {
					previous.push(CursorX);
					CursorX = 0;
					CursorY += 8;
				}
				if (CursorX < GridSize && CursorY < GridSize)
					DrawCursor("" + f.getCharacter());
			}
		}
	}

	public Image drawOnImg(int[][] bm) {
		GraphicsContext g = c.getGraphicsContext2D();
		g.clearRect(0, 0, c.getWidth(), c.getHeight());
		g.beginPath();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (bm[i][j] == 1) {
					g.strokeRect(grid[i][j].getX(), grid[i][j].getY(), grid[i][j].getWidth(), grid[i][j].getHeight());
					g.fillRect(grid[i][j].getX(), grid[i][j].getY(), grid[i][j].getWidth(), grid[i][j].getHeight());
				}
			}
		}
		SnapshotParameters s = new SnapshotParameters();
		WritableImage w = c.snapshot(s, null);
		return w;
	}

	public void DrawCursor(String e) {
		int[][] inv = { { 0, 0, 0, 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0 } };
		for (int i = CursorY, x = 0; i < 8 + CursorY; i++, x++) {
			for (int j = CursorX, z = 0; j < 8 + CursorX; j++, z++) {
				if (inv[x][z] == 1)
					grid[i][j].setFill(fontclr);
				else
					grid[i][j].setFill(Color.WHITE);
			}
		}
	}

	public void ClearSquare() {
		for (int i = CursorY, x = 0; i < 8 + CursorY; i++, x++) {
			for (int j = CursorX, z = 0; j < 8 + CursorX; j++, z++) {
				grid[i][j].setFill(Color.WHITE);
			}
		}
	}

	public void Rewrite() {
		CursorX = 0;
		CursorY = 0;
		String[] msg = TEXT.split(":");
		TEXT = "";
		for (int k = 0; k < msg.length; k++) {
			String s = "" + msg[k];
			if (s.compareTo("\b") == 0 && CursorX > 0) {
				ClearSquare();
				CursorX -= 8;
			} else if (s.compareTo("\b") == 0 && CursorX == 0 && CursorY != 0) {

				ClearSquare();
				CursorY -= 8;
				CursorX = previous.pop();
			}
			if (s.compareTo("\r") == 0) {

				ClearSquare();
				CursorY += 8;
				previous.push(CursorX);
				CursorX = 0;
			}
			// Normal Characters
			if (list.get("" + msg[k]) != null) {
				FontChar f = list.get("" + msg[k]);
				TEXT += msg[k] + ":";
				if (CursorY < GridSize) {
					for (int i = CursorY, x = 0; i < 8 + CursorY; i++, x++) {
						for (int j = CursorX, z = 0; j < 8 + CursorX; j++, z++) {
							if (f.getBitmap()[x][z] == 1)
								grid[i][j].setFill(fontclr);
							else
								grid[i][j].setFill(Color.WHITE);
						}
					}
					if (CursorX + 8 < GridSize && !(s.compareTo("\b") == 0) && !(s.compareTo("\r") == 0)) {
						// prevCursorX = CursorX;
						CursorX += 8;
					} else if (CursorY + 8 < GridSize && !(s.compareTo("\b") == 0) && !(s.compareTo("\r") == 0)) {

						previous.push(CursorX);
						CursorX = 0;
						CursorY += 8;
					}
					if (CursorX < GridSize && CursorY < GridSize)
						DrawCursor("" + msg[k]);
				}
			}
		}
	}

	public static void RGBtoHSV(float r, float g, float b, HSV hsv) {
		double max = Math.max(r, Math.max(g, b));
		double min = Math.min(r, Math.min(g, b));
		double mid = 0;
		if ((r <= g && g <= b) || (b <= g && g <= r))
			mid = g;
		else if ((g <= r && r <= b) || (b <= r && r <= g))
			mid = r;
		else
			mid = b;
		double delta = max - min;
		hsv.setV(max);
		if (hsv.getV() != 0) {
			hsv.setS(delta / max);
		} else
			hsv.setS(0);
		if (hsv.getS() == 0) {
			hsv.setH(0);
		} else {
			double alpha = 60 * ((mid - min) / (max - min));
			if ((r == max) && (b == min))
				hsv.setH(alpha);
			else if ((g == max) && (b == min))
				hsv.setH(120 - alpha);
			else if ((g == max) && (r == min))
				hsv.setH(alpha + 120);
			else if ((b == max) && (r == min))
				hsv.setH(240 - alpha);
			else if ((b == max) && (g == min))
				hsv.setH(alpha + 240);
			else
				hsv.setH(360 - alpha);
		}
	}
}
