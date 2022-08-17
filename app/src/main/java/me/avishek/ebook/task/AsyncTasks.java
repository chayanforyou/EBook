package me.avishek.ebook.task;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AsyncTasks<Params, Result> {

    private final ExecutorService executors;

    public AsyncTasks() {
        this.executors = Executors.newSingleThreadExecutor();
    }

    @SafeVarargs
    private final void startBackground(Params... params) {
        onPreExecute();
        executors.execute(() -> {
            Result result = doInBackground(params);
            new Handler(Looper.getMainLooper()).post(() -> onPostExecute(result));
        });
    }

    @SafeVarargs
    public final void execute(Params... params) {
        startBackground(params);
    }

    public void shutdown() {
        executors.shutdown();
    }

    public boolean isShutdown() {
        return executors.isShutdown();
    }

    @SuppressWarnings("unchecked")
    protected abstract Result doInBackground(Params... params);

    protected void onPreExecute() {
        throw new RuntimeException("Stub!");
    }

    protected void onPostExecute(Result result) {
        throw new RuntimeException("Stub!");
    }
}