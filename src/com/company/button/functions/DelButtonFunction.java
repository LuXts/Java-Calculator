package com.company.button.functions;

import com.company.data.CalculatorData;

import java.util.ArrayList;

public class DelButtonFunction implements BaseButtonFunction {
    @Override
    public CalculatorData exec(CalculatorData data) {
        // 重置运算符标记
        data.setMark(true);
        ArrayList<Character> rhsText = data.getRhsTextRaw();
        // 如果 rhsText 只有一个字符了，那就直接 clear
        if (rhsText.size() == 1) {
            ClearButtonFunction function = new ClearButtonFunction();
            return function.exec(data);
        }
        // 如果删掉的目标字符是 '.' 那么还要重置 dot 标志。
        Character c = rhsText.get(rhsText.size() - 1);
        if (c == '.') {
            data.setDot(false);
        }
        // 删除字符
        rhsText.remove(rhsText.size() - 1);
        data.setRhsTextRaw(rhsText);
        return data;
    }
}
