package application.model.play;

import application.MainApp;
import application.controllers.play.StartPlayStageController;
import application.model.Model;
import application.model.StageModel;
import application.util.StageUtil;
import cardSystem.Theme;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ArrayList;

public class StartPlayStageModel extends StageModel {

    private StartPlayStageController myController = (StartPlayStageController)controller;

    private PlayStageModel playStageModel;
    private PlaySettingStageModel playSettingModel;

    private Boolean isShuffle = false;
    private Boolean enableTimer = false;
    private Integer timerCountdown  = 0;

    public void setShuffle(Boolean shuffle) {
        isShuffle = shuffle;
    }

    public void setEnableTimer(Boolean enableTimer) {
        this.enableTimer = enableTimer;
    }

    public void setTimerCountdown(Integer timerCountdown) {
        this.timerCountdown = timerCountdown;
    }

    public StartPlayStageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);
        init();
        /*primaryStage.setOnCloseRequest(event -> {
                if( event.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST) {
                    parent.show();
                }
        });*/
    }
    public void init(){
        playStageModel = new PlayStageModel(
                StageUtil.makeNewStage("Play", getPrimaryStage()),
                this,
                MainApp.class.getResource("../fxml/play/Play.fxml")
        );
        playSettingModel = new PlaySettingStageModel(
                StageUtil.makeNewStage("Play setting", getPrimaryStage()),
                this,
                MainApp.class.getResource("../fxml/play/PlaySetting.fxml")
        );
    }

    public void closeAndOpenParent(){
        close();
        parent.show();
    }

    public void setThemeArrayList(ArrayList<Theme> themeArrayList){
        myController.setThemeArrayList(themeArrayList);
    }

    public void showSetting(){
        playSettingModel.show();
    }


    public void closeAndOpenPlayStage(ArrayList<Theme> themesList){
        close();
        System.out.println("shuffle: " + isShuffle);
        System.out.println("timer: " + enableTimer);
        System.out.println(timerCountdown);

        if(enableTimer)     playStageModel.enableTimer(timerCountdown);
        else                playStageModel.disableTimer();

        playStageModel.setData(themesList, isShuffle);
        playStageModel.show();
    }

}
