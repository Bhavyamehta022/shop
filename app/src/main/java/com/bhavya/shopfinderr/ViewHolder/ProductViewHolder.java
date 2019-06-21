package com.bhavya.shopfinderr.ViewHolder;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhavya.shopfinderr.Interface.ItemClickListener;
import com.bhavya.shopfinderr.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView productName,productDescription,productPrice;
    public ImageView imageView;
    public ItemClickListener listener;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.productImage);
        productName=itemView.findViewById(R.id.productName);
        productDescription=itemView.findViewById(R.id.productDescription);
        productPrice=itemView.findViewById(R.id.productPrice);
    }
    public void setItemCLickListener(ItemClickListener listener)
    {
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {

        listener.onClick(v,getAdapterPosition(),false);

    }
}
