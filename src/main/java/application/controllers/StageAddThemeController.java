package application.controllers;

import application.model.Model;
import application.model.stageModels.AddThemeStageModel;
import cardSystem.Theme;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class StageAddThemeController implements Controller {


    private ArrayList<Theme> themeArrayList;
    private Theme editTheme;
    private boolean isEditMode = false;
    private AddThemeStageModel myModel;

    @FXML
    private Button leftButton;

    @FXML
    private TextField themeField;


    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
        if(isEditMode)  {
            leftButton.setText("Edit");
        }
        else            {
            leftButton.setText("Add");
            clearField();
        }
    }

    public void setThemeFieldText(String theme){
        if(!isValidString(theme)){
            System.out.println("error input field string in " + getClass().getSimpleName());
            return;
        }
        themeField.setText(theme);
    }

    @Override
    public void initialize() {
        clearField();
    }

    @Override
    public void updateElementsData() {}

    @Override
    public void setInputData(Object data) {
        if(data == null)
            return;
        if(data instanceof ArrayList){
            themeArrayList = (ArrayList<Theme>)data;
            return;
        }
        if(data instanceof Theme){
            editTheme = (Theme) data;
            return;
        }
        System.out.println("error input data in " +getClass().getSimpleName());
        return;
    }

    @Override
    public void setMyModel(Model model) {
        this.myModel = (AddThemeStageModel) model;
    }

    @Override
    public ArrayList<Theme> getInputData() {
        return themeArrayList;
    }

    @FXML
    private void leftButton(){
        System.out.println("left button event!");
        String themeName = themeField.getText();
        if(!isValidString(themeName)){
            // TODO: 24.01.2017 rewrite with alert window
            System.out.println("error input field string in " + getClass().getSimpleName());
            return;
        }
        if(isEditMode){
            this.editTheme.setThemeName(themeName);
        }else{
            this.themeArrayList.add(new Theme(themeName));
            clearField();
        }
        myModel.updateAddedModel();
        return;
    }
    @FXML
    private void closeButton(){
        myModel.close();
    }
    @FXML
    private void settingButton(){
        System.out.println("setting event!");
    }

    public void clearField(){
        themeField.clear();
    }

    public boolean isValidString(String themeName){
        if(themeName == null || themeName.equals(""))
            return false;
        return true;
    }
}
