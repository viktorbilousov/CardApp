package cardSystem;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;

public class CardSystemStream {

    private CardSystem system;
    private boolean writeLastLoadFileToRegistr = true;
    public CardSystemStream(CardSystem system) {
        this.system = system;
    }


    public boolean loadCardSystemFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CardSystemWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Чтение XML из файла и демаршализация.
            CardSystemWrapper wrapper = (CardSystemWrapper) um.unmarshal(file);

            CardSystem loadSystem = wrapper.getThemesSystem();
            system.setThemeList(loadSystem.getThemeList());
            system.setUniversalQuestion(loadSystem.getUniversalQuestion());
            if(writeLastLoadFileToRegistr) setCardSystemFilePath(file);
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean saveCardSystemToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CardSystemWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            CardSystemWrapper wrapper = new CardSystemWrapper();
            wrapper.setThemesSystem(system);
            m.marshal(wrapper, file);
            if(writeLastLoadFileToRegistr) setCardSystemFilePath(file);
            return true;
        } catch (Exception e) { // catches ANY exception
            System.out.println(e);
            return false;
        }
    }
    private void setCardSystemFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(CardSystem.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
        } else {
            prefs.remove("filePath");
        }
    }

    public boolean tryLoadLastFile(){
        File lastFile = getLastLoadFilePath();
        if(lastFile != null){
            loadCardSystemFromFile(lastFile);
            return true;
        }
        return false;
    }

    public File getLastLoadFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(CardSystem.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
}
