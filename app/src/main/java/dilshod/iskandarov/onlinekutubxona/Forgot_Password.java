package dilshod.iskandarov.onlinekutubxona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity implements View.OnClickListener{

    Button btn_forgot_pass;
    EditText email_forgot;
    FirebaseAuth mAuth;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        email_forgot = findViewById(R.id.edit_forgot_password);
        btn_forgot_pass = findViewById(R.id.button_parolni);
        btn_forgot_pass.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        // Here ActionBar App name
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            
            case R.id.button_parolni:
                reset_password();
                break;
        }
    }

    private void reset_password() {
        String email = email_forgot.getText().toString().trim();

        if (email.isEmpty()){
            email_forgot.setError("Iltimos elektron pochtangizni kiriting!");
            email_forgot.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_forgot.setError("Iltimos EMAIL kiriting!");
            email_forgot.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Forgot_Password.this, "Parolni yangilash uchun Emailingizga xabar yuborildi!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Forgot_Password.this, "Xatolik yuz berdi!!!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}