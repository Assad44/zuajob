package cd.maichapayteam.zuajob.Tools;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.bitmap.Transform;
import com.koushikdutta.ion.builder.AnimateGifMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import cd.maichapayteam.zuajob.Front_end.Notification_result_classes.BrodcastLaunchService;
import cd.maichapayteam.zuajob.R;
import pl.droidsonroids.gif.GifDrawable;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/*import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;*/

public class Tool {


    /**
     * Methode pour mettre des espaces dans un nombre
     * @param number
     * @return
     */
    public static String  NUMBER_FORMAT(String number){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        DecimalFormat format = new DecimalFormat("#,###", symbols);
        return format.format(Integer.parseInt(number));
    }

    public static String[] Versions(){
        return new String[]{
                "PETIT FOUR",
                "CUPCAKE",
                "DONUT",
                "ECLAIR",
                "FROYO",
                "GINGERBREAD",
                "HONEYCOMB",
                "ICE CREAM SANDWICH",
                "JELLY BEAN",
                "KITKAT",
                "LOLLIPOP",
                "MARSHMALLOW",
                "NOUGAT",
                "OREO",
        };
    }

    // TODO LOAD IMAGES
    public static void Load_Image(Context context, RoundedImageView imageView, String url){
        try {
            GifDrawable gifFromResource = new GifDrawable( context.getResources(), R.drawable.gif4);
            Ion.with(imageView)
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.ic_alarm_blue)
                    //.animateGif(AnimateGifMode.ANIMATE)
                    .load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Load_Image2(Context context, ImageView imageView, String url){
        try {
            GifDrawable gifFromResource = new GifDrawable( context.getResources(), R.drawable.gif4);
            Ion.with(imageView)
                    .error(R.drawable.avatar_vested)
                    .placeholder(R.drawable.avatar)
                    .transform(new Transform() {
                        @Override
                        public Bitmap transform(Bitmap b) {
                            return createCircleBitmap(b);
                        }

                        @Override
                        public String key() {
                            return null;
                        }
                    })
                    .load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap createCircleBitmap(Bitmap bitmap){
        //设置一个与位图同样大小的新位图
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        //设置一个图片大小的矩形
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        int halfWidth = bitmap.getWidth()/2;
        int halfHeight = bitmap.getHeight()/2;
        canvas.drawCircle(halfWidth, halfHeight, Math.max(halfWidth, halfHeight), paint);
        //设置为取两层图像交集部门,只显示上层图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //画图像
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static void Load_Image(Context context, ImageView imageView, String url){
        try {
            GifDrawable gifFromResource = new GifDrawable( context.getResources(), R.drawable.gif4);
            Ion.with(imageView)
                    .placeholder(gifFromResource)
                    .error(R.drawable.avatar_error)
                    .animateGif(AnimateGifMode.ANIMATE)
                    .load("http://hotprintdesign.com/wp-content/uploads/2019/02/no-profile-photo.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SharedPreferences User_Preferences(Context context){
        return context.getSharedPreferences("User_Identities", MODE_PRIVATE);
    }

    public static void userPreferences_Init(Context context){
        Tool.setUserPreferences(context,"Firstuse","null");
        Tool.setUserPreferences(context,"phone","null");
        Tool.setUserPreferences(context,"CountryCode","null");
        Tool.setUserPreferences(context,"CountryName","null");
        Tool.setUserPreferences(context,"phoneCode","null");
        Tool.setUserPreferences(context,"nom","null");
        Tool.setUserPreferences(context,"prenom","null");
        Tool.setUserPreferences(context,"birthday","null");
        Tool.setUserPreferences(context,"sexe","null");
        Tool.setUserPreferences(context,"passe","null");
        Tool.setUserPreferences(context,"type", "null");
        Tool.setUserPreferences(context,"statut", "null");
    }

    public static void setUserPreferences(Context context, String key, String values){
        try {
            SharedPreferences.Editor editor = User_Preferences(context).edit();
            editor.putString(key, values);
            editor.apply();
            Toast.makeText(context, key+" = "+values, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("TAG_PREFERENCES", e.getMessage());
            Toast.makeText(context, key+" error", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getUserPreferences(Context context, String key ){
        SharedPreferences mshPreferences = User_Preferences(context);
        return mshPreferences.getString(key,"");
    }


    public static void setEntries(Context context ,Spinner spinner, ArrayList<String> DATA){
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, DATA);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
    }

    //TODO : DATE & TIME

    /**
     * Methode pour la current Date avec mle format ''dd/MM/yyyy''
     * @return
     */
    public static String Today() {
        Date aujourdhui = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(aujourdhui);
    }
    public static String Today2() {
        Date aujourdhui = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(aujourdhui);
    }
    /**
     * Méthode pour afficher le Date picker pour selectionner une datePublication
     * @param context
     * @param start_date
     * @return
     */
    public static String Date_Picker(final Context context, final EditText start_date){

        final Calendar c = Calendar.getInstance();
        final int mYear, mMonth, mDay;

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final String[] Date = {""};

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String m = String.valueOf(monthOfYear + 1);
                if (m.length()<= 1){
                    m = "0"+m;
                }
                String d = String.valueOf(dayOfMonth);
                if (d.length()<= 1){
                    d = "0"+d;
                }
                String date = d+ "-" + m + "-" +year ;
                Date[0] = date;
                Log.i("TTTTTTTTTTDate", Date[0]);
                start_date.setText(date);

            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();
        Log.i("TTTTTTTTTT", Date[0]);

        return start_date.getText().toString();
    }

    public static boolean Expired(String end) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = new Date(sdf1.parse(end).getTime());
        Date d2 = new Date(sdf1.parse(sdf1.format(new Date())).getTime());

        Log.i("SSSSSS1", sdf1.format(d1));
        Log.i("SSSSSS2", sdf1.format(d2));

        return d1.before(d2);
    }

    public static long[] Time_stay(String end) throws ParseException {

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");


        Date d1 = new Date(sdf1.parse(end).getTime());
        Date d2 = new Date(sdf1.parse(sdf1.format(new Date())).getTime());

        Log.i("VERIFICATION22 ", "PASSED_BEF "+sdf1.format(d1));

        /*GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d1);
        gc.add(GregorianCalendar.DATE, 30);
        d1 = gc.getTime();*/

        Log.i("VERIFICATION22 ", "PASSED "+sdf1.format(d1));
        Log.i("VERIFICATION22 ", "PASSED "+sdf1.format(d2));

        long intervalle = d1.getTime() - d2.getTime();

        long day2 = 0;
        long day = TimeUnit.MILLISECONDS.toDays(intervalle);
        long hours = TimeUnit.MILLISECONDS.toHours(intervalle);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(intervalle) % 60;

        long[] dif = new long[]{
                hours, minutes,
        };

        return dif;
    }

    public static String publication_Time(String start) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");


        Date d1 = new Date(sdf1.parse(start).getTime());
        Date d2 = new Date(sdf1.parse(sdf1.format(new Date())).getTime());

        long intervalle = d2.getTime() - d1.getTime();

        Log.i("PUBLISHING", String.valueOf(intervalle));

        long day2 = 0;
        long day = TimeUnit.MILLISECONDS.toDays(intervalle);
        long hours = TimeUnit.MILLISECONDS.toHours(intervalle);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(intervalle) % 60;


        String dif= "";
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        if (hours >=24 ) {
            day2 = hours / 24;
            hours = hours % 24;

            if (day2 == 1 ) {
                dif = "Hier à "+sdf2.format(d1);
            }else if (day2 > 30 && day2 < 35) {
                dif = "Il y a 1 mois";
            }else if (day2 < 30 ) {
                dif = "Il y a "+ day2 +" Jours";
            }else{
                dif = "Depuis le "+ sdf3.format(d1);
            }

        }else{

            if (hours < 1 ) {
                if (minutes < 3 ) {
                    dif = "A l'instant";
                }else{
                    dif = "Il y a "+ minutes +" min ";
                }
            }else{
                dif = "Il y a "+ hours +" h "+ minutes+" min";
            }

        }


        return dif;
    }

    public static long[] online_Time(String start) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Date d1 = new Date(sdf1.parse(start).getTime());
        Date d2 = new Date(sdf1.parse(sdf1.format(new Date())).getTime());

        long intervalle = d2.getTime() - d1.getTime();

        Log.i("TRAINNING_TODAY", String.valueOf(intervalle));

        long day2 = 0;
        long day = TimeUnit.MILLISECONDS.toDays(intervalle);
        long hours = TimeUnit.MILLISECONDS.toHours(intervalle);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(intervalle) % 60;

        long[] dif = new long[]{
                hours, minutes,
        };

        return dif;
    }

    public static String Add_days(String start, int amount) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = new Date(sdf1.parse(start).getTime());

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d1);
        gc.add(GregorianCalendar.DATE, amount);
        d1 = gc.getTime();

        return sdf1.format(d1);
    }

    public static String formatingDate(String origin_date){

        try {
            long[] date = Tool.online_Time(origin_date);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
            Date d1 = new Date(sdf1.parse(origin_date).getTime());

            String dif= "";
            long day2 = 0;
            long hours = date[0];
            long minutes = date[1];

            if (hours >=24 ) {
                day2 = hours / 24;
                hours = hours % 24;

                if (day2 >= 30 && day2 < 45) {
                    dif = "Il y'a 1 mois";
                }else if (day2 < 30 ) {
                    dif = "Il y a "+ day2 +" Jours";
                }else if (day2 == 1 ) {
                    dif = "Hier à "+sdf2.format(d1);
                }else{
                    dif = "Depuis "+ origin_date;
                }

            }else{

                if (hours < 1 ) {
                    if (minutes < 3 ) {
                        dif = "A l'instant";
                    }else{
                        dif = "Il y a "+ minutes +"min ";
                    }
                }else{
                    dif = "Il y a "+ hours +"h "+ minutes+"min";
                }

            }
            return dif;

        } catch (ParseException e) {
            e.printStackTrace();
            Log.i("ERRRRRRRRR", e.getMessage());
            return "";
        }

    }


    //TODO : MATH & RANDOM
    /**
     * Methode pour avoir le code aleatoire de confirmation
     * @return
     */
    public static int KeyCodeGen(){
        return new Random().nextInt(100000);
    }

    //TODO : SHARING & INTENTS TO OTHER APP
    public static void  SHARE(Context context, String message){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");
        context.startActivity(Intent.createChooser(intent, "Partager le lien de X-resolu via :"));

    }
    public static void  CALL(Context context, String phone){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phone));
        context.startActivity(intent);
    }
    public static void  LAUNCH_WEB_SITE(Context context){
        Intent intent;
        Uri uri = Uri.parse("url");
        intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(Intent.createChooser(intent, "Ouvrir via :"));
    }


    // TODO : ALARME
    public static  void Set_Alarm(Context context){
        Intent intent = new Intent(context, BrodcastLaunchService.class);
        boolean alarmUp = (PendingIntent.getBroadcast(context, 0,
                intent /*new Intent("com.my.package.MY_UNIQUE_ACTION")*/,
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp){
            Log.d("myTag", "Alarm is already active");
            Toast.makeText(context, "Alarm is already active", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Alarm is NOT YET active", Toast.LENGTH_SHORT).show();
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),3600, pendingIntent);
        }
        //Toast.makeText(this, "Alarm set in " + 1 + " seconds",Toast.LENGTH_LONG).show();
    }

    public static void Camera_Picker(int n){
        Intent camera = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);

        //Folder is already created
        String dirName = Environment.getExternalStorageDirectory().getPath()
                + "/MyAppFolder/MyApp" + n + ".png";

        Uri uriSavedImage = Uri.fromFile(new File(dirName));
        camera.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        //startActivityForResult(camera, 1);

        n++;
    }


    //TODO : OTHER
    public static int Savefile(String name, String path) {
        File direct = new File(Environment.getExternalStorageDirectory().toString() + "/Xresolu/profil/");
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/Xresolu/profil/"+name+".jpg");

        if(!direct.exists()) {
            direct.mkdir();
            Log.e("SAVE_IMAGE1", "Director created");
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                Log.e("SAVE_IMAGE2", "file created");
                FileChannel src = new FileInputStream(path).getChannel();
                FileChannel dst = new FileOutputStream(file).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                return 1;
            } catch (IOException e) {
                Log.e("SAVE_IMAGE_ERR", e.getMessage());
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static String Blob_encode(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //compression de l'image
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


}
