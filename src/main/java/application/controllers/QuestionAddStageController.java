package application.controllers;

import application.model.Model;
import application.model.stageModels.QuestionAddStageModel;
import cardSystem.Question;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class QuestionAddStageController implements Controller {

    private ArrayList<Question> listToAdding;
    private Question editQuestion;
    private QuestionAddStageModel myModel;
    private boolean checkValidBothValue = true;
    private boolean isEditMode = false;
    private final int RESIZE_CONST = 28; // def = 28

    @FXML
    private TextField questionField;
    @FXML
    private TextField answerField;
    @FXML
    private TextField tipField;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;

    @FXML
    private AnchorPane mainScene;


    private TextArea questionArea = new TextArea();
    private TextArea answerArea = new TextArea();
    private TextArea tipArea = new TextArea();

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
        initTextArea(questionArea, questionField);
        initTextArea(answerArea, answerField);
        initTextArea(tipArea, tipField);

        answerField.setOnKeyTyped   (observable -> fieldTextChange(answerField, answerArea));
        questionField.setOnKeyTyped (observable -> fieldTextChange(questionField, questionArea));
        tipField.setOnKeyTyped      (observable -> fieldTextChange(tipField, tipArea));

        answerArea.setOnKeyTyped    (observable -> areaTextChange(answerArea, answerField));
        questionArea.setOnKeyTyped  (observable -> areaTextChange(questionArea,questionField));
        tipArea.setOnKeyTyped       (observable -> areaTextChange(tipArea, tipField));

        questionField.focusedProperty().addListener (observable -> fieldFocusEvent(questionField, questionArea));
        answerField.focusedProperty().addListener   (observable -> fieldFocusEvent(answerField, answerArea));
        tipField.focusedProperty().addListener      (observable -> fieldFocusEvent(tipField, tipArea));

        questionArea.focusedProperty().addListener  (observable -> areaFocusEvent(questionArea, questionField));
        answerArea.focusedProperty().addListener    (observable -> areaFocusEvent(answerArea, answerField));
        tipArea.focusedProperty().addListener       (observable -> areaFocusEvent(tipArea, tipField));

        clearFields();
    }


    private void initTextArea(TextArea area, TextField field){
        area.setPrefSize(field.getPrefWidth(), 50);
        area.setLayoutX(field.getLayoutX());
        area.setLayoutY(field.getLayoutY());
        area.setWrapText(true);
        area.setVisible(false);
        mainScene.getChildren().add(area);
    }

    @Override
    public void updateElementsData() {}

    public ArrayList<Question> getListToAdding() {
        return listToAdding;
    }

    public void setListToAdding(ArrayList<Question> listToAdding) {
        this.listToAdding = listToAdding;
    }

    public Question getEditQuestion() {
        return editQuestion;
    }

    public void setEditQuestion(Question editQuestion) {
        this.editQuestion = editQuestion;
    }

    @Override
    public void setMyModel(Model model) {
        this.myModel = (QuestionAddStageModel) model;
    }

    @FXML
    private void addButton(){
        String question = questionField.getText();
        String answer = answerField.getText();
        String tip = tipField.getText();
        if(isValidValues(question, answer, tip)) {
            if (isEditMode) {
                editQuestion.setQuestion(question);
                editQuestion.setAnswer(answer);
                editQuestion.setTip(tip);
            } else {
                this.listToAdding.add(new Question(question, answer, tip));
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


    private void fieldFocusEvent(TextField field, TextArea area){
        if(field.isFocused()){
            if(field.getText().length() > RESIZE_CONST){
                replaceFieldToArea(field, area);
                area.requestFocus();
            }
        }
    }

    private void areaFocusEvent(TextArea area, TextField field) {
        if(!area.isFocused()){
            replaceAreaToField(area, field);
        }
    }
    private void fieldTextChange(TextField field, TextArea area){
        String text = field.getText();
        if(text.length() > RESIZE_CONST){
            replaceFieldToArea(field, area);
            area.requestFocus();
        }
    }

    private void areaTextChange(TextArea area, TextField field){
        String text = area.getText();
        if(text.length() <= RESIZE_CONST){
          //  replaceAreaToField(area, field);
            field.requestFocus();
            field.positionCaret(field.getText().length());
        }
    }


    private boolean isValidValues(String question, String answer, String tip){
        if(question.equals(""))
            return false;
        if(checkValidBothValue && answer.equals(""))
            return false;

        return true;
    }

    public void clearFields(){
        questionField.clear();
        answerField.clear();
        tipField.clear();
    }
    public void setDefFieldsValue(String question, String answer, String tip){
       /* if(!isValidValues(question, answer, tip)){
            System.out.println("error input fields value in " + getClass().getSimpleName());
            return;
        }*/
        questionField.setText(question);
        answerField.setText(answer);
        tipField.setText(tip);

    }

    private void replaceFieldToArea(TextField field , TextArea area){
        String text = field.getText();
        area.setText(text);
        area.setVisible(true);
        area.positionCaret(text.length());
    }
    private void replaceAreaToField(TextArea area, TextField field ){
        String text = area.getText();
        field.setText(text);
        field.positionCaret(text.length());
        area.setVisible(false);
    }

}
