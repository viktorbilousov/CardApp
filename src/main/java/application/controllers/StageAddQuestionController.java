package application.controllers;

import application.model.Model;
import application.model.stageModels.AddQuestionStageModel;
import cardSystem.Question;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class StageAddQuestionController implements Controller {

    private ArrayList<Question> listToAdding;
    private Question editQuestion;
    private AddQuestionStageModel myModel;
    private boolean checkValidBothValue = true;
    private boolean isEditMode = false;

    @FXML
    private TextField questionField;
    @FXML
    private TextField answerField;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;

    public void setCheckValidBothValue(boolean checkValidBothValue) {
        this.checkValidBothValue = checkValidBothValue;
    }

    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
        if (isEditMode)  leftButton.setText("Edit");
        else leftButton.setText("Add");
    }

    @FXML
    public void initialize() {

        clearFields();
    }

    @Override
    public void updateElementsData() {}

    @Override
    public void setInputData(Object data) {
        if(data == null)
            return;

        if(data instanceof ArrayList){
            listToAdding = (ArrayList<Question>) data;
            return;
        }else if(data instanceof Question){
            editQuestion = (Question)data;
            return;
        }else {
            System.out.println("error input data in " + getClass().getSimpleName());
        }
        return;
    }

    @Override
    public ArrayList<Question> getInputData() {
        return listToAdding;
    }

    @Override
    public void setMyModel(Model model) {
        this.myModel = (AddQuestionStageModel) model;
    }

    @FXML
    private void addButton(){
        String question = questionField.getText();
        String answer = answerField.getText();
        if(isValidValues(question, answer)) {
            if (isEditMode) {
                editQuestion.setQuestion(question);
                editQuestion.setAnswer(answer);
            } else {
                this.listToAdding.add(new Question(question, answer));
                clearFields();
            }
            myModel.updateDataInAddedModel();
        }else{
            System.out.println("error input fields data in " + getClass().getSimpleName());
        }
    }
    @FXML
    private void closeButton(){
        myModel.close();
    }

    private boolean isValidValues(String question, String answer){
        if(question.equals(""))
            return false;
        if(checkValidBothValue && answer.equals(""))
            return false;

        return true;
    }

    public void clearFields(){
        questionField.clear();
        answerField.clear();
    }
    public void setDefFieldsValue(String question, String answer){
        if(!isValidValues(question, answer)){
            System.out.println("error input fields value in " + getClass().getSimpleName());
            return;
        }
        questionField.setText(question);
        answerField.setText(answer);

    }
}
