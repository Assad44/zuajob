package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User2 {

    public String gender = "";
    public String email = "";
    public Name name;
    public Picture picture;

}

