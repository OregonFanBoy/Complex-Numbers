/*
 * @author Franklin Chappell
 * Purpose: Calculates complex numbers and performs the correct output
 *          of the resulted value.
 */
package complex;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Complex {

  private double real, imagniary;
  
  public Complex(){ }

  public Complex(double real, double imaginary) {
      this.real = real;
      this.imagniary = imaginary; 
  }
  
   
   // Method will preform the correct output of the resulted value.
  public String toString(){ 
      String operator,
         complex;
      if (imagniary < 0) // There is no need to display the operator if i < 0
          operator = "";
      else 
          operator = "+";
      
      if(imagniary ==0){ //We only want to display the real number if i=0
           complex =(""+real);
      }
      else if(imagniary ==1){ //We need to display the real + i if i=1
          complex = (""+real+operator+"i");
      }
      else if(real ==0){    // if real is 0 then we only want to display imaginary
          if(imagniary ==1) // if imaginary = 1 then we only want to display i
              complex =(""+"i");
          else{
          complex = (""+imagniary+"i");
          }
      }
      else{
       complex = String.format("%.5f"+operator+"%.5f"+"i",real,imagniary);
      }
      return complex;
  }
  /*****************************************************************************
   *        ADDITION 
   ****************************************************************************/
  public Complex add(Complex c){
      return new Complex( real+c.real, imagniary+c.imagniary);
  }
  
  /****************************************************************************
   *        SUBTRACTION
   ****************************************************************************/
  public Complex minus(Complex c){    
      return new Complex(real-c.real, imagniary-c.imagniary);
  }
  
  /****************************************************************************
   *        MULTIPLICATION
   ****************************************************************************/
  public Complex product(Complex c){    
      return new Complex(
            (real * c.real) - (imagniary * c.imagniary),
            (real * c.imagniary) + (imagniary * c.real)
      ); 
  }
  
  /****************************************************************************
   *        DIVISION
   ****************************************************************************/
public Complex division(Complex c) throws ArithmeticException{
     
   Complex conj = new Complex(c.real, c.imagniary * (-1.0));

   if(c.real == 0 && c.imagniary==0)
       throw new ArithmeticException();
    Complex numerator = product(conj);
    Complex denomiator = c.product(conj);

    double real = numerator.real / denomiator.real;
    double imagniary = numerator.imagniary / denomiator.real;
    return new Complex(real,imagniary);
  }

  /****************************************************************************
   *        PARSE COMPLEX NUMBERS
     * @return 
   ****************************************************************************/
  public static Complex parseComplex(String str) throws NumberFormatException,
                                                        NoSuchElementException{
      
    StringTokenizer token = new StringTokenizer(str,"i+-",true);
    
    double real;
    double imagniary;
    
    byte sign = 1;
    String token_String = token.nextToken();
    switch(token_String.charAt(0)){
        case '+':
            token_String = token.nextToken();
        break;
        case '-':
            sign = -1;
            token_String = token.nextToken();
            break;
    }
    real = Double.parseDouble(token_String)*sign;
    token_String = token.nextToken();
    
    switch(token_String.charAt(0)){
        case '+':
            sign = 1;
            token_String = token.nextToken();
            break;
        case '-':
            sign = -1;
            token_String = token.nextToken();
            break;
        case 'i':
            //imagniary = real;
            //real = 0;
            try{
                token_String = token.nextToken();
                System.out.println("Invalid entry");
                System.exit(0);
            }
        catch(Exception excpetion){}
        break;
    }
    
    imagniary = Double.parseDouble(token_String)*sign;
    
    return new Complex(real,imagniary);
  }
  
}
