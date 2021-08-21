package me.avishek.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.pdfview.PDFView;

public class PdfView extends AppCompatActivity {

    public static void openPdfFromAsset(Context context, String name) {
        context.startActivity(new Intent(context, PdfView.class)
        .putExtra(NAME, name));
    }

    private static final String NAME = "file_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        String name = getIntent().getStringExtra(NAME);

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PDFView pdfView = findViewById(R.id.pdf_view);
        pdfView.fromAsset(name.concat(".pdf")).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}