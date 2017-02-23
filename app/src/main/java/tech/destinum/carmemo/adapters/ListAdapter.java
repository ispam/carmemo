package tech.destinum.carmemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tech.destinum.carmemo.R;
import tech.destinum.carmemo.pojo.Category;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Category> mCategories;

    public ListAdapter(Context mContext, ArrayList<Category> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.selection_format, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = mCategories.get(position);
        holder.mTitle.setText(category.getName());
        holder.mDescription.setText(category.getDescription());
        holder.mImage.setImageResource(category.getImage());
    }

    @Override
    public int getItemCount() {
        return mCategories != null ? mCategories.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle, mDescription;
        public ImageView mImage;

        public ViewHolder(View view) {
            super(view);

            mTitle = (TextView) view.findViewById(R.id.tv_title);
            mDescription = (TextView) view.findViewById(R.id.tv_description);
            mImage = (ImageView) view.findViewById(R.id.image_category);

        }
    }
}
