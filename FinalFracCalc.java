import java.util.Scanner;
public class FinalFracCalc {
    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            Scanner console = new Scanner(System.in);
            System.out.print("enter expression (or enter \"quit\"): ");
            String expression = console.nextLine();
            if (expression.contains("quit")) {run = false;}
            else System.out.println(produceAnswer(expression));
        }
    }
    public static String produceAnswer(String input){// do not alter this method header
        String[] split = input.split(" ");
        for(int i = 0; i<split.length/2; i++) {
            String frac1 = split[0];
            String frac2 = split[2];
            String[] fracs1 = frac1.split("/");
            String[] fracs2 = frac2.split("/");
            String operation = split[1];
            int frac1denom = 1;
            int frac2denom = 1;
            int frac1numer = 1;
            int frac2numer = 1;
            if (!frac1.contains("_") && !frac1.contains("/")) {
                frac1numer = Integer.parseInt(frac1);
            } else {
                frac1numer = findTotalNumer(frac1);
                frac1denom = Integer.parseInt(fracs1[1]);
            }
            if (!frac2.contains("_") && !frac2.contains("/")) {
                frac2numer = Integer.parseInt(frac2);
            } else {
                frac2numer = findTotalNumer(frac2);
                frac2denom = Integer.parseInt(fracs2[1]);
            }
            if ((frac1denom == 0) || (frac2denom == 0) || operation.equals("/") && frac2numer == 0) {return "ERROR: Cannot divide by zero.";}
            split[0] = switch (operation) {
                case "+" -> addition(frac1numer, frac1denom, frac2numer, frac2denom);
                case "-" -> subtraction(frac1numer, frac1denom, frac2numer, frac2denom);
                case "*" -> multiplication(frac1numer, frac1denom, frac2numer, frac2denom);
                case "/" -> division(frac1numer, frac1denom, frac2numer, frac2denom);
                default -> "ERROR: Input is in an invalid format.";
            };
            for (int j = 1; j < split.length - 2; j++) {split[j] = split[j+2];}
        }
        return split[0];
    }
    public static String addition(int frac1N, int frac1D, int frac2N, int frac2D){
        int resultNumN = (frac2D * frac1N) + (frac1D * frac2N);
        int resultDenomN = frac1D * frac2D;
        return simplify(resultNumN, resultDenomN);
    }
    public static String subtraction(int frac1N, int frac1D, int frac2N, int frac2D){
        int resultNumN = (frac2D * frac1N) - (frac1D * frac2N);
        int resultDenomN = frac1D * frac2D;
        return simplify(resultNumN, resultDenomN);
    }
    public static String multiplication(int frac1N, int frac1D, int frac2N, int frac2D){
        int resultNumN = frac1N * frac2N;
        int resultDenomN = frac1D * frac2D;
        return simplify(resultNumN, resultDenomN);
    }
    public static String division(int frac1N, int frac1D, int frac2N, int frac2D){
        int resultNumN = frac1N * frac2D;
        int resultDenomN = frac1D * frac2N;
        return simplify(resultNumN, resultDenomN);
    }
    public static int findTotalNumer(String frac){
        if (frac.contains("_")) {
            String[] whole = frac.split("_");
            String wholenum = whole[0];
            String fraction = whole[1];
            String[] fracs = fraction.split("/");
            String numeratornumS = fracs[0];
            if (Integer.parseInt(wholenum) < 0) return -1*Integer.parseInt(numeratornumS)+(Integer.parseInt(wholenum)*Integer.parseInt(fracs[1]));
            return Integer.parseInt(numeratornumS)+(Integer.parseInt(wholenum)*Integer.parseInt(fracs[1]));
        } else {
            String[] fracs = frac.split("/");
            String numeratornumS = fracs[0];
            return Integer.parseInt(numeratornumS);
        }
    }
    public static int gcd(int a, int b) {return b == 0 ? a : gcd(b, a % b);}
    public static String simplify(int a, int b) {
        boolean neg = (((a < 0) && !(b <0)) || (!(a < 0) && (b <0)));
        a = Math.abs(a);
        b = Math.abs(b);
        int gcd = gcd(a, b);
        int numer = a / gcd;
        int denom = b / gcd;
        if(numer == 0) return "0";
        if(denom==1 && neg) return "-"+numer;
        if(numer > denom && neg) return "-"+(numer/denom)+"_"+(numer%denom)+"/"+Math.abs(denom);
        if(denom==1) return Integer.toString(numer);
        if(numer > denom) return (numer/denom)+"_"+(numer%denom)+"/"+Math.abs(denom);
        if(neg) return "-"+(a / gcd) + "/" + (b / gcd);
        return (a / gcd) + "/" + (b / gcd);
    }
}
