package dilshod.iskandarov.onlinekutubxona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class Sherlock_pdf extends AppCompatActivity {

    PDFView pdfFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sherlock_pdf);
        pdfFile = findViewById(R.id.pdf_viewer);

    }
}