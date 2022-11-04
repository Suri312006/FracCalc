
import java.util.*;

public class FracCalcOriginal {
    public static void main(String[] args) {
        boolean run = true;

        // while loop to keep asking for new fractions
        while (run) {
            Scanner console = new Scanner(System.in);
            System.out.print("enter expression (or enter \"quit\"): ");
            String lmao = console.nextLine();

            if (lmao.contains("quit")) {
                run = false;
            } else {
                String result = produceAnswer(lmao);
                System.out.println(result);
            }
        }

    }

    public static String produceAnswer(String input)// do not alter this method header
    {
        String endResult;

        String frac1 = promptSplit(input, 0);
        String frac2 = promptSplit(input, 2);
        String operation = findOperand(input);

        if (operation.equals("+")) {
            endResult = addition(frac1, frac2);
        } else if (operation.equals("-")) {
            endResult = subtraction(frac1, frac2);
        } else if (operation.equals("*")) {
            endResult = multiplication(frac1, frac2);
        } else if (operation.equals("/")) {
            endResult = division(frac1, frac2);
        } else {
            endResult = "Please enter a valid operator ( + , - , * , / )";
        }

        return endResult;
    }

    // checks if it is only a fraction
    public static boolean fracReal(String input) {
        boolean frac = false;
        if (input.contains("/") && !input.contains("_")) {
            frac = true;
        }
        return frac;

    }

    // checks if it is only a whole number
    public static boolean wholeReal(String input) {
        boolean whole = false;

        if (!input.contains("_") && !input.contains("/")) {
            whole = true;
        }

        return whole;

    }

    // checks if it is only a whole and fraction
    public static boolean wholefracReal(String input) {
        boolean wholefrac = false;

        if (input.contains("_") && input.contains("/")) {
            wholefrac = true;
        }
        return wholefrac;
    }

    // methods for all the different operations that can be done

    // ADDITION IS DONE LMAOOOO
    public static String addition(String frac1, String frac2) {

        String sum = "";

        int frac1whole = 0;
        int frac1numer = 0;
        int frac1denom = 0;

        int frac2whole = 0;
        int frac2numer = 0;
        int frac2denom = 0;

        if (wholeReal(frac1)) {
            frac1whole = findWhole(frac1);
        }
        if (wholeReal(frac2)) {
            frac2whole = findWhole(frac2);
        }
        if (fracReal(frac1)) {
            frac1numer = findNumerator(frac1);
            frac1denom = findDenominator(frac1);
        }
        if (fracReal(frac2)) {
            frac2numer = findNumerator(frac2);
            frac2denom = findDenominator(frac2);

        }
        if (wholefracReal(frac1)) {
            frac1whole = findWhole(frac1);
            frac1numer = findNumerator(frac1);
            frac1denom = findDenominator(frac1);
        }
        if (wholefracReal(frac2)) {
            frac2whole = findWhole(frac2);
            frac2numer = findNumerator(frac2);
            frac2denom = findDenominator(frac2);
        }

        // whole + whole
        if (wholeReal(frac1) && wholeReal(frac2)) {

            int resultWholeN = frac1whole + frac2whole;
            String resultWhole = String.valueOf(resultWholeN);
            sum = resultWhole;

        }

        // whole + frac
        if (wholeReal(frac1) && fracReal(frac2)) {

            String resultWholeFrac = frac1 + "_" + frac2;
            sum = resultWholeFrac;

        }

        // whole + wholefrac
        if (wholeReal(frac1) && wholefracReal(frac2)) {
            int wholeplus = frac1whole + frac2whole;
            String resultPlus = wholeplus + "_" + frac2numer + "/" + frac2denom;
            sum = resultPlus;

        }
        // frac + whole
        if (fracReal(frac1) && wholeReal(frac2)) {

            String resultWholeFrac = frac2 + "_" + frac1;
            sum = resultWholeFrac;

        }

        // frac + frac
        if (fracReal(frac1) && fracReal(frac2)) {
            int resultNumN = (frac2denom * frac1numer) + (frac1denom * frac2numer);
            int resultDenomN = frac1denom * frac2denom;
            String resultFrac = resultNumN + "/" + resultDenomN;
            sum = resultFrac;

        }

        // frac + wholefrac
        if (fracReal(frac1) && wholefracReal(frac2)) {
            int wholeNum = frac2whole;
            int fracAddNum = (frac2denom * frac1numer) + (frac1denom * frac2numer);
            int fracAddDenom = frac1denom * frac2denom;
            String resultWholeFrac = wholeNum + "_" + fracAddNum + "/" + fracAddDenom;
            sum = resultWholeFrac;

        }

        // wholeFrac + whole
        if (wholefracReal(frac1) && wholeReal(frac2)) {
            int wholeAdd = frac1whole + frac2whole;
            String resultWholeFrac = wholeAdd + "_" + frac1numer + "/" + frac1denom;
            sum = resultWholeFrac;

        }

        // WholeFrac + frac
        if (wholefracReal(frac1) && fracReal(frac2)) {
            int wholeNum = frac1whole;
            int fracAddNum = (frac2denom * frac1numer) + (frac1denom * frac2numer);
            int fracAddDenom = frac1denom * frac2denom;
            String resultWholeFrac = wholeNum + "_" + fracAddNum + "/" + fracAddDenom;
            sum = resultWholeFrac;

        }

        // wholefrac + wholefrac
        if (wholefracReal(frac1) && wholefracReal(frac2)) {
            int new1Numer = 0;
            if(frac1whole < 0){
                new1Numer = (frac1whole * frac1denom)-frac1numer;
            }
            if(frac1whole > 0){
                new1Numer = (frac1whole * frac1denom)+frac1numer;
            }

            int new2Numer = 0;
            if(frac2whole < 0){
                new2Numer = (frac2whole * frac2denom)-frac2numer;
            }
            if(frac2whole > 0){
                new2Numer = (frac2whole * frac2denom)+frac2numer;
            }

            int newResultNumer = (new1Numer * frac2denom)+(frac1denom * new2Numer);
            int newResultDenom = frac1denom*frac2denom;
            String resultFrac = newResultNumer + "/"+newResultDenom;
            sum = resultFrac;

        }
        sum = simplify(sum);
        return sum;
    }

