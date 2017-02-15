package application.controllers.play;

import application.controllers.Controller;
import application.model.Model;
import application.model.play.PlayStageModel;
import cardSystem.Question;
import cardSystem.Theme;
import cardSystem.ThemesCardPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayStageController implements Controller {

    @FXML
    private Button tipBtn;
    @FXML
    private Button dontKnowAnswerBtn;
    @FXML
    private Button knowAnswerBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private Button pauseBtn;
    @FXML
    private Button startAndStopBtn;
    @FXML
    private Label themeLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Label tipLabel;
    @FXML
    private Label numberCardLabel;
    @FXML
    private Label timerLabel;
    @FXML
    private Label answerLabel;



    enum PlayState{
        PLAYING,
        PAUSE,
        STOP, NOT_INIT,
    }

    private PlayStageModel myModel;
    private ThemesCardPlayer player;
    private PlayState state = PlayState.NOT_INIT;

    private Iterator<Question> playerIterator;

    private Question currentQuestion = null;
    private String currentThemeName = "";

    private boolean isClickTipButton = false;
    private ArrayList<Theme> notAnswerQuestions = null;
    private Integer itemsCounter = 0;

    private Timeline timer;
    private boolean isTimerOn = false;
    private final static int TICK_MSECONDS = 1000;
    private Integer downCounter = 0;
    private Integer timerTime = 0;
    private final int redColorTimer = 3;

    public void setPlayer(ThemesCardPlayer player) {
        this.player = player;
        playerIterator = player.iterator();
        updateElementsData();
    }
    public void enableTimer(int timeTimer) {
        this.isTimerOn = true;
        this.timerTime = timeTimer;
    }
    public void disableTimer(){
        this.isTimerOn = false;
        downCounter = 0;
        timerLabel.setText("off");
        pauseBtn.setDisable(true);
    }


    @Override
    public void initialize() {
        changeStateToStop();
        nextBtn.setDisable(true);
        clearLabels();
        numberCardLabel.setText("0/0");
        timer = new Timeline();
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.getKeyFrames().add(
                new KeyFrame(
                        Duration.millis(TICK_MSECONDS),
                        event -> tick()
                )
        );
        disableTimer();
    }

    @Override
    public void updateElementsData() {
        resetCounterLabel();
    }

    @Override
    public void setMyModel(Model model) {
        myModel = (PlayStageModel) model;
    }

    private void tick(){
        System.out.println("tick");
        if(downCounter == 0){
            dontKnowAnswerButton();
        }else {
            downCounter --;
            setTimerLabel();
        }
    }

    private void setTimerLabel(){
        if(downCounter <= redColorTimer){
            timerLabel.setTextFill(Color.RED);
        }else {
            timerLabel.setTextFill(Color.BLACK);
        }
        timerLabel.setText("timer: " + downCounter);
    }

    private void clearLabels(){
        questionLabel.setText("");
        tipLabel.setText("");
        themeLabel.setText("");
        answerLabel.setText("");
    }

    private void clearTipAndAnswerLabels(){
        tipLabel.setText("");
        answerLabel.setText("");
    }

    private void showThemeAndQuestionTextLabels(){
        themeLabel.setText(currentThemeName);
        questionLabel.setText(currentQuestion.getQuestion());
    }

    private void showTipLabel(){
        tipLabel.setText(currentQuestion.getTip());
    }

    private void showAnswerLabel(){
        answerLabel.setText(currentQuestion.getAnswer());
    }

    private void showNextQuestion(){
        if(!playerIterator.hasNext()) {
            setState(PlayState.STOP);
            return;
        }
        downCounter = timerTime;
        setTimerLabel();
        currentQuestion = playerIterator.next();
        currentThemeName = player.getQuestionTheme(currentQuestion);
        showThemeAndQuestionTextLabels();
        incrementCounterLabel();

    }

    private void setState(PlayState changeState){
        if(changeState == state)
            return;
        if(state == PlayState.STOP){
            if(changeState == PlayState.PLAYING){
                state = changeState;
                setDisableAnswerBnts(false);
                tipBtn.setDisable(false);
                startAndStopBtn.setText("Stop");
                nextBtn.setText("Next");
               // nextBtn.setDisable(false);
                isClickTipButton = false;
                player.reset();
                resetCounterLabel();
                if(this.isTimerOn) {
                    timer.play();
                    pauseBtn.setDisable(false);
                    setTimerLabel();
                }
                showNextQuestion();
                return;
            }
        }
        if(state == PlayState.PLAYING){
            if(changeState == PlayState.STOP){
                changeStateToStop();
                return;
            }
            if(changeState == PlayState.PAUSE){
                clearLabels();
                timer.pause();
                state = changeState;
                setDisableAnswerBnts(true);
                tipBtn.setDisable(true);
                pauseBtn.setText("Resume");
                return;
            }
        }
        if(state == PlayState.PAUSE){
            pauseBtn.setText("Pause");
            if(changeState == PlayState.STOP){
                changeStateToStop();
                return;
            }
            if(changeState == PlayState.PLAYING){
                showThemeAndQuestionTextLabels();
                setDisableAnswerBnts(false);
                tipBtn.setDisable(false);
                if(isClickTipButton)  showTipLabel();
                state = changeState;
                if(this.isTimerOn) {
                    timer.play();
                    setTimerLabel();
                }
                return;
            }
        }

    }

    private void changeStateToStop(){
        if(player != null) {
            notAnswerQuestions = player.stopAndGetNotAnsteredThemesList();
        }
        state = PlayState.STOP;
        setDisableAnswerBnts(true);
        tipBtn.setDisable(true);
        pauseBtn.setDisable(true);
        startAndStopBtn.setText("Start");
        isClickTipButton = false;
        nextBtn.setText("Statistic");
        nextBtn.setFont(Font.font("System", FontWeight.BOLD, 18));
        nextBtn.setDisable(false);
        if(this.isTimerOn) {
            timer.stop();
            timerLabel.setText("off");
        }
        timerTime = 0;
    }

    private void incrementCounterLabel(){
        itemsCounter++ ;
        numberCardLabel.setText(itemsCounter.toString() + "/" + player.size().toString() );
    }

    private void resetCounterLabel(){
        itemsCounter = 0;
        numberCardLabel.setText(itemsCounter.toString() + "/" + player.size().toString() );
    }

    private void setDisableAnswerBnts(boolean disable){
        dontKnowAnswerBtn.setDisable(disable);
        knowAnswerBtn.setDisable(disable);
    }

    @FXML
    private void startAndStopButton() {
        if(state == PlayState.STOP)     {
            setState(PlayState.PLAYING);
            return;
        }
        else if(state == PlayState.PLAYING)  {
            setState(PlayState.STOP);
            return;
        }
    }
    @FXML
    private void dontKnowAnswerButton() {
        player.setAnswerState(false);
        nextBtn.setDisable(false);
        pauseBtn.setDisable(true);
        setDisableAnswerBnts(true);
        showAnswerLabel();
        showTipButton();
        if(isTimerOn)
            timer.pause();
    }
    @FXML
    private void showTipButton() {
        isClickTipButton = true;
        showTipLabel();
    }
    @FXML
    private void knowAnswerButton() {
        player.setAnswerState(true);
        pauseBtn.setDisable(true);
        nextBtn.setDisable(false);
        setDisableAnswerBnts(true);
        showAnswerLabel();
        showTipButton();
        if(isTimerOn)
            timer.pause();
    }
    @FXML
    private void pauseButton() {
        if(state == PlayState.PLAYING) {
            setState(PlayState.PAUSE);
            return;
        }
        else if(state == PlayState.PAUSE) {
            setState(PlayState.PLAYING);
            return;
        }
    }
    @FXML
    private void nextButton() {

        if(state == PlayState.PLAYING) {
            isClickTipButton = false;
            setDisableAnswerBnts(false);

            if (isTimerOn)
                pauseBtn.setDisable(false);

            nextBtn.setDisable(true);
            clearTipAndAnswerLabels();
            if (isTimerOn)
                timer.play();
            showNextQuestion();
        }
        else if (state == PlayState.STOP){
            openStatisticButton();
        }
    }
    @FXML
    private void openStatisticButton() {
        myModel.openStatistic(player.getInputThemesList(), player.getNotAnsweredThemesList());
        nextBtn.setDisable(true);
    }
}

