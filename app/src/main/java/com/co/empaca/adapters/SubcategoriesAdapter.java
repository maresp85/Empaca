package com.co.empaca.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.co.empaca.R;
import com.co.empaca.models.Products;
import com.co.empaca.utils.BitmapManager;

import java.util.ArrayList;

public class SubcategoriesAdapter extends RecyclerView.Adapter<SubcategoriesAdapter.ProductViewHolder> {

    private ArrayList<Products> productsList;

    public SubcategoriesAdapter(ArrayList<Products> productsList) {
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
                inflate(R.layout.row_subcategories, viewGroup, false);

        return new ProductViewHolder(itemView);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        protected ImageView Image;
        protected TextView Name;
        protected TextView Description;

        public ProductViewHolder(View v) {
            super(v);
            Image = (ImageView) v.findViewById(R.id.image);
            Name = (TextView)  v.findViewById(R.id.name);
            Description = (TextView) v.findViewById(R.id.description);
        }
    }
}