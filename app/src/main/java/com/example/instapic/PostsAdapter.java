package com.example.instapic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> postList) {
        posts.addAll(postList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvLocation;
        private TextView tvUserNameDescription;
        private TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvUserNameDescription = itemView.findViewById(R.id.tvUsernameDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvLocation.setVisibility(View.GONE);
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvDate.setText(post.getDate(post));
            String location = post.getLocation();
            if (!location.isEmpty()){
                tvLocation.setVisibility(View.VISIBLE);
            }
            tvLocation.setText(location);
            tvUserNameDescription.setText((post.getUser().getUsername()));
            ParseFile image = post.getImage();
            if (image != null){
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

        }
    }
}
