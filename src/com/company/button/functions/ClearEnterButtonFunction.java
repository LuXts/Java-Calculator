package com.company.button.functions;

import com.company.data.CalculatorData;

import java.util.ArrayList;

public class ClearEnterButtonFunction implements BaseButtonFunction {
    @Override
    public CalculatorData exec(CalculatorData data) {
        data.setMark(true);
        data.setPn(true);
        data.setDot(false);
        data.setSignOperation(null);
        ArrayList<Character> lhsText = data.getLhsTextRaw();
        ArrayList<Character> rhsText = data.getRhsTextRaw();
        // 清理 rhsText 和 lhsText
        lhsText.clear();
        rhsText.clear();
        rhsText.add('0');
        data.setRhsTextRaw(rhsText);
        data.setLhsTextRaw(lhsText);
        return data;

    }
}
