package cd.maichapayteam.zuajob.Models.Object;

import android.widget.ArrayAdapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomUser {

    public List<User2> results = new ArrayList<>();

}
