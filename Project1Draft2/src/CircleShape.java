
public class CircleShape {
	private double radius;
	private double centerX;
	private double centerY;

	public CircleShape(double radius, double centerX, double centerY) {
		this.radius = radius;
		this.centerX = centerX;
		this.centerY = centerY;
	}
public double isQ(double x,double y) {
	double xRel=x-centerX;
	double yRel=y-centerY;
	double quadrant=0;
	if(xRel>0 && yRel<0) {
		quadrant=1;
	}
	else if(xRel<0 && yRel<0) {
		quadrant=2;
	}
	else if(yRel>0 && xRel<0) {
		quadrant=3;
	}
	else if(yRel>0 && xRel>0) {
		quadrant=4;
	}
	return quadrant;
}
public boolean InCircle(double x,double y) {
	return Math.sqrt(((x-centerX)*(x-centerX))+((y-centerY)*(y-centerY)))<radius;
}
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

}
