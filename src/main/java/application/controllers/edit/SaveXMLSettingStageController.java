package application.controllers.edit;

import application.controllers.Controller;
import application.model.Model;
import application.model.edit.stageModels.SaveXMLSettingStageModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

/**
 * Created by BellPC on 07.02.2017.
 */
public class SaveXMLSettingStageController implements Controller {

    @FXML
    private CheckBox answerCheck;
    @FXML
    private CheckBox tipsCheck;
    SaveXMLSettingStageModel myModel ;

    @Override
    public void initialize() {
    }

    @Override
    public void updateElementsData() {
    }

    @Override
    public void setMyModel(Model model) {
        myModel = (SaveXMLSettingStageModel) model;
    }

    @FXML
    private void okBtnAction() {
        myModel.saveToXmlFile(answerCheck.isSelected(), tipsCheck.isSelected());
        myModel.close();
    }

    @FXML
    private void cancelBtnAction() {
        myModel.close();
    }
}
