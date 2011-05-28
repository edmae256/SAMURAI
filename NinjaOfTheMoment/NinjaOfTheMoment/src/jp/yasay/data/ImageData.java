package jp.yasay.data;

import android.graphics.Point;
import android.graphics.Rect;


public class ImageData {
	private Point p;
	private Rect rect;

	public ImageData(){
		p = new Point(0,0);
		rect = new Rect(0, 0, 0, 0);
	}

	public Point getPoint(){ return this.p; }
	public void setPoint(Point p){ this.p = p; }
	public Rect getRectangle(){ return this.rect; }
	public void setRectangle(Rect r){ this.rect = r; }


}
