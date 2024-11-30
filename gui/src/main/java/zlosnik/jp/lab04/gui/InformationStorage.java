package zlosnik.jp.lab04.gui;

import java.util.List;

public class InformationStorage {
    List<String> swiadczeniaZdrowotne;
    List<String> swiadczeniodawcy;
    List<String> swiadczenia;

    public InformationStorage() {
        this.swiadczeniaZdrowotne = List.of();
        this.swiadczeniodawcy = List.of();
        this.swiadczenia = List.of();
    }
}
