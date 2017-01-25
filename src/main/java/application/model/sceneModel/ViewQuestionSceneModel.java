package application.model.sceneModel;

import application.MainApp;
import application.controllers.SceneViewQuestionsController;
import application.model.Model;
import application.model.SceneModel;
import application.model.stageModels.AddQuestionStageModel;
import application.model.stageModels.RootStageModel;
import application.util.StageUtil;
import cardSystem.Question;
import javafx.scene.layout.BorderPane;

import java.net.URL;


public class ViewQuestionSceneModel extends SceneModel {

    private SceneViewQuestionsController questionsController = (SceneViewQuestionsController)this.controller;
    private AddQuestionStageModel addQuestionModel;
    private RootStageModel rootStageModel = (RootStageModel) this.parentModel;

    public ViewQuestionSceneModel(BorderPane rootLayout, Model parentModel, URL FXMLLocation) {
        super(rootLayout, parentModel, FXMLLocation);
        init();
    }

    private void init(){
        addQuestionModel = new AddQuestionStageModel(
                StageUtil.makeNewStage("Question", rootStageModel.getPrimaryStage()),
                this,
                MainApp.class.getResource("../fxml/StageAddQuestion.fxml")
        );
    }

    public void goToViewThemes(){
       rootStageModel.showViewThemes();
    }
    public void showAddQuestionStage(){
        addQuestionModel.clearFields();
        addQuestionModel.setDataToController(questionsController.getInputData());
        addQuestionModel.setEditMode(false); // добавить новые
        addQuestionModel.show();
    }
    public void showEditQuestion(Question q) {
        addQuestionModel.setDefFieldsString(q.getQuestion(), q.getAnswer(), q.getTip());
        addQuestionModel.setDataToController(q);
        addQuestionModel.setEditMode(true); // включает редактирование
        addQuestionModel.show();
    }
    public void viewTheme(int indexTheme){
        setDataToController(rootStageModel
                .getCardSystem()
                .getThemeList()
                .get(indexTheme)
                .getQuestionsList()
        );
        updateData();
    }
    public void openErrorSelectWindow(){
        StageUtil.showAlertMessage(
                "No Selection",
                "No Question Selected",
                "Please select a question in the table.",
                rootStageModel.getPrimaryStage()
        );
    }

}
