package com.yuliamz.GUI;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.yuliamz.logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {


    private ObservableList<String> middleSquaresResult;
    private ObservableList<Float> congruentialLinealResult, congruentialMultiResult;
    private Thread threadMiddleSquaresExec;

    @FXML
    private TabPane mainTabbedPanel;
    @FXML
    private JFXSpinner loadingSpinner;
    @FXML
    private JFXTextField seedTextField, infiniteGenerationInput;
    @FXML
    private JFXToggleButton infiniteGenerationToggle;
    @FXML
    private JFXListView<String> numbersList;

    @FXML
    private ListView<Float> listNumbersConLineal, listNumbersConMulti;

    @FXML
    private Button saveMiddleSquaresTxtButton, generateMiddleSquearesButton, stopMiddleSquearesButton, saveMiddleSquaresXlsButton, saveConLinealTxtButton, saveConLinealXlsButton, saveConMultiTxtButton, saveConMultiXlsButton;

    @FXML
    private JFXTextField xoInput, xoInputM;

    @FXML
    private JFXTextField aInput, aInputM;

    @FXML
    private JFXTextField bInput;

    @FXML
    private JFXTextField mInput, mInputM;

    @FXML
    private JFXTextField iterationsInput, iterationsInputM;

    @FXML
    void saveConMultiTxt() {
        FileUtils.savePlainText(Utils.convertFloatToString(congruentialMultiResult), mainTabbedPanel.getScene().getWindow());
    }

    @FXML
    void saveConMultiXls() {
        FileUtils.saveExcel(Utils.convertFloatToString(congruentialMultiResult), mainTabbedPanel.getScene().getWindow());
    }

    @FXML
    void saveConLinealTxt() {
        FileUtils.savePlainText(Utils.convertFloatToString(congruentialLinealResult), mainTabbedPanel.getScene().getWindow());
    }

    @FXML
    void saveConLinealXls() {
        FileUtils.saveExcel(Utils.convertFloatToString(congruentialLinealResult), mainTabbedPanel.getScene().getWindow());
    }


    @FXML
    void generateCongruenLineal(ActionEvent event) {
        congruentialLinealResult.clear();
        OperationCongruenceLinear linear = new OperationCongruenceLinear(new Congruence_linear(
                Integer.parseInt(aInput.getText()),
                Integer.parseInt(bInput.getText()),
                Integer.parseInt(mInput.getText()),
                Integer.parseInt(xoInput.getText()),
                Integer.parseInt(iterationsInput.getText())));
        linear.iteration(congruentialLinealResult);
        congruentialLinealResult.forEach(System.out::println);
        saveConLinealTxtButton.setDisable(false);
        saveConLinealXlsButton.setDisable(false);
    }

    @FXML
    void generateCongruenMulti(ActionEvent event) {
        congruentialMultiResult.clear();
        OperationCongruenceMultiply multiply = new OperationCongruenceMultiply(new Congruence_Multiply(
                Integer.parseInt(aInputM.getText()),
                Integer.parseInt(mInputM.getText()),
                Integer.parseInt(xoInputM.getText()),
                Integer.parseInt(iterationsInputM.getText())));
        multiply.iteration(congruentialMultiResult);

        saveConMultiTxtButton.setDisable(false);
        saveConMultiXlsButton.setDisable(false);
    }

    @FXML
    void checkInfiniteGenerationInput(KeyEvent event) {
        if (!Utils.isValidIterationsNumber(infiniteGenerationInput.getText())) {
            infiniteGenerationInput.setFocusColor(GUIConstants.errorColor);
            infiniteGenerationInput.setUnFocusColor(GUIConstants.errorColor);
            infiniteGenerationInput.setEffect(GUIConstants.borderGlow);
        } else {
            infiniteGenerationInput.setFocusColor(GUIConstants.OKColor);
            infiniteGenerationInput.setUnFocusColor(Color.BLACK);
            infiniteGenerationInput.setEffect(null);
        }
    }

    @FXML
    void checkSeedInput(KeyEvent event) {
        if (!Utils.isValidSeed(seedTextField.getText())) {
            seedTextField.setFocusColor(GUIConstants.errorColor);
            seedTextField.setUnFocusColor(GUIConstants.errorColor);
            seedTextField.setEffect(GUIConstants.borderGlow);
        } else {
            seedTextField.setFocusColor(GUIConstants.OKColor);
            seedTextField.setUnFocusColor(Color.BLACK);
            seedTextField.setEffect(null);
        }
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void cleanMiddleSquares(ActionEvent event) {
        middleSquaresResult.clear();
        saveMiddleSquaresTxtButton.setDisable(true);
        saveMiddleSquaresXlsButton.setDisable(true);
        seedTextField.setText("");
        infiniteGenerationToggle.setSelected(false);
        infiniteGenerationInput.setText("");
    }

    private void disableAndEnabled() {
        loadingSpinner.setVisible(true);
        stopMiddleSquearesButton.setVisible(true);
        saveMiddleSquaresTxtButton.setDisable(false);
        saveMiddleSquaresXlsButton.setDisable(false);
        generateMiddleSquearesButton.setDisable(true);
    }

    @FXML
    void generateMiddleSqueares(ActionEvent event) {
        String seed = seedTextField.getText();
        String iterations = infiniteGenerationInput.getText();
        middleSquaresResult.clear();
        if (Utils.isValidSeed(seed)) {
            if (infiniteGenerationToggle.isSelected()) {
                disableAndEnabled();
                startThreadMiddlleSquares(new MiddleSquares(new BigInteger(seed)));
            } else {
                if (Utils.isValidIterationsNumber(iterations)) {
                    disableAndEnabled();
                    startThreadMiddlleSquares(new MiddleSquares(new BigInteger(seed), Integer.parseInt(iterations)));
                } else {
                    showAlertCantGenerate();
                }
            }
        } else {
            showAlertCantGenerate();
        }
    }

    private void startThreadMiddlleSquares(MiddleSquares middleSquares) {
        try {
            threadMiddleSquaresExec = new Thread(() -> {
                middleSquares.generateIn(middleSquaresResult);
                loadingSpinner.setVisible(false);
                stopMiddleSquearesButton.setVisible(false);
                generateMiddleSquearesButton.setDisable(false);
            });
            threadMiddleSquaresExec.start();
        } catch (IllegalStateException e) {
        }
    }

    @FXML
    void saveMiddleSquaresTxt() {
        FileUtils.savePlainText(new ArrayList<>(middleSquaresResult), mainTabbedPanel.getScene().getWindow());
    }

    @FXML
    void saveMiddleSquaresXls() {
        FileUtils.saveExcel(new ArrayList<>(middleSquaresResult), mainTabbedPanel.getScene().getWindow());
    }

    @FXML
    void stopMiddleSqueares() {
        threadMiddleSquaresExec.interrupt();
        threadMiddleSquaresExec.stop();
        stopMiddleSquearesButton.setVisible(false);
        loadingSpinner.setVisible(false);
        generateMiddleSquearesButton.setDisable(false);
    }

    @FXML
    void goToCongruentialTab() {
        mainTabbedPanel.getSelectionModel().select(1);
    }

    @FXML
    void goToMiddleSquaresTab() {
        mainTabbedPanel.getSelectionModel().select(0);
    }

    @FXML
    void goToUniformTab() {
        mainTabbedPanel.getSelectionModel().select(2);
    }

    @FXML
    void openAbout(ActionEvent event) {

    }

    private void showAlertCantGenerate() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No se pueden generar");
        alert.setHeaderText("Datos erroneos");
        alert.setContentText("Asegurese que los parámetros de entrada son correctos");
        alert.showAndWait();
    }

    @FXML
    void switchIterations(ActionEvent event) {
        infiniteGenerationInput.setDisable(infiniteGenerationToggle.isSelected());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new GUIConstants();
        middleSquaresResult = FXCollections.observableArrayList();
        numbersList.setItems(middleSquaresResult);
        congruentialLinealResult = FXCollections.observableArrayList();
        listNumbersConLineal.setItems(congruentialLinealResult);
        congruentialMultiResult = FXCollections.observableArrayList();
        listNumbersConMulti.setItems(congruentialMultiResult);
    }
}
