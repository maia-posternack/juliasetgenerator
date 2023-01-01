/**
 * @author     Maia Posternack
 * @since      2021-2-23
 */

 /*
 Maia Posternack
 Adv. CS 2
 No errors
 No extra credit
 Feb 2021
 */
public class ComplexNumber {

   	private double a;
   	private double b;

   	/**
   	 * Creates a new ComplexNumber with both real and imaginary components
   	 * @param a a double real component of the complex number
   	 * @param b a  double imaginary component of the complex number
   	 */
   	public ComplexNumber(double a, double b){
          	this.a = a;
          	this.b = b;
   	}


    /**
    *The default constructer which creates a new ComplexNumber (0)
    * @param none
    */

    public ComplexNumber(){
            this.a = 0;
            this.b = 0;
    }

   	/**
   	 * The "copy constructor" which creates a new ComplexNumber from an existing ComplexNumber
   	 * @param c a ComplexNumber
   	 */
   	public ComplexNumber(ComplexNumber c){
          	this.a = c.getReal();
          	this.b = c.getImaginary();
   	}

   	/**
   	 * An "accessor" method which returns the real component of this ComplexNumber
   	 * @return a double which is the private real component of this ComplexNumber
   	 */
   	public double getReal(){
          	return a;
   	}

   	/**
   	 * An "accessor" method which returns the imaginary component of this ComplexNumber
   	 * @return b double which is the private imaginary component of this ComplexNumber
   	 */
   	public double getImaginary(){
          	return b;
   	}

    /** The "to string" method
    * Returns this ComplexNumber as a string
    * @ return a string this ComplexNumber converted to a string
    */
    public String toString(){

      if((a!=0)&&(b>0)){
        if(b==1){
          return(a+"+i");
        }
        return a + "+" + b + "i";
      }
      else if((a!=0)&&(b<0)){
        if(b==-1){
          return(a+"-i");
        }
        return a + "" + b + "i";
      }
      else if((a!=0)&&(b==0)){
        return a+ "";
      }
      else if((a==0)&&(b!=0)){
        return b+"i";
      }
      else{
        return "0.0";
      }
    }

    /** an instance method of ADD which
    * returns the sum of this ComplexNumber and ComplexNumber c
    *@param c a ComplexNumber to add
    *@return a new ComplexNumber
  */
    public ComplexNumber add(ComplexNumber c){
      double newA = this.a + c.getReal();
      double newB = this.b + c.getImaginary();

       return new ComplexNumber (newA, newB);
    }//end add

    /** a static method of ADD which
    * returns the sum of ComplexNumber c1 and c2
    *@param c1 a ComplexNumber to add
    *@param c2 a ComplexNumber to add
    *@return a new ComplexNumber
  */
    public static ComplexNumber add(ComplexNumber c1, ComplexNumber c2){
      double newA = c1.getReal() + c2.getReal();
      double newB = c1 .getImaginary() + c2.getImaginary();

      return new ComplexNumber (newA, newB);

    }


    /** an instance method of SUBTRACT which
    * returns the difference of this ComplexNumber and ComplexNumber c
    *@param c a ComplexNumber to subtract from this
    *@return a new ComplexNumber
    */

    public ComplexNumber subtract(ComplexNumber c){
      double newA = this.a - c.getReal();
      double newB = this.b - c.getImaginary();

       return new ComplexNumber (newA, newB);
    }//end subtracted

    /** a static method of SUBTRACT which
    * Rrturns the difference of ComplexNumber c1 and c2
    *@param c1 a ComplexNumber
    *@param c2 a ComplexNumber to subtract from c1
    *@return a new ComplexNumber
  */

public static ComplexNumber subtract(ComplexNumber c1, ComplexNumber c2){
  double newA = c1.getReal() - c2.getReal();
  double newB = c1.getImaginary() - c2.getImaginary();

   return new ComplexNumber (newA, newB);

}


    /** an instance method of MULTIPLY which
    * returns the product of this ComplexNumber and ComplexNumber c
    *@param c a ComplexNumber to multiply
    *@return a new ComplexNumber
    */

    public ComplexNumber multiply(ComplexNumber c){
      double multA = c.getReal();
      double multB = c.getImaginary();
      double newA = this.a*multA - this.b*multB;
      double newB = this.a*multB + this.b*multA;

       return new ComplexNumber (newA, newB);
    }//end multiply


    /** an static method of MULTIPLY which
    * returns the product of ComplexNumber c1 and c2
    *@param c1 a ComplexNumber to multiply
    *@param c2 a ComplexNumber to multiply
    *@return a new ComplexNumber
  */