    public static String subtraction(String frac1, String frac2) {

        String sum = "";

        int frac1whole = 0;
        int frac1numer = 0;
        int frac1denom = 0;

        int frac2whole = 0;
        int frac2numer = 0;
        int frac2denom = 0;

        if (wholeReal(frac1)) {
            frac1whole = findWhole(frac1);
        }
        if (wholeReal(frac2)) {
            frac2whole = findWhole(frac2);
        }
        if (fracReal(frac1)) {
            frac1numer = findNumerator(frac1);
            frac1denom = findDenominator(frac1);
        }
        if (fracReal(frac2)) {
            frac2numer = findNumerator(frac2);
            frac2denom = findDenominator(frac2);

        }
        if (wholefracReal(frac1)) {
            frac1whole = findWhole(frac1);
            frac1numer = findNumerator(frac1);
            frac1denom = findDenominator(frac1);
        }
        if (wholefracReal(frac2)) {
            frac2whole = findWhole(frac2);
            frac2numer = findNumerator(frac2);
            frac2denom = findDenominator(frac2);
        }

        // whole - whole done
        if (wholeReal(frac1) && wholeReal(frac2)) {

            int resultWholeN = frac1whole - frac2whole;
            String resultWhole = String.valueOf(resultWholeN);
            sum = resultWhole;

        }

        // whole - frac done
        if (wholeReal(frac1) && fracReal(frac2)) {

            int newNumer = (frac1whole * frac2denom)-frac2numer;
            int newWhole = newNumer/frac2denom;
            int newFracNumer = newNumer % frac2denom;
            String resultWholeFrac = newWhole +"_"+newFracNumer+"/"+frac2denom;
            sum = resultWholeFrac;

        }

        // whole - wholefrac done
        if (wholeReal(frac1) && wholefracReal(frac2)) {
            int frac1NewNumer = frac1whole * frac2denom;
            int frac2NewNumer = (frac2whole * frac2denom)+frac2numer;
            int newFracNumer = frac1NewNumer - frac2NewNumer;
            int newWhole = newFracNumer / frac2denom;
            int newResultFracNumer = newFracNumer % frac2denom;
            String resultWholeFrac = newWhole+"_"+newResultFracNumer+"/"+frac2denom;
            sum = resultWholeFrac;
        }
        // frac - whole done
        if (fracReal(frac1) && wholeReal(frac2)) {
            int frac2NewNumer = (frac2whole * frac1denom);
            int newFracNumer = frac1numer - frac2NewNumer;
            int newResultFracWhole = newFracNumer / frac1denom;
            int newResultFracNumer = newFracNumer % frac1denom;
            String resultWholeFrac = newResultFracWhole+"_"+newResultFracNumer+"/"+frac1denom;
            sum = resultWholeFrac;

        }

        // frac - frac done
        if (fracReal(frac1) && fracReal(frac2)) {
            int resultNumN = (frac2denom * frac1numer) - (frac1denom * frac2numer);
            int resultDenomN = frac1denom * frac2denom;
            String resultFrac = resultNumN + "/" + resultDenomN;
            sum = resultFrac;

        }

        // frac - wholefrac done
        if (fracReal(frac1) && wholefracReal(frac2)) {
            int new2Numer = (frac2whole * frac2denom)+frac2numer;
            int newResultNumer = (frac1numer * frac2denom)-(frac1denom * new2Numer);
            int newResultDenom = frac2denom*frac1denom;
            String resultFrac = newResultNumer + "/"+newResultDenom;
            sum = resultFrac;
        }

        // wholeFrac - whole done
        if (wholefracReal(frac1) && wholeReal(frac2)) {
            int wholeAdd = frac1whole - frac2whole;
            String resultWholeFrac = wholeAdd + "_" + frac1numer + "/" + frac1denom;
            sum = resultWholeFrac;

        }

        // WholeFrac - frac done
        if (wholefracReal(frac1) && fracReal(frac2)) {
            int new1Numer = (frac1whole * frac1denom)+frac1numer;
            int newResultNumer = (new1Numer * frac2denom)-(frac1denom * frac2numer);
            int newResultDenom = frac1denom*frac2denom;
            String resultFrac = newResultNumer + "/"+newResultDenom;
            sum = resultFrac;

        }

        // wholefrac - wholefrac done
        if (wholefracReal(frac1) && wholefracReal(frac2)) {
            int new1Numer = 0;
            if(frac1whole < 0){
                new1Numer = (frac1whole * frac1denom)-frac1numer;
            }
            if(frac1whole > 0){
                new1Numer = (frac1whole * frac1denom)+frac1numer;
            }

            int new2Numer = 0;
            if(frac2whole < 0){
                new2Numer = (frac2whole * frac2denom)-frac2numer;
            }
            if(frac2whole > 0){
                new2Numer = (frac2whole * frac2denom)+frac2numer;
            }

            int newResultNumer = (new1Numer * frac2denom)-(frac1denom * new2Numer);
            int newResultDenom = frac1denom*frac2denom;
            String resultFrac = newResultNumer + "/"+newResultDenom;
            sum = resultFrac;

        }
        sum = simplify(sum);
        return sum;
    }

