package cd.maichapayteam.zuajob.Models.Object;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuth {

    private long id = -1;
    private String telephone = "";
    private String username = "anonyme";
    private String password = "anonyme";

    @Override
    public String toString() {
        String str = "{\n\t";
        str += "\"telephone\":\"" + telephone + "\"\n\t";
        str += "\"username\":\"" + username + "\"\n\t";
        str += "\"password\":\"" + password + "\"\n";
        str += "}";
        return str;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
