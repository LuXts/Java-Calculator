package com.company.button;

import com.company.button.functions.*;

public class ButtonFunctionFactory {
    public BaseButtonFunction getFunction(String input) {
        switch (input) {
            case "C" -> {
                return new ClearButtonFunction();
            }
            case "CE" -> {
                return new ClearEnterButtonFunction();
            }
            case "Del" -> {
                return new DelButtonFunction();
            }
            case "=" -> {
                return new CalculationResultsButtonFunction();
            }
            case "+/-" -> {
                return new ChangePNButtonFunction();
            }
            case "+", "-", "×", "÷" -> {
                return new AddSignButtonFunction(input.charAt(0));
            }
            default -> {
                return new AddNumberButtonFunction(input.charAt(0));
            }
        }
    }
}
