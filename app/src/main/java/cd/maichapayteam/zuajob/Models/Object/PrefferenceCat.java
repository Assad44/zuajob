package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "pref_categorie")
public class PrefferenceCat extends Model {

    @JsonProperty("user")
    public long userId = -1;

    @JsonIgnoreProperties
    public User user;

    @JsonIgnoreProperties
    public List<Categorie> categories = new ArrayList<>();

    public List<Long> prefferences = new ArrayList<>();

}
