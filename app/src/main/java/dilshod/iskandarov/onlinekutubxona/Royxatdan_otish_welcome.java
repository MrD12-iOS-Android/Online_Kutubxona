package dilshod.iskandarov.onlinekutubxona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Royxatdan_otish_welcome extends AppCompatActivity implements View.OnClickListener{

    EditText edit_ism, edit_email, edit_password;
    Button btn_reg;
    TextView textView_btn_kirish;
    private FirebaseAuth mAuth;
    ImageView gone,vis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_royxatdan_otish);
        inti();
        mAuth = FirebaseAuth.getInstance();
        // Register Button
        btn_reg.setOnClickListener((View.OnClickListener) this);
        textView_btn_kirish.setOnClickListener((View.OnClickListener)this);

        /*
        final Loading_Dialog loading_dialog = new Loading_Dialog(Royxatdan_otish_welcome.this);
        loading_dialog.startLoadingDialog();
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loading_dialog.dismissDialog();
            }
        },1500);
         */

        // Here Action Bar App name
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        vis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vis.setVisibility(View.GONE);
                gone.setVisibility(View.VISIBLE);
                edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });



        gone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vis.setVisibility(View.VISIBLE);
                gone.setVisibility(View.GONE);
                edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        });

    }

    private void inti() {
        edit_ism = findViewById(R.id.reg_name);
        edit_email = findViewById(R.id.reg_email);
        edit_password = findViewById(R.id.reg_password);
        btn_reg = findViewById(R.id.btn_royxat_olish);
        textView_btn_kirish = findViewById(R.id.reg_kirish);
        gone = findViewById(R.id.img_gone);
        vis= findViewById(R.id.img_visible);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_royxat_olish:
                button_royxatga_olish();
                break;
            case R.id.reg_kirish:
                startActivity(new Intent(this,Kirish.class));
                break;

        }
    }

    private void button_royxatga_olish() {
        String Ism = edit_ism.getText().toString().trim();
        String Email = edit_email.getText().toString().trim();
        String PassWord = edit_password.getText().toString().trim();

        if (Ism.isEmpty()) {
            edit_ism.setError("Ismingizni kiriting! BU MUHIM!");
            edit_ism.requestFocus();
            return;
        }
        if (Email.isEmpty()) {
            edit_email.setError("Emailingizni kiriting! BU MUHIM!");
            edit_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            edit_email.setError("Emailingiz kiritilmadi!");
            edit_email.requestFocus();
            return;
        }
        if (PassWord.isEmpty()) {
            edit_password.setError("Parolni kiriting! BU MUHIM!");
            edit_password.requestFocus();
            return;
        }
        if (PassWord.length() < 6) {
            edit_password.setError("Parol uzunligi kamida 6 raqam yoki harfdan iborat bo'lishi kerak! BU MUHIM!");
            edit_password.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(Email,PassWord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(Royxatdan_otish_welcome.this,Kirish.class);
                            startActivity(intent);
                           /* User user = new  User(Email,PassWord);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getUid())


                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {


                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Royxatdan_otish_welcome.this, "Siz muvaffaqiyatli ro'yxatdan o'tdingiz!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(Royxatdan_otish_welcome.this, "Ro'yxatdan o'tib bo'lmadi! Qayta urinib ko'ring", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            */
                        }else {
                            Toast.makeText(Royxatdan_otish_welcome.this, "Ro'yxatdan o'tib bo'lmadi! Qayta urinib ko'ring", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
    public void onStart() {

        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

    }
}



















