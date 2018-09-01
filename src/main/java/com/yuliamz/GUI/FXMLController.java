package com.yuliamz.GUI;

import com.jfoenix.controls.*;
import com.yuliamz.logic.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {


    private ObservableList<Double> middleSquaresResult,congruentialLinealResult,congruentialMultiResult;
    private Thread threadMeanSquaresExec;
    private GUIUtils gui;

    @FXML
    private TabPane mainTabbedPanel;
    @FXML
    private JFXSpinner loadingSpinner;
    @FXML
    private JFXTextField seedTextField, infiniteGenerationInput, xoInput, xoInputM, aInput, aInputM, bInput, mInput, mInputM, iterationsInput, iterationsInputM;
    @FXML
    private JFXToggleButton infiniteGenerationToggle;
    @FXML
    private JFXListView<Double> numbersList;
    @FXML
    private ListView<Double> listNumbersConLineal, listNumbersConMulti, listMeanTest, listVarianceTest;
    @FXML
    private Button saveMiddleSquaresTxtButton, generateMiddleSquearesButton, stopMeanSquearesButton, saveMiddleSquaresXlsButton, saveConLinealTxtButton, saveConLinealXlsButton, saveConMultiTxtButton, saveConMultiXlsButton, testMeansButton, testVarianceButton, testKSButton, doTestMeansButton;
    @FXML
    private JFXSlider meanAcceptGradesSlider, varianceAcceptGradesSlider;
    @FXML
    private Label meanAcceptGradeLabel, meanAlphaLabel, meanMeanLabel, meanNLabel, meanOneAlphaLabel, meanZLabel, meanLILabel, meanLSLabel, meanValidLabel,
            varianceAccepGradesLabel, varianceAlphaLabel, varianceMeanLabel, varianceNLabel, varianceVarianceLabel, varianceAlphaMediosLabel, varianceUnoAlphaMediosLabell, varianceChiAlphaLabel, varianceChiUnoAplhaLabel, varianceLILabel, varianceLSLabel, varianceValidLabel;


    @FXML
    void saveConMultiTxt() {
        FileUtils.savePlainText(Utils.convertDoubleToString(congruentialMultiResult), mainTabbedPanel.getScene().getWindow());
    }

    @FXML
    void saveConMultiXls() {
        FileUtils.saveExcel(Utils.convertDoubleToString(congruentialMultiResult), mainTabbedPanel.getScene().getWindow(),"Congruencial Multiplicativo");
    }

    @FXML
    void saveConLinealTxt() {
        FileUtils.savePlainText(Utils.convertDoubleToString(congruentialLinealResult), mainTabbedPanel.getScene().getWindow());
    }

    @FXML
    void saveConLinealXls() {
        FileUtils.saveExcel(Utils.convertDoubleToString(congruentialLinealResult), mainTabbedPanel.getScene().getWindow(),"Congruencial Lineal");
    }
    
    @FXML
    void saveMiddleSquaresTxt() {
        FileUtils.savePlainText(Utils.convertDoubleToString(new ArrayList<>(middleSquaresResult)), mainTabbedPanel.getScene().getWindow());
    }

    @FXML
    void saveMiddleSquaresXls() {
        FileUtils.saveExcel(Utils.convertDoubleToString(new ArrayList<>(middleSquaresResult)), mainTabbedPanel.getScene().getWindow(),"Cuadrados Medios");
    }

    @FXML
    void generateCongruenLineal() {
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
    void generateCongruenMulti() {
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
    void checkInfiniteGenerationInput() {
        if (!Utils.isValidIterationsNumber(infiniteGenerationInput.getText())) {
            gui.setTextFieldAsError(infiniteGenerationInput);
        } else {
            gui.setTextFieldAsOK(infiniteGenerationInput);
        }
    }

    @FXML
    void checkSeedInput() {
        if (!Utils.isValidSeed(seedTextField.getText())) {
            gui.setTextFieldAsError(seedTextField);
        } else {
            gui.setTextFieldAsOK(seedTextField);
        }
    }

    @FXML
    void exit() {
        System.exit(0);
    }

    @FXML
    void cleanMiddleSquares() {
        middleSquaresResult.clear();
        saveMiddleSquaresTxtButton.setDisable(true);
        saveMiddleSquaresXlsButton.setDisable(true);
        seedTextField.setText("");
        infiniteGenerationToggle.setSelected(false);
        infiniteGenerationInput.setText("");
        infiniteGenerationInput.setDisable(false);
    }

    private void disableAndEnabled() {
        loadingSpinner.setVisible(true);
        stopMeanSquearesButton.setVisible(true);
        saveMiddleSquaresTxtButton.setDisable(false);
        saveMiddleSquaresXlsButton.setDisable(false);
        generateMiddleSquearesButton.setDisable(true);
    }

    @FXML
    void generateMiddleSqueares() {
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

    private void startThreadMiddlleSquares(MiddleSquares meanSquares) {
        try {
            threadMeanSquaresExec = new Thread(() -> {
                meanSquares.generateIn(middleSquaresResult);
                loadingSpinner.setVisible(false);
                stopMeanSquearesButton.setVisible(false);
                generateMiddleSquearesButton.setDisable(false);
            });
            threadMeanSquaresExec.start();
        } catch (IllegalStateException e) {
        }
    }

    @FXML
    void stopMiddleSqueares() {
        threadMeanSquaresExec.interrupt();
        threadMeanSquaresExec.stop();
        stopMeanSquearesButton.setVisible(false);
        loadingSpinner.setVisible(false);
        generateMiddleSquearesButton.setDisable(false);
    }

    @FXML
    void goToMiddleSquaresTab() {
        mainTabbedPanel.getSelectionModel().select(0);
    }

    @FXML
    void goToUniformTab() {
        mainTabbedPanel.getSelectionModel().select(3);
    }

    @FXML
    void goToCongruentialLinealTab() {
        mainTabbedPanel.getSelectionModel().select(1);
    }

    @FXML
    void goToCongruentialMultiTab() {
        mainTabbedPanel.getSelectionModel().select(2);
    }

    @FXML
    void openAbout() {

    }

    private void disableRightOptionsMeanSquares(boolean disable) {

    }

    private void showAlertCantGenerate() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No se pueden generar");
        alert.setHeaderText("Datos erroneos");
        alert.setContentText("Asegurese que los parámetros de entrada son correctos");
        alert.showAndWait();
    }

    @FXML
    void switchIterations() {
        infiniteGenerationInput.setDisable(infiniteGenerationToggle.isSelected());
    }
    
     @FXML
    void testMeans() {
        MeanTest meanTest = new MeanTest(listMeanTest.getItems(), (int) meanAcceptGradesSlider.getValue());
        boolean isValid = meanTest.isValid();
        meanAcceptGradeLabel.setText(""+meanTest.getAcceptationGrade());
        meanAlphaLabel.setText(""+meanTest.getErrorGrade());
        meanMeanLabel.setText(""+meanTest.getAverage());
        meanNLabel.setText(""+meanTest.getNumbersQuantity());
        meanOneAlphaLabel.setText(""+meanTest.getaMedios());
        meanZLabel.setText(""+meanTest.getZ());
        meanLILabel.setText(""+meanTest.getLI());
        meanLSLabel.setText(""+meanTest.getLS());
        meanValidLabel.setText(isValid?"Válido":"Inválido");
        meanValidLabel.setTextFill(isValid?GUIUtils.OK_COLOR:GUIUtils.ERROR_COLOR);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gui= new GUIUtils();
        middleSquaresResult = FXCollections.observableArrayList();
        numbersList.setItems(middleSquaresResult);
        congruentialLinealResult = FXCollections.observableArrayList();
        listNumbersConLineal.setItems(congruentialLinealResult);
        congruentialMultiResult = FXCollections.observableArrayList();
        listNumbersConMulti.setItems(congruentialMultiResult);
        meanAcceptGradesSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            meanAcceptGradeLabel.setText(""+(int)newValue.doubleValue());
        });
    }

    public void goToTestMeans() {
        setResultsInListView(listMeanTest);
        mainTabbedPanel.getSelectionModel().select(4);
    }

    public void goToTestVariance() {
        setResultsInListView(listVarianceTest);
        mainTabbedPanel.getSelectionModel().select(5);
    }

    private void setResultsInListView(ListView<Double> list) {
        switch (mainTabbedPanel.getSelectionModel().getSelectedIndex()) {
            case 0:
                list.setItems(middleSquaresResult);
                break;
            case 1:
                list.setItems(congruentialLinealResult);
                break;
            case 2:
                list.setItems(congruentialMultiResult);
                break;
            case 3:
                list.setItems(middleSquaresResult);
                break;
            default:
                break;
        }
    }

    public void goToTestKS() {

    }

    public void testVariances(ActionEvent event) {
        VarianceTest varianceTest = new VarianceTest(listVarianceTest.getItems(), (int) varianceAcceptGradesSlider.getValue());
        boolean isValid = varianceTest.isValid();
        varianceAccepGradesLabel.setText("" + varianceTest.getAcceptationGrade());
        varianceAlphaLabel.setText("" + varianceTest.getErrorGrade());
        varianceMeanLabel.setText("" + varianceTest.getAverage());
        varianceNLabel.setText("" + varianceTest.getNumbersQuantity());
        varianceVarianceLabel.setText("" + varianceTest.getVariance());
        varianceAlphaMediosLabel.setText("" + varianceTest.getaMedios());
        varianceUnoAlphaMediosLabell.setText("" + varianceTest.getUnoAMedios());
        varianceChiAlphaLabel.setText("" + varianceTest.getChiSqaure1());
        varianceChiUnoAplhaLabel.setText("" + varianceTest.getChiSqaure2());
        varianceLILabel.setText("" + varianceTest.getLI());
        varianceLSLabel.setText("" + varianceTest.getLS());

        varianceValidLabel.setText(isValid ? "Válido" : "Inválido");
        varianceValidLabel.setTextFill(isValid ? GUIUtils.OK_COLOR : GUIUtils.ERROR_COLOR);
    }
}
