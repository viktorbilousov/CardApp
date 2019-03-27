package application.model.play;

import application.MainApp;
import application.controllers.play.PlayStageController;
import application.model.Model;
import application.model.StageModel;
import application.util.StageUtil;
import cardSystem.Theme;
import cardSystem.Play.CardPlay;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class PlayStageModel extends StageModel {

    private PlayStageController myController = (PlayStageController)controller;
    private StatisticStageModel statisticModel;
    private StartPlayStageModel startPlayStageModel = (StartPlayStageModel)parent;
    private boolean isShuffle = false;

    public boolean isShuffle() {
        return isShuffle;
    }

    public PlayStageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);
        init();
        getPrimaryStage().setMinHeight(700);
        getPrimaryStage().setMinWidth(800);
    }

    private void init() {
        statisticModel = new StatisticStageModel(
                StageUtil.makeNewStage("Statistic", getPrimaryStage()),
                this,
                MainApp.class.getResource("/fxml/play/Statistic.fxml")
        );
    }
    public void openStatistic(ArrayList<Theme> allThemes, ArrayList<Theme> notAnswered){
        this.close();
        statisticModel.setData(allThemes,notAnswered);
        statisticModel.show();
    }

    public void setData(ArrayList<Theme> themesList, boolean needShuffle){
        isShuffle = needShuffle;
        myController.setPlayer(new CardPlay(themesList, needShuffle));
    }
    public void enableTimer(int timerCountdown){
        myController.enableTimer(timerCountdown);
    }
    public void disableTimer(){
        myController.disableTimer();
    }
    public void openStartMenu(){
        close();
        parent.show();
    }
    public void openWithOldSetting(ArrayList<Theme> themeArrayList){
        startPlayStageModel.closeAndOpenPlayStage(themeArrayList);
    }

}
