import java.util.Scanner;
public class FracCalcCompressed {
    public static void main(String[] args) {
        boolean run = true;
        // while loop to keep asking for new fractions
        while (run) {
            Scanner console = new Scanner(System.in);
            System.out.print("enter expression (or enter \"quit\"): ");
            String expression = console.nextLine();
            if (expression.contains("quit")) {
                run = false;
            } else {
                System.out.println(produceAnswer(expression));
            }
        }
    }
    public static String produceAnswer(String input)// do not alter this method header
    {
        String[] split = input.split(" ");
        String frac1 = split[0];
        String frac2 = split[2];
        String[] fracs1 = frac1.split("/");
        String[] fracs2 = frac2.split("/");

        int frac1denom = 1;
        int frac2denom = 1;
        int frac1numer = 1;
        int frac2numer = 1;
        if (!frac1.contains("_") && !frac1.contains("/")) {
            frac1denom = 1;
            frac1numer = Integer.parseInt(frac1);
        } else {
            frac1numer = findTotalNumer(frac1);
            frac1denom = Integer.parseInt(fracs1[1]);
        }
        if (!frac2.contains("_") && !frac2.contains("/")) {
            frac2denom = 1;
            frac2numer = Integer.parseInt(frac2);
        } else {
            frac2numer = findTotalNumer(frac1);
            frac2denom = Integer.parseInt(fracs2[1]);
        }
        String operation = split[1];
        if (operation.equals("+")) {
            return addition(frac1numer, frac1denom, frac2numer, frac2denom);
        } else if (operation.equals("-")) {
            return subtraction(frac1numer, frac1denom, frac2numer, frac2denom);
        } else if (operation.equals("*")) {
            return multiplication(frac1numer, frac1denom, frac2numer, frac2denom);
        } else if (operation.equals("/")) {
            return division(frac1numer, frac1denom, frac2numer, frac2denom);
        } else {
            return "Please enter a valid operator ( + , - , * , / )";
        }
    }
    public static String addition(int frac1N, int frac1D, int frac2N, int frac2D){
        int resultNumN = (frac2D * frac1N) + (frac1D * frac2N);
        int resultDenomN = frac1D * frac2D;
        return resultNumN + "/" + resultDenomN;
    }
    public static String subtraction(int frac1N, int frac1D, int frac2N, int frac2D){
        int resultNumN = (frac2D * frac1N) - (frac1D * frac2N);
        int resultDenomN = frac1D * frac2D;
        return resultNumN + "/" + resultDenomN;
    }
    public static String multiplication(int frac1N, int frac1D, int frac2N, int frac2D){
        int resultNumN = frac1N * frac2N;
        int resultDenomN = frac1D * frac2D;
        return resultNumN + "/" + resultDenomN;
    }
    public static String division(int frac1N, int frac1D, int frac2N, int frac2D){
        int resultNumN = frac1N * frac2D;
        int resultDenomN = frac1D * frac2N;
        return resultNumN + "/" + resultDenomN;
    }
    public static int findTotalNumer(String frac){
        int TotalNumerator;
        if (frac.contains("_")) {
            String[] whole = frac.split("_");
            String wholenum = whole[0];
            String fraction = whole[1];
            String[] fracs = fraction.split("/");
            String numeratornumS = fracs[0];
            TotalNumerator = Integer.parseInt(numeratornumS)+(Integer.parseInt(wholenum)*Integer.parseInt(fracs[1]));
        } else {
            String[] fracs = frac.split("/");
            String numeratornumS = fracs[0];
            TotalNumerator = Integer.parseInt(numeratornumS);
        }
        return TotalNumerator;
    }

}