    public static String multiplication(String frac1, String frac2) {

        String sum = "";

        int frac1whole = 0;
        int frac1numer = 0;
        int frac1denom = 0;

        int frac2whole = 0;
        int frac2numer = 0;
        int frac2denom = 0;

        if (wholeReal(frac1)) {
            frac1whole = findWhole(frac1);
        }
        if (wholeReal(frac2)) {
            frac2whole = findWhole(frac2);
        }
        if (fracReal(frac1)) {
            frac1numer = findNumerator(frac1);
            frac1denom = findDenominator(frac1);
        }
        if (fracReal(frac2)) {
            frac2numer = findNumerator(frac2);
            frac2denom = findDenominator(frac2);

        }
        if (wholefracReal(frac1)) {
            frac1whole = findWhole(frac1);
            frac1numer = findNumerator(frac1);
            frac1denom = findDenominator(frac1);
        }
        if (wholefracReal(frac2)) {
            frac2whole = findWhole(frac2);
            frac2numer = findNumerator(frac2);
            frac2denom = findDenominator(frac2);
        }

        // whole * whole done
        if (wholeReal(frac1) && wholeReal(frac2)) {

            int resultWholeN = frac1whole * frac2whole;
            String resultWhole = String.valueOf(resultWholeN);
            sum = resultWhole;

        }

        // whole * frac done
        if (wholeReal(frac1) && fracReal(frac2)) {

            int result = frac1whole * frac2numer;
            String resultWholeFrac = result + "/" + frac2denom;
            sum = resultWholeFrac;

        }

        // whole * wholefrac done
        if (wholeReal(frac1) && wholefracReal(frac2)) {
            int new2numer = (frac2whole * frac2denom)+ frac2numer;
            int newResultNumer = new2numer*frac1whole;
            String resultPlus = newResultNumer +"/"+ frac2denom;
            sum = resultPlus;

        }
        // frac * whole done
        if (fracReal(frac1) && wholeReal(frac2)) {

            int result = frac2whole * frac1numer;
            String resultWholeFrac = result + "/" + frac1denom;
            sum = resultWholeFrac;

        }

        // frac * frac done
        if (fracReal(frac1) && fracReal(frac2)) {
            int resultNumN = frac1numer * frac2numer;
            int resultDenomN = frac1denom * frac2denom;
            String resultFrac = resultNumN + "/" + resultDenomN;
            sum = resultFrac;

        }

        // frac * wholefrac done
        if (fracReal(frac1) && wholefracReal(frac2)) {
            int new2numer = (frac2whole * frac2denom)+ frac2numer;
            int newResultNumer = new2numer*frac1numer;
            int newResultDenom = frac1denom * frac2denom;
            String resultPlus = newResultNumer +"/"+ newResultDenom;
            sum = resultPlus;

        }

        // wholeFrac * whole done
        if (wholefracReal(frac1) && wholeReal(frac2)) {
            int new1numer = (frac1whole * frac1denom)+ frac1numer;
            int newResultNumer = new1numer*frac2whole;
            String resultPlus = newResultNumer +"/"+ frac1denom;
            sum = resultPlus;

        }

        // WholeFrac * frac done
        if (wholefracReal(frac1) && fracReal(frac2)) {
            int new1numer = (frac1whole * frac1denom)+ frac1numer;
            int newResultNumer = new1numer*frac2numer;
            int newResultDenom = frac1denom * frac2denom;
            String resultPlus = newResultNumer +"/"+ newResultDenom;
            sum = resultPlus;


        }

        // wholefrac * wholefrac done
        if (wholefracReal(frac1) && wholefracReal(frac2)) {
            int new1Numer = 0;
            if(frac1whole < 0){
                new1Numer = (frac1whole * frac1denom)-frac1numer;
            }
            if(frac1whole > 0){
                new1Numer = (frac1whole * frac1denom)+frac1numer;
            }

            int new2Numer = 0;
            if(frac2whole < 0){
                new2Numer = (frac2whole * frac2denom)-frac2numer;
            }
            if(frac2whole > 0){
                new2Numer = (frac2whole * frac2denom)+frac2numer;
            }
            int newResultNumer = new1Numer*new2Numer;
            int newResultDenom = frac1denom * frac2denom;
            String resultPlus = newResultNumer +"/"+ newResultDenom;
            sum = resultPlus;

        }
        sum = simplify(sum);
        return sum;
    }

