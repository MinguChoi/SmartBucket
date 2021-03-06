package com.example.smartbucket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartbucket.Model.Item;

import java.net.URI;
import java.util.List;
import java.util.logging.Handler;

public class ItemAdapter extends ArrayAdapter {

    private List<Item> itemList;
    private Context context;

    // Constructor
    public ItemAdapter(List<Item> itemList, Context ctx) {
        super(ctx, R.layout.custom_row2, itemList);
        this.itemList = itemList;
        this.context = ctx;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_row2, parent, false);

            ItemDataHolder holder = new ItemDataHolder(convertView);
            convertView.setTag(holder);
        }
        ItemDataHolder holder = (ItemDataHolder) convertView.getTag();

        ImageView itemImg = holder.itemImg;
        TextView name = holder.nameTXT;
        TextView description = holder.descriptionTXT;
        TextView price = holder.priceTXT;

        Item item = itemList.get(position);
        API.getImage(item.getName(), context, new OnCompletion() {
            @Override
            public void onCompletion(Object object) {
                //itemImg.setImageBitmap((Bitmap)object);
                Glide.with(context)
                        .load((Uri)object)
                        .into(itemImg);
                //itemImg.setImageURI((Uri) object);
            }
        });
        name.setText(item.getName());
        description.setText(item.getDescription());
        price.setText(item.getPrice() + "???");

        return convertView;
    }
}
