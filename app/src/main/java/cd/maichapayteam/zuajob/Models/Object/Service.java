package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Service {

    public long id = -1;
    public SousCategorie sousCategorie = null;
    public String description = "";
    public Devise devise = null;
    public float montant = 0;
    public User prestateur = null;
    public List<Cote> cotes = new ArrayList<>();
    public int nombreRealisation = 0;

    public static List<Service> listService() {
        List<Service> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Service service = new Service();
            service.id = i++;
            service.description = GeneralClass.getPhrase();
            service.montant = new Random().nextFloat();
            service.cotes = Cote.getListCote();
            service.nombreRealisation = new Random().nextInt();
            service.prestateur = GeneralClass.getAleatoire();
            service.devise = GeneralClass.getDAleatoire();
            list.add(service);
        }
        return list;
    }

}
