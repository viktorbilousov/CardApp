package application.cardSystemProperty;

import cardSystem.Question;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;

public class QuestionProperty {
    private StringProperty questionProperty;
    private StringProperty answerProperty;
    private StringProperty tipProperty;
    private BooleanProperty check;

    public QuestionProperty(Question question) {
        questionProperty = new SimpleStringProperty(question.getQuestion());
        answerProperty = new SimpleStringProperty(question.getAnswer());
        tipProperty = new SimpleStringProperty(question.getTip());
        check = new SimpleBooleanProperty();
    }


    public String getTipProperty() {
        return tipProperty.get();
    }

    public boolean isCheck() {
        return check.get();
    }

    public BooleanProperty checkProperty() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check.set(check);
    }

    public StringProperty tipPropertyProperty() {
        return tipProperty;
    }

    public void setTipProperty(String tipProperty) {
        this.tipProperty.set(tipProperty);
    }

    public String getQuestionProperty() {
        return questionProperty.get();
    }

    public StringProperty questionPropertyProperty() {
        return questionProperty;
    }

    public void setQuestionProperty(String questionProperty) {
        this.questionProperty.set(questionProperty);
    }

    public String getAnswerProperty() {
        return answerProperty.get();
    }

    public StringProperty answerPropertyProperty() {
        return answerProperty;
    }

    public void setAnswerProperty(String answerProperty) {
        this.answerProperty.set(answerProperty);
    }

    public Question convertToQuestion(){
        return new Question(questionProperty.get(), answerProperty.get(), tipProperty.get());
    }

    public static StringConverter getConverter(){
        return new StringConverter() {
            @Override
            public String toString(Object object) {
                return object.toString();
            }

            @Override
            public QuestionProperty fromString(String string) {
                return new QuestionProperty(new Question(string));
            }
        };
    }

    @Override
    public String toString() {
        return "QuestionProperty{" +
                "questionProperty=" + questionProperty +
                ", answerProperty=" + answerProperty +
                ", tipProperty=" + tipProperty +
                ", check=" + check +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionProperty)) return false;

        QuestionProperty that = (QuestionProperty) o;

        if (getQuestionProperty() != null ? !getQuestionProperty().equals(that.getQuestionProperty()) : that.getQuestionProperty() != null)
            return false;
        if (getAnswerProperty() != null ? !getAnswerProperty().equals(that.getAnswerProperty()) : that.getAnswerProperty() != null)
            return false;
        if (getTipProperty() != null ? !getTipProperty().equals(that.getTipProperty()) : that.getTipProperty() != null)
            return false;
        return isCheck() == that.isCheck();
    }

}
