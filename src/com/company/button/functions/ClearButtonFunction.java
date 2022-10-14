package com.company.button.functions;

import com.company.data.CalculatorData;

import java.util.ArrayList;

public class ClearButtonFunction implements BaseButtonFunction {
    @Override
    public CalculatorData exec(CalculatorData data) {
        data.setMark(true);
        data.setPn(true);
        data.setDot(false);
        ArrayList<Character> lhsText = data.getLhsTextRaw();
        ArrayList<Character> rhsText = data.getRhsTextRaw();
        if (lhsText.size() > 1) {
            if (lhsText.get(lhsText.size() - 1) == '=') {
                // 如果目前 rhsText 里面放着上次运算的结果的话，调用 CE 操作
                ClearEnterButtonFunction function = new ClearEnterButtonFunction();

                return function.exec(data);
            }
        }
        // 清理rhsText，添加 "0"
        rhsText.clear();
        rhsText.add('0');
        data.setRhsTextRaw(rhsText);
        return data;
    }
}
