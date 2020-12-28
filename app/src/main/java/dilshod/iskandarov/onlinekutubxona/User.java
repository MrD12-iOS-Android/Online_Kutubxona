package dilshod.iskandarov.onlinekutubxona;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User extends Application {

    private String email_user, password_user;
    public User(){

    }
    public User(String email_user, String password_user){
        this.email_user = email_user;
        this.password_user = password_user;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
            startActivity(new Intent(User.this,Menu.class));
        }
    }
}

