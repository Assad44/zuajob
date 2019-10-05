package cd.maichapayteam.zuajob.Tools;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.DAOClass.AnnonceDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.CategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.CommentDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.PostulerDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.ServiceDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.SollicitationDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.SousCategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Lists.ListAnnonce;
import cd.maichapayteam.zuajob.Models.Lists.ListCategorie;
import cd.maichapayteam.zuajob.Models.Lists.ListNotification;
import cd.maichapayteam.zuajob.Models.Lists.ListPostulance;
import cd.maichapayteam.zuajob.Models.Lists.ListService;
import cd.maichapayteam.zuajob.Models.Lists.ListSollicitation;
import cd.maichapayteam.zuajob.Models.Lists.ListSousCategorie;
import cd.maichapayteam.zuajob.Models.Lists.ListUser;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Comment;
import cd.maichapayteam.zuajob.Models.Object.Notification;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.Models.Object.User;

public class RemoteDataSync {

    private static String BASE_URL = "http://apizuajob.000webhostapp.com/v1/";
    //private static String BASE_URL = "http://157.245.44.245:8000/api/";
    //private static String BASE_URL = "http://192.168.43.230:8000/api/v1/";
    //private static String BASE_URL = "http://192.168.43.60/zuajob/v1/";

    /**
     *   GET METHODS
     */
    //OK
    public static boolean checkNumero(String numero) {
        numero = numero.replace("+", "");
        String url = BASE_URL + "checknumero";
        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("phone", numero)
                .build();
        //Log.e("Users", "Url:" + url);
        try{
            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                boolean rep = response.getResult().getBoolean("exist");
                return rep;
            } else {
                if(response.getError()!=null) {
                    Log.e("Users", "checkNumero:AN err:" + response.getError().getErrorDetail());
                } else {
                    Log.e("Users", "checkNumero: AN err is null");
                }
            }
        } catch (Exception ex) {
            Log.e("Users", "checkNumero:" + ex.getMessage());
        }

        return true;
    }

    //OK
    public static long[] sendSMS(String numero) {
        long[] rep = new long[]{-1, -1};
        String url = BASE_URL + "sendsms";

        numero = numero.replace("+", "");

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("phone", numero)
                .build();
        try{
            ANResponse<JSONObject> response = request.executeForJSONObject();
            if(response.getResult()==null) {
                Log.e("Users", "sendSMS: response.getResult is null");
            } else {
                Log.e("Users", "sendSMS: " + response.getResult().length());
            }

            if (response.isSuccess()) {
                long id = response.getResult().getLong("id");
                long pw = response.getResult().getLong("code");
                rep[0] = id;
                rep[1] = pw;
                Log.e("Users", "sendSMS:response: " + id + "/" + pw);
            } else {
                if(response.getError()!=null) {
                    Log.e("Users", "sendSMS:error with AN: " + response.getError().getMessage());
                } else {
                    Log.e("Users", "sendSMS:error with AN: error is null");
                }
            }
        } catch (Exception ex) {
            Log.e("Users", "sendSMS:error general: " + ex.getMessage());
        }

        return rep;
    }

    //OK
    public static String confirmCode(long id, String code) {
        String url = BASE_URL + "confirmcode/" + id + "/" + code;
        Log.e("Users", "confirmCode:url:" + url);

        ANRequest request = AndroidNetworking.get(url)
                .build();

        try{
            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                String rep = response.getResult().getString("authCode");
                Log.e("Users", "confirmCode:ok:" + rep);
                return rep;
            } else {
                if(response.getError()!=null) {
                    Log.e("Users", "confirmCode:response.getError" + response.getError().getErrorDetail());
                } else {
                    Log.e("Users", "confirmCode:response.getError:error is null");
                }
            }
        } catch (Exception ex) {
            Log.e("Users", "confirmCode:" + ex.getMessage());
        }

        return "";
    }

    //Pas encore implementé
    public static boolean uploadImage(String image, String format) {
        String url = BASE_URL + "uploadimage/" + GeneralClass.Currentuser.getAuthCode();

        JSONObject jsonObject = new JSONObject();
        try { jsonObject.put("image", image); } catch (JSONException e) { }
        try { jsonObject.put("format", format); } catch (JSONException e) { }
        try { jsonObject.put("type", "profile"); } catch (JSONException e) { }

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.HIGH)
                    .build();

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                if(!response.getResult().getBoolean("error")) {
                    String urli = response.getResult().getString("url");
                    String thumb = response.getResult().getString("thumb");
                    User user = GeneralClass.Currentuser;
                    user.setUrlPhoto(urli);
                    user.setUrlThumbnail(thumb);
                    GeneralClass.Currentuser = user;
                    UserDAO.getInstance(GeneralClass.applicationContext).ajouter(user);
                }
            }
        } catch (Exception ex) {

        }
        return false;
    }

    //public static List<Pays> getListPays () {
    //    String url = "https://restcountries.eu/rest/v2/all";
    //    String TAG = "getListPays";
//
    //    List<Pays> list = new ArrayList<>();
//
    //    ANRequest request = AndroidNetworking.get(url)
    //            .build();
//
    //    try{
    //        ANResponse<List<Pays>> response = request.executeForObjectList(Pays.class);
    //        long i = 0;
    //        if (response.isSuccess()) {
    //            List<Pays> list2 = response.getResult();
    //            Log.e(TAG, "paysList size : " + list.size());
    //            for (Pays object : list2) {
    //                if(i>10) break;
    //                i = i++;
    //                object.remoteId = i;
    //                list.add(object);
    //                Log.e(TAG, "designation : " + object.designation);
    //                Log.e(TAG, "url flag : " + object.urlFlag);
    //                Log.e(TAG, "list code : " + object.listCode.size());
    //            }
    //        } else {
    //            ANError error = response.getError();
    //            Log.e(TAG + ":error", error.getMessage());
    //        }
    //    } catch (Exception ex) {
    //        Log.e(TAG + ":errorLocal", ex.getMessage());
    //    }
