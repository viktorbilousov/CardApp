package application.controllers.edit;

import application.controllers.Controller;
import application.model.Model;
import application.model.edit.stageModels.RootStageModel;
import application.model.edit.stageModels.SaveXMLSettingStageModel;
import application.util.StageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RootStageController implements Controller {


    RootStageModel myModel;

    @Override
    public void initialize() {
    }

    @Override
    public void updateElementsData() {
    }

    @Override
    public void setMyModel(Model model) {
        myModel = (RootStageModel) model;
    }

    @FXML
    private void onMenuNew(){
       myModel.newSystem();
    };
    @FXML
    private void onMenuOpen(){  myModel.showOpenMenu(); };
    @FXML
    private void onMenuSave(){ myModel.saveSystemToOpenFile();};
    @FXML
    private void onMenuSaveAs(){myModel.showSaveMenu();}
    @FXML
    private void onMenuExit(){
        myModel.close();
    };
    @FXML
    private void startPlayMenuAction() {
        myModel.close();
        myModel.showPlaySettingStage();
    }

    @FXML
    private void openAbout() {
        StageUtil.showAboutMessage();
    }

    public void saveToExcel() {
        myModel.showXMLSaveSetting();
    }
}
