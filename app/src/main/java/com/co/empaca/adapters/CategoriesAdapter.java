package com.co.empaca.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.co.empaca.Activity_second;
import com.co.empaca.R;
import com.co.empaca.fragment.Fragment_Subcategories;
import com.co.empaca.models.Products;
import com.co.empaca.utils.BitmapManager;
import com.co.empaca.utils.Constants;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ProductViewHolder> {

    private ArrayList<Products> productsList;
    public ClickListener clickListener;

    public CategoriesAdapter(ArrayList<Products> productsList) {
            this.productsList = productsList;
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {

        Products ci = productsList.get(i);
        BitmapManager.getInstance().loadBitmap(ci.getImagen(), productViewHolder.Image);
        productViewHolder.Name.setText(ci.getName());
        productViewHolder.Description.setText(ci.getDescription());
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.row_categories, viewGroup, false);

        return new ProductViewHolder(itemView);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        protected ImageView Image;
        protected TextView Name;
        protected TextView Description;

        public ProductViewHolder(View v) {
            super(v);
            Image = (ImageView) v.findViewById(R.id.imageCategories);
            Name = (TextView) v.findViewById(R.id.nameCategories);
            Description = (TextView) v.findViewById(R.id.descriptionCategories);
            // specify listeners for the title or the icon.
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(v, getPosition());
            }

        }

        @Override
        public boolean onLongClick(View v) {

            // If long clicked, passed last variable as true.
            Log.e(Constants.TAG, "clic largo" + getPosition());
            return true;
        }
    }

    public interface ClickListener {
        public void onItemClick(View v, int position);
    }

    /* Setter for listener. */
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

}