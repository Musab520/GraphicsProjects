package application;

public class HSV {
private double h;
private double s;
private double v;
public double getH() {
	return h;
}
public void setH(double h) {
	this.h = h;
}
public double getS() {
	return s;
}
public void setS(double s) {
	this.s = s;
}
public double getV() {
	return v;
}
public void setV(double v) {
	this.v = v;
}
@Override
public String toString() {
	return "HSV [h=" + h + ", s=" + s + ", v=" + v + "]";
}

}
