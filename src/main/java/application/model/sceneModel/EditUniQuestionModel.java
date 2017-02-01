package application.model.sceneModel;


import application.controllers.EditUniQuestionController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.stageModels.UniQuestionModel;
import application.util.StageUtil;
import cardSystem.Question;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;

public class EditUniQuestionModel extends SceneModel {

    EditUniQuestionController myController = (EditUniQuestionController) controller;
    UniQuestionModel uniParent = (UniQuestionModel)this.parent;

    public EditUniQuestionModel(BorderPane rootLayout, StageModel parent, URL FXMLLocation) {
        super(rootLayout, parent, FXMLLocation);
    }

    public void setQuestionList(ArrayList<Question> questionList){
        myController.setQuestionsList(questionList);
    }

    public void showCheckQuestionScene(){
        uniParent.showCheckUniQuestion();
    }
    public void openErrorSelectWindow(){
        StageUtil.showAlertMessage(
                "No Selection",
                "No Question Selected",
                "Please select a Question in the table.",
                parent.getPrimaryStage()
        );
    };
}
