package application;

public class rgbobj {
private double r;
private double g;
private double b;
public rgbobj() {
	
}
public double getR() {
	return r;
}
public void setR(double r) {
	this.r = r;
}
public double getG() {
	return g;
}
public void setG(double g) {
	this.g = g;
}
public double getB() {
	return b;
}
public void setB(double b) {
	this.b = b;
}
@Override
public String toString() {
	return "rgbobj [r=" + r + ", g=" + g + ", b=" + b + "]";
}

}
