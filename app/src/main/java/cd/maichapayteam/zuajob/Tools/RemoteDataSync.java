package cd.maichapayteam.zuajob.Tools;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.gson.JsonArray;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.Pays;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.Models.Object.Ville;
import okhttp3.Response;

public class RemoteDataSync {

    private static String BASE_URL = "http://192.168.43.60/doxa_event_server/v1/";

    public static boolean confirmCode(String numero, String code) {
        String url = BASE_URL + "confirmenumero?numero=" + numero + "&code=" + code;
        String TAG = "confirmenumero";

        ANRequest request = AndroidNetworking.get(url)
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
        String url = BASE_URL + "checknumero?phone=" + numero;
        String TAG = "checknumero";

        ANRequest request = AndroidNetworking.get(url)
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
        String url = BASE_URL + "sendsms?numero=" + numero;
        String TAG = "checknumero";

        ANRequest request = AndroidNetworking.get(url)
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

    public static List<Pays> getListPays () {
        String url = "https://restcountries.eu/rest/v2/all";
        String TAG = "getListPays";

        List<Pays> list = new ArrayList<>();

        ANRequest request = AndroidNetworking.get(url)
                .build();

        try{
            ANResponse<List<Pays>> response = request.executeForObjectList(Pays.class);
            long i = 0;
            if (response.isSuccess()) {
                List<Pays> list2 = response.getResult();
                Log.e(TAG, "paysList size : " + list.size());
                for (Pays object : list2) {
                    if(i>10) break;
                    i = i++;
                    object.remoteId = i;
                    list.add(object);
                    Log.e(TAG, "designation : " + object.designation);
                    Log.e(TAG, "url flag : " + object.urlFlag);
                    Log.e(TAG, "list code : " + object.listCode.size());
                }
            } else {
                ANError error = response.getError();
                Log.e(TAG + ":error", error.getMessage());
            }
        } catch (Exception ex) {
            Log.e(TAG + ":errorLocal", ex.getMessage());
        }

        return list;
    }

    public static List<Ville> getListVille () {
        //String url = BASE_URL + "listville";
        //String TAG = "getListVille";
//
        List<Ville> list = new ArrayList<>();
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
        return list;
    }

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
                    if(!user.error) {
                        user.myProfil = true;
                        user.save();
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
    }

    public static User login(String auth_code, String password) {
        String url = BASE_URL + "login?auth_code=" + auth_code + "&password=" + password;
        String TAG = "login";

        User user;

        ANRequest request = AndroidNetworking.get(url)
                .build();

        try{
            ANResponse<User> response = request.executeForObject(User.class);
            if (response.isSuccess()) {
                user = response.getResult();
                if(user!=null) {
                    if(!user.error) {
                        user.myProfil = true;
                        user.save();
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
    }

    public interface LoadImageListener {
        void OnResult(long id, String url);
        void OnProgress(long bytesUploaded, long totalBytes);
        void OnError(String message);
    }

}
