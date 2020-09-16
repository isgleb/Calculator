import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currentCurrency.getItems().addAll("RUB", "EUR", "USD");
        aimCurrency.getItems().addAll("RUB", "EUR", "USD");
        rate.setText("-");

        currentCurrency.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                rate.setText(RatesHandler.getRatesValues().get(currentCurrency.getValue() + "_" + aimCurrency.getValue())));

        aimCurrency.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                rate.setText(RatesHandler.getRatesValues().get(currentCurrency.getValue() + "_" + aimCurrency.getValue())));
    }


    public void writeNumber(ActionEvent actionEvent) {

        Button pushedButton = (Button) actionEvent.getSource();
        if (one.equals(pushedButton)) ExpressionFormatter.addNumber("1");
        else if (two.equals(pushedButton)) ExpressionFormatter.addNumber("2");
        else if (three.equals(pushedButton)) ExpressionFormatter.addNumber("3");
        else if (four.equals(pushedButton)) ExpressionFormatter.addNumber("4");
        else if (five.equals(pushedButton)) ExpressionFormatter.addNumber("5");
        else if (six.equals(pushedButton)) ExpressionFormatter.addNumber("6");
        else if (seven.equals(pushedButton)) ExpressionFormatter.addNumber("7");
        else if (eight.equals(pushedButton)) ExpressionFormatter.addNumber("8");
        else if (nine.equals(pushedButton)) ExpressionFormatter.addNumber("9");
        else if (zero.equals(pushedButton)) ExpressionFormatter.addNumber("0");
        else if (comma.equals(pushedButton)) ExpressionFormatter.addComma();

        line.setText(ExpressionFormatter.outputLine);
    }

    public void doOperation(ActionEvent actionEvent) {
        Button pushedButton = (Button) actionEvent.getSource();
        if (plus.equals(pushedButton)) ExpressionFormatter.addOperator("+");
        else if (minus.equals(pushedButton)) ExpressionFormatter.addOperator("-");
        else if (multiply.equals(pushedButton)) ExpressionFormatter.addOperator("*");
        else if (divide.equals(pushedButton)) ExpressionFormatter.addOperator("/");
        else if (delete.equals(pushedButton)) ExpressionFormatter.deleteLast();
        else if (AC.equals(pushedButton)) ExpressionFormatter.clear();
        else if (brackets.equals(pushedButton)) ExpressionFormatter.addBrackets();
        else if (equals.equals(pushedButton)) Evaluator.getResult();
        else if (refresh.equals(pushedButton)) RatesHandler.refreshRates();
        else if (convert.equals(pushedButton)) ExpressionFormatter.addRate(RatesHandler.getRatesValues().get(currentCurrency.getValue() + "_" + aimCurrency.getValue()));

        line.setText(ExpressionFormatter.outputLine);
    }

    @FXML
    void keyBoardHandler(KeyEvent event) {
        String key = event.getText();

        if (event.getCode().toString().equals("BACK_SPACE")) {
            ExpressionFormatter.deleteLast();
        } else if (event.getCode().toString().equals("ENTER")) {
            Evaluator.getResult();
        }

        switch (key) {
            case "1" : ExpressionFormatter.addNumber("1"); break;
            case "2" : ExpressionFormatter.addNumber("2"); break;
            case "3" : ExpressionFormatter.addNumber("3"); break;
            case "4" : ExpressionFormatter.addNumber("4"); break;
            case "5" : ExpressionFormatter.addNumber("5"); break;
            case "6" : ExpressionFormatter.addNumber("6"); break;
            case "7" : ExpressionFormatter.addNumber("7"); break;
            case "8" : ExpressionFormatter.addNumber("8"); break;
            case "9" : ExpressionFormatter.addNumber("9"); break;
            case "0" : ExpressionFormatter.addNumber("0"); break;
            case "." :
            case "," : ExpressionFormatter.addComma(); break;
            case "-" : ExpressionFormatter.addOperator("-"); break;
            case "+" : ExpressionFormatter.addOperator("+"); break;
            case "=" : Evaluator.getResult(); break;
            case "/" : ExpressionFormatter.addOperator("/"); break;
            case "*" : ExpressionFormatter.addOperator("*"); break;
            case "(" :
            case ")" : ExpressionFormatter.addBrackets(); break;
        }
        line.setText(ExpressionFormatter.outputLine);
    }

    @FXML
    TextField line;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button zero;
    @FXML
    private Button comma;
    @FXML
    private Button AC;
    @FXML
    private Button delete;
    @FXML
    private Button brackets;
    @FXML
    private Button divide;
    @FXML
    private Button multiply;
    @FXML
    private Button minus;
    @FXML
    private Button plus;
    @FXML
    private Button refresh;
    @FXML
    private Button equals;
    @FXML
    private Button convert;
    @FXML
    private ChoiceBox<String> currentCurrency;
    @FXML
    private ChoiceBox<String> aimCurrency;
    @FXML
    private TextField rate;
}