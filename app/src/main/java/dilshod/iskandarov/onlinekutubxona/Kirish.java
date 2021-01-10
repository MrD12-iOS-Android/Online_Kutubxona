package dilshod.iskandarov.onlinekutubxona;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Kirish extends AppCompatActivity implements View.OnClickListener {

    EditText login_email, login_password;
    Button btn_kirish;
    TextView textView_btn_royxat_olish,forgot_parolni_unutdim;
    private FirebaseAuth mAuth;
    CheckBox checkBox;
    Context context;
    private Intent Menu_Activity;
    ImageView gone,vis;
    /*
    //
    SharedPreferences sharedPreferences;
    public  static final String Email = "email";
    public  static final String Password = "password";
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirish);
        init();

        // Here Action Bar App name
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

       final Loading_Dialog loading_dialog = new Loading_Dialog(Kirish.this);

        vis.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               vis.setVisibility(View.GONE);
               gone.setVisibility(View.VISIBLE);
               login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
           }
       });
        gone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vis.setVisibility(View.VISIBLE);
                gone.setVisibility(View.GONE);
                login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        });
        mAuth = FirebaseAuth.getInstance();
        btn_kirish.setOnClickListener(this);
        textView_btn_royxat_olish.setOnClickListener(this);
        forgot_parolni_unutdim.setOnClickListener(this);

     /*   checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(Kirish.this, "Saqlandi", Toast.LENGTH_SHORT).show();
                }else if (!buttonView.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(Kirish.this, "Saqlanmadi !!!", Toast.LENGTH_SHORT).show();
                }
                 SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putString(KEY_NAME, String.valueOf(login_password));
                   editor.putString(KEY_EMAIL, String.valueOf(login_email));
                   editor.apply();


            }
        });*/
      /*sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);
        if (name != null){
            startActivity(new Intent(Kirish.this,Dashboard.class));
        }

       */



    }




    private void init() {
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        btn_kirish = findViewById(R.id.btn_royxat_olish);
        textView_btn_royxat_olish = findViewById(R.id.login_reg);
        gone = findViewById(R.id.img_gone);
        vis = findViewById(R.id.img_visible);
        forgot_parolni_unutdim = findViewById(R.id.forgot_parolni_unutdim);

    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_royxat_olish:
             button_Kirish();
                break;
            case R.id.login_reg:
                startActivity(new Intent(this,Royxatdan_otish_welcome.class));
                break;

            case R.id.forgot_parolni_unutdim:
                startActivity(new Intent(this,Forgot_Password.class));
                break;

        }

    }

    private void button_Kirish() {


        String EmaiL = login_email.getText().toString().trim();
        String PassworD = login_password.getText().toString().trim();

        /*
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", String.valueOf(login_email));
        editor.putString("password", String.valueOf(login_password));
        editor.apply();
         */
        if (EmaiL.isEmpty()){
            login_email.setError("Emailingizni kiriting!");
            login_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(EmaiL).matches()){
            login_email.setError("Emailingiz kiritilmadi!");
            login_email.requestFocus();
            return;
        }
        if (PassworD.isEmpty()){
            login_password.setError("Parolni kiriting! BU MUHIM!");
            login_password.requestFocus();
            return;
        }
        if (PassworD.length() < 6 ){
            login_password.setError("Parol uzunligi kamida 6 raqam yoki harfdan iborat bo'lishi kerak! BU MUHIM!");
            login_password.requestFocus();
            return;
        }


       mAuth.signInWithEmailAndPassword(login_email.getText().toString(),login_password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();

                            String email = user.getEmail();
                            String name = user.getDisplayName();



                            FirebaseUser user_Verified = FirebaseAuth.getInstance().getCurrentUser();

                            if (user_Verified.isEmailVerified()){
                                startActivity(new Intent(Kirish.this,Menu.class));
                            }else {
                                user_Verified.sendEmailVerification();
                                Toast.makeText(Kirish.this, "Email tasdiqlanmagan! Iltimos Emailingizni tekshiring...", Toast.LENGTH_SHORT).show();
                            }
                        }else {

                            Toast.makeText(Kirish.this, "Foydalanuvchi topilmadi...", Toast.LENGTH_SHORT).show();
                        }
                    }


                });


    }
    public void onStart() {

        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

    }






}
