import java.awt.Color;
import java.awt.Point;

public class Rec {
	private Point[] p;
	private Point BL;
	private Point BR;
	private Point UR;
	private Point UL;
	private Color c;
	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	private int[] x;
	private int[] y;
	private int CenterX;
	private int CenterY;

	public Rec(Point[] point) {
		this.p = point;
		x = new int[p.length - 1];
		y = new int[p.length - 1];
		for (int i = 0; i < p.length; i++) {
			x[i] = (int) p[i].getX();
			y[i] = (int) p[i].getY();
		}
	}

	public Rec(Point BottomLeft, Point BottomRight, Point UpperRight, Point UpperLeft,Color c) {
		this.c=c;
		this.BL = BottomLeft;
		this.BR = BottomRight;
		this.UR = UpperRight;
		this.UL = UpperLeft;
		this.CenterX = (int) (Math.abs(BottomRight.getX() - BottomLeft.getX()));
		CenterX = CenterX / 2;
		CenterX += BottomLeft.getX();
		this.CenterY = (int) (Math.abs(UpperRight.getY()-BottomRight.getY()));
		CenterY = CenterY / 2;
		CenterY += BottomLeft.getY();
		p = new Point[4];
		p[0] = BottomLeft;
		p[1] = BottomRight;
		p[2] = UpperRight;
		p[3] = UpperLeft;
		x = new int[p.length];
		y = new int[p.length];
		for (int i = 0; i < p.length; i++) {
			x[i] = (int) p[i].getX();
			y[i] = (int) p[i].getY();
		}
	}

	public int getCenterY() {
		return CenterY;
	}

	public void setCenterY(int centerY) {
		CenterY = centerY;
	}

	public int getCenterX() {
		return CenterX;
	}

	public void setCenterX(int centerX) {
		CenterX = centerX;
	}

	public int[] getX() {
		return x;
	}

	public void setX(int[] x) {
		this.x = x;
	}

	public int[] getY() {
		return y;
	}

	public void setY(int[] y) {
		this.y = y;
	}

	public Point getBL() {
		return BL;
	}

	public void setBL(Point bL) {
		BL = bL;
	}

	public Point getBR() {
		return BR;
	}

	public void setBR(Point bR) {
		BR = bR;
	}

	public Point getUR() {
		return UR;
	}

	public void setUR(Point uR) {
		UR = uR;
	}

	public Point getUL() {
		return UL;
	}

	public void setUL(Point uL) {
		UL = uL;
	}

	public Point[] getP() {
		return p;
	}

	public void setP(Point[] p) {
		this.p = p;
	}

}
