package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.maichapayteam.zuajob.Tools.RemoteDataSync;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "user")
public class Service extends Model {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;

    @JsonProperty("id")
    @Column(name = "remoteId")
    public long remoteId = -1;

    @Column(name = "idSousCategorie")
    public long idSousCategorie = -1;

    @JsonIgnoreProperties
    @Column(name = "sousCategorie")
    public SousCategorie sousCategorie = null;

    @Column(name = "description")
    public String description = "";

    @JsonIgnoreProperties
    @Column(name = "devise")
    public Devise devise = null;

    @Column(name = "idDevise")
    public long idDevise = -1;

    @Column(name = "montant")
    public float montant = 0;

    @JsonIgnoreProperties
    @Column(name = "prestateur")
    public User prestateur = null;

    @Column(name = "idPrestateur")
    public long idPrestateur = -1;

    @JsonIgnoreProperties
    public List<Cote> cotes = new ArrayList<>();

    @Column(name = "nombreRealisation")
    public int nombreRealisation = 0;

    public static ArrayList<Service> listService() {
        ArrayList<Service> list = new ArrayList<>();
        List<User> listPrestateur = User.listJobeurs();
        for (int i = 0; i < 100; i++) {
            Service service = new Service();
            service.remoteId = i++;
            service.description = RemoteDataSync.getRandomParagraphe(new Random().nextInt(9) + 1);
            service.montant = new Random().nextFloat();
            service.cotes = Cote.getListCote();
            service.nombreRealisation = new Random().nextInt();
            service.prestateur = listPrestateur.get(new Random().nextInt(listPrestateur.size())); //
            service.devise = GeneralClass.getDAleatoire();
            service.sousCategorie = GeneralClass.getDAleatoireSCat(); //
            list.add(service);
        }
        return list;
    }

}
