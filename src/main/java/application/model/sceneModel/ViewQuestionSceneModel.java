package application.model.sceneModel;

import application.MainApp;
import application.controllers.QuestionsViewSceneController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.stageModels.AddQuestionStageModel;
import application.model.stageModels.RootStageModel;
import application.model.stageModels.UniQuestionModel;
import application.util.StageUtil;
import cardSystem.Question;
import cardSystem.Theme;
import javafx.scene.layout.BorderPane;

import java.net.URL;


public class ViewQuestionSceneModel extends SceneModel {

    private QuestionsViewSceneController questionsController = (QuestionsViewSceneController)this.controller;
    private AddQuestionStageModel addQuestionModel;
    private RootStageModel rootStageModel = (RootStageModel) this.parent;
    private UniQuestionModel uniQuestionModel;


    public ViewQuestionSceneModel(BorderPane rootLayout, StageModel parentModel, URL FXMLLocation) {
        super(rootLayout, parentModel, FXMLLocation);
        init();
    }

    private void init(){
        addQuestionModel = new AddQuestionStageModel(
                StageUtil.makeNewStage("Question", rootStageModel.getPrimaryStage()),
                this,
                MainApp.class.getResource("../fxml/StageQuestionAdd.fxml")
        );

        uniQuestionModel = new UniQuestionModel(
                StageUtil.makeNewStage("Universal Question", rootStageModel.getPrimaryStage()),
                this,
                MainApp.class.getResource("../fxml/StageUniQuestion.fxml")
        );


    }

    public void goToViewThemes(){
       rootStageModel.showViewThemes();
    }
    public void showAddQuestionStage(){
        addQuestionModel.clearFields();
        addQuestionModel.setQuestionList(questionsController.getQuestionsList());
        addQuestionModel.setEditMode(false); // добавить новые
        addQuestionModel.show();
    }
    public void showEditQuestion(Question q) {
        addQuestionModel.setDefFieldsString(q.getQuestion(), q.getAnswer(), q.getTip());
        addQuestionModel.setEditQuestion(q);
        addQuestionModel.setEditMode(true); // включает редактирование
        addQuestionModel.show();
    }
    public void showUniQuestionStage(){
        uniQuestionModel.showCheckUniQuestion();
    }
    public void viewThemesQuestion(int indexTheme){

        Theme theme = rootStageModel
                .getCardSystem()
                .getThemeList()
                .get(indexTheme);

        questionsController.setQuestionsList(theme.getQuestionsList());
        questionsController.setNameThemeLabel(theme.getThemeName());
        updateData();

        uniQuestionModel.setDataToController(
                rootStageModel.getCardSystem().getUniversalQuestion(),
                questionsController.getQuestionsList()
        );
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
