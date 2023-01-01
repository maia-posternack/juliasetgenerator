/**
 * @author     Maia Posternack
 * @since      2021-4-16
 */

 /*
 Maia Posternack
 Adv. CS 2
 No bugs :)
 i did the EC with the arrow keys
 */

//libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;


public class JuliaSetGenerator {

  /**
   * The main function
   * @param args a String
   */

  public static void main(String[] args) {

    //tracks when mouse pressed
    int pressCheck = 0;

    //setup stuff
    StdDraw.enableDoubleBuffering();
    StdDraw.setCanvasSize(800, 300);
    StdDraw.setXscale​(0,800);
    StdDraw.setYscale​(0,300);
    DisplayWindow a = new DisplayWindow (0, 300, 300, 400);
    DisplayWindow b = new DisplayWindow (400, 300, 300, 400);
    Font font = new Font("Times New Roman", Font.BOLD, 15);
    StdDraw.setFont(font);
    boolean leftSide = true;

    //draw the mandelbrot and julia set
    runMand(a, new ComplexNumber(0,0));
    runJul(a,b);
    StdDraw.show();

    //draw loop
    while(true){

      //display x and y coordinate
      String text = "";
      if((StdDraw.mouseX()>=0)&&(StdDraw.mouseX()<=400)&&(StdDraw.mouseY()>=0)&&(StdDraw.mouseY()<=300)){
        ComplexNumber currentPos = new ComplexNumber((Math.round(100*a.mapA(StdDraw.mouseX()))),(Math.round(100*a.mapB(StdDraw.mouseY()))));
        ComplexNumber divide = new ComplexNumber(100,0);
        text = currentPos.divide(divide).toString();
      }
      else if ((StdDraw.mouseX()>=400)&&(StdDraw.mouseX()<=800)&&(StdDraw.mouseY()>=0)&&(StdDraw.mouseY()<=300)){
        ComplexNumber currentPos = new ComplexNumber((Math.round(100*a.mapA(StdDraw.mouseX()-400))),(Math.round(100*a.mapB(StdDraw.mouseY()))));
        ComplexNumber divide = new ComplexNumber(100,0);
        text = currentPos.divide(divide).toString();
      }
      else{
        text = "out of bounds";

      }
      StdDraw.setPenColor(255,255,255);
      StdDraw.filledRectangle(30,9,60,18);
      StdDraw.setPenColor(0,0,0);
      StdDraw.textLeft(10,10,text);

      //checks whether should zoom in/out, reset view, reset center
      if(StdDraw.hasNextKeyTyped() == true){
        char key = StdDraw.nextKeyTyped();
        if((key == 'i')){
          if(leftSide == true){
            a.zoomIn();
            runMand(a, new ComplexNumber(0,0));
          }
          else{
            b.zoomIn();
            runJul(a,b);
          }
        }
        else if((key == 'o')){
          if(leftSide == true){
            a.zoomOut();
            runMand(a, new ComplexNumber(0,0));
          }
          else{
            b.zoomOut();
            runJul(a,b);
          }
        }
        else if ((key == 'r')){
          if(leftSide == true){
            a.resetView();
            runMand(a, new ComplexNumber(0,0));
          }
          else{
            b.resetView();
            runJul(a,b);
          }

        }
      }
      //move right
      if(StdDraw.isKeyPressed(39)){
        if(leftSide == true){
          a.center = a.center.add(new ComplexNumber(.1,0));
          a.moveAround();
          runJul(a,b);
          runMand(a, new ComplexNumber(0,0));

        }
        else{
          b.center = b.center.add(new ComplexNumber(.1,0));
          b.moveAround();
          runJul(a,b);
        }
      }
      //move left
      if(StdDraw.isKeyPressed(37)){
        if(leftSide == true){
          a.center = a.center.add(new ComplexNumber(-.1,0));
          a.moveAround();
          runJul(a,b);
          runMand(a, new ComplexNumber(0,0));

        }
        else{
          b.center = b.center.add(new ComplexNumber(-.1,0));
          b.moveAround();
          runJul(a,b);
        }
      }
      //move up
      if(StdDraw.isKeyPressed(38)){
        if(leftSide == true){
          a.center = a.center.add(new ComplexNumber(0,.1));
          a.moveAround();
          runJul(a,b);
          runMand(a, new ComplexNumber(0,0));

        }
        else{
          b.center = b.center.add(new ComplexNumber(0,.1));
          b.moveAround();
          runJul(a,b);
        }
      }
      //move down
      if(StdDraw.isKeyPressed(40)){
        if(leftSide == true){
          a.center = a.center.add(new ComplexNumber(0,-.1));
          a.moveAround();
          runJul(a,b);
          runMand(a, new ComplexNumber(0,0));

        }
        else{
          b.center = b.center.add(new ComplexNumber(0,-.1));
          b.moveAround();
          runJul(a,b);
        }
      }

      //checks if mouse is released
      if(StdDraw.mousePressed()==true){
        pressCheck = 2;
      }
      else{
        pressCheck -=1;
      }
      if(pressCheck == 1){
        if((StdDraw.mouseX()>=0)&&(StdDraw.mouseX()<=400)&&(StdDraw.mouseY()>=0)&&(StdDraw.mouseY()<=300)){
          a.center = new ComplexNumber(a.mapA(StdDraw.mouseX()),a.mapB(StdDraw.mouseY()));
          runJul(a,b);
          a.updateZoom();
          runMand(a, new ComplexNumber(0,0));
          leftSide = true;
        }
        if((StdDraw.mouseX()>=400)&&(StdDraw.mouseX()<=800)&&(StdDraw.mouseY()>=0)&&(StdDraw.mouseY()<=300)){
          b.center = new ComplexNumber(b.mapA(StdDraw.mouseX()-400),b.mapB(StdDraw.mouseY()));
          b.updateZoom();
          runJul(a,b);
          leftSide = false;
        }
      }

      //show buffer
      StdDraw.show();
  }
}

/**
 * a function which runs through all points on a displaywindow and checks if they are in the mandelbrot set
 * @param d a DisplayWindow
 * @param startZ a ComplexNumber
 */

// loops through all points on the display window and colors in based off of how long it takes to escape to infinity
  public static void runMand(DisplayWindow d, ComplexNumber startZ){
    StdDraw.setPenRadius(0);
    //for each point
    for(double X = 0; X<=400; X++){
      for(double Y = 0; Y <=300;Y++){
        //turn to a complex number
        double A = d.mapA(X);
        double B = d.mapB(Y);
        ComplexNumber C = new ComplexNumber(A,B);
        //find number of tries to escape to infinity
        double color = testMand(startZ, C, 1);
        //color with corresponding color
        if(color == -1){
          StdDraw.setPenColor(0,0,0);
        }
        else{
          StdDraw.setPenColor(java.awt.Color.getHSBColor((float) (color+.5), (float) 1.0, (float) 1.0));
        }
        StdDraw.point(X+d.leftX,Y);
      }
    }
  }

