package dilshod.iskandarov.onlinekutubxona;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import java.util.zip.Inflater;

class Loading_Dialog {
    private  Activity activity;
    private  AlertDialog dialog;

    Loading_Dialog (Activity myActivity){
        activity = myActivity;
    }
    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }
    void dismissDialog(){
        dialog.dismiss();

    }


}
