package application.model.play;

import application.controllers.play.PlaySettingStageController;
import application.model.Model;
import application.model.StageModel;
import javafx.stage.Stage;

import java.net.URL;

public class PlaySettingStageModel extends StageModel {

    PlaySettingStageController myController = (PlaySettingStageController)controller;
    private StartPlayStageModel startPlay = (StartPlayStageModel)parent;

    public PlaySettingStageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);

    }

    public void setShuffle(boolean shuffle) {
        startPlay.setShuffle(shuffle);
    }

    public void setEnableTimer(boolean enableTimer) {
        startPlay.setEnableTimer(enableTimer);
    }

    public void setTimerCountdown(int secondTimer) {
        startPlay.setTimerCountdown(secondTimer);
    }

}
