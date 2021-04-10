package com.example.smartbucket;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDataHolder {

    public ImageView itemImg;
    public TextView nameTXT, descriptionTXT, priceTXT;

    public ItemDataHolder (View root) {
        this.itemImg = root.findViewById(R.id.itemImg_row2);
        this.nameTXT = root.findViewById(R.id.name_row_TXT2);
        this.descriptionTXT = root.findViewById(R.id.description_row_TXT2);
        this.priceTXT = root.findViewById(R.id.price_row_TXT2);
    }
}
