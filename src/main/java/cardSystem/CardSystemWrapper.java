package cardSystem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ThemesSystems")
public class CardSystemWrapper {
    private CardSystem themesSystem;

    @XmlElement(name = "CardSystem")
    public CardSystem getThemesSystem() {
        return themesSystem;
    }

    public void setThemesSystem(CardSystem themesSystem) {
        this.themesSystem = themesSystem;
    }
}