  /**
   * a function which runs through all points on a displaywindow and checks if they are in the julia set
   * @param a a DisplayWindow
   * @param b a DisplayWindow
   */

  // loops through all points on the display window and colors in based off of how long it takes to escape to infinity
  public static void runJul(DisplayWindow a, DisplayWindow b){
    StdDraw.setPenRadius(0);
    //for each point
    for(double X = 0; X<=400; X++){
      for(double Y = 0; Y <=300;Y++){
        //turn to complex number

        double A = b.mapA(X);
        double B = b.mapB(Y);


      ComplexNumber startZ = new ComplexNumber(A,B);
      //startZ=startZ.add(new ComplexNumber(0,1));
       ComplexNumber C = new ComplexNumber(a.center.getReal(), a.center.getImaginary());
        //find number of tries to escape to infitiy
        double color = testMand(startZ, C, 1);
        //color with corresponding color
        if(color == -1){
          StdDraw.setPenColor(0,0,0);
        }
        else{
          StdDraw.setPenColor(java.awt.Color.getHSBColor((float) (color+.5), (float) 1.0, (float) 1.0));
        }
        StdDraw.point(X+b.leftX,Y);
      }
    }
  }

  /**
   * a recursive function which runs tests when a point escapes to infinity
   * @param lastZ a ComplexNumber
   * @param C a ComplexNumber
   * @param tries a double
   * @return an int (tries/50)
   */

//recursive function that finds how long it takes for each coordinate to escape to infinity
  public static double testMand(ComplexNumber lastZ, ComplexNumber C, double tries){
    //if you have tested less than 255 rounds
    if (tries<=255){
      //add again
      ComplexNumber newZ = new ComplexNumber(lastZ.square().add(C));
      // if escaped to infinity
      if(newZ.magnitude()>2){
        //stop and return tries/50
        return(tries/50);
      }
      //if didn't escape to infinity
      else{
        //try again
        return testMand(newZ, C, tries+1);
      }
    }
    //if this is your 256th test
    else{
      return (-1);
    }
  }
}
