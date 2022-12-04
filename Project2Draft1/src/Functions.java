import javax.swing.JFrame;
//import javax.swing.JPanel;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public class Functions {
	static int count = 0;

	public void brezline(GL2 gl, int x1, int y1, int x2, int y2) {

		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		int x = x1;
		int y = y1;

		gl.glBegin(GL2.GL_POINTS);
		gl.glVertex2i(x, y);

		if (dx > dy) {
			// m < 1
			int p = 2 * dy - dx;
			int xEnd = 0;
			int twoDy = 2 * dy;
			int twoDyDx = 2 * (dy - dx);

			if (x1 > x2) {
				x = x2;
				y = y2;
				xEnd = x1;
			} else {
				x = x1;
				y = y1;
				xEnd = x2;
			}
			gl.glVertex2i(x, y);

			while (x < xEnd) {
				x++;
				if (p < 0)
					p = p + twoDy;
				else {
					y = y2 - y1 < 0 ? y - 1 : y + 1;
					p = p + twoDyDx;
				}
				gl.glVertex2i(x, y);
			}
		} else {
			// m >= 1

			int p = 2 * dx - dy;
			int yEnd = 0;
			int xEnd = 0;
			int twoDx = 2 * dx;
			int twoDxDy = 2 * (dx - dy);

			if (y1 > y2) {
				x = x2;
				y = y2;
				yEnd = y1;
				xEnd = x1;
			} else {
				x = x1;
				y = y1;
				yEnd = y2;
				xEnd = x2;
			}

			gl.glVertex2i(x, y);

			while (y < yEnd) {
				y++;
				if (p < 0)
					p = p + twoDx;
				else {
					x = xEnd - x < 0 ? x - 1 : x + 1;
					p = p + twoDxDy;
				}
				gl.glVertex2i(x, y);
			}
		}
		gl.glEnd();
	}

	public void drawpoly(GL2 gl, int[] x, int[] y, int n) {
		if ((n < 3) || (n > 31)) {
			return;
		} else {
			int curx = x[0];
			int cury = y[0];
			int lastx = 0;
			int lasty = 0;
			for (int i = 0; i < n - 1; i++) {
				brezline(gl, x[i], y[i], x[i + 1], y[i + 1]);
				lastx = x[i + 1];
				lasty = y[i + 1];
			}
			brezline(gl, curx, cury, lastx, lasty);
		}
	}

	public void fillpoly4(GL2 gl, Rec r, int x, int y, Color fill, Color Bounding, JFrame frm) {
		float rfill = fill.getRed() / 255;
		float gfill = fill.getGreen() / 255;
		float bfill = fill.getBlue() / 255;
		float fills[] = { rfill, gfill, bfill };
		Point p = new Point((int) ScaleX(gl, x, 500, 1000), (int) ScaleY(y, 500, 1000));
		Color c = getColorAt(gl, r, frm, p, fill);
		if (equalColor(fill, c) != 0 && equalColor(c, Bounding) != 0) {
			gl.glColor3f(rfill, gfill, bfill);
			gl.glBegin(GL2.GL_POINT);
			gl.glVertex2i(x, y);
			gl.glEnd();
			gl.glFlush();
			fillpoly4(gl, r, x + 1, y, fill, Bounding, frm);
			fillpoly4(gl, r, x, y + 1, fill, Bounding, frm);
			fillpoly4(gl, r, x - 1, y, fill, Bounding, frm);
			fillpoly4(gl, r, x, y - 1, fill, Bounding, frm);

		}
	}

	public int equalColor(Color c1, Color c2) {
		int r1 = c1.getRed();
		int g1 = c1.getGreen();
		int b1 = c1.getBlue();
		int r2 = c2.getRed();
		int g2 = c2.getGreen();
		int b2 = c2.getBlue();
		if ((r1 == r2) && (g1 == g2) && (b1 == b2)) {
			return 0;
		} else
			return -1;
	}

	public static Color getColorAt(GL2 gl, Rec r, JFrame frm, Point p, Color c) {
		for (int t = (int) r.getBL().getY(); t < r.getUL().getY(); t++) {
			for (int z = (int) r.getBL().getX(); z < r.getBR().getX(); z++) {
				gl.glBegin(GL2.GL_POINTS);
				gl.glVertex2i(z, t);
				gl.glEnd();
			}
		}
		Rectangle rect = frm.getContentPane().getBounds();
		BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
		frm.getContentPane().paintAll(img.createGraphics());
		return c;
	}

	public double ScaleX(GL2 gl, double x, double newrange, double oldrange) {
		double scaled = 0;
		x = x / oldrange;
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

	public double ScaleY(double x, double newrange, double oldrange) {
		double scaled = 0;
		x = x / oldrange;
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

	public void boundaryFill4(GL2 gl, int x2, int y2, int x, int y, Color boundaryColor, Color fillColor) {
		count++;
		if (count >= 200)
			return;

		ByteBuffer pixel = ByteBuffer.allocate(3);
		gl.glReadPixels(x2 - 1, y2 - 1, 1, 1, gl.GL_RGB, gl.GL_UNSIGNED_BYTE, pixel);
		int red = pixel.get(0) & 0xFF, green = pixel.get(1) & 0xFF, blue = pixel.get(2) & 0xFF;

		System.out.println("x: " + x + " y: " + y);
		System.out.println("current red --> " + red);
		System.out.println("current green --> " + green);
		System.out.println("current blue --> " + blue);

		if (!(red == boundaryColor.getRed() && green == boundaryColor.getGreen() && blue == boundaryColor.getBlue())
				&& !(red == fillColor.getRed() && green == fillColor.getGreen() && blue == fillColor.getBlue())) {

			// set color
			gl.glColor3f((float) fillColor.getRed() / 255, (float) fillColor.getGreen() / 255,
					(float) fillColor.getBlue() / 255); // new color
			gl.glBegin(GL2.GL_POINTS);
			gl.glVertex2i(x, y);
			gl.glEnd();

			boundaryFill4(gl, x2, y2, x + 1, y, boundaryColor, fillColor);
			boundaryFill4(gl, x2, y2, x, y + 1, boundaryColor, fillColor);
			boundaryFill4(gl, x2, y2, x - 1, y, boundaryColor, fillColor);
			boundaryFill4(gl, x2, y2, x, y - 1, boundaryColor, fillColor);
		}
	}
}
