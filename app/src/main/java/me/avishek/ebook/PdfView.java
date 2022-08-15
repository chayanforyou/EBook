package me.avishek.ebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import me.avishek.ebook.databinding.ActivityPdfViewBinding;
import me.avishek.ebook.task.AsyncTasks;

public class PdfView extends AppCompatActivity {

    public static void openPDF(Context context, String name, String url) {
        context.startActivity(new Intent(context, PdfView.class)
                .putExtra(NAME, name)
                .putExtra(URL, url));
    }

    private static final String NAME = "PDF_NAME";
    private static final String URL = "PDF_URL";

    private ActivityPdfViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String pdfName = getIntent().getStringExtra(NAME);
        String pdfUrl = getIntent().getStringExtra(URL);

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(pdfName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new RetrievePDFFromUrl().execute(pdfUrl);
    }

    private class RetrievePDFFromUrl extends AsyncTasks<String, InputStream> {

        @Override
        protected void onPreExecute() {
            binding.loadingProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected InputStream doInBackground(String pdfUrl) {
            InputStream inputStream = null;

            try {
                URL url = new URL(pdfUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            binding.loadingProgress.setVisibility(View.GONE);
            if (inputStream != null) {
                binding.pdfView.fromStream(inputStream).load();
            } else {
                Toast.makeText(getApplicationContext(), "Error, Can't load pdf", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}