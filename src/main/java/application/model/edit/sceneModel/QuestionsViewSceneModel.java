package application.model.edit.sceneModel;

import application.MainApp;
import application.controllers.edit.QuestionsViewSceneController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.edit.stageModels.QuestionAddStageModel;
import application.model.edit.stageModels.RootStageModel;
import application.model.edit.stageModels.UniQuestionStageModel;
import application.util.StageUtil;
import cardSystem.Theme;
import javafx.scene.layout.BorderPane;

import java.net.URL;


public class QuestionsViewSceneModel extends SceneModel {

    private QuestionsViewSceneController questionsController = (QuestionsViewSceneController)this.controller;
    private QuestionAddStageModel addQuestionModel;
    private RootStageModel rootStageModel = (RootStageModel) this.parent;
    private UniQuestionStageModel uniQuestionStageModel;
    private Theme theme;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
        initThemesQuestion(theme);
        updateData();
    }

    public QuestionsViewSceneModel(BorderPane rootLayout, StageModel parentModel, URL FXMLLocation) {
        super(rootLayout, parentModel, FXMLLocation);
        init();
    }

    private void init(){
        addQuestionModel = new QuestionAddStageModel(
                StageUtil.makeNewStage("Question", rootStageModel.getPrimaryStage()),
                this,
                MainApp.class.getResource("../fxml/edit/StageQuestionAdd.fxml")
        );

        uniQuestionStageModel = new UniQuestionStageModel(
                StageUtil.makeNewStage("Universal Question", rootStageModel.getPrimaryStage()),
                this,
                MainApp.class.getResource("../fxml/edit/StageUniQuestion.fxml")
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
    public void showEditQuestion() {
       rootStageModel.showEditQuestionScene(theme);
    }
    public void showUniQuestionStage(){
        uniQuestionStageModel.showCheckUniQuestion();
    }
    public void initThemesQuestion(Theme theme){

        questionsController.setQuestionsList(theme.getQuestionsList());
        questionsController.setNameThemeLabel(theme.getThemeName());
        updateData();

        uniQuestionStageModel.setDataToController(
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
