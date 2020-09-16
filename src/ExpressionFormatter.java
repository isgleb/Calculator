import java.util.ArrayList;

public class ExpressionFormatter {

    private static ArrayList<String> expression = new ArrayList<>();
    static String outputLine = "";
    private static String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static String[] operators = {"+", "-", "*", "/"};


    private static boolean isNumber(String string) {
        for (String number: numbers) {
            if (number.equals(string.substring(string.length()-1)))return true;
        }
        return false;
    }

    private static boolean isOperator(String string) {
        for (String operator: operators) {
            if (operator.equals(string.substring(string.length()-1)))return true;
        }
        return false;
    }

    private static byte countOperators (String operator) {
        byte count = 0;
        for (String oper: expression) {
            if (oper.equals(operator)) count++;
        }
        return count;
    }

    private static void refreshSequence() {
        outputLine = "";
        for (String element: expression) {
            outputLine += element;
        }
    }

    public static ArrayList<String> getExpression() {
        return expression;
    }

    public static void setExpression(ArrayList<String> expression) {
        ExpressionFormatter.expression = expression;
        refreshSequence();
    }

    static void addNumber(String number) {

        int lastElement = expression.size()-1;

        if (!expression.isEmpty() && expression.get(lastElement).equals(")")) {
            expression.add("*");
            lastElement = expression.size()-1;
        }

        if (expression.isEmpty() ||
                expression.get(lastElement).equals("(") ||
                expression.get(lastElement).equals("+") ||
                expression.get(lastElement).equals("*") ||
                expression.get(lastElement).equals("/") || (
                expression.get(lastElement).equals("-") && (
                        expression.size()>1 &&
                                ExpressionFormatter.isNumber(expression.get(expression.size()-2))))) {
            expression.add(number);

        } else if (!number.equals("0") || !expression.get(lastElement).equals("0")) {
            expression.set(lastElement, expression.get(lastElement) + number);
        }
        refreshSequence();
    }

    static void addComma() {
        int lastElement = expression.size()-1;
        if (!expression.get(lastElement).contains(",") && isNumber(expression.get(lastElement))) {
            expression.set(lastElement, expression.get(lastElement) + ".");
            refreshSequence();
        }
    }

    static void addOperator(String operator) {

        int lastElement = expression.size()-1;

        if (operator.equals("-")){
            if (!expression.isEmpty() && isOperator(expression.get(lastElement))) {
                expression.add("(");
            }
            expression.add("-");
            refreshSequence();
        }
        else if (isNumber(expression.get(lastElement)) || expression.get(lastElement).equals(")")) {
            expression.add(operator);
            refreshSequence();
        }
    }

    static void addBrackets() {
        int lastElement = expression.size()-1;

        if (!expression.isEmpty() &&
                !isOperator(expression.get(lastElement)) &&
                countOperators("(")==countOperators(")")) {
            expression.add("*");
            lastElement = expression.size()-1;
        }

        if (expression.isEmpty() ||
                isOperator(expression.get(lastElement)) ||
                expression.get(lastElement).equals("(") ) {
            expression.add("(");
        }

        else if (countOperators("(") > countOperators(")")) {
            expression.add(")");
        }

        outputLine = "";
        for (String element: expression) {
            outputLine += element;
        }
    }

    public static void deleteLast() {
        int lastElement = expression.size()-1;
        if (isNumber(expression.get(lastElement)) && expression.get(lastElement).length()>1) {
            expression.set(lastElement, expression.get(lastElement).substring(0, expression.get(lastElement).length()-1));
        } else expression.remove(lastElement);
        refreshSequence();
    }

    public static void clear(){
        ExpressionFormatter.expression.clear();
        ExpressionFormatter.outputLine = "";
    }

    public static void addRate(String rate) {
        if (rate != null) {
            expression.add(rate.substring(0,1));
            expression.add(rate.substring(2));
        }
        refreshSequence();
    }

    public static void checkExpression() {
        while (countOperators("(") > countOperators(")")){
            expression.add(")");
        }
    }
}
