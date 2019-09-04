package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pays {

    public long remoteId = -1;

    @JsonProperty("name")
    public String designation = "";

    @JsonProperty("flag")
    public String urlFlag = "";

    @JsonProperty("callingCodes")
    public List<String> listCode = new ArrayList<>();

}