    public static String division(String frac1, String frac2) {

        String sum = "";

        int frac1whole = 0;
        int frac1numer = 0;
        int frac1denom = 0;

        int frac2whole = 0;
        int frac2numer = 0;
        int frac2denom = 0;

        if (wholeReal(frac1)) {
            frac1whole = findWhole(frac1);
        }
        if (wholeReal(frac2)) {
            frac2whole = findWhole(frac2);
        }
        if (fracReal(frac1)) {
            frac1numer = findNumerator(frac1);
            frac1denom = findDenominator(frac1);
        }
        if (fracReal(frac2)) {
            frac2numer = findNumerator(frac2);
            frac2denom = findDenominator(frac2);

        }
        if (wholefracReal(frac1)) {
            frac1whole = findWhole(frac1);
            frac1numer = findNumerator(frac1);
            frac1denom = findDenominator(frac1);
        }
        if (wholefracReal(frac2)) {
            frac2whole = findWhole(frac2);
            frac2numer = findNumerator(frac2);
            frac2denom = findDenominator(frac2);
        }

        // whole / whole done
        if (wholeReal(frac1) && wholeReal(frac2)) {


            String resultWholeFrac = frac1 + "/" + frac2;
            sum = resultWholeFrac;

        }

        // whole / frac done
        if (wholeReal(frac1) && fracReal(frac2)) {

            int resultNumer = frac1whole * frac2denom;
            String resultWholeFrac = resultNumer + "/" + frac2numer;
            sum = resultWholeFrac;

        }

        // whole / wholefrac done
        if (wholeReal(frac1) && wholefracReal(frac2)) {
            int new2numer = (frac2whole * frac2denom)+ frac2numer;
            int newResultNumer = (frac1whole * frac2denom);
            String resultPlus = newResultNumer +"/"+ new2numer;
            sum = resultPlus;

        }
        // frac / whole done
        if (fracReal(frac1) && wholeReal(frac2)) {

            int result = frac2whole * frac1denom;
            String resultWholeFrac = result + "/" + frac1numer;
            sum = resultWholeFrac;

        }

        // frac / frac done
        if (fracReal(frac1) && fracReal(frac2)) {
            int resultNumN = frac1numer * frac2denom;
            int resultDenomN = frac1denom * frac2numer;
            String resultFrac = resultNumN + "/" + resultDenomN;
            sum = resultFrac;

        }

        // frac / wholefrac done
        if (fracReal(frac1) && wholefracReal(frac2)) {
            int new2numer = (frac2whole * frac2denom)+ frac2numer;
            int newResultNumer = new2numer*frac1denom;
            int newResultDenom = frac1numer * frac2denom;
            String resultPlus = newResultNumer +"/"+ newResultDenom;
            sum = resultPlus;

        }

        // wholeFrac / whole done
        if (wholefracReal(frac1) && wholeReal(frac2)) {
            int new1numer = (frac1whole * frac1denom)+ frac1numer;
            int newResultNumer = new1numer;
            int newResultDenom = (frac1denom * frac2whole);
            String resultPlus = newResultNumer +"/"+ newResultDenom;
            sum = resultPlus;

        }

        // WholeFrac / frac done
        if (wholefracReal(frac1) && fracReal(frac2)) {
            int new1numer = (frac1whole * frac1denom)+ frac1numer;
            int newResultNumer = new1numer*frac2denom;
            int newResultDenom = frac1denom * frac2numer;
            String resultPlus = newResultNumer +"/"+ newResultDenom;
            sum = resultPlus;


        }

        // wholefrac / wholefrac done
        if (wholefracReal(frac1) && wholefracReal(frac2)) {
            int new1Numer = 0;
            if(frac1whole < 0){
                new1Numer = (frac1whole * frac1denom)-frac1numer;
            }
            if(frac1whole > 0){
                new1Numer = (frac1whole * frac1denom)+frac1numer;
            }

            int new2Numer = 0;
            if(frac2whole < 0){
                new2Numer = (frac2whole * frac2denom)-frac2numer;
            }
            if(frac2whole > 0){
                new2Numer = (frac2whole * frac2denom)+frac2numer;
            }
            int newResultNumer = new1Numer*frac2denom;
            int newResultDenom = frac1denom * new2Numer;
            String resultPlus = newResultNumer +"/"+ newResultDenom;
            sum = resultPlus;

        }
        sum = simplify(sum);
        return sum;
    }
/*
    public static String simplify (String frac){
        String sum = "";

        int fracnumer = findNumerator(frac);
    }


*/


