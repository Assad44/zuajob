package cd.maichapayteam.zuajob.Back_end;

/**
 * Created by Deon_Mass on 09/07/2018.
 */

public class Config {

    /**
     * Application Authentication
     */
    public static final String auth = "Zuajob@DeonMass.digitaledge";

    /**
     * ONLINE
     */
    public static String SERVER_IP_ADRESSE         = "http://";
    public static String SERVER_PATH_              = SERVER_IP_ADRESSE +"";
    /**

    /**
     * OFFLINE
     */
    public static String SERVER_IP_ADRESSE_ = "http://192.168.43.211/";
    public static String SERVER_PATH        = SERVER_IP_ADRESSE_ +"zuajob_services/Functions.php";

    /**
     * BASE DE DONNEES LOCAL CONFIGURATION
     */
    public static final String db_name= "zuajob2.db";


    /**
     * DATA LIMIT FOR QUERIES
     */
    public static int DATA_LIMIT = 20;



}