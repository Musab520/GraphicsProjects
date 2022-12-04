import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import java.awt.Canvas;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Driver implements GLEventListener {

	private JFrame frame;
	private JTextField slider2;
	private JTextField slider3;
	private JTextField slider1;
	private JTextField top1;
	private JTextField top2;
	private JTextField top3;
	private JTextField bottom1;
	private JTextField bottom2;
	private JTextField bottom3;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JLabel lbl6;
	private JLabel lblrgb1;
	private JLabel lblrgb2;
	private JLabel lblrgb3;
	private JSlider one;
	private JSlider two;
	private JSlider three;
	private static JPanel pane;

	/**
	 * Launch the application.
	 */
	static ColorofShapes shapes = new ColorofShapes();// contains Color of Shapes
	static ShapeRanges ranges = new ShapeRanges();//contains Shape Objects
	static final GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	static final GLCanvas glcanvas = new GLCanvas(capabilities);
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();

	public static void main(String[] args) {
		Driver app = new Driver();
		glcanvas.addGLEventListener(app);
		glcanvas.setSize(800, 800);
		glcanvas.addMouseListener((MouseListener) new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color c = pane.getBackground();
				//if else statements that check the whole house inside to out
				if (ranges.getTri().InTri(e.getX(), e.getY())) {
					shapes.setTriangle(c);
				} else if (ranges.getRoof().contains(new Point(e.getX(), e.getY()))) {
					shapes.setRoof(c);
				} else if (ranges.getBack().contains(new Point(e.getX(), e.getY()))) {
					shapes.setBack(c);
				} else if (ranges.getCenterWin().InCircle(e.getX(), e.getY())
						&& ranges.getCenterWin().isQ(e.getX(), e.getY()) == 1) {
					shapes.setCenterWin1(c);
				} else if (ranges.getCenterWin().InCircle(e.getX(), e.getY())
						&& ranges.getCenterWin().isQ(e.getX(), e.getY()) == 2) {
					shapes.setCenterWin2(c);
				} else if (ranges.getCenterWin().InCircle(e.getX(), e.getY())
						&& ranges.getCenterWin().isQ(e.getX(), e.getY()) == 3) {
					shapes.setCenterWin3(c);
				} else if (ranges.getCenterWin().InCircle(e.getX(), e.getY())
						&& ranges.getCenterWin().isQ(e.getX(), e.getY()) == 4) {
					shapes.setCenterWin4(c);
				} else if (ranges.getHandle1().InCircle(e.getX(), e.getY())) {
					shapes.setHandle1(c);
				} else if (ranges.getHandle2().InCircle(e.getX(), e.getY())) {
					shapes.setHandle2(c);
				} else if (ranges.getDoor2().isInside(e.getX(), e.getY())) {
					shapes.setDoor2(c);
				} else if (ranges.getDoor1().isInside(e.getX(), e.getY())) {
					shapes.setDoor1(c);
				} else if (ranges.getWin1cur1().InTri(e.getX(), e.getY())) {
					shapes.setWin1Cur1(c);
				} else if (ranges.getWin1cur2().InTri(e.getX(), e.getY())) {
					shapes.setWin1Cur2(c);
				} else if (ranges.getWin1cur3().InTri(e.getX(), e.getY())) {
					shapes.setWin1Cur3(c);
				} else if (ranges.getWin1cur4().InTri(e.getX(), e.getY())) {
					shapes.setWin1Cur4(c);
				} else if (ranges.getWindow1().isInside(e.getX(), e.getY())) {
					shapes.setWindow1(c);
				} else if (ranges.getWin2cur1().InTri(e.getX(), e.getY())) {
					shapes.setWin2Cur1(c);
				} else if (ranges.getWin2cur2().InTri(e.getX(), e.getY())) {
					shapes.setWin2Cur2(c);
				} else if (ranges.getWin2cur3().InTri(e.getX(), e.getY())) {
					shapes.setWin2Cur3(c);
				} else if (ranges.getWin2cur4().InTri(e.getX(), e.getY())) {
					shapes.setWin2Cur4(c);
				} else if (ranges.getWindow2().isInside(e.getX(), e.getY())) {
					shapes.setWindow2(c);
				} else if (ranges.getFrntsq().isInside(e.getX(), e.getY())) {
					shapes.setFrontSquare(c);
				}
				glcanvas.display();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver window = new Driver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Driver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1242, 930);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Switch Color Modes between RGB, CMY and HSV:");
		lblNewLabel.setBounds(51, 66, 335, 14);
		frame.getContentPane().add(lblNewLabel);

		JRadioButton rgb = new JRadioButton("RGB");
		rgb.setSelected(true);
		rgb.setAction(action);
		rgb.setBounds(25, 87, 57, 23);
		frame.getContentPane().add(rgb);

		JRadioButton cmy = new JRadioButton("CMY");
		cmy.setAction(action_1);
		cmy.setBounds(84, 87, 59, 23);
		frame.getContentPane().add(cmy);

		JRadioButton hsv = new JRadioButton("HSV");
		hsv.setAction(action_2);
		hsv.setBounds(146, 87, 53, 23);
		frame.getContentPane().add(hsv);
		ButtonGroup group = new ButtonGroup();
		group.add(rgb);
		group.add(cmy);
		group.add(hsv);
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(69, 126, 256, 35);
		frame.getContentPane().add(horizontalBox);

		lblrgb1 = new JLabel("R");
		horizontalBox.add(lblrgb1);

		one = new JSlider();
		horizontalBox.add(one);
		one.setMaximum(255);
		one.setMinimum(0);
		one.setValue(0);
		slider1 = new JTextField();
		horizontalBox.add(slider1);
		slider1.setColumns(10);

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBounds(69, 179, 256, 35);
		frame.getContentPane().add(horizontalBox_1);
		lblrgb2 = new JLabel("G");
		horizontalBox_1.add(lblrgb2);
		DecimalFormat df = new DecimalFormat("###.#");
		one.setPaintLabels(true);
		one.setLabelTable(one.createStandardLabels(255));
		//change listener for sliders
ChangeListener change=new ChangeListener() {

	public void stateChanged(ChangeEvent e) {
		slider1.setText("" + one.getValue());
		slider2.setText("" + two.getValue());
		slider3.setText("" + three.getValue());
		if (rgb.isSelected()) {
			pane.setBackground(new Color(one.getValue(), two.getValue(), three.getValue()));
			top1.setText("" + df.format(255 - one.getValue()));
			top2.setText("" + df.format(255 - two.getValue()));
			top3.setText("" + df.format(255 - three.getValue()));
			HSV hsv = new HSV();
			RGBtoHSV((float) one.getValue() / 255, (float) two.getValue() / 255, (float) three.getValue() / 255,
					hsv);
			bottom1.setText("" + df.format((float) hsv.getH()));
			bottom2.setText("" + df.format((float) hsv.getS()));
			bottom3.setText("" + df.format((float) hsv.getV()));
		} else if (cmy.isSelected()) {
			pane.setBackground(new Color(255 - one.getValue(), 255 - two.getValue(), 255 - three.getValue()));
			top1.setText("" + df.format(255 - one.getValue()));
			top2.setText("" + df.format(255 - two.getValue()));
			top3.setText("" + df.format(255 - three.getValue()));
			HSV hsv = new HSV();
			RGBtoHSV((float) (255 - one.getValue()) / 255, (float) (255 - two.getValue()) / 255,
					(float) (255 - three.getValue()) / 255, hsv);
			bottom1.setText("" + df.format((float) hsv.getH()));
			bottom2.setText("" + df.format((float) hsv.getS()));
			bottom3.setText("" + df.format((float) hsv.getV()));
		} else if (hsv.isSelected()) {
			if ((one.getMaximum() == 360 && two.getMaximum() == 100) && three.getMaximum() == 100) {
				RGB rgbobj = new RGB();
				HSVtoRGB(one.getValue(), (float) two.getValue() / 100, (float) three.getValue() / 100, rgbobj);
				pane.setBackground(new Color((int) (rgbobj.getR() * 255), (int) (rgbobj.getG() * 255),
						(int) (rgbobj.getB() * 255)));
				top1.setText("" + df.format((float) rgbobj.getR() * 255));
				top2.setText("" + df.format((float) rgbobj.getG() * 255));
				top3.setText("" + df.format((float) rgbobj.getB() * 255));
				bottom1.setText("" + df.format((float) (1 - rgbobj.getR()) * 255));
				bottom2.setText("" + df.format((float) (1 - rgbobj.getG()) * 255));
				bottom3.setText("" + df.format((float) (1 - rgbobj.getB()) * 255));
			}
		}
	
}};
		one.addChangeListener(change);
		two = new JSlider();
		horizontalBox_1.add(two);
		two.setMaximum(255);
		two.setMinimum(0);
		two.setValue(0);
		slider2 = new JTextField();
		horizontalBox_1.add(slider2);
		slider2.setColumns(10);
		two.setPaintLabels(true);
		two.setLabelTable(two.createStandardLabels(255));
		two.addChangeListener(change);
		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setBounds(69, 248, 256, 35);
		frame.getContentPane().add(horizontalBox_2);

		lblrgb3 = new JLabel("B");

		horizontalBox_2.add(lblrgb3);

		three = new JSlider();
		horizontalBox_2.add(three);
		three.setMaximum(255);
		three.setMinimum(0);
		three.setValue(0);
		slider3 = new JTextField();
		horizontalBox_2.add(slider3);
		slider3.setColumns(10);
		three.setPaintLabels(true);
		three.setLabelTable(three.createStandardLabels(255));
		three.addChangeListener(change);
		pane = new JPanel();
		pane.setBackground(Color.BLACK);
		pane.setBounds(51, 400, 99, 90);
		frame.getContentPane().add(pane);

		lbl1 = new JLabel("C=");
		lbl1.setBounds(26, 339, 15, 14);
		frame.getContentPane().add(lbl1);

		top1 = new JTextField();
		top1.setEditable(false);
		top1.setBounds(51, 335, 71, 23);
		frame.getContentPane().add(top1);
		top1.setColumns(10);

		lbl2 = new JLabel("M=");
		lbl2.setBounds(132, 339, 46, 14);
		frame.getContentPane().add(lbl2);

		top2 = new JTextField();
		top2.setEditable(false);
		top2.setBounds(146, 335, 74, 23);
		frame.getContentPane().add(top2);
		top2.setColumns(10);

		lbl3 = new JLabel("Y=");
		lbl3.setBounds(235, 339, 46, 14);
		frame.getContentPane().add(lbl3);

		top3 = new JTextField();
		top3.setEditable(false);
		top3.setBounds(251, 335, 74, 23);
		frame.getContentPane().add(top3);
		top3.setColumns(10);

		lbl4 = new JLabel("H=");
		lbl4.setBounds(25, 372, 15, 14);
		frame.getContentPane().add(lbl4);

		bottom1 = new JTextField();
		bottom1.setEditable(false);
		bottom1.setBounds(51, 369, 71, 20);
		frame.getContentPane().add(bottom1);
		bottom1.setColumns(10);

		lbl5 = new JLabel("S=");
		lbl5.setBounds(132, 375, 23, 14);
		frame.getContentPane().add(lbl5);

		bottom2 = new JTextField();
		bottom2.setEditable(false);
		bottom2.setBounds(147, 366, 80, 23);
		frame.getContentPane().add(bottom2);
		bottom2.setColumns(10);

		lbl6 = new JLabel("V=");
		lbl6.setBounds(237, 372, 15, 14);
		frame.getContentPane().add(lbl6);

		bottom3 = new JTextField();
		bottom3.setEditable(false);
		bottom3.setBounds(251, 369, 71, 20);
		frame.getContentPane().add(bottom3);
		bottom3.setColumns(10);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(390, 66, 806, 803);
		panel_1.add(glcanvas);
		frame.getContentPane().add(panel_1);
		JPanel pop=new JPanel();
		//action listeners for textfields
		slider1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(slider1.getText());
					one.setValue(value);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(pop, "Please enter an integer");
				}
			}

		});
		slider2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(slider2.getText());
					two.setValue(value);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(pop, "Please enter an integer");
				}
			}

		});
		slider3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(slider3.getText());
					three.setValue(value);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(pop, "Please enter an integer");
				}
			}

		});
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		float r1 = ((float) shapes.getFrontSquare().getRed()) / 255;
		float g1 = ((float) shapes.getFrontSquare().getGreen()) / 255;
		float b1 = ((float) shapes.getFrontSquare().getBlue()) / 255;
		// House Pentagon
		gl.glColor3f(r1, g1, b1);
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex2f(-0.35f, -0.35f);
		gl.glVertex2f(0.35f, -0.35f);
		gl.glVertex2f(0.35f, 0.35f);
		gl.glVertex2f(-0.35f, 0.35f);
		gl.glEnd();
		ranges.setFrntsq(new FrontSquare(ScaleX(-0.35, 800), ScaleX(0.35, 800), ScaleY(-0.35, 800), ScaleY(0.35, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(-0.35f, -0.35f);
		gl.glVertex2f(0.35f, -0.35f);
		gl.glVertex2f(0.35f, 0.35f);
		gl.glVertex2f(-0.35f, 0.35f);
		gl.glEnd();
		// House Back
		float r2 = ((float) shapes.getBack().getRed()) / 255;
		float g2 = ((float) shapes.getBack().getGreen()) / 255;
		float b2 = ((float) shapes.getBack().getBlue()) / 255;
		gl.glColor3f(r2, g2, b2);
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex2f(0.35f, -0.35f);
		gl.glVertex2f(0.6f, -0.1f);
		gl.glVertex2f(0.6f, 0.4f);
		gl.glVertex2f(0.35f, 0.35f);
		gl.glEnd();
		int x[] = { (int) ScaleX(0.35, 800), (int) ScaleX(0.6, 800), (int) ScaleX(0.6, 800), (int) ScaleX(0.35, 800) };
		int y[] = { (int) ScaleY(-0.35, 800), (int) ScaleY(-0.1, 800), (int) ScaleY(0.4, 800),
				(int) ScaleY(0.35, 800) };
		Polygon p = new Polygon(x, y, 4);
		ranges.setBack(p);
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(0.35f, -0.35f);
		gl.glVertex2f(0.6f, -0.1f);
		gl.glVertex2f(0.6f, 0.4f);
		gl.glVertex2f(0.35f, 0.35f);
		gl.glEnd();
//Roof
		float r3 = ((float) shapes.getRoof().getRed()) / 255;
		float g3 = ((float) shapes.getRoof().getGreen()) / 255;
		float b3 = ((float) shapes.getRoof().getBlue()) / 255;
		gl.glColor3f(r3, g3, b3);
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex2f(0.6f, 0.4f);
		gl.glVertex2f(0.35f, 0.6f);
		gl.glVertex2f(0, 0.55f);
		gl.glVertex2f(0.35f, 0.35f);
		gl.glEnd();
		int x1[] = { (int) ScaleX(0.6, 800), (int) ScaleX(0.35, 800), (int) ScaleX(0, 800), (int) ScaleX(0.35, 800) };
		int y1[] = { (int) ScaleY(0.4, 800), (int) ScaleY(0.6, 800), (int) ScaleY(0.55, 800), (int) ScaleY(0.35, 800) };
		Polygon p1 = new Polygon(x1, y1, 4);
		ranges.setRoof(p1);
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(0.6f, 0.4f);
		gl.glVertex2f(0.35f, 0.6f);
		gl.glVertex2f(0, 0.55f);
		gl.glVertex2f(0.35f, 0.35f);
		gl.glEnd();
		// Triangle
		float r4 = ((float) shapes.getTriangle().getRed()) / 255;
		float g4 = ((float) shapes.getTriangle().getGreen()) / 255;
		float b4 = ((float) shapes.getTriangle().getBlue()) / 255;
		gl.glColor3f(r4, g4, b4);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2f(-0.35f, 0.35f);
		gl.glVertex2f(0, 0.55f);
		gl.glVertex2f(0.35f, 0.35f);
		gl.glEnd();
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(-0.35f, 0.35f);
		gl.glVertex2f(0.35f, 0.35f);
		gl.glEnd();
		ranges.setTri(new TriangleRoof(ScaleX(-0.35, 800), ScaleX(0.35, 800), ScaleX(0, 800), ScaleY(0.35, 800),
				ScaleY(0.35, 800), ScaleY(0.55, 800)));
		// Doors
		float r5 = ((float) shapes.getDoor1().getRed()) / 255;
		float g5 = ((float) shapes.getDoor1().getGreen()) / 255;
		float b5 = ((float) shapes.getDoor1().getBlue()) / 255;
		gl.glColor3f(r5, g5, b5);
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex2f(-0.15f, -0.35f);
		gl.glVertex2f(0f, -0.35f);
		gl.glVertex2f(0f, -0.1f);
		gl.glVertex2f(-0.15f, -0.1f);
		gl.glEnd();
		ranges.setDoor1(new FrontSquare(ScaleX(-0.15, 800), ScaleX(0, 800), ScaleY(-0.35, 800), ScaleY(-0.1, 800)));
		float r6 = ((float) shapes.getDoor2().getRed()) / 255;
		float g6 = ((float) shapes.getDoor2().getGreen()) / 255;
		float b6 = ((float) shapes.getDoor2().getBlue()) / 255;
		gl.glColor3f(r6, g6, b6);
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex2f(0f, -0.35f);
		gl.glVertex2f(0.15f, -0.35f);
		gl.glVertex2f(0.15f, -0.1f);
		gl.glVertex2f(0f, -0.1f);
		gl.glEnd();
		ranges.setDoor2(new FrontSquare(ScaleX(0, 800), ScaleX(0.15, 800), ScaleY(-0.35, 800), ScaleY(-0.1, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(-0.15f, -0.35f);
		gl.glVertex2f(0f, -0.35f);
		gl.glVertex2f(0f, -0.1f);
		gl.glVertex2f(-0.15f, -0.1f);
		gl.glEnd();
		gl.glColor3f(0, 0, 0);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(0f, -0.35f);
		gl.glVertex2f(0.15f, -0.35f);
		gl.glVertex2f(0.15f, -0.1f);
		gl.glVertex2f(0f, -0.1f);
		gl.glEnd();
		// DoorHandles
		drawsolidcircle(gl, 0.02, 0.025f, -0.25f, 360, shapes.getHandle1());
		ranges.setHandle1(new CircleShape(0.02 * 800, ScaleX(0.025, 800), ScaleY(-0.25, 800)));
		drawsolidcircle(gl, 0.02, -0.025f, -0.25f, 360, shapes.getHandle2());
		ranges.setHandle2(new CircleShape(0.02 * 800, ScaleX(-0.025, 800), ScaleY(-0.25, 800)));
		drawcircle(gl, 0.02, 0.025f, -0.25f, 360);
		drawcircle(gl, 0.02, -0.025f, -0.25f, 360);
		// Windows
		float r7 = ((float) shapes.getWindow1().getRed()) / 255;
		float g7 = ((float) shapes.getWindow1().getGreen()) / 255;
		float b7 = ((float) shapes.getWindow1().getBlue()) / 255;
		gl.glColor3f(r7, g7, b7);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex2f(-0.1f, 0f);
		gl.glVertex2f(-0.1f, 0.15f);
		gl.glVertex2f(-0.25f, 0.15f);
		gl.glVertex2f(-0.25f, 0f);
		gl.glEnd();
		ranges.setWindow1(new FrontSquare(ScaleX(-0.25, 800), ScaleX(-0.10, 800), ScaleY(0, 800), ScaleY(0.15, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(-0.1f, 0f);
		gl.glVertex2f(-0.1f, 0.15f);
		gl.glVertex2f(-0.25f, 0.15f);
		gl.glVertex2f(-0.25f, 0f);
		gl.glEnd();
		float r8 = ((float) shapes.getWindow2().getRed()) / 255;
		float g8 = ((float) shapes.getWindow2().getGreen()) / 255;
		float b8 = ((float) shapes.getWindow2().getBlue()) / 255;
		gl.glColor3f(r8, g8, b8);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex2f(0.1f, 0f);
		gl.glVertex2f(0.1f, 0.15f);
		gl.glVertex2f(0.25f, 0.15f);
		gl.glVertex2f(0.25f, 0f);
		gl.glEnd();
		ranges.setWindow2(new FrontSquare(ScaleX(0.1, 800), ScaleX(0.25, 800), ScaleY(0, 800), ScaleY(0.15, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(0.1f, 0f);
		gl.glVertex2f(0.1f, 0.15f);
		gl.glVertex2f(0.25f, 0.15f);
		gl.glVertex2f(0.25f, 0f);
		gl.glEnd();
		// WindowCurtains
		float r9 = ((float) shapes.getWin1Cur1().getRed()) / 255;
		float g9 = ((float) shapes.getWin1Cur1().getGreen()) / 255;
		float b9 = ((float) shapes.getWin1Cur1().getBlue()) / 255;
		gl.glColor3f(r9, g9, b9);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2f(-0.175f, 0f);
		gl.glVertex2f(-0.1f, 0f);
		gl.glVertex2f(-0.1f, 0.075f);
		gl.glEnd();
		ranges.setWin1cur1(new TriangleRoof(ScaleX(-0.175, 800), ScaleX(-0.1, 800), ScaleX(-0.1, 800), ScaleY(0, 800),
				ScaleY(0, 800), ScaleY(0.075, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(-0.175f, 0f);
		gl.glVertex2f(-0.1f, 0f);
		gl.glVertex2f(-0.1f, 0.075f);
		gl.glEnd();
		float r10 = ((float) shapes.getWin1Cur2().getRed()) / 255;
		float g10 = ((float) shapes.getWin1Cur2().getGreen()) / 255;
		float b10 = ((float) shapes.getWin1Cur2().getBlue()) / 255;
		gl.glColor3f(r10, g10, b10);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2f(-0.1f, 0.075f);
		gl.glVertex2f(-0.1f, 0.15f);
		gl.glVertex2f(-0.175f, 0.15f);
		gl.glEnd();
		ranges.setWin1cur2(new TriangleRoof(ScaleX(-0.1, 800), ScaleX(-0.1, 800), ScaleX(-0.175, 800),
				ScaleY(0.075, 800), ScaleY(0.15, 800), ScaleY(0.15, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(-0.1f, 0.075f);
		gl.glVertex2f(-0.1f, 0.15f);
		gl.glVertex2f(-0.175f, 0.15f);
		gl.glEnd();
		float r11 = ((float) shapes.getWin1Cur3().getRed()) / 255;
		float g11 = ((float) shapes.getWin1Cur3().getGreen()) / 255;
		float b11 = ((float) shapes.getWin1Cur3().getBlue()) / 255;
		gl.glColor3f(r11, g11, b11);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2f(-0.25f, 0.075f);
		gl.glVertex2f(-0.25f, 0.15f);
		gl.glVertex2f(-0.175f, 0.15f);
		gl.glEnd();
		ranges.setWin1cur3(new TriangleRoof(ScaleX(-0.25, 800), ScaleX(-0.25, 800), ScaleX(-0.175, 800),
				ScaleY(0.075, 800), ScaleY(0.15, 800), ScaleY(0.15, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(-0.25f, 0.075f);
		gl.glVertex2f(-0.25f, 0.15f);
		gl.glVertex2f(-0.175f, 0.15f);
		gl.glEnd();
		float r12 = ((float) shapes.getWin1Cur4().getRed()) / 255;
		float g12 = ((float) shapes.getWin1Cur4().getGreen()) / 255;
		float b12 = ((float) shapes.getWin1Cur4().getBlue()) / 255;
		gl.glColor3f(r12, g12, b12);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2f(-0.25f, 0.075f);
		gl.glVertex2f(-0.175f, 0f);
		gl.glVertex2f(-0.25f, 0f);
		gl.glEnd();
		ranges.setWin1cur4(new TriangleRoof(ScaleX(-0.25, 800), ScaleX(-0.175, 800), ScaleX(-0.25, 800),
				ScaleY(0.075, 800), ScaleY(0, 800), ScaleY(0, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(-0.25f, 0.075f);
		gl.glVertex2f(-0.175f, 0f);
		gl.glVertex2f(-0.25f, 0f);
		gl.glEnd();
		float r13 = ((float) shapes.getWin2Cur1().getRed()) / 255;
		float g13 = ((float) shapes.getWin2Cur1().getGreen()) / 255;
		float b13 = ((float) shapes.getWin2Cur1().getBlue()) / 255;
		gl.glColor3f(r13, g13, b13);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2f(0.1f, 0.075f);
		gl.glVertex2f(0.1f, 0.15f);
		gl.glVertex2f(0.175f, 0.15f);
		gl.glEnd();
		ranges.setWin2cur1(new TriangleRoof(ScaleX(0.1, 800), ScaleX(0.1, 800), ScaleX(0.175, 800), ScaleY(0.075, 800),
				ScaleY(0.15, 800), ScaleY(0.15, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(0.1f, 0.075f);
		gl.glVertex2f(0.1f, 0.15f);
		gl.glVertex2f(0.175f, 0.15f);
		gl.glEnd();
		float r14 = ((float) shapes.getWin2Cur2().getRed()) / 255;
		float g14 = ((float) shapes.getWin2Cur2().getGreen()) / 255;
		float b14 = ((float) shapes.getWin2Cur2().getBlue()) / 255;
		gl.glColor3f(r14, g14, b14);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2f(0.1f, 0.075f);
		gl.glVertex2f(0.175f, 0f);
		gl.glVertex2f(0.1f, 0f);
		gl.glEnd();
		ranges.setWin2cur2(new TriangleRoof(ScaleX(0.1, 800), ScaleX(0.175, 800), ScaleX(0.1, 800), ScaleY(0.075, 800),
				ScaleY(0, 800), ScaleY(0, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(0.1f, 0.075f);
		gl.glVertex2f(0.175f, 0f);
		gl.glVertex2f(0.1f, 0f);
		gl.glEnd();
		float r15 = ((float) shapes.getWin2Cur3().getRed()) / 255;
		float g15 = ((float) shapes.getWin2Cur3().getGreen()) / 255;
		float b15 = ((float) shapes.getWin2Cur3().getBlue()) / 255;
		gl.glColor3f(r15, g15, b15);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2f(0.175f, 0.15f);
		gl.glVertex2f(0.25f, 0.075f);
		gl.glVertex2f(0.25f, 0.15f);
		gl.glEnd();
		ranges.setWin2cur3(new TriangleRoof(ScaleX(0.175, 800), ScaleX(0.25, 800), ScaleX(0.25, 800), ScaleY(0.15, 800),
				ScaleY(0.075, 800), ScaleY(0.15, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(0.175f, 0.15f);
		gl.glVertex2f(0.25f, 0.075f);
		gl.glVertex2f(0.25f, 0.15f);
		gl.glEnd();
		float r16 = ((float) shapes.getWin2Cur4().getRed()) / 255;
		float g16 = ((float) shapes.getWin2Cur4().getGreen()) / 255;
		float b16 = ((float) shapes.getWin2Cur4().getBlue()) / 255;
		gl.glColor3f(r16, g16, b16);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2f(0.25f, 0.075f);
		gl.glVertex2f(0.175f, 0f);
		gl.glVertex2f(0.25f, 0f);
		gl.glEnd();
		ranges.setWin2cur4(new TriangleRoof(ScaleX(0.25, 800), ScaleX(0.175, 800), ScaleX(0.25, 800),
				ScaleY(0.075, 800), ScaleY(0, 800), ScaleY(0, 800)));
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2f(0.25f, 0.075f);
		gl.glVertex2f(0.175f, 0f);
		gl.glVertex2f(0.25f, 0f);
		gl.glEnd();
		// Center Window
		drawcircle(gl, 0.07, 0f, 0.25f, 360);
		drawsolidcirclestrip(gl, 0, 0.07, 0f, 0.25f, 90, shapes.getCenterWin1());
		drawsolidcirclestrip(gl, 90, 0.07, 0f, 0.25f, 180, shapes.getCenterWin2());
		drawsolidcirclestrip(gl, 180, 0.07, 0f, 0.25f, 270, shapes.getCenterWin3());
		drawsolidcirclestrip(gl, 270, 0.07, 0f, 0.25f, 360, shapes.getCenterWin4());
		drawcircle(gl, 0.07, 0f, 0.25f, 360);
		ranges.setCenterWin(new CircleShape(0.03 * 800, ScaleX(0, 800), ScaleY(0.25, 800)));
		// Cross
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(0f, 0.32f);
		gl.glVertex2f(0f, 0.18f);
		gl.glEnd();
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(-0.07f, 0.25f);
		gl.glVertex2f(0.07f, 0.25f);
		gl.glEnd();

	}

	public void drawsolidcircle(GL2 gl, double radius, float centerX, float centerY, int alpha, Color color) {
		float r = ((float) color.getRed()) / 255;
		float g = ((float) color.getGreen()) / 255;
		float b = ((float) color.getBlue()) / 255;
		gl.glColor3f(r, g, b);
		gl.glBegin(GL2.GL_LINES);
		for (int i = 0; i <= alpha; i++) {
			double angle = ((i * Math.PI) / 180);
			float x = (float) (radius * (Math.cos(angle)));
			float y = (float) (radius * (Math.sin(angle)));
			gl.glVertex2f(centerX, centerY);
			gl.glVertex2f(x + centerX, y + centerY);
		}
		gl.glEnd();
	}

	public void drawcircle(GL2 gl, double radius, float centerX, float centerY, int alpha) {
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		for (int i = 0; i <= alpha; i++) {
			double angle = ((i * Math.PI) / 180);
			float x = (float) (radius * (Math.cos(angle)));
			float y = (float) (radius * (Math.sin(angle)));
			gl.glVertex2f(x + centerX, y + centerY);
		}
		gl.glEnd();
	}

	public void drawsolidcirclestrip(GL2 gl, int start, double radius, float centerX, float centerY, int alpha,
			Color color) {

		float r = ((float) color.getRed()) / 255;
		float g = ((float) color.getGreen()) / 255;
		float b = ((float) color.getBlue()) / 255;
		gl.glColor3f(r, g, b);
		gl.glBegin(GL2.GL_LINES);
		for (int i = start; i <= alpha; i++) {
			double angle = ((i * Math.PI) / 180);
			float x = (float) (radius * (Math.cos(angle)));
			float y = (float) (radius * (Math.sin(angle)));
			gl.glVertex2f(centerX, centerY);
			gl.glVertex2f(x + centerX, y + centerY);
		}
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

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

	public static void HSVtoRGB(double h, double s, double v, RGB rgb) {
		double max = v;
		double min = max * (1 - s);
		while (h < 0) {
			h = h + 360;
		}
		while (h >= 360)
			h = h - 360;
		int Ih = (int) (h / 60);
		double alpha = h / 60 - Ih;
		if (Ih % 2 == 1) {
			alpha = 1 - alpha;
		}
		double mid = min + alpha * (max - min);
		switch (Ih) {
		case 0:
			rgb.setR(max);
			rgb.setG(mid);
			rgb.setB(min);
			break;
		case 1:
			rgb.setR(mid);
			rgb.setG(max);
			rgb.setB(min);
			break;
		case 2:
			rgb.setR(min);
			rgb.setG(max);
			rgb.setB(mid);
			break;
		case 3:
			rgb.setR(min);
			rgb.setG(mid);
			rgb.setB(max);
			break;
		case 4:
			rgb.setR(mid);
			rgb.setG(min);
			rgb.setB(max);
			break;
		case 5:
			rgb.setR(max);
			rgb.setG(min);
			rgb.setB(mid);
			break;
		}
	}

	public double ScaleX(double x, double newrange) {
		double scaled = 0;
		if (x < 0) {
			x = x * -1;
			scaled = newrange / 2 - (x * newrange / 2);
		} else if (x > 0) {
			scaled = (x * newrange / 2);
			scaled = newrange / 2 + scaled;
		} else if (x == 0) {
			scaled = newrange / 2;
		}
		return scaled;
	}

	public double ScaleY(double x, double newrange) {
		double scaled = 0;
		if (x < 0) {
			x = x * -1;
			scaled = (x * newrange / 2);
			scaled = newrange / 2 + scaled;

		} else if (x > 0) {
			scaled = newrange / 2 - (x * newrange / 2);

		} else if (x == 0) {
			scaled = newrange / 2;
		}
		return scaled;
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "RGB");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			lbl1.setText("C=");
			lbl2.setText("M=");
			lbl3.setText("Y=");
			lbl4.setText("H=");
			lbl5.setText("S=");
			lbl6.setText("V=");
			top1.setText("");
			top2.setText("");
			top3.setText("");
			bottom1.setText("");
			bottom2.setText("");
			bottom3.setText("");
			lblrgb1.setText("R");
			lblrgb2.setText("G");
			lblrgb3.setText("B");
			one.setMaximum(255);
			one.setMinimum(0);
			one.setLabelTable(one.createStandardLabels(255));
			two.setMaximum(255);
			two.setMinimum(0);
			two.setLabelTable(two.createStandardLabels(255));
			three.setMaximum(255);
			three.setMinimum(0);
			three.setLabelTable(three.createStandardLabels(255));
			one.setValue(0);
			two.setValue(0);
			three.setValue(0);
			pane.setBackground(new Color(0, 0, 0));

		}
	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "CMY");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			lbl1.setText("R=");
			lbl2.setText("G=");
			lbl3.setText("B=");
			lbl4.setText("H=");
			lbl5.setText("S=");
			lbl6.setText("V=");
			lblrgb1.setText("C");
			lblrgb2.setText("M");
			lblrgb3.setText("Y");
			one.setMaximum(255);
			one.setMinimum(0);
			one.setLabelTable(one.createStandardLabels(255));
			two.setMaximum(255);
			two.setMinimum(0);
			two.setLabelTable(two.createStandardLabels(255));
			three.setMaximum(255);
			three.setMinimum(0);
			three.setLabelTable(three.createStandardLabels(255));
			one.setValue(0);
			two.setValue(0);
			three.setValue(0);
			pane.setBackground(new Color(255, 255, 255));
		}
	}

	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "HSV");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			lbl1.setText("R=");
			lbl2.setText("G=");
			lbl3.setText("B=");
			lbl4.setText("C=");
			lbl5.setText("M=");
			lbl6.setText("Y=");
			lblrgb1.setText("H");
			lblrgb2.setText("S");
			lblrgb3.setText("V");
			one.setMaximum(360);
			one.setMinimum(0);
			one.setValue(0);
			one.setLabelTable(one.createStandardLabels(360));
			two.setMaximum(100);
			two.setMinimum(0);
			two.setValue(0);
			two.setLabelTable(two.createStandardLabels(100));
			three.setMaximum(100);
			three.setMinimum(0);
			three.setValue(0);
			three.setLabelTable(three.createStandardLabels(100));
			pane.setBackground(new Color(0, 0, 0));

		}
	}
}