  public static String simplify (String frac){

    String sum = "";

    int fracnumer = findNumerator(frac);
    int fracdenom = findDenominator(frac);
    boolean neg = (fracnumer < 0) != (fracdenom < 0);

    int whole = fracnumer/fracdenom;

    if(whole == 0 && fracnumer > 0){
      // have to find common denominator and stuff like that
       int fracNum = fracnumer;
       int fracDen = fracdenom;
       int temp1 = fracNum;
       int temp2 = fracDen;
       while (temp1 != temp2){
         if(temp1 > temp2)
            temp1 = temp1 - temp2;
         else
            temp2 = temp2 - temp1;
       }

      int simpFracNum = fracNum / temp1;
      int simpFracDenom = fracDen / temp1;

      String result = simpFracNum+"/"+simpFracDenom;
      return result;


    }
    else if (fracnumer > 0){
      //take out the whole from numerator and then find common denominator n stuff like that
      int newNumer = fracnumer-(whole*fracdenom);
      int newDenom = fracdenom;


      int fracNum = newNumer;
      int fracDen = newDenom;
      int temp1 = fracNum;
      int temp2 = fracDen;

      while (temp1 != temp2){
        if(temp1 > temp2)
           temp1 = temp1 - temp2;
        else
          temp2 = temp2 - temp1;
      }

      int simpFracNum = fracNum / temp1;
      int simpFracDenom = fracDen / temp1;

      String result = whole+"_"+simpFracNum+"/"+simpFracDenom;

      if(neg){
        result = "-"+result;
      }

      return result;

    }
    else if (neg = true && whole==0){
       int fracNum = fracnumer;
       int fracDen = fracdenom;
       int temp1 = fracNum;
       int temp2 = fracDen;

       while (temp1 != temp2){
         if(temp1 > temp2)
            temp1 = temp1 - temp2;
         else
            temp2 = temp2 - temp1;
       }

      int simpFracNum = fracNum / temp1;
      int simpFracDenom = fracDen / temp1;

      String result = -1*simpFracNum+"/"+simpFracDenom;
      return result;
    }
    else if(neg = true && whole<0){
      int newNumer = Math.abs(fracnumer-(whole*fracdenom));
      int newDenom = Math.abs(fracdenom);


      int fracNum = newNumer;
      int fracDen = newDenom;
      int temp1 = fracNum;
      int temp2 = fracDen;

      while (temp1 != temp2){
        if(temp1 > temp2)
           temp1 = temp1 - temp2;
        else
          temp2 = temp2 - temp1;
      }

      int simpFracNum = fracNum / temp1;
      int simpFracDenom = fracDen / temp1;

      String result = -1*whole+"_"+simpFracNum+"/"+simpFracDenom;
      return result;

    }
    else{
      return "Can you get real please?";
    }

  }







