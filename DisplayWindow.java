/**
 * @author     Maia Posternack
 * @since      2021-2-23
 */

 /*
 Maia Posternack
 Adv. CS 2
 */
public class DisplayWindow {

    public double leftX, bottomY; //The position of the DisplayWindow
    private double length, width; //The length along the x-axis and width along the y-axis of the DisplayWindow
    public double rMax, rMin; //The min and max values on the real axis. Starts at -2 ≤ a ≤ 2
    public double iMax, iMin; //The min and max values on the imaginary axis. Starts at-1.5 ≤ a ≤ 1.5
    private double widthHalf, heightHalf;
    private double zoomFactor; //Keeps track of the zoom ratio. Starts at 1. Increases/decreases according to “i”/“I” and “o”/“O” key presses.
    public ComplexNumber center;  //starts at 0+0i. Changes according to mouse clicks on the DisplayWindow


   	public DisplayWindow(double leftX, double bottomY, double length, double width){
    	this.leftX = leftX;
    	this.bottomY = bottomY;
      this.length = length;
      this.width = width;
      this.resetView();
   	}
    public void resetView(){
      this.center = new ComplexNumber(0,0);
      this.widthHalf = 2;
      this.heightHalf = 1.5;
      this.zoomFactor = 1;
      this.updateZoom();

    }

    public void zoomIn(){
      this.zoomFactor =.9 ;
      this.updateZoom();
    }

    public void zoomOut(){
      this.zoomFactor =1.1;
      this.updateZoom();
    }

    public void updateZoom(){
      this.widthHalf*= this.zoomFactor;
      this.heightHalf*= this.zoomFactor;
      this.rMax = this.center.getReal() + this.widthHalf;
      this.rMin = this.center.getReal() - this.widthHalf;
      this.iMax = this.center.getImaginary() + this.heightHalf;
      this.iMin = this.center.getImaginary() - this.heightHalf;

    }

    public void moveAround(){
      this.rMax = this.center.getReal() + this.widthHalf;
      this.rMin = this.center.getReal() - this.widthHalf;
      this.iMax = this.center.getImaginary() + this.heightHalf;
      this.iMin = this.center.getImaginary() - this.heightHalf;

    }

    private double map(double value, double oldMin, double oldMax, double newMin, double newMax){
      double x = ((value-oldMin)*(newMax-newMin))/(oldMax-oldMin) + newMin;
      return(x);
    }

    public double mapX(double a){
      return(map(a,rMin, rMax, leftX, width+leftX));

    }
    public double mapY(double b){
      return(map(b,iMin, iMax, bottomY-length,bottomY));

    }
    public double mapA(double windowX){
      return(map(windowX+this.leftX,leftX, width+leftX,rMin, rMax));

    }
    public double mapB(double windowY){
        return(map(windowY, bottomY-length, bottomY,iMin, iMax));
    }

    public void point(double x, double y){
      StdDraw.point(this.mapX(x)+this.leftX,this.mapY(y));
    }

   	 public static void main(String[] args) {

   	}
}
