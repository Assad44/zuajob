package cd.maichapayteam.zuajob.Tools;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

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
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Comment;
import cd.maichapayteam.zuajob.Models.Object.GeneralClass;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.Models.Object.User;

public class RemoteDataSync {

    private static String BASE_URL = "http://192.168.43.60/doxa_event_server/v1/";
    private static String BASE_URL2 = "http://192.168.43.230:8000/api/v1/";

    /**
    *   GET METHODS
     */

    public static boolean confirmCode(String numero, String code) {
        String url = BASE_URL + "confirmenumero";

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("numero", numero)
                .addQueryParameter("code", code)
                .build();

        try{
            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) {
                if(response.getResult().equals("1")) return true;
            }
        } catch (Exception ex) {

        }

        return false;
    }

    public static boolean checkNumero(String numero) {
        String url = BASE_URL + "checknumero";

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("phone", numero)
                .build();

        try{
            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) {
                if(response.getResult().equals("1")) return true;
            }
        } catch (Exception ex) {

        }

        return false;
    }

    public static boolean sendSMS(String numero) {
        String url = BASE_URL + "sendsms";

        try{
            AndroidNetworking.get(url)
                    .addQueryParameter("numero", numero)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            //if (response.isSuccess()) {
                            //    if(response.getResult().equals("1")) return true;
                            //}
                        }
                        @Override
                        public void onError(ANError error) {
                            // handle error
                        }
                    });
        } catch (Exception ex) {

        }

        return true;
    }

    public static void uploadImageAsync(File image, final LoadImageListener loadImageListener) {
        String url = BASE_URL + "uploadimage?";
        String TAG = "uploadimage";

        try{

            AndroidNetworking.upload(url)
                    .addMultipartFile("image",image)
                    //.addMultipartParameter("key","value")
                    .setPriority(Priority.HIGH)
                    .build()
                    .setUploadProgressListener(new UploadProgressListener() {
                        @Override
                        public void onProgress(long bytesUploaded, long totalBytes) {
                            if(loadImageListener!=null) loadImageListener.OnProgress(bytesUploaded, totalBytes);
                        }
                    })
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            long id = -1;
                            String url = "";
                            try {
                                id=response.getLong("id");
                            }catch (Exception ex) {

                            }
                            try {
                                url=response.getString("url");
                            }catch (Exception ex) {

                            }
                            if(loadImageListener!=null) loadImageListener.OnResult(id, url);
                        }
                        @Override
                        public void onError(ANError error) {
                            if(loadImageListener!=null) loadImageListener.OnError(error.getMessage());
                        }
                    });

        } catch (Exception ex) {

        }

    }

    public static String uploadImage(File image, final LoadImageListener loadImageListener) {
        String url = BASE_URL + "uploadimage?";

        try{
            ANRequest request = AndroidNetworking.upload(url)
                    .addMultipartFile("image",image)
                    //.addMultipartParameter("key","value")
                    .setPriority(Priority.HIGH)
                    .build()
                    .setUploadProgressListener(new UploadProgressListener() {
                        @Override
                        public void onProgress(long bytesUploaded, long totalBytes) {
                            if(loadImageListener!=null) loadImageListener.OnProgress(bytesUploaded, totalBytes);
                        }
                    });

            ANResponse<JSONObject> response = request.executeForJSONObject();
            if (response.isSuccess()) {
                if(response.getResult().optBoolean("error")) {
                    return "error:" + response.getResult().optString("errorMessage");
                } else {
                    return response.getResult().optString("url");
                }
            } else {
                return "error:" + response.getError().getMessage();
            }

        } catch (Exception ex) {
            return "error:" + ex.getMessage();
        }

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

    public static List<Categorie> getListCategorie () {
        String url = BASE_URL2 + "category/";

        List<Categorie> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .build();

        try{
            ANResponse<List<Categorie>> response = request.executeForObjectList(Categorie.class);
            if (response.isSuccess()) {
                list = response.getResult();
                CategorieDAO cdao = new CategorieDAO(GeneralClass.applicationContext);
                for (Categorie object : list) {
                    cdao.ajouter(object);
                    Log.e("Category", "designation : " + object.designation);
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

    public static List<SousCategorie> getListSousCategorie () {
        String url = BASE_URL2 + "undercat/";

        List<SousCategorie> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .build();

        try{
            ANResponse<List<SousCategorie>> response = request.executeForObjectList(SousCategorie.class);
            if (response.isSuccess()) {
                list = response.getResult();
                SousCategorieDAO cdao = new SousCategorieDAO(GeneralClass.applicationContext);
                for (SousCategorie object : list) {
                    cdao.ajouter(object);
                    Log.e("Under Category", "designation : " + object.designation);
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

    public static User login(String auth_code, String password) {
        String url = BASE_URL + "login";

        User user;

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("auth_code", auth_code)
                .addQueryParameter("password", password)
                .build();

        try{
            ANResponse<User> response = request.executeForObject(User.class);
            if (response.isSuccess()) {
                user = response.getResult();
                if(user!=null) {
                    if(!user.error) {
                        user.myProfil = true;
                        UserDAO userDAO = new UserDAO(GeneralClass.applicationContext);
                        user = userDAO.ajouter(user);
                        if(user==null) {
                            user = new User();
                            user.setError(true);
                            user.setErrorCode(7127);
                            user.setErrorMessage("Une erreur est survenue lors de l'enregistrement de vos informations. Veuillez SVP vous reconnecter.");
                        }
                    }
                }
            } else {
                user = new User();
                user.error = true;
                user.errorCode = 3188;
                user.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            user = new User();
            user.error = true;
            user.errorCode = 3198;
            user.errorMessage = ex.getMessage();
        }

        return user;
        //return new User();
    }

    public static List<Annonce> getAnnonces (int next, int souscategorie) {
        String url = BASE_URL2 + "annonces/";

        List<Annonce> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("next", String.valueOf(next))
                .addQueryParameter("sous_categorie", String.valueOf(souscategorie))
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Annonce>> response = request.executeForObjectList(Annonce.class);
            if (response.isSuccess()) {
                list = response.getResult();
                AnnonceDAO cdao = new AnnonceDAO(GeneralClass.applicationContext);
                for (Annonce object : list) {
                    cdao.ajouter(object);
                    Log.e("Annonce", "designation : " + object.description);
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

    public static List<Annonce> getMesAnnonces () {
        String url = BASE_URL2 + "mesannonces/";

        List<Annonce> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Annonce>> response = request.executeForObjectList(Annonce.class);
            if (response.isSuccess()) {
                list = response.getResult();
                AnnonceDAO cdao = new AnnonceDAO(GeneralClass.applicationContext);
                for (Annonce object : list) {
                    cdao.ajouter(object);
                    Log.e("MesAnnonce", "description : " + object.description);
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

    public static List<Service> getMesServices () {
        String url = BASE_URL2 + "messervices/";

        List<Service> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Service>> response = request.executeForObjectList(Service.class);
            if (response.isSuccess()) {
                list = response.getResult();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    cdao.ajouter(object);
                    Log.e("MesService", "description : " + object.description);
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

    public static List<Service> getNewServices (int next, int souscategorie) {
        String url = BASE_URL2 + "newservices/";

        List<Service> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("next", String.valueOf(next))
                .addQueryParameter("sous_categorie", String.valueOf(souscategorie))
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Service>> response = request.executeForObjectList(Service.class);
            if (response.isSuccess()) {
                list = response.getResult();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    cdao.ajouter(object);
                    Log.e("NewService", "description : " + object.description);
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

    public static List<Service> getRandomServices (int next) {
        String url = BASE_URL2 + "randomservices/";

        List<Service> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("next", String.valueOf(next))
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Service>> response = request.executeForObjectList(Service.class);
            if (response.isSuccess()) {
                list = response.getResult();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    cdao.ajouter(object);
                    Log.e("RandomService", "description : " + object.description);
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

    public static List<Service> getServicesByRealisationCount (int next, int souscategorie) {
        String url = BASE_URL2 + "servicesbyrealisationcount/";

        List<Service> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("next", String.valueOf(next))
                .addQueryParameter("sous_categorie", String.valueOf(souscategorie))
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Service>> response = request.executeForObjectList(Service.class);
            if (response.isSuccess()) {
                list = response.getResult();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    cdao.ajouter(object);
                    Log.e("NewService", "description : " + object.description);
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

    public static List<Service> getServicesByLevel (int next, int souscategorie) {
        String url = BASE_URL2 + "servicesbylevel/";

        List<Service> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("next", String.valueOf(next))
                .addQueryParameter("sous_categorie", String.valueOf(souscategorie))
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Service>> response = request.executeForObjectList(Service.class);
            if (response.isSuccess()) {
                list = response.getResult();
                ServiceDAO cdao = new ServiceDAO(GeneralClass.applicationContext);
                for (Service object : list) {
                    cdao.ajouter(object);
                    Log.e("NewService", "description : " + object.description);
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

    public static List<Postuler> getPostulants (long idAnnonce) {
        String url = BASE_URL2 + "postulant/";

        List<Postuler> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("idAnnonce", String.valueOf(idAnnonce))
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Postuler>> response = request.executeForObjectList(Postuler.class);
            if (response.isSuccess()) {
                list = response.getResult();
                PostulerDAO cdao = new PostulerDAO(GeneralClass.applicationContext);
                for (Postuler object : list) {
                    cdao.ajouter(object);
                    Log.e("Postuler", "description : " + object.descriptionAnnonce);
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
    * Les annonces auxquels moi j'ai postulé
    */
    public static List<Postuler> getMesPostulations () {
        String url = BASE_URL2 + "maspostulation/";

        List<Postuler> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Postuler>> response = request.executeForObjectList(Postuler.class);
            if (response.isSuccess()) {
                list = response.getResult();
                PostulerDAO cdao = new PostulerDAO(GeneralClass.applicationContext);
                for (Postuler object : list) {
                    object.setHavePostuled(true);
                    cdao.ajouter(object);
                    Log.e("Postuler", "description : " + object.descriptionAnnonce);
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
    public static List<Sollicitation> getSollicitations (long idService) {
        String url = BASE_URL2 + "sollicitation/";

        List<Sollicitation> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addQueryParameter("isService", String.valueOf(idService))
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Sollicitation>> response = request.executeForObjectList(Sollicitation.class);
            if (response.isSuccess()) {
                list = response.getResult();
                SollicitationDAO cdao = new SollicitationDAO(GeneralClass.applicationContext);
                for (Sollicitation object : list) {
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.descriptionService);
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
     * Les annonces auxquels moi j'ai sollicité
     */
    public static List<Sollicitation> getMesSollicitations () {
        String url = BASE_URL2 + "massollicitation/";

        List<Sollicitation> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<List<Sollicitation>> response = request.executeForObjectList(Sollicitation.class);
            if (response.isSuccess()) {
                list = response.getResult();
                SollicitationDAO cdao = new SollicitationDAO(GeneralClass.applicationContext);
                for (Sollicitation object : list) {
                    object.setHaveSollicited(true);
                    cdao.ajouter(object);
                    Log.e("Sollicitation", "description : " + object.descriptionService);
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


    /**

               POST METHODS

     */

    public static User createUser (User user) {
        String url = BASE_URL + "createuser";

        ANRequest request = AndroidNetworking.post(url)
                .addBodyParameter(user) // posting java object
                .setTag("user" + user.prenom + user.nom)
                .setPriority(Priority.MEDIUM)
                .build();

        try{
            ANResponse<User> response = request.executeForObject(User.class);
            if (response.isSuccess()) {
                user = response.getResult();
                if(user!=null) {
                    if(!user.isError()) {
                        user.myProfil = true;
                        UserDAO userDAO = new UserDAO(GeneralClass.applicationContext);
                        user = userDAO.ajouter(user);
                        if(user==null) {
                            user = new User();
                            user.setError(true);
                            user.setErrorCode(7127);
                            user.setErrorMessage("Une erreur est survenue lors de l'enregistrement de vos informations. Veuillez SVP vous connecter avec vos nouveax identifiants");
                        }
                    }
                }
            } else {
                user = new User();
                user.error = true;
                user.errorCode = 319288;
                user.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            user = new User();
            user.error = true;
            user.errorCode = 319288;
            user.errorMessage = ex.getMessage();
        }

        return user;
        //return new User();
    }

    public static Postuler postuler (Postuler postuler) {
        String url = BASE_URL + "postuler";

        ANRequest request = AndroidNetworking.post(url)
                .addBodyParameter(postuler) // posting java object
                .setTag("postuler" + postuler.phoneUser)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
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
                postuler.error = true;
                postuler.errorCode = 31921;
                postuler.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            postuler = new Postuler();
            postuler.error = true;
            postuler.errorCode = 49288;
            postuler.errorMessage = ex.getMessage();
        }

        return postuler;
    }

    public static Sollicitation solliciter (Sollicitation sollicitation) {
        String url = BASE_URL + "solliciter";

        ANRequest request = AndroidNetworking.post(url)
                .addBodyParameter(sollicitation) // posting java object
                .setTag("sollicitation" + sollicitation.phoneUser)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
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
                sollicitation.error = true;
                sollicitation.errorCode = 31921;
                sollicitation.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            sollicitation = new Sollicitation();
            sollicitation.error = true;
            sollicitation.errorCode = 49288;
            sollicitation.errorMessage = ex.getMessage();
        }

        return sollicitation;
    }

    public static Annonce publierAnnonce (Annonce object) {
        String url = BASE_URL + "annonce";

        ANRequest request = AndroidNetworking.post(url)
                .addBodyParameter(object) // posting java object
                .setTag("publierannonce" + object.phoneUser)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
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
                object.error = true;
                object.errorCode = 31921;
                object.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            object = new Annonce();
            object.error = true;
            object.errorCode = 49288;
            object.errorMessage = ex.getMessage();
        }

        return object;
    }

    public static Service publierService (Service object) {
        String url = BASE_URL + "service";

        ANRequest request = AndroidNetworking.post(url)
                .addBodyParameter(object) // posting java object
                .setTag("publierservice" + object.getNomsJobeur())
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
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
                            object.setErrorMessage("Une erreur est survenue lors de la publication de votre service.");
                        }
                    }
                }
            } else {
                object = new Service();
                object.error = true;
                object.errorCode = 31921;
                object.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            object = new Service();
            object.error = true;
            object.errorCode = 49288;
            object.errorMessage = ex.getMessage();
        }

        return object;
    }

    public static Comment commenter (Comment object) {
        String url = BASE_URL + "commenter";

        ANRequest request = AndroidNetworking.post(url)
                .addBodyParameter(object) // posting java object
                .setTag("commenter" + object.getIdUserConcerne())
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
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
                object.error = true;
                object.errorCode = 31921;
                object.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            object = new Comment();
            object.error = true;
            object.errorCode = 49288;
            object.errorMessage = ex.getMessage();
        }

        return object;
    }

    public static Postuler creerRDVbyPostuler (long isPostuler, String date, String heure) {
        String url = BASE_URL + "creerrdvbypostuler";

        ANRequest request = AndroidNetworking.post(url)
                .addQueryParameter("id", String.valueOf(isPostuler))
                .addQueryParameter("datePublication", date)
                .addQueryParameter("heure", heure)
                .setTag("creerRDVByPostuler" + isPostuler)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        Postuler postuler;

        try{
            ANResponse<Postuler> response = request.executeForObject(Postuler.class);
            if (response.isSuccess()) {
                postuler = response.getResult();
                if(postuler!=null) {
                    if(!postuler.isError()) {
                        PostulerDAO postulerDAO = new PostulerDAO(GeneralClass.applicationContext);
                        postuler = postulerDAO.ajouter(postuler);
                        if(postuler==null) {
                            postuler = new Postuler();
                            postuler.setError(true);
                            postuler.setErrorCode(1127);
                            postuler.setErrorMessage("Une erreur est survenue lors de la création du rendez-vous.");
                        }
                    }
                }
            } else {
                postuler = new Postuler();
                postuler.error = true;
                postuler.errorCode = 31921;
                postuler.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            postuler = new Postuler();
            postuler.error = true;
            postuler.errorCode = 49288;
            postuler.errorMessage = ex.getMessage();
        }

        return postuler;
    }

    public static Sollicitation creerRDVbySolliciter (long idSolliciter, String date, String heure) {
        String url = BASE_URL + "creerrdvbysolliciter";

        ANRequest request = AndroidNetworking.post(url)
                .addQueryParameter("idsolliciter", String.valueOf(idSolliciter))
                .addQueryParameter("datePublication", date)
                .addQueryParameter("heure", heure)
                .setTag("creerRDVBySollicitation" + idSolliciter)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        Sollicitation sollicitation;

        try{
            ANResponse<Sollicitation> response = request.executeForObject(Sollicitation.class);
            if (response.isSuccess()) {
                sollicitation = response.getResult();
                if(sollicitation!=null) {
                    if(!sollicitation.isError()) {
                        SollicitationDAO sollicitationDAO = new SollicitationDAO(GeneralClass.applicationContext);
                        sollicitation = sollicitationDAO.ajouter(sollicitation);
                        if(sollicitation==null) {
                            sollicitation = new Sollicitation();
                            sollicitation.setError(true);
                            sollicitation.setErrorCode(1127);
                            sollicitation.setErrorMessage("Une erreur est survenue lors de la création de votre rendez-vous.");
                        }
                    }
                }
            } else {
                sollicitation = new Sollicitation();
                sollicitation.error = true;
                sollicitation.errorCode = 31921;
                sollicitation.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            sollicitation = new Sollicitation();
            sollicitation.error = true;
            sollicitation.errorCode = 49288;
            sollicitation.errorMessage = ex.getMessage();
        }

        return sollicitation;
    }

    public static Postuler confirmationRDVbyUser (long isPostuler, String phoneMaisha, String passwordMaisha) {
        String url = BASE_URL + "confirmationrdvbyuser";

        ANRequest request = AndroidNetworking.post(url)
                .addQueryParameter("idPostuler", String.valueOf(isPostuler))
                .addQueryParameter("phonemaisha", phoneMaisha)
                .addQueryParameter("passwordmaisha", passwordMaisha)
                .setTag("confirmationRDVbyUser" + isPostuler)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        Postuler postuler;

        try{
            ANResponse<Postuler> response = request.executeForObject(Postuler.class);
            if (response.isSuccess()) {
                postuler = response.getResult();
                if(postuler!=null) {
                    if(!postuler.isError()) {
                        PostulerDAO postulerDAO = new PostulerDAO(GeneralClass.applicationContext);
                        postuler = postulerDAO.ajouter(postuler);
                        if(postuler==null) {
                            postuler = new Postuler();
                            postuler.setError(true);
                            postuler.setErrorCode(1127);
                            postuler.setErrorMessage("Une erreur est survenue lors de la confirmation de votre rendez-vous.");
                        }
                    }
                }
            } else {
                postuler = new Postuler();
                postuler.error = true;
                postuler.errorCode = 31921;
                postuler.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            postuler = new Postuler();
            postuler.error = true;
            postuler.errorCode = 49288;
            postuler.errorMessage = ex.getMessage();
        }

        return postuler;
    }

    public static Sollicitation confirmationRDVbyJobeur (long idSollicitation) {
        String url = BASE_URL + "confirmationrdvbyjobeur";

        ANRequest request = AndroidNetworking.post(url)
                .addQueryParameter("idSollicitation", String.valueOf(idSollicitation))
                .setTag("confirmationRDVbyJobeur" + idSollicitation)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        Sollicitation sollicitation;

        try{
            ANResponse<Sollicitation> response = request.executeForObject(Sollicitation.class);
            if (response.isSuccess()) {
                sollicitation = response.getResult();
                if(sollicitation!=null) {
                    if(!sollicitation.isError()) {
                        SollicitationDAO objectDAO = new SollicitationDAO(GeneralClass.applicationContext);
                        sollicitation = objectDAO.ajouter(sollicitation);
                        if(sollicitation==null) {
                            sollicitation = new Sollicitation();
                            sollicitation.setError(true);
                            sollicitation.setErrorCode(1127);
                            sollicitation.setErrorMessage("Une erreur est survenue lors de la confirmation de votre rendez-vous.");
                        }
                    }
                }
            } else {
                sollicitation = new Sollicitation();
                sollicitation.error = true;
                sollicitation.errorCode = 31921;
                sollicitation.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            sollicitation = new Sollicitation();
            sollicitation.error = true;
            sollicitation.errorCode = 49288;
            sollicitation.errorMessage = ex.getMessage();
        }

        return sollicitation;
    }

    public static boolean confirmationDuServiceRendu (String code, int cote, String comment) {
        String url = BASE_URL + "confirmationservice";

        ANRequest request = AndroidNetworking.post(url)
                .addQueryParameter("code", code)
                .addBodyParameter("cote", String.valueOf(cote))
                .addBodyParameter("comment", comment)
                .setTag("confirmationDuServiceRendu" + code)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) if(response.getResult().equals("1")) return true;
        } catch (Exception ex) {

        }

        return false;
    }

    public static boolean signalerUtilisateur (long idUtilisateur, String comment, int niveauDanger) {
        String url = BASE_URL + "signaler";

        ANRequest request = AndroidNetworking.post(url)
                .addQueryParameter("idUtilisateur", String.valueOf(idUtilisateur))
                .addBodyParameter("comment", comment)
                .addBodyParameter("niveau", String.valueOf(niveauDanger))
                .setTag("signalerUtilisateur" + idUtilisateur)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
            ANResponse<Sollicitation> response = request.executeForObject(Sollicitation.class);
            if (response.isSuccess()) if(response.getResult().equals("1")) return true;
        } catch (Exception ex) {

        }

        return false;
    }



    /**

     POST METHODS

     */

    public static User updateUser (User user) {
        String url = BASE_URL + "updateuser";

        ANRequest request = AndroidNetworking.put(url)
                .addBodyParameter(user)
                .addHeaders("token", GeneralClass.userToken)
                .setTag("user" + user.id)
                .setPriority(Priority.MEDIUM)
                .build();

        try{
            ANResponse<User> response = request.executeForObject(User.class);
            if (response.isSuccess()) {
                user = response.getResult();
                if(user!=null) {
                    if(!user.isError()) {
                        UserDAO userDAO = new UserDAO(GeneralClass.applicationContext);
                        user = userDAO.ajouter(user);
                        if(user==null) {
                            user = new User();
                            user.setError(true);
                            user.setErrorCode(7127);
                            user.setErrorMessage("Une erreur est survenue lors de l'enregistrement de vos informations. Veuillez SVP vous connecter avec vos nouveax identifiants");
                        }
                    }
                }
            } else {
                user = new User();
                user.error = true;
                user.errorCode = 319288;
                user.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            user = new User();
            user.error = true;
            user.errorCode = 319288;
            user.errorMessage = ex.getMessage();
        }

        return user;
        //return new User();
    }

    public static Annonce updateAnnonce (Annonce object) {
        String url = BASE_URL + "annonce";

        ANRequest request = AndroidNetworking.put(url)
                .addBodyParameter(object)
                .setTag("updateannonce" + object.phoneUser)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
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
                object.error = true;
                object.errorCode = 31921;
                object.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            object = new Annonce();
            object.error = true;
            object.errorCode = 49288;
            object.errorMessage = ex.getMessage();
        }

        return object;
    }

    public static Service updateService (Service object) {
        String url = BASE_URL + "service";

        ANRequest request = AndroidNetworking.put(url)
                .addBodyParameter(object)
                .setTag("updateservice" + object.getNomsJobeur())
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{
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
                object.error = true;
                object.errorCode = 31921;
                object.errorMessage = response.getError().getMessage();
            }
        } catch (Exception ex) {
            object = new Service();
            object.error = true;
            object.errorCode = 49288;
            object.errorMessage = ex.getMessage();
        }

        return object;
    }


    /**

     DELETE METHODS

     */

    public static boolean deleteUser () {
        String url = BASE_URL + "user";

        ANRequest request = AndroidNetworking.delete(url)
                .addHeaders("token", GeneralClass.userToken)
                .setTag("deleteuser" + GeneralClass.userToken)
                .setPriority(Priority.MEDIUM)
                .build();

        try{
            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) if(response.getResult().equals("1")) return true;
        } catch (Exception ex) {

        }

        return false;
    }

    public static boolean deleteAnnonce (long idAnnonce) {
        String url = BASE_URL + "annonce";

        ANRequest request = AndroidNetworking.delete(url)
                .addQueryParameter("idAnnonce", String.valueOf(idAnnonce))
                .setTag("deleteannonce" + idAnnonce)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{

            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) if(response.getResult().equals("1")) return true;
        } catch (Exception ex) {

        }

        return false;
    }

    public static boolean deleteService (long idService) {
        String url = BASE_URL + "service";

        ANRequest request = AndroidNetworking.delete(url)
                .addQueryParameter("IdService", String.valueOf(idService))
                .setTag("deleteservice" + idService)
                .setPriority(Priority.MEDIUM)
                .addHeaders("token", GeneralClass.userToken)
                .build();

        try{

            ANResponse<String> response = request.executeForString();
            if (response.isSuccess()) if(response.getResult().equals("1")) return true;
        } catch (Exception ex) {

        }

        return false;
    }

    /**

        LOAD DATA TEST

    **/

    public static List<User> getRandomUser () {
        //String url = "https://randomuser.me/api/?results=100";
//
        //List<User> userList = new ArrayList<>();
//
        //ANRequest request = AndroidNetworking.get(url)
        //        .build();
//
        //try{
        //    ANResponse<RandomUser> response = request.executeForObject(RandomUser.class);
        //    if (response.isSuccess()) {
        //        Log.e("RandomUser", String.valueOf(response.getResult().results.size()));
        //        int i = 1;
        //        for (User2 user2 : response.getResult().results) {
        //            User user = new User();
        //            user.prenom = user2.name.first.substring(0, 1).toUpperCase() + user2.name.first.substring(1);
        //            user.nom = user2.name.first.substring(0, 1).toUpperCase() + user2.name.first.substring(1);
        //            user.remoteId = i;
        //            user.urlPhoto = user2.picture.thumbnail;
        //            user.type = new Random().nextInt(2);
        //            user.phone = 890000000 + new Random().nextInt(899999999 - 890000000);
        //            user.codePays = "+243";
        //            user.pays = "Congo DR";
        //            user.about = getRandomParagraphe(new Random().nextInt(3) + 1);
        //            user.email = user2.email;
        //            user.sexe = "M";
        //            if(user2.gender.equals("female")) user.sexe = "F";
        //            user.save();
        //            userList.add(user);
        //            Log.e("RandomUser", user2.name.first + " " + user2.name.last);
        //        }
        //    } else {
        //        ANError error = response.getError();
        //        Log.e("RandomUser", error.getMessage());
        //    }
        //} catch (Exception ex) {
        //    Log.e("RandomUser", ex.getMessage());
        //}
//
        //return userList;
        return new ArrayList<>();
    }

    public static String getRandomParagraphe (int nombrePhrase) {
        String url = "https://baconipsum.com/api/?type=all-meat&sentences=" + String.valueOf(nombrePhrase) +"&start-with-lorem=0";

        ANRequest request = AndroidNetworking.get(url)
                .build();

        try{
            ANResponse<String> response = request.executeForString();
            long i = 0;
            if (response.isSuccess()) {
                String rep = response.getResult();
                Log.e("RandomUser", rep);
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

    public interface LoadImageListener {
        void OnResult(long id, String url);
        void OnProgress(long bytesUploaded, long totalBytes);
        void OnError(String message);
    }

}
