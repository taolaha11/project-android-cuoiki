package com.mobile.quanlybanhangonline.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.model.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_product, null);
        }
        Product product = getItem(position);
        if (product != null) {
            ImageView image = (ImageView) view.findViewById(R.id.image);
            TextView txtName = (TextView) view.findViewById(R.id.tvName);
            TextView txtQuantity = (TextView) view.findViewById(R.id.tvQuantity);
            TextView txtPrice = (TextView) view.findViewById(R.id.tvPrice);
            TextView txtCategory = (TextView) view.findViewById(R.id.tvCategory);
            byte[] productImage = product.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);

            image.setImageBitmap(bitmap);
            txtName.setText(product.getName());
            txtQuantity.setText(String.valueOf(product.getQuantity()));
            txtPrice.setText(String.valueOf(product.getPrice()));
            txtCategory.setText(String.valueOf(product.getCategoryName()));
        }
        return view;
    }
}
