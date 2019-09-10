package cd.maichapayteam.zuajob.Models.Object ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture {

    public String large = "";
    public String medium = "";
    public String thumbnail = "";

}
