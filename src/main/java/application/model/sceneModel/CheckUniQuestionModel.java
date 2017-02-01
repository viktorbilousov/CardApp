package application.model.sceneModel;

import application.controllers.CheckUniQuestionController;
import application.model.Model;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.stageModels.UniQuestionModel;
import cardSystem.Question;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;


public class CheckUniQuestionModel extends SceneModel {

    CheckUniQuestionController myController = (CheckUniQuestionController)controller;
    UniQuestionModel parentModel = (UniQuestionModel)parent;

    public CheckUniQuestionModel(BorderPane rootLayout, StageModel parentModel, URL FXMLLocation) {
        super(rootLayout, parentModel, FXMLLocation);
    }

    public void setDataToController(ArrayList<Question> uniQuestions, ArrayList<Question> themeQuestion ) {
        myController.setQuestion(themeQuestion);
        myController.setUniQuestion(uniQuestions);
        myController.updateElementsData();
    }

    public void showEditQuestionScene(){
        parentModel.showEditUniQuestion();
    }

}
