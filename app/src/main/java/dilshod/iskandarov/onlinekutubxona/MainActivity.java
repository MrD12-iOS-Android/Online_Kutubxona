package dilshod.iskandarov.onlinekutubxona;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    //Here Splash Screen
    private static int SPLASH_SCREEN = 3000;
    // here animation
    Animation animation;
    ImageView logo;
    boolean visible = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        logo.animate().scaleYBy(90).scaleXBy(90).setDuration(2000);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.loading);
        Sprite doubleBounce = new Circle();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(this,R.anim.splash_screen);
        logo.setAnimation(animation);
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,Reg_Log_welcome.class));
            }

        },SPLASH_SCREEN);
       // Here Action Bar App name
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        //Here FULLSCREEN Start
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Here FULLSCREEN End
    }
    private void init() {
        logo = findViewById(R.id.logo);
    }
   /* public void logo(View view) {
        if (visible){
            logo.animate().rotation(2700).scaleXBy(-1).scaleYBy(-1).setDuration(2000);
            visible = false;
        }else {
            visible = true;
        }
    }
    */
}