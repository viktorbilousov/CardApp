package application.model.stageModels;

import application.MainApp;
import application.controllers.StageAddQuestionController;
import application.model.Model;
import application.model.StageModel;
import cardSystem.Question;
import cardSystem.Theme;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;


public class AddQuestionStageModel extends StageModel {

   private StageAddQuestionController myController = (StageAddQuestionController) this.controller;

    public AddQuestionStageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);
    }

    public void setDefFieldsString(String question, String answer, String tip){
        myController.setDefFieldsValue(question, answer, tip);
    }

    public void setEditMode(boolean editMode){
        myController.setEditMode(editMode);
    }

    public void clearFields(){
        myController.clearFields();
    }

    public void updateDataInAddedModel() {
       parent.updateData();
    }

    public void setQuestionList(ArrayList<Question> questionList){
        myController.setListToAdding(questionList);
    }
    public void setEditQuestion(Question question){
        myController.setEditQuestion(question);
    }

}