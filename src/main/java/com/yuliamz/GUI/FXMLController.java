package com.yuliamz.GUI;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.yuliamz.logic.MiddleSquares;
import com.yuliamz.logic.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLController implements Initializable {


    private ObservableList<String> middleSquaresResult;
    private Thread threadMiddleSquaresExec;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem middleSquaresMenuItem;

    @FXML
    private MenuItem congruentialMenuItem;

    @FXML
    private MenuItem uniformMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private TabPane mainTabbedPanel;

    @FXML
    private Tab middleSquresTab;

    @FXML
    private JFXSpinner loadingSpinner;

    @FXML
    private JFXTextField seedTextField;

    @FXML
    private JFXTextField infiniteGenerationInput;

    @FXML
    private JFXToggleButton infiniteGenerationToggle;

    @FXML
    private JFXListView<String> numbersList;

    @FXML
    private Button saveMiddleSquaresTxtButton;

    @FXML
    private Tab congruentialTab;

    @FXML
    private Tab uniformTab;

    @FXML
    private Tab testTab;

    @FXML
    private Button generateMiddleSquearesButton;

    @FXML
    private Button stopMiddleSquearesButton;

    @FXML
    private Button saveMiddleSquaresXlsButton;

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
                MiddleSquares middleSquares = new MiddleSquares(new BigInteger(seed));
                startThreadMiddlleSquares(middleSquares);
            } else {
                if (Utils.isValidIterationsNumber(iterations)) {
                    disableAndEnabled();
                    MiddleSquares middleSquares = new MiddleSquares(new BigInteger(seed), Integer.parseInt(iterations));
                    startThreadMiddlleSquares(middleSquares);
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(mainTabbedPanel.getScene().getWindow());
        FileUtils.saveAsPlainText(new ArrayList<>(middleSquaresResult), file);
    }

    @FXML
    void saveMiddleSquaresXls() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog(mainTabbedPanel.getScene().getWindow());
        FileUtils.saveAsExcel(new ArrayList<>(middleSquaresResult), file);
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
    }
}
