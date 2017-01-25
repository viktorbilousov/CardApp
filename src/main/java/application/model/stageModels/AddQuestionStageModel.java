package application.model.stageModels;

import application.MainApp;
import application.controllers.StageAddQuestionController;
import application.model.Model;
import application.model.StageModel;
import javafx.stage.Stage;

import java.net.URL;


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

}
