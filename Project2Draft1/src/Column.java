import java.util.Stack;

public class Column {
	private int ColumnX;
	private int ColumnY;
	private Stack<Rec> s;

	public Column(int X, int Y, Stack<Rec> s) {
		this.ColumnX = X;
		this.ColumnY = Y;
		this.s = s;
	}

	public int getColumnX() {
		return ColumnX;
	}

	public void setColumnX(int columnX) {
		ColumnX = columnX;
	}

	public int getColumnY() {
		return ColumnY;
	}

	public void setColumnY(int columnY) {
		ColumnY = columnY;
	}

	public Stack<Rec> getS() {
		return s;
	}

	public void setS(Stack<Rec> s) {
		this.s = s;
	}
	
}
