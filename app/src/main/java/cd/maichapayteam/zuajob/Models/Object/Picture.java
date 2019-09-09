package cd.maichapayteam.zuajob.Tools.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture {

    public String large = "";
    public String medium = "";
    public String thumbnail = "";

}
