package dilshod.iskandarov.onlinekutubxona;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Reg_Log_welcome extends AppCompatActivity implements View.OnClickListener{

    Button reg,log;


    private FirebaseAuth mAuth;
    private Intent Kirish_Activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg__log_welcome);
        init();
        reg.setOnClickListener((View.OnClickListener) this);
        log.setOnClickListener((View.OnClickListener) this);
        /*
        final Loading_Dialog loading_dialog = new Loading_Dialog(Reg_Log_welcome.this);
        loading_dialog.startLoadingDialog();
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loading_dialog.dismissDialog();
            }
        },2500);
         */
        // here Dashboard Activity
        // Kirish_Activity = new Intent(this, dilshod.iskandarov.onlinekutubxona.Kirish.class);

        // Here Action Bar App name
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        mAuth = FirebaseAuth.getInstance();
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Here FULLSCREEN Start
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Here FULLSCREEN End
    }

    private void init() {
        log = findViewById(R.id.log);
        reg = findViewById(R.id.reg);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.log:
                startActivity(new Intent(this,Kirish.class));
                break;
            case R.id.reg:
                startActivity(new Intent(this,Royxatdan_otish_welcome.class));
                break;

        }
    }

    private void onClickGoogle() {
        Toast.makeText(Reg_Log_welcome.this, "Google orqali kirish", Toast.LENGTH_SHORT).show();
    }






    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            startActivity(new Intent(Reg_Log_welcome.this,Menu.class));
        }
    }





}