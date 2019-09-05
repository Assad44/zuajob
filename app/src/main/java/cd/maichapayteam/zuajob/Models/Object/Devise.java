package cd.maichapayteam.zuajob.Models.Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Devise {

    public long id = -1;
    public String desoignation = "";

    public static List<Devise> getListDevise() {
        List<Devise> list = new ArrayList<>();
        for (int i = 0; i < 20 ; i++) {
            Devise devise = new Devise();
            devise.desoignation = "FC";
            list.add(devise);
            Devise devise2 = new Devise();
            devise2.desoignation = "$";
            list.add(devise2);
        }
        return list;
    }

}
