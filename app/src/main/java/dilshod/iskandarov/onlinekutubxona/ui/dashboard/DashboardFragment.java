package dilshod.iskandarov.onlinekutubxona.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dilshod.iskandarov.onlinekutubxona.R;
public class DashboardFragment extends Fragment {

    ImageView img_vis_b, img_gone_w;
    RelativeLayout back;


    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        img_gone_w = root.findViewById(R.id.img_gone_white);
        img_vis_b = root.findViewById(R.id.img_visible_black);
        back = root.findViewById(R.id.back);

        img_vis_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_vis_b.setVisibility(View.GONE);
                img_gone_w.setVisibility(View.VISIBLE);
                back.setBackgroundColor(Color.parseColor("#191919"));
            }
        });

        img_gone_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_gone_w.setVisibility(View.GONE);
                img_vis_b.setVisibility(View.VISIBLE);
                back.setBackgroundColor(Color.parseColor("#FF6D00"));
            }
        });


        return root;
    }
}