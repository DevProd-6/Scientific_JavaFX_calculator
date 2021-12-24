package com.devprod.scientific_javafx_calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.ToggleSwitch;


public class mainViewController {
    @FXML
    private TextField tf;
    @FXML
    private Label on_off, opr;
    private double a, b;
    private String operator;
    private boolean showingResult = false;
    @FXML
    private ToggleSwitch ts;
    @FXML
    private Button mul, plus, div, mins, ac, del, ln, log, cos, sin, tan, sev, eigh, nin, five, four, thr, one, two, six, zro, dot, equal, sqrt, ypow, cbc, dbl, sign, prcntg, excla, exp, rev, pi;
    
    @FXML
    protected void onACClick () {
        tf.setText("");
        opr.setText("");
        a = 0;
        b = 0;
        operator = "";
        showingResult = false;
    }
    
    @FXML
    protected void onDELClick () {
        if (!tf.getText().isBlank()) tf.setText(tf.getText().substring(0, tf.getText().length() - 1));
    }
    
    @FXML
    protected void setOn_off () {
        on_off.setText(ts.isSelected() ? "ON" : "OFF");
        button(ac, del, ln, log, cos, sin, tan, sev, eigh, nin, five, four, thr, rev);
        button(one, two, six, zro, dot, equal, sqrt, ypow, cbc, dbl, sign, prcntg, excla, pi);
        exp.setDisable(!ts.isSelected());
        mul.setDisable(!ts.isSelected());
        plus.setDisable(!ts.isSelected());
        mins.setDisable(!ts.isSelected());
        div.setDisable(!ts.isSelected());
    }
    
    private void button (Button one, Button two, Button six, Button zro, Button dot, Button equal, Button sqrt, Button ypow, Button cbc, Button dbl, Button sign, Button prcntg, Button excla, Button rev) {
        turns(one, two, six, zro, dot, equal, rev);
        turns(sqrt, ypow, cbc, dbl, sign, prcntg, rev);
        excla.setDisable(!ts.isSelected());
    }
    
    public void turns (Button one, Button two, Button six, Button zro, Button dot, Button equal, Button rev) {
        one.setDisable(!ts.isSelected());
        two.setDisable(!ts.isSelected());
        six.setDisable(!ts.isSelected());
        zro.setDisable(!ts.isSelected());
        dot.setDisable(!ts.isSelected());
        equal.setDisable(!ts.isSelected());
        rev.setDisable(!ts.isSelected());
    }
    
    @FXML
    protected void buttonManager (ActionEvent ae) {
        Button src = (Button) ae.getSource();
        switch (src.getAccessibleHelp()) {
            case "number":
                if (showingResult) {
                    showingResult = false;
                    tf.setText(src.getText());
                }
                tf.setText(tf.getText().concat(src.getText()));
                break;
            case "equal":
                if (tf.getText().isEmpty() || operator.isEmpty()) return;
                b = Double.parseDouble(tf.getText());
                tf.setText(arithmetic.calculateEquation(a, b, operator));
                showingResult = true;
                opr.setText("");
                operator = "";
                break;
            case "arithmetic":
                operator = src.getText();
                if (!tf.getText().isEmpty()) {
                    if (opr.getText().isEmpty()) {
                        a = Double.parseDouble(tf.getText());
                        updateOperation();
                    } else {
                        b = Double.parseDouble(tf.getText());
                        a = Double.parseDouble(arithmetic.calculateEquation(a, b, "" + opr.getText().charAt(opr.getText().length() - 1)));
                        updateOperation();
                        tf.setText("");
                    }
                } else {
                    if (!opr.getText().isEmpty()) {
                        updateOperation();
                    } else {
                        updateOperation();
                    }
                }
                tf.setText("");
                break;
            
            case "math":
                mathsManager(src.getText());
                break;
        }
    }
    
