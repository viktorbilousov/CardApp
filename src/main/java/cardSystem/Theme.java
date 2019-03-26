package cardSystem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlType(name = "Theme")
@XmlAccessorType(XmlAccessType.FIELD)
public class Theme {
    private ArrayList<Question> questionsList = new ArrayList<Question>();
    private String themeName;

    public Theme() {}


    public Theme(String themeName) {
        this.themeName = themeName;
    }

    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public void addQuestion(Question question){
        this.questionsList.add(question);
    }

    @Override
    protected Theme clone() throws CloneNotSupportedException {
        Theme cloneTheme = new Theme(new String(themeName));
        for (Question question : questionsList) {
            cloneTheme.addQuestion(question.clone());
        }
        return cloneTheme;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Theme))
            return false;
        Theme theme = (Theme)obj;
        return themeName.equals(theme.getThemeName());
    }

    @Override
    public String toString() {
        String outString = "";
        outString += "theme : " + themeName ;
        return outString;
    }
}
