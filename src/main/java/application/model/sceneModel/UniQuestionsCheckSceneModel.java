package application.model.sceneModel;

import application.controllers.UniQuestionCheckController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.stageModels.UniQuestionStageModel;
import cardSystem.Question;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;


public class UniQuestionsCheckSceneModel extends SceneModel {

    UniQuestionCheckController myController = (UniQuestionCheckController)controller;
    UniQuestionStageModel parentModel = (UniQuestionStageModel)parent;

    public UniQuestionsCheckSceneModel(BorderPane rootLayout, StageModel parentModel, URL FXMLLocation) {
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
