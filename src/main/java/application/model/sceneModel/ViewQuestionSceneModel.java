package application.model.sceneModel;

import application.MainApp;
import application.cardSystemProperty.QuestionProperty;
import application.controllers.SceneViewQuestionsController;
import application.model.Model;
import application.model.SceneModel;
import application.model.stageModels.AddQuestionStageModel;
import cardSystem.Question;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

/**
 * Created by BellPC on 23.01.2017.
 */
public class ViewQuestionSceneModel extends SceneModel {

    SceneViewQuestionsController questionsController = (SceneViewQuestionsController)this.controller;

    public ViewQuestionSceneModel(BorderPane rootLayout, MainApp mainApp, String pathToFXML) {
        super(rootLayout, mainApp, pathToFXML);
    }

    public void goToViewThemes(){
        mainApp.getViewThemesModel().show();
    }

    public void showAddQuestionStage(){
        AddQuestionStageModel addQuestion = mainApp.getAddQuestionStageModel();
        addQuestion.clearFields();
        addQuestion.setDataToController(questionsController.getInputData());
        addQuestion.setEditMode(false); // добавить новые
        addQuestion.show();
    }
    public void showEditQuestion(Question q) {
        AddQuestionStageModel addQuestion = mainApp.getAddQuestionStageModel();
        addQuestion.setDefFieldsString(q.getQuestion(), q.getAnswer());
        addQuestion.setDataToController(q);
        addQuestion.setEditMode(true); // включает редактирование
        addQuestion.show();
    }

    public void viewTheme(int indexTheme){
        setDataToController(mainApp.getCardSystem().getThemeList()
                .get(indexTheme).getQuestionsList());
        updateData();
    }
    public void openErrorSelectWindow(){
        mainApp.showAlertMessage(
                "No Selection",
                "No Question Selected",
                "Please select a question in the table.");
    }

}
