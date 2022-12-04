import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import com.jogamp.opengl.util.FPSAnimator;

public class Hanoi implements GLEventListener {
	private JFrame frame;
	private JFrame frame2;
	private JFrame frame3;
	private JTextField NumDisks;
	private JTextField NumSteps;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private static Functions f = new Functions();
	private static final GLProfile profile = GLProfile.get(GLProfile.GL2);
	private static GLCapabilities capabilities = new GLCapabilities(profile);
	private static final GLCanvas glcanvas = new GLCanvas(capabilities);
	private static int ColumnMaxH = 900;
	private static Column A = new Column(-600, 900, new Stack<Rec>());
	private static Column B = new Column(0, 900, new Stack<Rec>());
	private static Column C = new Column(600, 900, new Stack<Rec>());
	private static int MaxDiskWidth;
	private static int width = 1000;
	private static int height = 1000;
	private static int disks = 0;
	private static int steps = 0;
	private static Rec[] r;
	private static float[] colors = { 0, 1, 1 };
	private JTextField Colors;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Hanoi app = new Hanoi();
		glcanvas.addGLEventListener(app);
		glcanvas.setSize(width, height);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hanoi window = new Hanoi();
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
	public Hanoi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 511, 215);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame2 = new JFrame();
		frame2.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame2.setBounds(500, 500, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		NumDisks = new JTextField();
		NumDisks.setBounds(114, 42, 86, 20);
		frame.getContentPane().add(NumDisks);
		NumDisks.setColumns(10);

		NumSteps = new JTextField();
		NumSteps.setBounds(315, 42, 86, 20);
		frame.getContentPane().add(NumSteps);
		NumSteps.setColumns(10);
		NumSteps.setEditable(false);
		JPanel pop = new JPanel();
		JButton run = new JButton("RUN");
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					disks = Integer.parseInt(NumDisks.getText());
					if (disks < 10) {
						NumSteps.setText("" + (Math.pow(2, disks) - 1));
						r = new Rec[disks];
						A = new Column(-600, 900, new Stack<Rec>());
						B = new Column(0, 900, new Stack<Rec>());
						C = new Column(600, 900, new Stack<Rec>());
						try {
							String[] s = Colors.getText().split(",");
							colors[0] = Float.parseFloat(s[0]);
							colors[1] = Float.parseFloat(s[1]);
							colors[2] = Float.parseFloat(s[2]);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(pop, "Float Values Only");
						}
						int divisionY = 100;
						int divisionX = 50;
						MaxDiskWidth = disks * 100;
						for (int i = 0; i < r.length; i++) {
							Point bl = new Point(A.getColumnX() - ((MaxDiskWidth / 2) - divisionX * i), i * divisionY);
							Point br = new Point(A.getColumnX() + ((MaxDiskWidth / 2) - divisionX * i), i * divisionY);
							Point tr = new Point(A.getColumnX() + ((MaxDiskWidth / 2) - divisionX * i),
									(i + 1) * divisionY);
							Point tl = new Point(A.getColumnX() - ((MaxDiskWidth / 2) - divisionX * i),
									(i + 1) * divisionY);
							float rd = (float) Math.random();
							float g = (float) Math.random();
							float b = (float) Math.random();
							Color c = new Color(rd, g, b);
							r[i] = new Rec(bl, br, tr, tl, c);
							A.getS().push(r[i]);
						}
						frame2.getContentPane().add(glcanvas);
						frame2.setVisible(true);
						frame2.setBounds(500, 500, width, height);
						glcanvas.display();
					} else
						JOptionPane.showMessageDialog(pop, "Disk limit at 9");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(pop, "Disks/Steps must be an integer value");
				}
			}
		});
		run.setBounds(100, 94, 89, 23);
		frame.getContentPane().add(run);

		lblNewLabel = new JLabel("Number of Disks");
		lblNewLabel.setBounds(10, 45, 94, 14);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Number of Steps");
		lblNewLabel_1.setBounds(210, 45, 95, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JButton Show = new JButton("Show");
		Show.setBounds(210, 94, 89, 23);
		Show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (r != null) {
					MoveAnimations m = new MoveAnimations(glcanvas, r);
					Colors.setEditable(false);
					towerOfHanoi(disks, r, A, C, B, m);
					Colors.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(pop, "Please set Steps and Disks as Integers and Click Run First");
				}
			}

		});
		frame.getContentPane().add(Show);

		Colors = new JTextField();
		Colors.setBounds(315, 95, 95, 20);
		frame.getContentPane().add(Colors);
		Colors.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Color:");
		lblNewLabel_2.setBounds(325, 70, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		reshape(drawable, -1000, 1000, -1000, 1000);
		// reshape(drawable,-1000,1000,1000,1000);
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glColor3f(1, 1, 1);
		// Horizontal line
		f.brezline(gl, -1000, 0, 1000, 0);
		// Column A
		f.brezline(gl, A.getColumnX(), 0, A.getColumnX(), ColumnMaxH);
		// Column B
		f.brezline(gl, B.getColumnX(), 0, B.getColumnX(), ColumnMaxH);
		// Column C
		f.brezline(gl, C.getColumnX(), 0, C.getColumnX(), ColumnMaxH);
		// Draw initial Disks
		gl.glColor3f(colors[0], colors[1], colors[2]);
		for (int i = 0; i < r.length; i++) {
			gl.glColor3f(r[i].getC().getRed(), r[i].getC().getGreen(), r[i].getC().getBlue());
			f.drawpoly(gl, r[i].getX(), r[i].getY(), 4);
			f.fillpoly4(gl, r[i], (int) r[i].getBL().getX(), (int) r[i].getBL().getY(),
					new Color(r[i].getC().getRed(), r[i].getC().getGreen(), r[i].getC().getBlue()),
					new Color(r[i].getC().getRed(), r[i].getC().getGreen(), r[i].getC().getBlue()), frame2);
		}
	}

	public static void towerOfHanoi(int n, Rec[] r, Column A, Column C, Column B, MoveAnimations m) {
		// base case
		if (n == 1) {
			m.Moveto(1, A, C, glcanvas, r);

			return;
		}
		towerOfHanoi(n - 1, r, A, B, C, m);

		m.Moveto(n, A, C, glcanvas, r);

		towerOfHanoi(n - 1, r, B, C, A, m);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable arg0) {

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		final GL2 gl = arg0.getGL().getGL2();
		gl.glMatrixMode(GL2.GL_PROJECTION_MATRIX);
		gl.glLoadIdentity();
		gl.glOrtho(arg1, arg2, arg3, arg4, -1, 1);
		gl.glMatrixMode(GL2.GL_MODELVIEW);

	}
}
