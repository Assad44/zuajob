package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.R;

public class Webpaiemnt extends AppCompatActivity {

    Context context = this;
    String HTML = "";
    WebView mainWebView;
    SwipeRefreshLayout swiper;
    ProgressBar progressbar;
    int exit = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpaiemnt);


        INIT_Componants();
        
        if (!getIntent().hasExtra("HTML")){
            Toast.makeText(context, "No HTM", Toast.LENGTH_SHORT).show();
            return;
        }
        HTML = getIntent().getExtras().getString("HTML");
        Log.e("EEEEEEEEE",HTML);

        Setting_Up();
    }

    void INIT_Componants(){
        mainWebView = findViewById(R.id.mainWebView);
        swiper = findViewById(R.id.swiper);
        progressbar = findViewById(R.id.progressbar);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.webpaiement, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.close_activity) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainWebView.reload();
                swiper.setRefreshing(false);
            }
        });
    }

    private class SSLTolerentWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //super.onPageStarted(view, url, favicon);
            progressbar.setVisibility(View.VISIBLE);
            //swiper.setRefreshing(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //super.onPageFinished(view, url);
            progressbar.setVisibility(View.GONE);

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            //super.onReceivedError(view, errorCode, description, failingUrl);
            if(errorCode == WebViewClient.ERROR_FILE_NOT_FOUND){
                // your code
            }
        }

    }

    void Setting_Up(){
        swiper.setRefreshing(true);
        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.setWebViewClient(new WebViewClient());
        mainWebView.setHorizontalScrollBarEnabled(false);
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mainWebView.loadDataWithBaseURL(null,HTML,"text/html","utf-8",null);
        mainWebView.setWebViewClient(
                new SSLTolerentWebViewClient()
        );
        swiper.setRefreshing(false);

    }

    @Override
    public void onBackPressed() {
        if (mainWebView.canGoBack() ==  false){
            startActivity(new Intent(context, Home.class));
            finish();
        }else mainWebView.goBack();
    }

}
