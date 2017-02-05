package application.controllers.play;

import application.cardSystemProperty.ThemeProperty;
import application.controllers.Controller;
import application.model.Model;
import application.model.play.StartPlayStageModel;
import cardSystem.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class StartPlayStageController implements Controller {


    @FXML
    private TableColumn<ThemeProperty, String> ThemesTable;
    @FXML
    private TableColumn<ThemeProperty, Boolean> checkTable;
    @FXML
    private TableView<ThemeProperty> tableView;

    private StartPlayStageModel myModel ;
    private ArrayList<Theme> themeArrayList;
    private ObservableList<ThemeProperty> observableList = FXCollections.observableArrayList();
    private boolean needShuffle = false;

    public ArrayList<Theme> getChekedThemesList(){
        ArrayList<Theme> output = new ArrayList<>();
        for(ThemeProperty themeProperty : observableList){
            if(themeProperty.isIsCheck()){
                int index = themeArrayList.indexOf(themeProperty.convertToTheme()); // lost question
                output.add(themeArrayList.get(index));
            }
        }
        return output;
    }

    public void setThemeArrayList(ArrayList<Theme> themeArrayList) {
        this.themeArrayList = themeArrayList;
        updateElementsData();
    }



    @Override
    public void initialize() {
        ThemesTable.setCellValueFactory(param -> param.getValue().themeNameProperty());
        checkTable.setCellFactory(param ->  new CheckBoxTableCell());
        checkTable.setCellValueFactory(param -> param.getValue().isCheckProperty());
    }

    @Override
    public void updateElementsData() {
        observableList.clear();
        for(Theme theme : themeArrayList){
            observableList.add(new ThemeProperty(theme));
            observableList.get(observableList.size()-1).setIsCheck(true);
        }
        tableView.setItems(observableList);
    }

    @Override
    public void setMyModel(Model model) {
        myModel = (StartPlayStageModel) model;
    }

    @FXML
    private void startButton() {
        myModel.closeAndOpenPlayStage();
    }

    @FXML
    private void closeButton() {
       myModel.closeAndOpenParent();
    }

    @FXML
    private void selectAllButton() {
        for(ThemeProperty themeProperty : observableList){
            themeProperty.setIsCheck(true);
        }
    }
    @FXML
    private void deselectAllButton() {
        for(ThemeProperty themeProperty : observableList){
            themeProperty.setIsCheck(false);
        }
    }

    @FXML
    private void settingMenu() {
        myModel.showSetting();
    }

    @FXML
    private void exitMenu() {
        myModel.close();
    }

    @FXML
    private void openEditMenu() {
        myModel.closeAndOpenParent();
    }
}
