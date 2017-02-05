package cardSystem;

public class Question {
    private String question;
    private String answer = "";
    private String tip = "";

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question(String question, String answer, String tip) {
        this.question = question;
        this.answer = answer;
        this.tip = tip;
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    protected Question clone() throws CloneNotSupportedException {
        return new Question(
                new String(this.question),
                new String(this.answer),
                new String(this.tip)
        );
    }

    @Override
    public String toString() {
        return  "question='" + question + '\'' +
                ", answer='" + answer + '\''   +
                ", tip='" + tip + "\'";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Question))
            return false;
        Question q = (Question)obj;

        return this.question.equals(q.question);
              /*  && this.answer.equals(q.answer)
                && this.tip.equals(q.tip);*/
    }
}
