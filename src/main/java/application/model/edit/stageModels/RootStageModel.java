package application.model.edit.stageModels;


import application.MainApp;
import application.model.StageModel;
import application.model.edit.sceneModel.QuestionsEditSceneModel;
import application.model.edit.sceneModel.QuestionsViewSceneModel;
import application.model.edit.sceneModel.ThemesEditSceneModel;
import application.model.edit.sceneModel.ThemesViewSceneModel;
import application.model.play.StartPlayStageModel;
import application.util.StageUtil;
import cardSystem.CardSystem;
import cardSystem.CardSystemStream;
import cardSystem.Theme;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RootStageModel extends StageModel {

    private QuestionsViewSceneModel viewQuestionScene;
    private QuestionsEditSceneModel editQuestionScene;
    private ThemesViewSceneModel viewThemesScene;
    private ThemesEditSceneModel editThemesScene;
    private SaveXMLSettingStageModel saveXMLSetting;

    private StartPlayStageModel playStage;

    private CardSystem system;
    private CardSystemStream systemStream;
    private String AppName = "Card App";
    private boolean isNew = false;


    public RootStageModel(Stage primaryStage, StageModel parent, URL FXMLLOcation){
        super(primaryStage,parent,FXMLLOcation);

        system = new CardSystem();
        systemStream = new CardSystemStream(system);

        init();
        newSystem();
        File file = systemStream.getLastLoadFilePath();

      if(file != null) loadSystemFromFile(file);
        else  newSystem();

        getPrimaryStage().setOnCloseRequest(event -> {
            File openFile  = systemStream.getLastLoadFilePath();
            if(openFile != null && !isNew){
                saveSystemToFile(openFile);
            }
        });

        getPrimaryStage().setMinHeight(450);
        getPrimaryStage().setMinWidth(600);
        showViewThemes();
    }

    public CardSystem getCardSystem() {
        return system;
    }

    public void init(){
        viewQuestionScene = new QuestionsViewSceneModel(
                (BorderPane) getRootLayout(),
                this,
                MainApp.class.getResource("/fxml/edit/SceneQuestionsView.fxml")
        );
        viewThemesScene = new ThemesViewSceneModel(
                (BorderPane)getRootLayout(),
                this,
                MainApp.class.getResource("/fxml/edit/SceneThemesView.fxml")
        );
        viewThemesScene.setThemeList(system.getThemeList());

        editThemesScene = new ThemesEditSceneModel(
                (BorderPane) getRootLayout(),
                this,
                MainApp.class.getResource("/fxml/edit/SceneThemesEdit.fxml")
        );
        editThemesScene.setThemesList(system.getThemeList());

        editQuestionScene = new QuestionsEditSceneModel(
                (BorderPane)rootLayout,
                this,
                MainApp.class.getResource("/fxml/edit/SceneQuestionsEdit.fxml")
        );

        playStage = new StartPlayStageModel(
                StageUtil.makeNewStage("Play Setting", getPrimaryStage()),
                this,
                MainApp.class.getResource("/fxml/play/StartPlay.fxml")
        );
        saveXMLSetting = new SaveXMLSettingStageModel(
                StageUtil.makeNewStage("Save Excel File Setting",getPrimaryStage()),
                this,
                MainApp.class.getResource("/fxml/edit/SaveXLSXSetting.fxml"),
                systemStream
        );

    }

    public void newSystem(){
        isNew = true;
        getPrimaryStage().setTitle(AppName + " - New Data");
        system.getThemeList().clear();
        try {
            viewThemesScene.updateData();
            editQuestionScene.updateData();
        }catch (Exception e){
            System.out.println(e);
        }
        updateData();

    }
    public void showViewQuestionOfTheme(Theme theme){
        viewQuestionScene.setTheme(theme);
        viewQuestionScene.show();
    }
    public void showViewQuestion(){
        viewQuestionScene.updateData();
        viewQuestionScene.show();
    }
    public void showEditThemesScene(){
        editThemesScene.updateData();
        editThemesScene.show();
    }
    public void showViewThemes(){
        viewThemesScene.updateData();
        viewThemesScene.show();
    }
    public void showEditQuestionScene(Theme theme){
        editQuestionScene.setQuestionList(theme.getQuestionsList());
        editQuestionScene.updateData();
        editQuestionScene.show();
    }
    public void showXMLSaveSetting(){
        saveXMLSetting.show();
    }


    public void saveSystemToOpenFile(){
        File file  = systemStream.getLastLoadFilePath();
        if(file != null && !isNew){
            saveSystemToFile(file);
        }else {
            showSaveMenu();
        }
    }
    public void showOpenMenu(){
        loadSystemFromFile(StageUtil.FileChooser(getPrimaryStage(), StageUtil.XMLFiler ));
    }
    public void showSaveMenu(){
        saveSystemToFile(StageUtil.FileOpener(getPrimaryStage(), StageUtil.XMLFiler));
    }
    public void showSaveToExcel(boolean writeAnswers, boolean writeTips) {
        saveToXlmxFile(
            StageUtil.FileOpener(getPrimaryStage(), StageUtil.XLSXFiler).getPath(),
            writeAnswers,
            writeTips
        );
    }

    private void saveSystemToFile(File file){
        getPrimaryStage().setTitle(AppName + " - " + file.getName());
        systemStream.saveCardSystemToFile(file);
        isNew = false;
    }
    private void loadSystemFromFile(File file){
        getPrimaryStage().setTitle(AppName + " - " + file.getName());
        systemStream.loadCardSystemFromFile(file);
        viewThemesScene.setThemeList(system.getThemeList());
        editThemesScene.setThemesList(system.getThemeList());
        updateData();
    }
    private void saveToXlmxFile(String path, boolean writeAnswers,boolean writeTips ){
        Platform.runLater(() -> { try {
            systemStream.saveCardsToXLSXFile(path, writeAnswers, writeTips);
        } catch (IOException e) {
            StageUtil.showAlertMessage("Error", "Error save to Excel file",
                    e.getMessage(), getPrimaryStage());
        }});
    }

    public void showPlaySettingStage(){
        try {
            playStage.setThemeArrayList(system.clone().getThemeList()); // отвязка
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        playStage.show();
    }
}
