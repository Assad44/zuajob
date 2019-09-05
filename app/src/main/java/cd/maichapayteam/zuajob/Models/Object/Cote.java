package cd.maichapayteam.zuajob.Models.Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cote {

    public long id = -1;
    public String reaction = "";
    public int cote = 0;
    public User user = null;
    public User prestateur;

    public static List<Cote> getListCote() {
        List<Cote> list = new ArrayList<>();
        for (int i = 0; i < 20 ; i++) {
            Cote cote = new Cote();
            cote.cote = new Random().nextInt(5);
            cote.reaction = GeneralClass.getPhrase();
            list.add(cote);
        }
        return list;
    }

}