//
    //    return list;
    //}

    //Pas encore implementé
    public static List<Categorie> getUserPreference () {
        String url = BASE_URL + "userpreference/" + GeneralClass.Currentuser.getAuthCode();

        List<Categorie> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListCategorie> response = request.executeForObject(ListCategorie.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                CategorieDAO cdao = new CategorieDAO(GeneralClass.applicationContext);
                for (Categorie object : list) {
                    object.setUserPreference(true);
                    cdao.ajouter(object);
                    Log.e("Category", "designation : " + object.getDesignation());
                }
            } else {
                ANError error = response.getError();
                Log.e("Category" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Category" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Categorie> getListCategorie () {
        String url = BASE_URL + "categories";

        List<Categorie> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .build();

        try{
            ANResponse<ListCategorie> response = request.executeForObject(ListCategorie.class);
            if (response.isSuccess()) {
                if(response.getResult().isError()) {
                    Log.e("Category", "server error : " + response.getResult().getErrorMessage());
                } else {
                    list = response.getResult().getListe();
                    CategorieDAO cdao = new CategorieDAO(GeneralClass.applicationContext);
                    for (Categorie object : list) {
                        cdao.ajouter(object);
                        Log.e("Category", "designation : " + object.getDesignation());
                    }
                }
            } else {
                ANError error = response.getError();
                if(error!=null) {
                    Log.e("Category", "AN error: " + error.getErrorBody());
                } else {
                    Log.e("Category", "AN error is null");
                }
            }
        } catch (Exception ex) {
            Log.e("Category", "errorLocal:" + ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<SousCategorie> getListSousCategorie () {
        String url = BASE_URL + "souscategories";

        List<SousCategorie> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .build();

        try{
            ANResponse<ListSousCategorie> response = request.executeForObject(ListSousCategorie.class);
            if (response.isSuccess()) {
                if(response.getResult().isError()) {
                    Log.e("Category", "server error : " + response.getResult().getErrorMessage());
                } else {
                    list = response.getResult().getListe();
                    SousCategorieDAO cdao = new SousCategorieDAO(GeneralClass.applicationContext);
                    for (SousCategorie object : list) {
                        cdao.ajouter(object);
                        Log.e("Under Category", "designation : " + object.getDesignation());
                    }
                }
            } else {
                ANError error = response.getError();
                Log.e("Under Category" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("UnderCateg" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<User> getListJobeur (int next) {
        String url = BASE_URL + "listjobeurs/" + next;

        List<User> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                //.addQueryParameter("next", String.valueOf(next))
                .build();

        try{
            ANResponse<ListUser> response = request.executeForObject(ListUser.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                UserDAO objectDAO = new UserDAO(GeneralClass.applicationContext);
                for (User object : list) {
                    if(GeneralClass.Currentuser.getId()==object.getId()) object.setMyProfil(true);
                    objectDAO.ajouter(object);
                    Log.e("User", "id : " + object.getId());
                }
                //Log.e("User", "id : " + response.getResult().get("liste"));
            } else {
                ANError error = response.getError();
                Log.e("User", "error:" + error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("User", "errorLocal:" + ex.getMessage());
        }

        return list;
    }

    //Pas encore implementé
    public static List<User> getListJobeur (String keyword, int next) {
        String url = BASE_URL + "listjobeur/";

        List<User> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("keyword", keyword)
                .addQueryParameter("next", String.valueOf(next))
                .build();

        try{
            ANResponse<ListUser> response = request.executeForObject(ListUser.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                UserDAO objectDAO = new UserDAO(GeneralClass.applicationContext);
                for (User object : list) {
                    if(GeneralClass.Currentuser.getId()==object.getId()) object.setMyProfil(true);
                    objectDAO.ajouter(object);
                    Log.e("User", "id : " + object.getId());
                }
            } else {
                ANError error = response.getError();
                Log.e("User" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("User" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //Pas encore implementé
    public static List<Comment> getListComment (int next) {
        String url = BASE_URL + "comment/";

        List<Comment> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("next", String.valueOf(next))
                .build();

        try{
            ANResponse<List<Comment>> response = request.executeForObject(Comment.class);
            if (response.isSuccess()) {
                list = response.getResult();
                CommentDAO objectDAO = new CommentDAO(GeneralClass.applicationContext);
                for (Comment object : list) {
                    objectDAO.ajouter(object);
                    Log.e("Comment", "id : " + object.getIdUserConcerne());
                }
            } else {
                ANError error = response.getError();
                Log.e("Comment" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Comment" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static User login(String phone, String password) {
        String url = BASE_URL + "login";

        User user;

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("phone", phone)
                .addQueryParameter("password", password)
                .build();

        try{
            ANResponse<User> response = request.executeForObject(User.class);
            if (response.isSuccess()) {
                user = response.getResult();
                if(user!=null) {
                    Log.e("Users", "Login:\n" + user.toString());
                    if(!user.isError()) {
                        user.setMyProfil(true);
                        //Log.e("Users", "Inscription:after net" + user.toString());
                        UserDAO userDAO = new UserDAO(GeneralClass.applicationContext);
                        user = userDAO.ajouter(user);
                        if(user==null) {
                            user = new User();
                            user.setError(true);
                            user.setErrorCode(7127);
                            user.setErrorMessage("Une erreur est survenue lors de la mise à jour de vos informations. Veuillez SVP vous connecter avec vos nouveax identifiants");
                        }
                    } else {
                        Log.e("Users", "Login:" + user.getErrorMessage());
                    }
                } else {
                    Log.e("Users", "Login:user is null");
                }
            } else {
                user = new User();
                user.setError(true);
                user.setErrorCode(319288);
                user.setErrorMessage(response.getError().getMessage());
                if(response.getError()!=null) {
                    Log.e("Users", "Login:AN error:" + response.getError().getErrorCode());
                } else {
                    Log.e("Users", "Login:AN error:is null");
                }
            }
        } catch (Exception ex) {
            user = new User();
            user.setError(true);
            user.setErrorCode(319288);
            user.setErrorMessage(ex.getMessage());
            Log.e("Users", "Login:ex:" + ex.getMessage());
        }

        return user;
        //return new User();
    }


    //OK
    public static List<Annonce> getRandomAnnonces () {
        String url = BASE_URL + "randomannonces";

        List<Annonce> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addQueryParameter("next", String.valueOf(next))
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListAnnonce> response = request.executeForObject(ListAnnonce.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                AnnonceDAO cdao = new AnnonceDAO(GeneralClass.applicationContext);
                for (Annonce object : list) {
                    if(GeneralClass.Currentuser.getId()==object.getIdUser()) object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("Annonce", "designation : " + object.getDescription());
                }
            } else {
                ANError error = response.getError();
                Log.e("Annonce" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Annonce" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Annonce> getAnnonces (int next, long souscategorie) {
        String url = BASE_URL + "annonces/" + souscategorie + "/" + next;

        List<Annonce> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    .build();

            ANResponse<ListAnnonce> response = request.executeForObject(ListAnnonce.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                AnnonceDAO cdao = new AnnonceDAO(GeneralClass.applicationContext);
                for (Annonce object : list) {
                    if(GeneralClass.Currentuser.getId()==object.getIdUser()) object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("Annonce", "designation : " + object.getDescription());
                }
            } else {
                ANError error = response.getError();
                Log.e("Annonce" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Annonce" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Annonce> getNewAnnonces (int next, long souscategorie) {
        String url = BASE_URL + "newannonces/" + souscategorie + "/" + next;

        List<Annonce> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    .build();

            ANResponse<ListAnnonce> response = request.executeForObject(ListAnnonce.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                AnnonceDAO cdao = new AnnonceDAO(GeneralClass.applicationContext);
                for (Annonce object : list) {
                    if(GeneralClass.Currentuser.getId()==object.getIdUser()) object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("Annonce", "designation : " + object.getDescription());
                }
            } else {
                ANError error = response.getError();
                Log.e("Annonce" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Annonce" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Annonce> getMesAnnonces () {
        String url = BASE_URL + "mesannonces/" + GeneralClass.Currentuser.getAuthCode();

        List<Annonce> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    .build();

            ANResponse<ListAnnonce> response = request.executeForObject(ListAnnonce.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                AnnonceDAO cdao = new AnnonceDAO(GeneralClass.applicationContext);
                for (Annonce object : list) {
                    object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("MesAnnonce", "description : " + object.getDescription());
                }
            } else {
                ANError error = response.getError();
                Log.e("MesAnnonce" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("MesAnnonce" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Service> getMesServices () {
        String url = BASE_URL + "messervices/" + GeneralClass.Currentuser.getAuthCode();

        List<Service> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListService> response = request.executeForObject(ListService.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("MesService", "description : " + object.getDescription());
                }
            } else {
                ANError error = response.getError();
                Log.e("MesService" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("MesService" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Service> getNewServices (int next, long souscategorie) {
        String url = BASE_URL + "newservices/" + souscategorie + "/" + next;

        List<Service> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    .build();

            ANResponse<ListService> response = request.executeForObject(ListService.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    if(GeneralClass.Currentuser.getId()==object.getIdJobeur()) object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("NewService", "description : " + object.getDescription());
                }
            } else {
                ANError error = response.getError();
                Log.e("NewService" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("NewService" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Service> getRandomServices (int next) {
        String url = BASE_URL + "randomservices";

        List<Service> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    .build();

            ANResponse<ListService> response = request.executeForObject(ListService.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    if(GeneralClass.Currentuser.getId()==object.getIdJobeur()) object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("RandomService", "description : " + object.getDescription());
                }
            } else {
                ANError error = response.getError();
                Log.e("RandomService" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("RandomServic" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Service> getServicesByRealisationCount (int next, long souscategorie) {
        String url = BASE_URL + "servicesbyrealisation/" + souscategorie + "/" + next;

        List<Service> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addQueryParameter("next", String.valueOf(next))
                    //.addQueryParameter("sous_categorie", String.valueOf(souscategorie))
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListService> response = request.executeForObject(ListService.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    if(GeneralClass.Currentuser.getId()==object.getIdJobeur()) object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("NewService", "description : " + object.getDescription());
                }
            } else {
                ANError error = response.getError();
                Log.e("NewService" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("NewService" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Service> getServicesByCote (int next, long souscategorie) {
        String url = BASE_URL + "servicesbycote/" + souscategorie + "/" + next;

        List<Service> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    .build();

            ANResponse<ListService> response = request.executeForObject(ListService.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    if(GeneralClass.Currentuser.getId()==object.getIdJobeur()) object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("NewService", "description : " + object.getDescription());
                }
            } else {
                ANError error = response.getError();
                Log.e("NewService" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("NewService" + ":errorLocal", ex.getMessage());
        }

        return list;
    }
    //OK
    public static List<Postuler> getPostulants (long idAnnonce) {
        String url = BASE_URL + "postulants/" + idAnnonce + "/" + GeneralClass.Currentuser.getAuthCode();

        List<Postuler> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addQueryParameter("idAnnonce", String.valueOf(idAnnonce))
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListPostulance> response = request.executeForObject(ListPostulance.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                PostulerDAO cdao = new PostulerDAO(GeneralClass.applicationContext);
                for (Postuler object : list) {
                    object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("Postuler", "description : " + object.getDescriptionAnnonce());
                }
            } else {
                ANError error = response.getError();
                Log.e("Postuler" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Postuler" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    public static List<Postuler> getHistoriqueRealisationByPostulance () {
        String url = BASE_URL + "histobypoatulance/";

        List<Postuler> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    .addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListPostulance> response = request.executeForObject(ListPostulance.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                PostulerDAO cdao = new PostulerDAO(GeneralClass.applicationContext);
                for (Postuler object : list) {
                    cdao.ajouter(object);
                    Log.e("histobypoatulance", "description : " + object.getDescriptionAnnonce());
                }
            } else {
                ANError error = response.getError();
                Log.e("histobypoatulance" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("histobypoatu" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    public static List<Sollicitation> getHistoriqueRealisationBySollicitation () {
        String url = BASE_URL + "histobysollicitation/";

        List<Sollicitation> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    .addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListSollicitation> response = request.executeForObject(ListSollicitation.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                SollicitationDAO cdao = new SollicitationDAO(GeneralClass.applicationContext);
                for (Sollicitation object : list) {
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionService());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    /**
     * Les annonces auxquels moi j'ai postulé
     */
    //OK
    public static List<Postuler> getMesPostulations () {
        String url = BASE_URL + "mespostulances/" + GeneralClass.Currentuser.getAuthCode();

        List<Postuler> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListPostulance> response = request.executeForObject(ListPostulance.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                PostulerDAO cdao = new PostulerDAO(GeneralClass.applicationContext);
                for (Postuler object : list) {
                    object.setHavePostuled(true);
                    object.setHavePostuled(true);
                    cdao.ajouter(object);
                    Log.e("Postuler", "description : " + object.getDescriptionAnnonce());
                }
            } else {
                ANError error = response.getError();
                Log.e("Postuler" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Postuler" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    /**
     * Les annonces auxquels moi j'ai sollicité
     */
    //OK
    public static List<Sollicitation> getSollicitations (long idService) {
        String url = BASE_URL + "sollicitants/" + idService + "/" + GeneralClass.Currentuser.getAuthCode();

        List<Sollicitation> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addQueryParameter("idService", String.valueOf(idService))
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListSollicitation> response = request.executeForObject(ListSollicitation.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                SollicitationDAO cdao = new SollicitationDAO(GeneralClass.applicationContext);
                for (Sollicitation object : list) {
                    object.setMy(true);
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionService());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    /**
     * Les sollicitaations auxquels moi j'ai sollicité
     */
    //OK
    public static List<Sollicitation> getMesSollicitations () {
        String url = BASE_URL + "messollicitations/" + GeneralClass.Currentuser.getAuthCode();

        List<Sollicitation> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListSollicitation> response = request.executeForObject(ListSollicitation.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                SollicitationDAO cdao = new SollicitationDAO(GeneralClass.applicationContext);
                for (Sollicitation object : list) {
                    object.setHaveSollicited(true);
                    object.setHaveSollicited(true);
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionService());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //OK
    public static List<Postuler> getMesRDVenAttentePost () {
        String url = BASE_URL + "mesrdvattente/postulance/" + GeneralClass.Currentuser.getAuthCode();

        List<Postuler> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListPostulance> response = request.executeForObject(ListPostulance.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                PostulerDAO cdao = new PostulerDAO(GeneralClass.applicationContext);
                for (Postuler object : list) {
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionAnnonce());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }
    //OK
    public static List<Sollicitation> getMesRDVenAttenteSoll () {
        String url = BASE_URL + "mesrdvattente/sollicitation/" + GeneralClass.Currentuser.getAuthCode();

        List<Sollicitation> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListSollicitation> response = request.executeForObject(ListSollicitation.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                SollicitationDAO cdao = new SollicitationDAO(GeneralClass.applicationContext);
                for (Sollicitation object : list) {
                    object.setHaveSollicited(true);
                    object.setHaveSollicited(true);
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionService());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }
    //OK
    public static List<Sollicitation> getMesRDVSollicitance () {
        String url = BASE_URL + "mesrdvaccepte/sollicitation/" + GeneralClass.Currentuser.getAuthCode();

        List<Sollicitation> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListSollicitation> response = request.executeForObject(ListSollicitation.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                SollicitationDAO cdao = new SollicitationDAO(GeneralClass.applicationContext);
                for (Sollicitation object : list) {
                    object.setHaveSollicited(true);
                    object.setHaveSollicited(true);
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionService());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }
    //OK
    public static List<Postuler> getMesRDVPostulance () {
        String url = BASE_URL + "mesrdvaccepte/postulance/" + GeneralClass.Currentuser.getAuthCode();

        List<Postuler> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListPostulance> response = request.executeForObject(ListPostulance.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                PostulerDAO cdao = new PostulerDAO(GeneralClass.applicationContext);
                for (Postuler object : list) {
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionAnnonce());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }
    //OK
    public static List<Sollicitation> getMesSollicitationsRefuses () {
        String url = BASE_URL + "messollicitationsrefuses/" + GeneralClass.Currentuser.getAuthCode();

        List<Sollicitation> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListSollicitation> response = request.executeForObject(ListSollicitation.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                SollicitationDAO cdao = new SollicitationDAO(GeneralClass.applicationContext);
                for (Sollicitation object : list) {
                    object.setHaveSollicited(true);
                    object.setHaveSollicited(true);
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionService());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }
    //OK
    public static List<Postuler> getMesPostulancesRefuses () {
        String url = BASE_URL + "mespostulancesrefuses/" + GeneralClass.Currentuser.getAuthCode();

        List<Postuler> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListPostulance> response = request.executeForObject(ListPostulance.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                PostulerDAO cdao = new PostulerDAO(GeneralClass.applicationContext);
                for (Postuler object : list) {
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionAnnonce());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }
    //OK
    public static List<Postuler> getMesAnnoncesConclu () {
        String url = BASE_URL + "mesannoncesconclu/" + GeneralClass.Currentuser.getAuthCode();

        List<Postuler> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListPostulance> response = request.executeForObject(ListPostulance.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                PostulerDAO cdao = new PostulerDAO(GeneralClass.applicationContext);
                for (Postuler object : list) {
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionAnnonce());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }
    //OK
    public static List<Sollicitation> getMesServicesConclu () {
        String url = BASE_URL + "messervicesconclu/" + GeneralClass.Currentuser.getAuthCode();

        List<Sollicitation> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<ListSollicitation> response = request.executeForObject(ListSollicitation.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                SollicitationDAO cdao = new SollicitationDAO(GeneralClass.applicationContext);
                for (Sollicitation object : list) {
                    object.setHaveSollicited(true);
                    object.setHaveSollicited(true);
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.getDescriptionService());
                }
            } else {
                ANError error = response.getError();
                Log.e("Sollicitation" + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Sollicit" + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    //public static List<Ville> getListVille () {
    //String url = BASE_URL + "listville";
    //String TAG = "getListVille";
//
    //List<Ville> list = new ArrayList<>();
//
    //ANRequest request = AndroidNetworking.get(url)
    //        .build();
//
    //try{
    //    ANResponse<ListVille> response = request.executeForObject(ListVille.class);
    //    if (response.isSuccess()) {
    //        ListVille listPays = response.getResult();
    //        list = listPays.listVille;
    //        Log.e(TAG, "villeList size : " + list.size());
    //        for (Ville object : list) {
    //            //Log.e(TAG, "id : " + object.remoteId);
    //            Log.e(TAG, "designation : " + object.designation);
    //            //Log.e(TAG, "provinceId : " + object.idProvince);
    //        }
    //        Response okHttpResponse = response.getOkHttpResponse();
    //        //Log.e(TAG, "headers : " + okHttpResponse.headers().toString());
    //    } else {
    //        ANError error = response.getError();
    //        Log.e(TAG + ":error", error.getMessage());
    //    }
    //} catch (Exception ex) {
    //    Log.e(TAG + ":error", ex.getMessage());
    //}
//
    //return list;
    //}

    //OK
    public static List<Notification> getMesNotifications () {
        String url = BASE_URL + "mesnotifications/" + GeneralClass.Currentuser.getAuthCode();

        List<Notification> list = new ArrayList<>();

        try{
            ANRequest request = AndroidNetworking.get(url)
                    .build();

            ANResponse<ListNotification> response = request.executeForObject(ListNotification.class);
            if (response.isSuccess()) {
                list = response.getResult().getListe();
                for (Notification object : list) {
                    if(object.getTypeObject().equals("postulance")) {
                        if(object.getPostulance()!=null &&
                                !object.getPostulance().isError()) {
                            PostulerDAO postulerDAO = PostulerDAO.getInstance(GeneralClass.applicationContext);
                            postulerDAO.ajouter(object.getPostulance());
                        }
                    } else {
                        if(object.getSollicitation()!=null &&
                                !object.getSollicitation().isError()) {
                            SollicitationDAO sollicitationDAO = SollicitationDAO.getInstance(GeneralClass.applicationContext);
                            sollicitationDAO.ajouter(object.getSollicitation());
                        }
                    }
                    Log.e("Notification", "message: " + object.getMessage());
                }
            } else {
                ANError error = response.getError();
                Log.e("Notification", "AN error:" + error.getMessage());
            }
        } catch (Exception ex) {
            Log.e("Notification", "errorLocal: " + ex.getMessage());
        }

        return list;
    }


    /**

     POST METHODS

     */
    //OK
    public static User createUser (User user) {
        String url = BASE_URL + "createuser";

        ANRequest request = AndroidNetworking.post(url)
                .addJSONObjectBody(user.toJsonObject())
                .setTag("user" + user.getPrenom() + user.getNom())
                .setPriority(Priority.MEDIUM)
                .build();

        Log.e("Users", "Inscription:avant net" + user.toString());
        try{
            ANResponse<User> response = request.executeForObject(User.class);
            if (response.isSuccess()) {
                user = response.getResult();
                if(user!=null) {
                    if(!user.isError()) {
                        user.setMyProfil(true);
                        Log.e("Users", "Inscription:after net" + user.toString());
                        UserDAO userDAO = new UserDAO(GeneralClass.applicationContext);
                        user = userDAO.ajouter(user);
                        if(user==null) {
                            user = new User();
                            user.setError(true);
                            user.setErrorCode(7127);
                            user.setErrorMessage("Une erreur est survenue lors de l'enregistrement de vos informations. Veuillez SVP vous connecter avec vos nouveax identifiants");
                        }
                    } else {
                        Log.e("Users", "Inscription:" + user.getErrorMessage());
                    }
                } else {
                    Log.e("Users", "Inscription:user is null");
                }
            } else {
                user = new User();
                user.setError(true);
                user.setErrorCode(319288);
                user.setErrorMessage(response.getError().getMessage());
                if(response.getError()!=null) {
                    Log.e("Users", "Inscription:AN error:" + response.getError().getErrorCode());
                } else {
                    Log.e("Users", "Inscription:AN error:is null");
                }
            }
        } catch (Exception ex) {
            user = new User();
            user.setError(true);
            user.setErrorCode(319288);
            user.setErrorMessage(ex.getMessage());
            Log.e("Users", "Inscription:ex:" + ex.getMessage());
        }

        return user;
        //return new User();
    }
    //OK
    public static Postuler postuler (long idAnnonce) {
        String url = BASE_URL + "postuler/" + idAnnonce + "/" + GeneralClass.Currentuser.getAuthCode();

        Postuler postuler;

        try{
            ANRequest request = AndroidNetworking.post(url)
                    //.setTag("postuler" + postuler.getPhoneUser())
                    .setPriority(Priority.MEDIUM)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<Postuler> response = request.executeForObject(Postuler.class);
            if (response.isSuccess()) {
                postuler = response.getResult();
                if(postuler!=null) {
                    if(!postuler.isError()) {
                        postuler.setHavePostuled(true);
                        PostulerDAO postulerDAO = new PostulerDAO(GeneralClass.applicationContext);
                        postuler = postulerDAO.ajouter(postuler);
                        if(postuler==null) {
                            postuler = new Postuler();
                            postuler.setError(true);
                            postuler.setErrorCode(1127);
                            postuler.setErrorMessage("Une erreur est survenue lors de votre postulation.");
                        }
                    }
                }
            } else {
                postuler = new Postuler();
                postuler.setError(true);
                postuler.setErrorCode(31921);
                postuler.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            postuler = new Postuler();
            postuler.setError(true);
            postuler.setErrorCode(49288);
            postuler.setErrorMessage(ex.getMessage());
        }

        return postuler;
    }
    //OK
    public static Sollicitation solliciter (long idService) {
        String url = BASE_URL + "solliciter/" + idService + "/" + GeneralClass.Currentuser.getAuthCode();

        Sollicitation sollicitation;

        try{
            ANRequest request = AndroidNetworking.post(url)
                    //.addBodyParameter(sollicitation) // posting java object
                    //.setTag("sollicitation" + sollicitation.getPhoneUser())
                    .setPriority(Priority.MEDIUM)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<Sollicitation> response = request.executeForObject(Sollicitation.class);
            if (response.isSuccess()) {
                sollicitation = response.getResult();
                if(sollicitation!=null) {
                    if(!sollicitation.isError()) {
                        sollicitation.setHaveSollicited(true);
                        SollicitationDAO sollicitationDAO = new SollicitationDAO(GeneralClass.applicationContext);
                        sollicitation = sollicitationDAO.ajouter(sollicitation);
                        if(sollicitation==null) {
                            sollicitation = new Sollicitation();
                            sollicitation.setError(true);
                            sollicitation.setErrorCode(1127);
                            sollicitation.setErrorMessage("Une erreur est survenue lors de votre sollicitation.");
                        }
                    }
                }
            } else {
                sollicitation = new Sollicitation();
                sollicitation.setError(true);
                sollicitation.setErrorCode(31921);
                sollicitation.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            sollicitation = new Sollicitation();
            sollicitation.setError(true);
            sollicitation.setErrorCode(49288);
            sollicitation.setErrorMessage(ex.getMessage());
        }

        return sollicitation;
    }
    //OK
    public static Annonce publierAnnonce (Annonce object) {
        String url = BASE_URL + "annonce/" + GeneralClass.Currentuser.getAuthCode();

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addJSONObjectBody(object.toJsonObject())
                    .setTag("publierannonce" + object.getPhoneUser())
                    .setPriority(Priority.MEDIUM)
                    //.addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<Annonce> response = request.executeForObject(Annonce.class);
            if (response.isSuccess()) {
                object = response.getResult();
                if(object!=null) {
                    if(!object.isError()) {
                        object.setMy(true);
                        AnnonceDAO annonceDAO = new AnnonceDAO(GeneralClass.applicationContext);
                        object = annonceDAO.ajouter(object);
                        if(object==null) {
                            object = new Annonce();
                            object.setError(true);
                            object.setErrorCode(14347);
                            object.setErrorMessage("Une erreur est survenue lors de la publication de votre annonce.");
                        }
                    }
                }
            } else {
                object = new Annonce();
                object.setError(true);
                object.setErrorCode(31921);
                object.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            object = new Annonce();
            object.setError(true);
            object.setErrorCode(49288);
            object.setErrorMessage(ex.getMessage());
        }

        return object;
    }
    //OK
    public static Service publierService (Service object) {
        String url = BASE_URL + "service/" + GeneralClass.Currentuser.getAuthCode();

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addJSONObjectBody(object.toJsonObject())
                    .setTag("publierservice" + object.getNomsJobeur())
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<Service> response = request.executeForObject(Service.class);
            if (response.isSuccess()) {
                object = response.getResult();
                if(object!=null) {
                    if(!object.isError()) {
                        object.setMy(true);
                        ServiceDAO serviceDAO = new ServiceDAO(GeneralClass.applicationContext);
                        object = serviceDAO.ajouter(object);
                        if(object==null) {
                            object = new Service();
                            object.setError(true);
                            object.setErrorCode(14347);
                            object.setErrorMessage("Une erreur est survenue lors de la publication de votre service.");
                        }
                    }
                } else {
                    object = new Service();
                    object.setError(true);
                    object.setErrorCode(14347);
                    object.setErrorMessage("Une erreur est survenue lors de la publication de votre service.");
                }
            } else {
                object = new Service();
                object.setError(true);
                object.setErrorCode(31921);
                object.setErrorMessage(response.getError().getErrorBody());
            }
        } catch (Exception ex) {
            object = new Service();
            object.setError(true);
            object.setErrorCode(49288);
            object.setErrorMessage(ex.getMessage());
        }

        return object;
    }

    public static Comment commenter (Comment object) {
        String url = BASE_URL + "commenter";

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addBodyParameter(object) // posting java object
                    .setTag("commenter" + object.getIdUserConcerne())
                    .setPriority(Priority.MEDIUM)
                    .addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<Comment> response = request.executeForObject(Comment.class);
            if (response.isSuccess()) {
                object = response.getResult();
                if(object!=null) {
                    if(!object.isError()) {
                        CommentDAO objectDAO = new CommentDAO(GeneralClass.applicationContext);
                        object = objectDAO.ajouter(object);
                        if(object==null) {
                            object = new Comment();
                            object.setError(true);
                            object.setErrorCode(2247);
                            object.setErrorMessage("Une erreur est survenue lors de la publication de votre commentaire.");
                        }
                    }
                }
            } else {
                object = new Comment();
                object.setError(true);
                object.setErrorCode(31921);
                object.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            object = new Comment();
            object.setError(true);
            object.setErrorCode(49288);
            object.setErrorMessage(ex.getMessage());
        }

        return object;
    }



    /**

     PUT METHODS

     */
    //OK
    public static User updateUser (User user) {
        String url = BASE_URL + "user/" + user.getId();

        ANRequest request = AndroidNetworking.post(url)
                .addJSONObjectBody(user.toJsonObject())
                .setTag("user" + user.getId())
                .setPriority(Priority.MEDIUM)
                .build();
        try{
            ANResponse<User> response = request.executeForObject(User.class);
            if (response.isSuccess()) {
                user = response.getResult();
                if(user!=null) {
                    if(!user.isError()) {
                        user.setMyProfil(true);
                        //Log.e("Users", "Inscription:after net" + user.toString());
                        UserDAO userDAO = new UserDAO(GeneralClass.applicationContext);
                        user = userDAO.ajouter(user);
                        if(user==null) {
                            user = new User();
                            user.setError(true);
                            user.setErrorCode(7127);
                            user.setErrorMessage("Une erreur est survenue lors de la mise à jour de vos informations. Veuillez SVP vous connecter avec vos nouveax identifiants");
                        }
                    } else {
                        Log.e("Users", "Update:" + user.getErrorMessage());
                    }
                } else {
                    Log.e("Users", "Update:user is null");
                }
            } else {
                user = new User();
                user.setError(true);
                user.setErrorCode(319288);
                user.setErrorMessage(response.getError().getMessage());
                if(response.getError()!=null) {
                    Log.e("Users", "Update:AN error:" + response.getError().getErrorCode());
                } else {
                    Log.e("Users", "Update:AN error:is null");
                }
            }
        } catch (Exception ex) {
            user = new User();
            user.setError(true);
            user.setErrorCode(319288);
            user.setErrorMessage(ex.getMessage());
            Log.e("Users", "Update:ex:" + ex.getMessage());
        }

        return user;
        //return new User();
    }
    //Pas encore implémenté
    public static Annonce updateAnnonce (Annonce object) {
        String url = BASE_URL + "annonce";

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addBodyParameter(object)
                    .setTag("updateannonce" + object.getPhoneUser())
                    .setPriority(Priority.MEDIUM)
                    .addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<Annonce> response = request.executeForObject(Annonce.class);
            if (response.isSuccess()) {
                object = response.getResult();
                if(object!=null) {
                    if(!object.isError()) {
                        AnnonceDAO annonceDAO = new AnnonceDAO(GeneralClass.applicationContext);
                        object = annonceDAO.ajouter(object);
                        if(object==null) {
                            object = new Annonce();
                            object.setError(true);
                            object.setErrorCode(14347);
                            object.setErrorMessage("Une erreur est survenue lors de la modification de votre annonce.");
                        }
                    }
                }
            } else {
                object = new Annonce();
                object.setError(true);
                object.setErrorCode(31921);
                object.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            object = new Annonce();
            object.setError(true);
            object.setErrorCode(49288);
            object.setErrorMessage(ex.getMessage());
        }

        return object;
    }
    //Pas encore implémenté
    public static Service updateService (Service object) {
        String url = BASE_URL + "service";

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addBodyParameter(object)
                    .setTag("updateservice" + object.getNomsJobeur())
                    .setPriority(Priority.MEDIUM)
                    .addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<Service> response = request.executeForObject(Service.class);
            if (response.isSuccess()) {
                object = response.getResult();
                if(object!=null) {
                    if(!object.isError()) {
                        object.setMy(true);
                        ServiceDAO annonceDAO = new ServiceDAO(GeneralClass.applicationContext);
                        object = annonceDAO.ajouter(object);
                        if(object==null) {
                            object = new Service();
                            object.setError(true);
                            object.setErrorCode(14347);
                            object.setErrorMessage("Une erreur est survenue lors de la modification de votre service.");
                        }
                    }
                }
            } else {
                object = new Service();
                object.setError(true);
                object.setErrorCode(31921);
                object.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            object = new Service();
            object.setError(true);
            object.setErrorCode(49288);
            object.setErrorMessage(ex.getMessage());
        }

        return object;
    }
    //OK
    public static boolean updatePassword (String lastpassword, String newPassword) {
        String url = BASE_URL + "updatepassword/" + GeneralClass.Currentuser.getAuthCode();

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addQueryParameter("ancienpassword", lastpassword)
                    .addQueryParameter("nouveaupassword", newPassword)
                    .setPriority(Priority.MEDIUM)
                    .build();

            Log.e("updatePassword", "result:start");
            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                Log.e("updatePassword", "result:" + response.getResult().toString());
                if(response.getResult()!=null) {
                    return response.getResult().getBoolean("updated");
                }
            } else {
                if(response.getError()!=null)
                    Log.e("updatePassword", "AN error:" + response.getError().getErrorCode());
                else
                    Log.e("updatePassword", "AN error: is null");
            }
        } catch (Exception ex) {
            Log.e("updatePassword", "general error:" + ex.getMessage());
        }

        return false;
    }


    //OK
    public static Sollicitation creerRDVbyJobeur (long idSolliciter, String date, String heure, String detail, float montant, String devise) {
        String url = BASE_URL + "creerrdv/jobeur/" + GeneralClass.Currentuser.getAuthCode();

        Sollicitation sollicitation = null;

        JSONObject jsonObject = new JSONObject();
        try { jsonObject.put("idSollicitation", idSolliciter); } catch (JSONException e) { }
        try { jsonObject.put("date", date); } catch (JSONException e) { }
        try { jsonObject.put("heure", heure); } catch (JSONException e) { }
        try { jsonObject.put("detail", detail); } catch (JSONException e) { }
        try { jsonObject.put("montant", montant); } catch (JSONException e) { }
        try { jsonObject.put("devise", devise); } catch (JSONException e) { }

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addJSONObjectBody(jsonObject)
                    .setTag("creerRDVByPostuler" + idSolliciter)
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                jsonObject = response.getResult();
                if(jsonObject!=null) {
                    if(!jsonObject.getBoolean("error") && jsonObject.getBoolean("created")) {
                        SollicitationDAO sollicitationDAO = new SollicitationDAO(GeneralClass.applicationContext);
                        sollicitation = sollicitationDAO.find(idSolliciter);
                        if(sollicitation!=null) {
                            sollicitation.setRDV(true);
                            sollicitation.setDateRDV(date);
                            sollicitation.setHeureRDV(heure);
                            sollicitation.setDetailRDV(detail);
                            sollicitation.setMontantConclu(montant);
                            sollicitation.setDeviseConclu(devise);
                            sollicitation = sollicitationDAO.ajouter(sollicitation);
                        }
                    }
                }
            } else {
                sollicitation = new Sollicitation();
                sollicitation.setError(true);
                sollicitation.setErrorCode(31921);
                sollicitation.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            sollicitation = new Sollicitation();
            sollicitation.setError(true);
            sollicitation.setErrorCode(49288);
            sollicitation.setErrorMessage(ex.getMessage());
        }

        if(sollicitation==null) {
            sollicitation = new Sollicitation();
            sollicitation.setError(true);
            sollicitation.setErrorCode(1127);
            sollicitation.setErrorMessage("Une erreur est survenue lors de la création de votre rendez-vous.");
        }

        return sollicitation;
    }
    //OK
    public static Sollicitation refuserSollicitation (long idSolliciter) {
        String url = BASE_URL + "refusersollicitation/jobeur/" + idSolliciter + "/" + GeneralClass.Currentuser.getAuthCode();

        Sollicitation sollicitation = null;

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .setTag("creerRDVByPostuler" + idSolliciter)
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                JSONObject jsonObject = response.getResult();
                if(jsonObject!=null) {
                    if(!jsonObject.getBoolean("error") && jsonObject.getBoolean("refused")) {
                        SollicitationDAO sollicitationDAO = new SollicitationDAO(GeneralClass.applicationContext);
                        sollicitation = sollicitationDAO.find(idSolliciter);
                        if(sollicitation!=null) {
                            sollicitation.setRefused(true);
                            sollicitation = sollicitationDAO.ajouter(sollicitation);
                        }
                    }
                }
            } else {
                sollicitation = new Sollicitation();
                sollicitation.setError(true);
                sollicitation.setErrorCode(31921);
                sollicitation.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            sollicitation = new Sollicitation();
            sollicitation.setError(true);
            sollicitation.setErrorCode(49288);
            sollicitation.setErrorMessage(ex.getMessage());
        }

        if(sollicitation==null) {
            sollicitation = new Sollicitation();
            sollicitation.setError(true);
            sollicitation.setErrorCode(1127);
            sollicitation.setErrorMessage("Une erreur est survenue lors de la création de votre rendez-vous.");
        }

        return sollicitation;
    }
    //OK
    public static Sollicitation confirmerRDVbyUser (long idSolliciter, String codePayement) {
        String url = BASE_URL + "confirmerrdv/user/" + GeneralClass.Currentuser.getAuthCode();

        Sollicitation sollicitation = null;

        JSONObject jsonObject = new JSONObject();
        try { jsonObject.put("idSollicitation", idSolliciter); } catch (JSONException e) { }
        try { jsonObject.put("codePayement", codePayement); } catch (JSONException e) { }

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addJSONObjectBody(jsonObject)
                    .setTag("creerRDVByPostuler" + idSolliciter)
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                jsonObject = response.getResult();
                if(jsonObject!=null) {
                    if(!jsonObject.getBoolean("error") && jsonObject.getBoolean("confirmed")) {
                        SollicitationDAO sollicitationDAO = new SollicitationDAO(GeneralClass.applicationContext);
                        sollicitation = sollicitationDAO.find(idSolliciter);
                        if(sollicitation!=null) {
                            sollicitation.setAccepted(true);
                            sollicitation = sollicitationDAO.ajouter(sollicitation);
                        }
                    }
                }
            } else {
                sollicitation = new Sollicitation();
                sollicitation.setError(true);
                sollicitation.setErrorCode(31921);
                sollicitation.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            sollicitation = new Sollicitation();
            sollicitation.setError(true);
            sollicitation.setErrorCode(49288);
            sollicitation.setErrorMessage(ex.getMessage());
        }

        if(sollicitation==null) {
            sollicitation = new Sollicitation();
            sollicitation.setError(true);
            sollicitation.setErrorCode(1127);
            sollicitation.setErrorMessage("Une erreur est survenue lors de la création de votre rendez-vous.");
        }

        return sollicitation;
    }
    //OK
    public static Sollicitation serviceRenduBySollicitance (long idSolliciter, int cote, String comment) {
        String url = BASE_URL + "servicerendu/sollicitation/" + idSolliciter + "/" + GeneralClass.Currentuser.getAuthCode();

        Sollicitation sollicitation = null;

        JSONObject jsonObject = new JSONObject();
        try { jsonObject.put("cote", cote); } catch (JSONException e) { }
        try { jsonObject.put("comment", comment); } catch (JSONException e) { }

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addJSONObjectBody(jsonObject)
                    .setTag("creerRDVByPostuler" + idSolliciter)
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                jsonObject = response.getResult();
                if(jsonObject!=null) {
                    if(!jsonObject.getBoolean("error") && jsonObject.getBoolean("rended")) {
                        SollicitationDAO sollicitationDAO = new SollicitationDAO(GeneralClass.applicationContext);
                        sollicitation = sollicitationDAO.find(idSolliciter);
                        if(sollicitation!=null) {
                            sollicitation.setConclu(true);
                            sollicitation = sollicitationDAO.ajouter(sollicitation);
                        }
                    }
                }
            } else {
                sollicitation = new Sollicitation();
                sollicitation.setError(true);
                sollicitation.setErrorCode(31921);
                sollicitation.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            sollicitation = new Sollicitation();
            sollicitation.setError(true);
            sollicitation.setErrorCode(49288);
            sollicitation.setErrorMessage(ex.getMessage());
        }

        if(sollicitation==null) {
            sollicitation = new Sollicitation();
            sollicitation.setError(true);
            sollicitation.setErrorCode(1127);
            sollicitation.setErrorMessage("Une erreur est survenue lors de la création de votre rendez-vous.");
        }

        return sollicitation;
    }





    //OK
    public static Postuler creerRDVbyUser (long idPostuler, String date, String heure, String detail, String codePayement, float montant, String devise) {
        String url = BASE_URL + "creerrdv/user/" + GeneralClass.Currentuser.getAuthCode();

        Postuler postuler = null;

        JSONObject jsonObject = new JSONObject();
        try { jsonObject.put("idPostulance", idPostuler); } catch (JSONException e) { }
        try { jsonObject.put("date", date); } catch (JSONException e) { }
        try { jsonObject.put("heure", heure); } catch (JSONException e) { }
        try { jsonObject.put("detail", detail); } catch (JSONException e) { }
        try { jsonObject.put("codePayement", codePayement); } catch (JSONException e) { }
        try { jsonObject.put("montant", montant); } catch (JSONException e) { }
        try { jsonObject.put("devise", devise); } catch (JSONException e) { }

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addJSONObjectBody(jsonObject)
                    .setTag("creerRDVByPostuler" + idPostuler)
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                jsonObject = response.getResult();
                if(jsonObject!=null) {
                    if(!jsonObject.getBoolean("error") && jsonObject.getBoolean("created")) {
                        PostulerDAO postulerDAO = new PostulerDAO(GeneralClass.applicationContext);
                        postuler = postulerDAO.find(idPostuler);
                        if(postuler!=null) {
                            postuler.setRDV(true);
                            postuler.setDateRDV(date);
                            postuler.setHeureRDV(heure);
                            postuler.setDetailRDV(detail);
                            postuler.setMontantConclu(montant);
                            postuler.setDeviseConclu(devise);
                            postuler = postulerDAO.ajouter(postuler);
                        }
                    }
                }
            } else {
                postuler = new Postuler();
                postuler.setError(true);
                postuler.setErrorCode(31921);
                postuler.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            postuler = new Postuler();
            postuler.setError(true);
            postuler.setErrorCode(49288);
            postuler.setErrorMessage(ex.getMessage());
        }
        if(postuler==null) {
            postuler = new Postuler();
            postuler.setError(true);
            postuler.setErrorCode(1127);
            postuler.setErrorMessage("Une erreur est survenue lors de la création du rendez-vous.");
        }
        return postuler;
    }
    //OK
    public static Postuler refuserPostulance (long idPostuler) {
        String url = BASE_URL + "refuserpostulance/user/" + idPostuler + "/" + GeneralClass.Currentuser.getAuthCode();

        Postuler postuler = null;

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .setTag("creerRDVByPostuler" + idPostuler)
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                JSONObject jsonObject = response.getResult();
                if(jsonObject!=null) {
                    if(!jsonObject.getBoolean("error") && jsonObject.getBoolean("refused")) {
                        PostulerDAO postulerDAO = new PostulerDAO(GeneralClass.applicationContext);
                        postuler = postulerDAO.find(idPostuler);
                        if(postuler!=null) {
                            postuler.setRefused(true);
                            postuler = postulerDAO.ajouter(postuler);
                        }
                    }
                }
            } else {
                postuler = new Postuler();
                postuler.setError(true);
                postuler.setErrorCode(31921);
                postuler.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            postuler = new Postuler();
            postuler.setError(true);
            postuler.setErrorCode(49288);
            postuler.setErrorMessage(ex.getMessage());
        }
        if(postuler==null) {
            postuler = new Postuler();
            postuler.setError(true);
            postuler.setErrorCode(1127);
            postuler.setErrorMessage("Une erreur est survenue lors de la création du rendez-vous.");
        }
        return postuler;
    }
    //OK
    public static Postuler confirmerRDVbyJobeur (long idPostuler) {
        String url = BASE_URL + "confirmerrdv/jobeur/" + idPostuler + "/" + GeneralClass.Currentuser.getAuthCode();

        Postuler postuler = null;

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .setTag("creerRDVByPostuler" + idPostuler)
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                JSONObject jsonObject = response.getResult();
                if(jsonObject!=null) {
                    if(!jsonObject.getBoolean("error") && jsonObject.getBoolean("confirmed")) {
                        PostulerDAO postulerDAO = new PostulerDAO(GeneralClass.applicationContext);
                        postuler = postulerDAO.find(idPostuler);
                        if(postuler!=null) {
                            postuler.setAccepted(true);
                            postuler = postulerDAO.ajouter(postuler);
                        }
                    }
                }
            } else {
                postuler = new Postuler();
                postuler.setError(true);
                postuler.setErrorCode(31921);
                postuler.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            postuler = new Postuler();
            postuler.setError(true);
            postuler.setErrorCode(49288);
            postuler.setErrorMessage(ex.getMessage());
        }
        if(postuler==null) {
            postuler = new Postuler();
            postuler.setError(true);
            postuler.setErrorCode(1127);
            postuler.setErrorMessage("Une erreur est survenue lors de la création du rendez-vous.");
        }
        return postuler;
    }
    //OK
    public static Postuler serviceRenduByPostulance (long idPostuler, int cote, String comment) {
        String url = BASE_URL + "servicerendu/postulance/" + idPostuler + "/" + GeneralClass.Currentuser.getAuthCode();

        Postuler postuler = null;

        JSONObject jsonObject = new JSONObject();
        try { jsonObject.put("cote", cote); } catch (JSONException e) { }
        try { jsonObject.put("comment", comment); } catch (JSONException e) { }

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addJSONObjectBody(jsonObject)
                    .setTag("creerRDVByPostuler" + idPostuler)
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                jsonObject = response.getResult();
                if(jsonObject!=null) {
                    if(!jsonObject.getBoolean("error") && jsonObject.getBoolean("rended")) {
                        PostulerDAO postulerDAO = new PostulerDAO(GeneralClass.applicationContext);
                        postuler = postulerDAO.find(idPostuler);
                        if(postuler!=null) {
                            postuler.setConclu(true);
                            postuler = postulerDAO.ajouter(postuler);
                        }
                    }
                }
            } else {
                postuler = new Postuler();
                postuler.setError(true);
                postuler.setErrorCode(31921);
                postuler.setErrorMessage(response.getError().getMessage());
            }
        } catch (Exception ex) {
            postuler = new Postuler();
            postuler.setError(true);
            postuler.setErrorCode(49288);
            postuler.setErrorMessage(ex.getMessage());
        }
        if(postuler==null) {
            postuler = new Postuler();
            postuler.setError(true);
            postuler.setErrorCode(1127);
            postuler.setErrorMessage("Une erreur est survenue lors de la création du rendez-vous.");
        }
        return postuler;
    }







    public static boolean signalerUtilisateur (long idUtilisateur, String comment, int niveauDanger) {
        String url = BASE_URL + "signaler";

        try{
            ANRequest request = AndroidNetworking.post(url)
                    .addQueryParameter("idUtilisateur", String.valueOf(idUtilisateur))
                    .addBodyParameter("comment", comment)
                    .addBodyParameter("niveau", String.valueOf(niveauDanger))
                    .setTag("signalerUtilisateur" + idUtilisateur)
                    .setPriority(Priority.MEDIUM)
                    .addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<Sollicitation> response = request.executeForObject(Sollicitation.class);
            if (response.isSuccess()) if(response.getResult().equals("1")) return true;
        } catch (Exception ex) {

        }

        return false;
    }


    /**

     DELETE METHODS

     */

    public static boolean deleteUser () {
        String url = BASE_URL + "user";

        try{
            ANRequest request = AndroidNetworking.delete(url)
                    .addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .setTag("deleteuser" + GeneralClass.Currentuser.getAuthCode())
                    .setPriority(Priority.MEDIUM)
                    .build();

            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) if(response.getResult().equals("1")) return true;
        } catch (Exception ex) {

        }

        return false;
    }

    public static boolean deleteAnnonce (long idAnnonce) {
        String url = BASE_URL + "annonce";

        try{
            ANRequest request = AndroidNetworking.delete(url)
                    .addQueryParameter("idAnnonce", String.valueOf(idAnnonce))
                    .setTag("deleteannonce" + idAnnonce)
                    .setPriority(Priority.MEDIUM)
                    .addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) if(response.getResult().equals("1")) return true;
        } catch (Exception ex) {

        }

        return false;
    }

    public static boolean deleteService (long idService) {
        String url = BASE_URL + "service";

        try{
            ANRequest request = AndroidNetworking.delete(url)
                    .addQueryParameter("IdService", String.valueOf(idService))
                    .setTag("deleteservice" + idService)
                    .setPriority(Priority.MEDIUM)
                    .addHeaders("token", GeneralClass.Currentuser.getAuthCode())
                    .build();

            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) if(response.getResult().equals("1")) return true;
        } catch (Exception ex) {

        }

        return false;
    }

    /**

     LOAD DATA TEST

     **/

    //public static List<User> getRandomUser (int nombre) {
    //    //String url = "https://randomuser.me/api/?results=100";
    //    String url = "https://randomuser.me/api/";
//
    //    List<User> userList = new ArrayList<>();
//
    //    ANRequest request = AndroidNetworking.get(url)
    //            .addQueryParameter("results", String.valueOf(nombre))
    //            .build();
//
    //    try{
    //        ANResponse<RandomUser> response = request.executeForObject(RandomUser.class);
    //        UserDAO userDAO = new UserDAO(GeneralClass.applicationContext);
    //        if (response.isSuccess()) {
    //            Log.e("RandomUser", String.valueOf(response.getResult().results.size()));
    //            long i = userDAO.max();
    //            for (User2 user2 : response.getResult().results) {
    //                i++;
    //                User user = new User();
    //                user.prenom = user2.name.first.substring(0, 1).toUpperCase() + user2.name.first.substring(1);
    //                user.nom = user2.name.last.substring(0, 1).toUpperCase() + user2.name.last.substring(1);
    //                user.id = i;
    //                user.urlPhoto = user2.picture.thumbnail;
    //                user.type = 1;
    //                user.phone = String.valueOf(890000000 + new Random().nextInt(899999999 - 890000000));
    //                user.codePays = "+243";
    //                user.pays = "Congo DR";
    //                user.email = user2.email;
    //                user.sexe = "M";
    //                if(user2.gender.equals("female")) user.sexe = "F";
//
    //                userList.add(user);
//
    //                User u = userDAO.ajouter(user);
//
    //                if(u!=null) {
    //                    Log.e("RandomUser", u.getPrenom() + " " + u.getNom());
    //                } else {
    //                    Log.e("RandomUser", "Erreur lors de l'ajout de l'item " + user.getId());
    //                }
//
    //            }
    //        } else {
    //            ANError error = response.getError();
    //            Log.e("RandomUser", error.getMessage());
    //        }
    //    } catch (Exception ex) {
    //        Log.e("RandomUser", ex.getMessage());
    //    }
//
    //    return userList;
    //    //return new ArrayList<>();
    //}

    public static String getRandomParagraphe (int nombrePhrase) {
        String url = "https://baconipsum.com/api/?type=all-meat&sentences=" + String.valueOf(nombrePhrase) +"&start-with-lorem=0";

        ANRequest request = AndroidNetworking.get(url)
                .build();

        try{
            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) {
                String rep = response.getResult();
                //Log.e("RandomUser", rep);
                return rep.substring(2, rep.length()-2);
            } else {
                return "Aucune phrase trouvée";
            }
        } catch (Exception ex) {
            return "Aucune phrase trouvée";
        }
    }







    /*

               OTHERS

     */

    public interface UploadImageListener {
        void OnResult(long id, String url);
        void OnProgress(long bytesUploaded, long totalBytes);
        void OnError(String message);
    }

}
