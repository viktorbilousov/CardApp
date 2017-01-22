package application.controllers;

import application.MainApp;
import cardSystem.Question;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StageAddQuestionController implements Controller {

    private ArrayList<Question> listToAdding;
    private MainApp mainApp;
    private Stage addQuestionStage;
    private boolean checkValidBothValue = true;

    @FXML
    private TextField questionField;
    @FXML
    private TextField answerField;

    @FXML
    public void initialize() {
    }

    @Override
    public void updateElementsData() {
        clearFields();
    }

    @Override
    public void setInputData(Object data) {
        if(data == null)
            return;

        if(!(data instanceof ArrayList)){
            System.out.println("error input data in " + getClass().getSimpleName());
            return;
        }
        listToAdding = (ArrayList<Question>) data;
    }

    @Override
    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
        addQuestionStage = ((MainApp) mainApp).getAddQuestionStageModel().getPrimaryStage();
    }


    @FXML
    private void addButton(){
        String question = questionField.getText();
        String answer = answerField.getText();
        if(isValidValues(question, answer)){
            this.listToAdding.add(new Question(question,answer));
        }
        mainApp.getViewQuestionModel().updateData();
        clearFields();
    }
    @FXML
    private void closeButton(){
        addQuestionStage.close();
    }

    private boolean isValidValues(String question, String answer){
        if(question.equals(""))
            return false;
        if(checkValidBothValue && answer.equals(""))
            return false;

        return true;
    }

    private void clearFields(){
        questionField.clear();
        answerField.clear();
    }


}
