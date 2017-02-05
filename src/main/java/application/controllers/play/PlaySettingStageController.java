package application.controllers.play;

import application.controllers.Controller;
import application.model.Model;
import application.model.play.PlaySettingStageModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Created by BellPC on 05.02.2017.
 */
public class PlaySettingStageController implements Controller {

    @FXML
    private CheckBox isShuffleCheck;
    @FXML
    private CheckBox isEnableTimer;
    @FXML
    private TextField timeText;

    private PlaySettingStageModel myModel;

    @Override
    public void initialize() {
        isEnableTimer.setOnAction(event -> timeText.setDisable(!isEnableTimer.isSelected()));
    }

    @Override
    public void updateElementsData() {

    }

    @Override
    public void setMyModel(Model model) {
        myModel = (PlaySettingStageModel)model;
    }

    @FXML
    private void cancelButton(){
        myModel.close();
    }

    @FXML
    private void okButton() {
        myModel.setShuffle(isShuffleCheck.isSelected());
        myModel.setEnableTimer(isEnableTimer.isSelected());
        try {
            myModel.setTimerCountdown(Integer.parseInt(timeText.getText()));
        }catch (Exception e){
            System.out.println(e);
        }
        myModel.close();
    }
}
