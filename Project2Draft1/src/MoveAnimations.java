import java.awt.Point;

import com.jogamp.opengl.awt.GLCanvas;

public class MoveAnimations {
	private GLCanvas glcanvas;
	private Rec[] r;
	private int steps;
	private int currsteps = 0;

	public MoveAnimations(GLCanvas gl, Rec[] r) {
		this.glcanvas = gl;
		this.r = r;
	}

	public void Moveto(int disk, Column a, Column b, GLCanvas glcanvas, Rec[] r) {
		if (a.getColumnX() < b.getColumnX()) {
			MoveDiskUp(disk, a.getColumnY(), glcanvas, r);
			MoveDiskRight(disk, b.getColumnX(), glcanvas, r);
			MoveDiskDown(disk, glcanvas, r, a, b);
		} else {
			MoveDiskUp(disk, a.getColumnY(), glcanvas, r);
			MoveDiskLeft(disk, b.getColumnX(), glcanvas, r);
			MoveDiskDown(disk, glcanvas, r, a, b);
		}
	}

	public void MoveDiskUp(int disk, int ColumnMaxH, GLCanvas glcanvas, Rec[] r) {
		if (disk - 1 < r.length) {
			while (r[r.length - disk].getUR().getY() < ColumnMaxH) {
				MoveDiskIncYpos(r, 50, disk);
				glcanvas.display();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void MoveDiskDown(int disk, GLCanvas glcanvas, Rec[] r, Column before, Column after) {
		if (disk - 1 < r.length) {
			double constraint = 0;
			// sees if there is an object in stack
			if (!after.getS().isEmpty()) {
				Rec tmp = after.getS().pop();
				after.getS().push(tmp);
				constraint = tmp.getUR().getY();
			}
			while (r[r.length - disk].getBL().getY() > constraint) {
				MoveDiskIncYneg(r, 50, disk);
				glcanvas.display();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			before.getS().pop();
			after.getS().push(r[r.length - disk]);
		}
	}

	public void MoveDiskRight(int disk, int XCol, GLCanvas glcanvas, Rec[] r) {
		if (disk - 1 < r.length) {
			while (r[r.length - disk].getCenterX() < XCol) {
				MoveDiskIncXpos(r, 50, disk);
				glcanvas.display();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void MoveDiskLeft(int disk, int XCol, GLCanvas glcanvas, Rec[] r) {
		if (disk - 1 < r.length) {
			while (r[r.length - disk].getCenterX() > XCol) {
				MoveDiskIncXneg(r, 50, disk);
				glcanvas.display();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void MoveDiskIncYneg(Rec[] r, int increment, int disk) {
		Rec r1 = r[r.length - disk];
		Point bl = new Point((int) (r1.getBL().getX()), (int) (r1.getBL().getY() - increment));
		Point br = new Point((int) (r1.getBR().getX()), (int) (r1.getBR().getY() - increment));
		Point tr = new Point((int) (r1.getUR().getX()), (int) (r1.getUR().getY() - increment));
		Point tl = new Point((int) (r1.getUL().getX()), (int) (r1.getUL().getY() - increment));
		r[r.length - disk] = new Rec(bl, br, tr, tl, r1.getC());
	}

	public void MoveDiskIncXneg(Rec[] r, int increment, int disk) {
		Rec r1 = r[r.length - disk];
		Point bl = new Point((int) (r1.getBL().getX() - increment), (int) (r1.getBL().getY()));
		Point br = new Point((int) (r1.getBR().getX() - increment), (int) (r1.getBR().getY()));
		Point tr = new Point((int) (r1.getUR().getX() - increment), (int) (r1.getUR().getY()));
		Point tl = new Point((int) (r1.getUL().getX() - increment), (int) (r1.getUL().getY()));
		r[r.length - disk] = new Rec(bl, br, tr, tl, r1.getC());
	}

	public void MoveDiskIncXpos(Rec[] r, int increment, int disk) {
		Rec r1 = r[r.length - disk];
		Point bl = new Point((int) (r1.getBL().getX() + increment), (int) (r1.getBL().getY()));
		Point br = new Point((int) (r1.getBR().getX() + increment), (int) (r1.getBR().getY()));
		Point tr = new Point((int) (r1.getUR().getX() + increment), (int) (r1.getUR().getY()));
		Point tl = new Point((int) (r1.getUL().getX() + increment), (int) (r1.getUL().getY()));
		r[r.length - disk] = new Rec(bl, br, tr, tl, r1.getC());
	}

	public void MoveDiskIncYpos(Rec[] r, int increment, int disk) {
		Rec r1 = r[r.length - disk];
		Point bl = new Point((int) (r1.getBL().getX()), (int) (r1.getBL().getY() + increment));
		Point br = new Point((int) (r1.getBR().getX()), (int) (r1.getBR().getY() + increment));
		Point tr = new Point((int) (r1.getUR().getX()), (int) (r1.getUR().getY() + increment));
		Point tl = new Point((int) (r1.getUL().getX()), (int) (r1.getUL().getY() + increment));
		r[r.length - disk] = new Rec(bl, br, tr, tl, r1.getC());
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getCurrsteps() {
		return currsteps;
	}

	public void setCurrsteps(int currsteps) {
		this.currsteps = currsteps;
	}

}
