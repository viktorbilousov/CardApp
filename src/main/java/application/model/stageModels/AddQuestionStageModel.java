package application.model.stageModels;

import application.MainApp;
import application.controllers.StageAddQuestionController;
import application.model.StageModel;
import javafx.stage.Stage;

/**
 * Created by BellPC on 23.01.2017.
 */
public class AddQuestionStageModel extends StageModel {

   private StageAddQuestionController myController = (StageAddQuestionController) this.controller;

    public AddQuestionStageModel(Stage primaryStage, MainApp mainApp, String pathToFXML) {
        super(primaryStage, mainApp, pathToFXML);
    }

    public void setDefFieldsString(String question, String answer){
        myController.setDefFieldsValue(question, answer);
    }

    public void setEditMode(boolean editMode){
        myController.setEditMode(editMode);
    }

    public void clearFields(){
        myController.clearFields();
    }

    public void updateDataInAddedModel() {
        mainApp.getViewQuestionModel().updateData();
    }

}
