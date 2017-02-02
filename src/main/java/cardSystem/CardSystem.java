package cardSystem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CardSystem {

    // TODO: 24.01.2017 подсказка
    private ArrayList<Theme> themeList = new ArrayList<Theme>();
    private ArrayList<Question> universalQuestion = new ArrayList<Question>();

  //region setters\getters
    public ArrayList<Theme> getThemeList() {
        return themeList;
    }

    public void setThemeList(ArrayList<Theme> themeList) {
        this.themeList = themeList;
    }

    public ArrayList<Question> getUniversalQuestion() {
        return universalQuestion;
    }

    public void setUniversalQuestion(ArrayList<Question> universalQuestion) {
        this.universalQuestion = universalQuestion;
    }
    //endregion

    public CardSystem() { }


    public void addTheme(Theme theme){
        if(themeList.contains(theme))
            return;
        themeList.add(theme);
    }

    public void addUniversalQuestion(Question question){
        question.setAnswer("");
        if(universalQuestion.contains(question))
            return;
        universalQuestion.add(question);
    }

    public boolean addAllUniQuestionToTheme(Theme theme){
        int index = themeList.indexOf(theme);
        if(index == -1)
            return false;
        for(Question q : universalQuestion){
            themeList.get(index).addQuestion(q);
        }
        return true;
    }

    public void removeTheme(int index){
        themeList.remove(index);
    }
    public void removeUniversalQuesion(int index){
        universalQuestion.remove(index);
    }

    private void displayList(List list){
        for(Object o : list){
            System.out.println(o.toString());
        }
        System.out.println("--------------------------------\n");
    }

    public boolean loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CardSystemWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Чтение XML из файла и демаршализация.
            CardSystemWrapper wrapper = (CardSystemWrapper) um.unmarshal(file);

            CardSystem system = wrapper.getThemesSystem();
            themeList = system.getThemeList();
            universalQuestion = system.getUniversalQuestion();
            return true;

        } catch (Exception e) { // catches ANY exception
            System.out.println(e);
            return false;
        }
    }

    public boolean savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CardSystemWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            CardSystemWrapper wrapper = new CardSystemWrapper();
            wrapper.setThemesSystem(this);
            m.marshal(wrapper, file);

            // Сохраняем путь к файлу в реестре.
           // setPersonFilePath(file);
            return true;
        } catch (Exception e) { // catches ANY exception
            System.out.println(e);
            return false;
        }
    }

    public void displayAll(){
        System.out.println("\tTHEMES\t");
        displayList(this.themeList);
        System.out.println("\tUNIVERSAL QUESTION\t");
        displayList(this.universalQuestion);
    }

    public void loadDefPar(){
        Theme firstTheme = new Theme("First theme");
        firstTheme.addQuestion(new Question("question11" , "answer11", "tip11"));
        firstTheme.addQuestion(new Question("question12" , "answer12", "tip12"));
        firstTheme.addQuestion(new Question("question13" , "answer13", "tip13"));
        firstTheme.addQuestion(new Question("question14" , "answer14", "tip14"));

        Theme secondTheme = new Theme("Second theme");
        secondTheme.addQuestion(new Question("question21", "answer21", "tip21"));
        secondTheme.addQuestion(new Question("question22", "answer22", "tip22"));
        secondTheme.addQuestion(new Question("question23", "answer23", "tip23"));
        secondTheme.addQuestion(new Question("question24", "answer24", "tip24"));
        secondTheme.addQuestion(new Question("question25", "answer25", "tip25"));
        secondTheme.addQuestion(new Question("question26", "answer26", "tip26"));

        Theme thirdTheme = new Theme("Third theme");
        thirdTheme.addQuestion(new Question("question31", "answer31", "tip31"));
        thirdTheme.addQuestion(new Question("question32", "answer32", "tip32"));
        thirdTheme.addQuestion(new Question("question33", "answer33", "tip33"));

        addUniversalQuestion(new Question("universal Question 1"));
        addUniversalQuestion(new Question("universal Question 2"));
        addUniversalQuestion(new Question("universal Question 3"));
        addUniversalQuestion(new Question("universal Question 4"));
        addUniversalQuestion(new Question("universal Question 5"));

        addTheme(firstTheme);
        addTheme(secondTheme);
        addTheme(thirdTheme);

/*        addAllUniQuestionToTheme(firstTheme);
        addAllUniQuestionToTheme(secondTheme);
        addAllUniQuestionToTheme(thirdTheme);*/

    }

}
