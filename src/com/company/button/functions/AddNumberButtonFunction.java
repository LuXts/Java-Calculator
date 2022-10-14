package com.company.button.functions;

import com.company.data.CalculatorData;

import java.util.ArrayList;

public class AddNumberButtonFunction implements BaseButtonFunction {

    private final Character c;

    public AddNumberButtonFunction(Character c) {
        this.c = c;
    }

    @Override
    public CalculatorData exec(CalculatorData data) {
        ArrayList<Character> lhsText = data.getLhsTextRaw();
        ArrayList<Character> rhsText = data.getRhsTextRaw();
        // 重置运算符标记
        data.setMark(true);
        // 如果现在正好是上次运算完成后，那么先进行一次 CE
        if (lhsText.size() != 0) {
            if (lhsText.get(lhsText.size() - 1) == '=') {
                BaseButtonFunction function = new ClearEnterButtonFunction();
                data = function.exec(data);
            }
        }
        // 输入数字
        if (rhsText.size() == 1 && rhsText.get(0) == '0') {
            // 如果目前 rhsText 是 "0"，
            if (c != '.') {
                // 先删掉 "0" 再添加数字
                rhsText.clear();
            } else {
                // 那么对 "." 输入做特殊处理
                data.setDot(true);
            }
        } else {
            // 对 dot 做处理
            if (c == '.') {
                if (data.getDot()) {
                    // 如果 dot 是 true 且输入是 "." 就直接返回
                    return data;
                } else {
                    // 否则把 dot 置为 true
                    data.setDot(true);
                }
            }
            // rhsText 尾部添加输入
        }
        // rhsText 尾部加上输入的内容
        rhsText.add(c);
        data.setRhsTextRaw(rhsText);
        data.setLhsTextRaw(lhsText);
        return data;
    }
}
