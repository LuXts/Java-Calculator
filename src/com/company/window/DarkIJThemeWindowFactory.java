package com.company.window;

import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;

import javax.swing.*;

public class DarkIJThemeWindowFactory extends BaseWindowFactory {
    @Override
    public JFrame createThemeWindow() {
        // 主题美化 https://www.formdev.com/flatlaf/
        FlatMaterialDesignDarkIJTheme.setup();
        UIManager.put("Button.arc", 5);
        return super.createThemeWindow();
    }
}
