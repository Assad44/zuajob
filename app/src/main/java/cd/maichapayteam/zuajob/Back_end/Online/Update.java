package cd.maichapayteam.zuajob.Back_end.Online;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import cd.maichapayteam.zuajob.Back_end.Config;

/**
 * Created by Deon_Mass on 09/07/2018.
 */

public class Update {
    HttpURLConnection httpURLConnection = null;
    InputStream in      = null;
    OutputStream out    = null;
    public final String method = "2";
    public final String auth = Config.auth;

    /**
     * BASIC METHOD
     * Méthode qui se charge d'envoyer les données au serveur
     * @param query
     * @return String
     */
    public String UPDATE_REQUEST(String query){
        String r = "-1";
        try {
            URL url = new URL(Config.SERVER_PATH);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStream out = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));

            String data =
                    URLEncoder.encode( "auth","UTF-8" ) +"="+ URLEncoder.encode(auth,"UTF-8" ) +"&"+
                    URLEncoder.encode( "method","UTF-8" ) +"="+ URLEncoder.encode(method,"UTF-8" ) +"&"+
                    URLEncoder.encode( "query","UTF-8" ) +"="+ URLEncoder.encode(query,"UTF-8" );

            writer.write(data);
            writer.flush();
            writer.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bw = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            // todo : Keeping data in a StringBuffer
            while ((line=bw.readLine()) !=null){
                buffer.append(line);
                Log.i("TAG_UPDATE", " LINE_DATAS "+ line);
            }

            r = buffer.toString();
            Log.i("TAG_UPDATE","reponse du serveur "+ r);
            inputStream.close();
            connection.disconnect();
            r = "1";


        }catch (Exception e){
            e.printStackTrace();
            //Log.e("TAG_UPDATE",e.getMessage());
            r = "-1";
        }
        return r;
    }

    /**
     * ASYNC TASK
     * @param context
     * @param query
     * @return
     */
    public boolean APPLY_UPDATE(final Context context, String query, final ProgressBar p){
        final boolean[] ret = {true};
        AsyncTask<String, Void, String> a = new AsyncTask<String, Void, String>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (p == null) return;
                else p.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(String... strings) {
                return UPDATE_REQUEST(strings[0].toString());
            }
            @Override
            protected void onPostExecute(String o) {
                //super.onPostExecute(o);
                if (p == null) return;
                else p.setVisibility(View.GONE);
                if (o.equals("1")) {
                    ret[0] = true;
                } else {
                    ret[0] = false;
                }
                Log.i("TAG_UPDATE2", o.toString()+" | Return : "+ret[0]);
            }
        }.execute(query);

        return ret[0];
    }

}
