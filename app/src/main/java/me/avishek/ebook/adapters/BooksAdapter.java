package me.avishek.ebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import me.avishek.ebook.PdfView;
import me.avishek.ebook.constant.Constant;
import me.avishek.ebook.databinding.RowBookBinding;
import me.avishek.ebook.models.Book;

public class BooksAdapter extends ListAdapter<Book, BooksAdapter.MyViewHolder> {

    private final Context mContext;

    public BooksAdapter(Context mContext) {
        super(Book.itemCallback);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowBookBinding rowBookBinding = RowBookBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(rowBookBinding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Book book = getItem(position);
        holder.bind(book);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RowBookBinding binding;

        public MyViewHolder(RowBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Book book) {
            binding.title.setText(book.getName());
            //binding.rating.setRating((float) book.getRating());

            // loading album cover using Glide library
            Glide.with(mContext).load(Constant.BASE_URL + book.getThumbnail()).into(binding.thumbnail);

            itemView.setOnClickListener(v -> PdfView.openPdfFromAsset(mContext, book.getName()));
        }
    }
}
