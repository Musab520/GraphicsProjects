
public class FrontSquare {
	private double FrontSquareX1;
	private double FrontSquareX2;
	private double FrontSquareY1;
	private double FrontSquareY2;

	public FrontSquare(double x1, double x2, double y1, double y2) {
		this.FrontSquareX1 = x1;
		this.FrontSquareX2 = x2;
		this.FrontSquareY1 = y1;
		this.FrontSquareY2 = y2;
	}

	public boolean isInside(double x, double y) {
		return ((x >= getFrontSquareX1()) && (x <= getFrontSquareX2()))
				&& ((y <= getFrontSquareY1()) && (y >= getFrontSquareY2()));

	}

	public double getFrontSquareX1() {
		return FrontSquareX1;
	}

	public void setFrontSquareX1(double frontSquareX1) {
		FrontSquareX1 = frontSquareX1;
	}

	public double getFrontSquareX2() {
		return FrontSquareX2;
	}

	public void setFrontSquareX2(double frontSquareX2) {
		FrontSquareX2 = frontSquareX2;
	}

	public double getFrontSquareY1() {
		return FrontSquareY1;
	}

	public void setFrontSquareY1(double frontSquareY1) {
		FrontSquareY1 = frontSquareY1;
	}

	public double getFrontSquareY2() {
		return FrontSquareY2;
	}

	public void setFrontSquareY2(double frontSquareY2) {
		FrontSquareY2 = frontSquareY2;
	}

}
