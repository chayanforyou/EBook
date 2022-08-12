package me.avishek.ebook.api;

import java.util.List;

import me.avishek.ebook.models.Book;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequests {

    @GET("api/book_list")
    Call<List<Book>> getBookList();
}
