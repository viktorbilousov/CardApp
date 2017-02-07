package application.util;


import cardSystem.Question;
import cardSystem.Theme;

import java.util.ArrayList;

public class CardGenerator {

    public static final String defThemeName = "Theme";
    public static final String defQuestionAnswer = "Answer";
    public static final String defQuestionQuestion = "Question";
    public static final String defQuestionTip = "Tip";

    public static ArrayList<Theme> getThemesList(int cntThemes, int cntQuestion)
    {
        ArrayList<Theme> themes = new ArrayList<>();
        for(int i=0; i<cntThemes ; i++){
            Theme theme = new Theme(defThemeName + (i+1) ) ;
            for(int j=0 ; j<cntQuestion ; j++){
                theme.addQuestion(new Question(defQuestionQuestion + (j+1),
                        defQuestionAnswer + (j+1),
                        defQuestionTip + (j+1)));
            }
            themes.add(theme);
        }
        return themes;
    }
}
