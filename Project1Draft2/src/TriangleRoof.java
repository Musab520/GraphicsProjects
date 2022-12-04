
public class TriangleRoof {
	private double x1;
	private double x2;
	private double x3;
	private double y1;
	private double y2;
	private double y3;

	public TriangleRoof(double x1, double x2, double x3, double y1, double y2, double y3) {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		
	}
	public double TriArea(double x1, double x2, double x3, double y1, double y2, double y3) {
		return Math.abs(((x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2)))/2.0);
	}
	public boolean InTri(double x,double y) {
		double a=TriArea(x1,x2,x3,y1,y2,y3);
		double a1=TriArea(x,x2,x3,y,y2,y3);
		double a2=TriArea(x1,x,x3,y1,y,y3);
		double a3=TriArea(x1,x2,x,y1,y2,y);
		return (a==a1+a2+a3);
	}
	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getX3() {
		return x3;
	}

	public void setX3(double x3) {
		this.x3 = x3;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public double getY3() {
		return y3;
	}

	public void setY3(double y3) {
		this.y3 = y3;
	}

}
