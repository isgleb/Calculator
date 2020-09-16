import java.util.ArrayList;
import java.util.List;

public class Evaluator {

    static void evaluateResult() {
        ExpressionFormatter.checkExpression();
        ArrayList<String> generalExpression = ExpressionFormatter.getExpression();

        while (generalExpression.contains("(")) {
            int[] brackets = findBrackets(generalExpression);

            ArrayList<String> subExpression = new ArrayList<>();

            generalExpression.remove(brackets[1]);
            generalExpression.remove(brackets[0]);

            for (int i = 0; i < brackets[1] - brackets[0] - 1; i++) {
                subExpression.add(generalExpression.get(brackets[0]));
                generalExpression.remove(brackets[0]);
            }
            generalExpression.add(brackets[0], calculate(subExpression));
        }
        ArrayList<String> result = new ArrayList<>();
        result.add(calculate(generalExpression));
        ExpressionFormatter.setExpression(result);
    }


    private static int[] findBrackets(ArrayList<String> result) {

        int[] brackets = new int[2];
        for (int i = result.size() - 1; i >= 0 ; i--) {
            if (result.get(i).equals("(")) {
                brackets[0] = i;
                for (int j = i; j < result.size(); j++) {
                    if (result.get(j).equals(")")){
                        brackets[1] = j;
                        break;
                    }
                }
                break;
            }
        }
        return brackets;
    }


    private static String calculate(List<String> expression) {

        for (int i = 0; i < expression.size(); i++) {
            if (expression.get(i).equals("*")) {
                Float value = Float.parseFloat(expression.get(i-1)) *
                        Float.parseFloat(expression.get(i+1));
                expression.add(i-1, value.toString());
                expression.remove(i);
                expression.remove(i);
                expression.remove(i);
                i=0;
            }
            if (expression.size()> i+1 && expression.get(i).equals("/")) {
                Float value = Float.parseFloat(expression.get(i-1)) / Float.parseFloat(expression.get(i+1));
                expression.add(i-1, value.toString());
                expression.remove(i);
                expression.remove(i);
                expression.remove(i);
                i=0;
            }
        }

        for (int i = 0; i < expression.size(); i++) {
            if (expression.get(i).equals("+")) {
                Float value = Float.parseFloat(expression.get(i-1)) + Float.parseFloat(expression.get(i+1));
                expression.add(i-1, value.toString());
                expression.remove(i);
                expression.remove(i);
                expression.remove(i);
                i=0;
            }
            if (expression.get(i).equals("-")) {
                Float value = Float.parseFloat(expression.get(i-1)) - Float.parseFloat(expression.get(i+1));
                expression.add(i-1, value.toString());
                expression.remove(i);
                expression.remove(i);
                expression.remove(i);
                i=0;
            }
        }
        return expression.get(0);
    }
}