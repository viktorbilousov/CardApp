package application.model.play;

import application.controllers.play.PlayStageController;
import application.model.Model;
import application.model.StageModel;
import cardSystem.Theme;
import cardSystem.ThemesCardPlayer;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class PlayStageModel extends StageModel {

    private PlayStageController myController = (PlayStageController)controller;

    public PlayStageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);
    }
    public void setData(ArrayList<Theme> themesList, boolean needShuffle){
        myController.setPlayer(new ThemesCardPlayer(themesList, needShuffle));
    }
    public void enableTimer(int timerCountdown){
        myController.enableTimer(timerCountdown);
    }
    public void disableTimer(){
        myController.disableTimer();
    }

}
