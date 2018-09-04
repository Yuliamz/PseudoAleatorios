package com.yuliamz.GUI;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

class GUIUtils {
    static final DropShadow ERROR_GLOW = new DropShadow();
    static final Color ERROR_COLOR = Color.rgb(218, 3, 3);
    static final Color OK_COLOR = Color.rgb(0, 150, 136);
    static final double ACCEPT_GRADES=95.0;

    protected GUIUtils() {
        createErrorGlow();
    }

    private void createErrorGlow() {
        ERROR_GLOW.setOffsetY(3);
        ERROR_GLOW.setOffsetX(3);
        ERROR_GLOW.setColor(ERROR_COLOR);
        ERROR_GLOW.setWidth(90);
        ERROR_GLOW.setHeight(90);
    }
    
    public void setTextFieldAsError(JFXTextField field){
            field.setFocusColor(ERROR_COLOR);
            field.setUnFocusColor(ERROR_COLOR);
            field.setEffect(ERROR_GLOW);
    }
    public void setTextFieldAsOK(JFXTextField field){
            field.setFocusColor(OK_COLOR);
            field.setUnFocusColor(Color.BLACK);
            field.setEffect(null);
    }

}
