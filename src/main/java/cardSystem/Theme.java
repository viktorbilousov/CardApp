package cardSystem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlType(name = "Theme")
public class Theme {
    private List<Question> questionsList = new ArrayList<Question>();
    private String themeName;

    public Theme() {}


    public Theme(String themeName) {
        this.themeName = themeName;
    }

    @XmlElement(name = "questions")
    public List<Question> getQuestionsList() {
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
    public boolean equals(Object obj) {
        if(!(obj instanceof  Theme))
            return false;
        Theme theme = (Theme)obj;
        return questionsList.equals(theme.getThemeName());
    }

    @Override
    public String toString() {
        String outString = "";
        outString += "theme : " + themeName ;
        outString += "\n--------question----------\n";
        for (Question q: questionsList){
            outString += q + "\n";
        }
        return outString;
    }
}
