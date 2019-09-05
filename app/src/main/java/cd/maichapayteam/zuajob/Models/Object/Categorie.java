package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "categorie")
public class Categorie extends Model {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;

    @JsonProperty("id")
    public long remoteId = -1;

    public String designation = "";

    public List<SousCategorie> items() {
        return getMany(SousCategorie.class, "categorie");
    }
}
