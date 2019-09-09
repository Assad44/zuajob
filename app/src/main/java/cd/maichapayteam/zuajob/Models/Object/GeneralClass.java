package cd.maichapayteam.zuajob.Models.Object;

import java.util.Random;

public class GeneralClass {

    private static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static String getMot() {
        String str = "";
        for (int i = 0; i < new Random().nextInt(10) ; i++) {
            str += chars[new Random().nextInt(25)];
        }
        return str;
    }

    public static String getMotWithoutMaj() {
        String str = "";
        for (int i = 4; i < 10 ; i++) {
            str += chars[new Random().nextInt(25)];
        }
        return str;
    }

    public static String getPhrase() {
        String str = "";
        for (int i = 2; i < 10 ; i++) {
            str += getMotWithoutMaj() + " ";
        }
        str = str.trim();
        str += ".";
        return str;
    }

    public static User getAleatoire() {
        User user = new User();
        user.remoteId = new Random().nextInt();
        user.prenom = GeneralClass.getMot();
        user.nom = GeneralClass.getMot();
        return user;
    }

    public static Categorie getDAleatoireCat(){
        Categorie cat = new Categorie();
        cat.remoteId = new Random().nextInt();
        cat.designation = getMot();
        return cat;
    }

    public static SousCategorie getDAleatoireSCat(){
        SousCategorie cat = new SousCategorie();
        cat.remoteId = new Random().nextInt();
        cat.designation = getMot();
        cat.categorie = getDAleatoireCat();
        return cat;
    }


}
