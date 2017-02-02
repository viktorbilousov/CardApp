package application.model.sceneModel;

import application.controllers.QuestionEditSceneController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.stageModels.RootStageStageModel;
import cardSystem.Question;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by BellPC on 02.02.2017.
 */
public class QuestionsEditSceneModel extends SceneModel {

    QuestionEditSceneController myController = (QuestionEditSceneController) controller;
    RootStageStageModel rootStage = (RootStageStageModel)parent;

    public QuestionsEditSceneModel(BorderPane rootLayout, StageModel parent, URL FXMLLocation) {
        super(rootLayout, parent, FXMLLocation);
    }

    public void setQuestionList(ArrayList<Question> questionList){
        myController.setQuestionList(questionList);
    }

    public void backToViewQuestionsScene(){
        rootStage.showViewQuestion();
    }

}
