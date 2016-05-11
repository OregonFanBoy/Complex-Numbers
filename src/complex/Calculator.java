/**
 * @author Franklin Chappell
 * Purpose: Main driver class. Presents a good use of String tokenizer for
 *          complex numbers.
 */
package complex;
import java.io.*;

public class Calculator {
    public static void main (String[] args){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        Complex num1, num2, result = new Complex();
        char        output;
        boolean     more;
        
        System.out.println("\nComplex number calculation program\n");
        
        do{
            num1 = readComplex(in);
            num2 = readComplex(in);
            
            output = readChar(in, "Enter the operation '+, -, *, or /': ", "+-*/");
            switch(output){
                case '+':
                    result = num1.add(num2);
                    break;
                case '-':
                    result = num1.minus(num2);
                    break;
                case '*':
                    result = num1.product(num2);
                    break;
                case '/':
                    try{
                    result = num1.division(num2);
                    }
                    catch(ArithmeticException e){
                        System.out.println("Can not divide by zero.");
                        System.exit(1);
                    }
                    break;
            }
            System.out.println(num1+" "+output+" "+num2+" = "+result);
            more =(readChar(in, "Perfome another calculation? (y/n): ", "yn")!='n');
        
        }while (more);
        
        System.out.println("\n\nCalculation program completed\n");
    }
    
    /***************************************************************************
     *      METHOD TO READ A COMPLEX NUMBER
     **************************************************************************/
     public static Complex readComplex(BufferedReader in){
         String token;
        
        while(true){
            System.out.print("Enter a complex number: ");
            try{
                token = in.readLine();
                return Complex.parseComplex(token);
            }
            catch(Exception exception){
                System.out.println("Ilegal Format - Enter 'a+bi' and try again");
            }        
        }
    }
     
     /**************************************************************************
      *     METHOD TO READ THE FIRST CHARACTER OF A STRING
      *************************************************************************/
     public static char readChar(BufferedReader in, String prompt, String chars){
         String token;
         char   value;
         
         while(true){
             try{
                 System.out.print(prompt);
                 token = in.readLine();
                 value = token.toLowerCase().charAt(0);
                 if(chars.indexOf(value)<0)throw new NumberFormatException();
                 return value;
             }
             catch(Exception exception){
                 System.out.println("Illegal - Try again");
             }
         }
     }
}      
