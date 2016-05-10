/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complex;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
//import static sun.management.Agent.error;

/**
 *
 * @author Franklin
 */
public class Complex {

  private double real, imagniary;
  
  public Complex(){
      
  }

  public Complex(double real, double imaginary) {
      this.real = real;
      this.imagniary = imaginary; 
  }
  
  public String toString(){
     
      
      String operator,
         complex;
      if (imagniary < 0) 
          operator = "";
      else 
          operator = "+";
      
      if(imagniary ==0){
           complex =(""+real);
      }
      else if(imagniary ==1){
          complex = (""+real+operator+"i");
      }
      else if(real ==0){
          if(imagniary ==1)
              complex =(""+"i");
          else{
          complex = (""+imagniary+"i");
          }
      }
      else{
       //complex = (""+real+operator+imagniary+"i");   
       complex = String.format("%.5f"+operator+"%.5f"+"i",real,imagniary);
      }


 
        //if(imagniary ==0) return real+"";
      //if(real == 0) return imagniary+ "i";
      //if(imagniary <0) return real+ " - "+(-imagniary)+"i";
      //return real+" + "+imagniary + "i";
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

   /* if(denomiator.imagniary == 0 && numerator.imagniary == 0)
        imagniary = 0;
    else if(denomiator.imagniary ==0 || denomiator.real ==0)
        throw new ArithmeticException();
    */

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