    private void mathsManager (String src) {
        switch (src) {
            case "±":
                if (!tf.getText().startsWith("-")) tf.setText("-" + tf.getText());
                else tf.setText(tf.getText().replace("-", ""));
                break;
            case "⁒":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText(String.valueOf(Double.parseDouble(opr.getText().replace(operator, "")) / 100));
                else return;
                else tf.setText(String.valueOf(Double.parseDouble(tf.getText()) / 100));
                break;
            case "χ!":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + factorial(Double.parseDouble(opr.getText().replace(operator, ""))));
                else return;
                else tf.setText("" + factorial(Double.parseDouble(tf.getText())));
                break;
            case "exp":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + Math.exp(Double.parseDouble(opr.getText().replace(operator, ""))));
                else return;
                else tf.setText(Math.exp(Double.parseDouble(tf.getText())) + "");
                break;
            case "χ²":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + Math.pow(Double.parseDouble(opr.getText().replace(operator, "")), 2));
                else return;
                else tf.setText(Math.pow(Double.parseDouble(tf.getText()), 2) + "");
                break;
            case "χ³":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + Math.pow(Double.parseDouble(opr.getText().replace(operator, "")), 3));
                else return;
                else tf.setText(Math.pow(Double.parseDouble(tf.getText()), 3) + "");
                break;
            case "χʸ":
                if (tf.getText().isEmpty() || opr.getText().isEmpty()) return;
                else opr.setText(Math.pow(Double.parseDouble(opr.getText()), Double.parseDouble(tf.getText())) + "");
                break;
            case "√":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + Math.sqrt(Double.parseDouble(opr.getText().replace(operator, ""))));
                else return;
                else tf.setText(Math.sqrt(Double.parseDouble(tf.getText())) + "");
                break;
            case "cos":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + Math.cos(Double.parseDouble(opr.getText().replace(operator, ""))));
                else return;
                else tf.setText(Math.cos(Double.parseDouble(tf.getText())) + "");
                break;
            case "sin":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + Math.sin(Double.parseDouble(opr.getText().replace(operator, ""))));
                else return;
                else tf.setText(Math.sin(Double.parseDouble(tf.getText())) + "");
                break;
            case "tan":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + Math.tan(Double.parseDouble(opr.getText().replace(operator, ""))));
                else return;
                else tf.setText(Math.tan(Double.parseDouble(tf.getText())) + "");
                break;
            case "log":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + Math.log10(Double.parseDouble(opr.getText().replace(operator, ""))));
                else return;
                else tf.setText(Math.log10(Double.parseDouble(tf.getText())) + "");
                break;
            case "ln":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + Math.log(Double.parseDouble(opr.getText().replace(operator, ""))));
                else return;
                else tf.setText(Math.log(Double.parseDouble(tf.getText())) + "");
                break;
            case "1/χ":
                if (tf.getText().isEmpty()) if (!opr.getText().isEmpty()) tf.setText("" + (1 / Double.parseDouble(opr.getText().replace(operator, ""))));
                else return;
                else tf.setText(1 / Double.parseDouble(tf.getText()) + "");
                break;
            case "π":
                if (!tf.getText().isEmpty()) if (opr.getText().isEmpty()) opr.setText(tf.getText());
                else if (!tf.getText().isEmpty() && !opr.getText().isEmpty()) opr.setText(arithmetic.calculateEquation(Double.parseDouble(tf.getText()), getDouble(opr), operator));
                tf.setText(Math.PI + "");
                break;
        }
        
    }
    
    private Double factorial (double a) {
        double fact = 1;
        for (int i = 1; i <= a; i++) fact *= i;
        return fact;
    }
    
    private void updateOperation () {
        opr.setText(a + " " + operator);
    }
    
    private Double getDouble (TextField tf) {
        if (tf.getText().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(tf.getText());
    }
    
    private Double getDouble (Label lb) {
        if (lb.getText().isEmpty()) {
            return 0.0;
        }
        if (lb.getText().endsWith("÷") || lb.getText().endsWith("×") || lb.getText().endsWith("−") || lb.getText().endsWith("＋")) return Double.parseDouble(lb.getText().substring(lb.getText().length() - 1));
        return Double.parseDouble(lb.getText());
    }
    
    @FXML
    private void setDot () {
        if (tf.getText().isEmpty()) tf.setText("0.");
        if (!tf.getText().contains(".")) tf.setText(tf.getText().concat("."));
        
    }
}