package application.model.edit.sceneModel;

import application.controllers.edit.QuestionEditSceneController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.edit.stageModels.RootStageModel;
import cardSystem.Question;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by BellPC on 02.02.2017.
 */
public class QuestionsEditSceneModel extends SceneModel {

    QuestionEditSceneController myController = (QuestionEditSceneController) controller;
    RootStageModel rootStage = (RootStageModel)parent;

    public QuestionsEditSceneModel(BorderPane rootLayout, StageModel parent, URL FXMLLocation) {
        super(rootLayout, parent, FXMLLocation);
    }

    public void setThemesName (String name){myController.setThemeName(name);}
    public void setQuestionList(ArrayList<Question> questionList){
        myController.setQuestionList(questionList);
    }

    public void backToViewQuestionsScene(){
        rootStage.showViewQuestion();
    }

}