    // promptSplit splits up the prompt into 2 different fractions.
    public static String promptSplit(String in, int x) {

        String[] split = in.split(" ");

        return split[x];

    }

    // all under here find all the different parts of the equation.
    public static String findOperand(String in) {

        String[] split = in.split(" ");

        String symbol = split[1];

        return symbol;
    }

    public static int findWhole(String input) {

        int wholenum;

        if (input.contains("_")) {
            String[] whole = input.split("_");
            String wholenumS = whole[0];
            wholenum = Integer.parseInt(wholenumS);

        } else {

            wholenum = Integer.parseInt(input);

        }
        return wholenum;

    }

    public static int findNumerator(String input) {

        int numeratornum;

        if (input.contains("_")) {
            String[] whole = input.split("_");
            String fraction = whole[1];
            String[] fracs = fraction.split("/");
            String numeratornumS = fracs[0];
            numeratornum = Integer.parseInt(numeratornumS);
        } else {
            String[] fracs = input.split("/");
            String numeratornumS = fracs[0];
            numeratornum = Integer.parseInt(numeratornumS);

        }
        return numeratornum;

    }

    public static int findDenominator(String input) {

        int denominatornum;

        if (input.contains("_")) {
            String[] whole = input.split("_");
            String fraction = whole[1];
            String[] fracs = fraction.split("/");
            String denominatornumS = fracs[1];
            denominatornum = Integer.parseInt(denominatornumS);
        } else {
            String[] fracs = input.split("/");
            String denominatornumS = fracs[1];
            denominatornum = Integer.parseInt(denominatornumS);

        }
        return denominatornum;
    }
}


