package com.example.calculatrice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentInput = "";
    private double operand1 = Double.NaN;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        currentInput += button.getText().toString();
        updateResultTextView();
    }

    public void onOperatorClick(View view) {
        if (!currentInput.isEmpty()) {
            operator = ((Button) view).getText().toString();
            if (!Double.isNaN(operand1)) {
                onEqualsClick(view);
            } else {
                operand1 = Double.parseDouble(currentInput);
            }
            currentInput = "";
        }
    }

    public void onEqualsClick(View view) {
        if (!currentInput.isEmpty() && !Double.isNaN(operand1)) {
            double operand2 = Double.parseDouble(currentInput);
            double result = performOperation(operand1, operand2, operator);
            currentInput = String.valueOf(result);
            operand1 = result;
            operator = "";
            updateResultTextView();
        }
    }

/*    public void onClearClick(View view) {
        currentInput = "";
        operand1 = Double.NaN;
        operator = "";
        updateResultTextView();
    }*/

    private void updateResultTextView() {
        resultTextView.setText(currentInput);
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    // Gestion de la division par zéro
                    return Double.NaN;
                }
                return operand1 / operand2;
            default:
                return operand2;
        }
    }

}