    public static ComplexNumber multiply(ComplexNumber c1, ComplexNumber c2){
      double newA = c1.getReal()*c2.getReal() - c1.getImaginary()*c2.getImaginary();
      double newB = c1.getReal()*c2.getImaginary() + c1.getImaginary()*c2.getReal();

       return new ComplexNumber (newA, newB);
    }


      /** an instance method of DIVIDE which
      *returns the quotient of this ComplexNumber and ComplexNumber c
      *@param c a ComplexNumber to divide this by
      *@throws ArithmeticException division by 0
      *@return a new ComplexNumber
      */
    public ComplexNumber divide(ComplexNumber c){
      double divA = c.getReal();
      double divB = c.getImaginary();

      if((divA == 0)&&(divB == 0)){
        throw new ArithmeticException("you can't divide by 0");
        //return new ComplexNumber(0,0);
      }

      double real =this.multiply(c.conjugate()).getReal();
      double imaginary = this.multiply(c.conjugate()).getImaginary();


      double denominator = divA*divA+divB*divB;
       return new ComplexNumber (real/denominator, imaginary/denominator);

    }//end divide


    /** a static method of DIVIDE which
    * returns the quotient of ComplexNumber c1 and c2
    *@param c1 a ComplexNumber
    *@param c2 a ComplexNumber to divide c1 by
    *@throws ArithmeticException division by 0
    *@return a new ComplexNumber
  */

    public static ComplexNumber divide(ComplexNumber c1, ComplexNumber c2){
      double divA = c2.getReal();
      double divB = c2.getImaginary();

      if((divA == 0)&&(divB == 0)){
        throw new ArithmeticException("you can't divide by 0");
        //return new ComplexNumber(0,0);
      }

      double real =c1.multiply(c2.conjugate()).getReal();
      double imaginary = c1.multiply(c2.conjugate()).getImaginary();
      double denominator = divA*divA+divB*divB;
       return new ComplexNumber (real/denominator, imaginary/denominator);

    }

    /** an instance method of SQUARE which
    *returns the square of this ComplexNumber
    *@param none
    *@return a new ComplexNumber
    */

      public ComplexNumber square(){
        return (this.multiply(this));

      }
      /** a static method of SQUARE which
      * returns the square of c
      *@param c a ComplexNumber
      *@return a new ComplexNumber
      */


      public static ComplexNumber square(ComplexNumber c){
        return (c.multiply(c));


      }

      /** Returns the conjugate of this ComplexNumber
      * @param none
      * @return a new ComplexNumber
      */

    private ComplexNumber conjugate(){
      return new ComplexNumber(a, -1*b);
    }

    /** Returns the magnitude of this ComplexNumber
    * @param none
    * @return a new ComplexNumber
    */
    public double magnitude(){
      double a = this.getReal()*this.getReal();
      double b = this.getImaginary()*this.getImaginary();

    return(Math.sqrt(a+b));
  }

  /** Compares the magnitude of this ComplexNumber to ComplexNumber c
  * @param c a ComplexNumber
  * @return an int (-1: this < c; 0: this = c; 1: this > c)
  */

  public int compareTo(ComplexNumber c){
    double thisLength = this.magnitude();
    double cLength = c.magnitude();
    if(thisLength<cLength){
      return(-1);
    }
    else if(thisLength==cLength){
      return(0);
    }
    else{
      return(1);
    }
  }

   	/**
   	 * A tester method
   	 * @param args a String
   	 */
   	public static void main(String[] args) {
    //below, find my tests! 

            /*ComplexNumber c1 = new ComplexNumber(1, -2);
            ComplexNumber c2 = new ComplexNumber(2, 3);


            System.out.println("c1 "+c1);
            System.out.println("c2 "+c2);

            System.out.println("c1 real "+c1.getReal());
            System.out.println("c1 imag "+c1.getImaginary());
            System.out.println("add instance "+c1.add(c2));
            System.out.println("add static "+add(c1,c2));
            System.out.println("subtract instance "+c1.subtract(c2));
            System.out.println("subtract static "+subtract(c1,c2));
            System.out.println("mult instance "+c1.multiply(c2));
            System.out.println("mult static "+multiply(c1,c2));
            System.out.println("div instance "+c1.divide(c2));
            System.out.println("div static "+divide(c1,c2));
            System.out.println("square instance "+c1.square());
            System.out.println("square static "+square(c1));
            System.out.println("magnitude c1 "+c1.magnitude());
            System.out.println("magnitude c2 "+c2.magnitude());
            System.out.println("compare c1 c2 "+c1.compareTo(c2));
            System.out.println("compare c2 c1 "+c2.compareTo(c1));*/

   	}
}
