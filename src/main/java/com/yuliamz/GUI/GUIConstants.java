package com.yuliamz.GUI;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

class GUIConstants {
    static final DropShadow borderGlow = new DropShadow();
    static final Color errorColor = Color.rgb(218, 3, 3);
    static final Color OKColor = Color.rgb(0, 150, 136);

    protected GUIConstants() {
        createErrorGlow();
    }

    private void createErrorGlow() {
        borderGlow.setOffsetY(3);
        borderGlow.setOffsetX(3);
        borderGlow.setColor(errorColor);
        borderGlow.setWidth(90);
        borderGlow.setHeight(90);
    }
}
