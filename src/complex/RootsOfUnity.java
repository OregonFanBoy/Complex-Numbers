package complex;

import java.util.Scanner;

/**
 * @author Franklin Chappell
 * Purpose: Using the complex numbers, we can calculate the roots of unity.
 */
public class RootsOfUnity {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        
        int num = 0;
        boolean notpass = true;
        System.out.println("Please enter a number to find the root of unity");
        do{
            try{
                num = scan.nextInt();
                notpass=false;
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a whole number. ");
                notpass = true;
            }
            if(num >100 || num < 1){
                System.out.println("Please enter a number within the range: 1-100");
                notpass = true;
            }
        }while(notpass);
        
        for(int angle =0;angle<Math.PI*2;angle +=Math.PI*2/num){
           System.out.println(new Complex(Math.cos(angle),Math.sin(angle)));
        } 
    }
}
