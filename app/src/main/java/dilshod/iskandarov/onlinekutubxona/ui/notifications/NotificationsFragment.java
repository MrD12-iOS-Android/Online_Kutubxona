package dilshod.iskandarov.onlinekutubxona.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import dilshod.iskandarov.onlinekutubxona.Forgot_Password;
import dilshod.iskandarov.onlinekutubxona.Kirish;
import dilshod.iskandarov.onlinekutubxona.R;
import dilshod.iskandarov.onlinekutubxona.Send;
import dilshod.iskandarov.onlinekutubxona.Settings;

public class NotificationsFragment extends Fragment implements View.OnClickListener{

    LinearLayout chiq__btn, btn_settings, btn_forgot_pass, btn_send;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        chiq__btn = root.findViewById(R.id.chiqish_btn);
        btn_settings = root.findViewById(R.id.settings_button);
        btn_send = root.findViewById(R.id.send_friend);
        btn_forgot_pass = root.findViewById(R.id.forgot_password);

        chiq__btn.setOnClickListener(this);
        btn_settings.setOnClickListener(this);
        btn_forgot_pass.setOnClickListener(this);
        btn_send.setOnClickListener(this);




        return root;
    }


    @Override
    public void onClick(View v) {
     switch (v.getId()){
            case (R.id.chiqish_btn):
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), Kirish.class));
                break;

            case (R.id.settings_button):
                startActivity(new Intent(getActivity(), Settings.class));
                break;

            case (R.id.forgot_password):
                startActivity(new Intent(getActivity(), Forgot_Password.class));
                break;

            case (R.id.send_friend):
                startActivity(new Intent(getActivity(), Send.class));
                break;

        }


    }
}