package cd.maichapayteam.zuajob.Front_end.Signup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Front_end.Home;
import cd.maichapayteam.zuajob.R;

public class Preferences_screen extends AppCompatActivity {


    Context context = this;
    ImageView btn_back_arrow;
    TextView btn_next;

    GridView list_item;
    GridView list_item_selected;


    ArrayList<String> l = new ArrayList<>();
    ArrayList<String> l2 = new ArrayList<>();


    private void Init_Components(){
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
        btn_next = findViewById(R.id.btn_next);
        list_item = findViewById(R.id.list_item);
        list_item_selected = findViewById(R.id.list_item_selected);

        for (int i = 0; i < 10; i++) {
            l.add("Cat "+i);
        }

        ArrayAdapter a = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l);
        list_item.setAdapter(a);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // Todo ; Initialisation des composants
        Init_Components();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                l2.add(l.get(position));
                l.remove(position);
                ArrayAdapter a1 = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l);
                ArrayAdapter a2 = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l2);
                list_item.setAdapter(a1);
                list_item_selected.setAdapter(a2);

            }
        });
        list_item_selected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                l.add(l2.get(position));
                l2.remove(position);
                ArrayAdapter a1 = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l);
                ArrayAdapter a2 = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l2);
                list_item.setAdapter(a1);
                list_item_selected.setAdapter(a2);

            }
        });


        btn_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Home.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Password_screen.class);
        startActivity(i);
        finish();
    }

}
