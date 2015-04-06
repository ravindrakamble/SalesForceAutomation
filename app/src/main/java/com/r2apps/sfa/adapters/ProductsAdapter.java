package com.r2apps.sfa.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.r2apps.sfa.R;
import com.r2apps.sfa.dao.Product;

import java.util.List;

/**
 * Created by happiestminds on 3/9/15.
 */
public class ProductsAdapter extends ArrayAdapter<Product> {

    private Context context;
    private List<Product> items;
    public ProductsAdapter(Context context, int resourceId,
                              List<Product> items) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;

    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtSubTitle;
        TextView txtSellingPrice;
        TextView txtActualPrice;
        TextView txtAvailability;
    }

    @Override
    public Product getItem(int position) {
        return items.get(position);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final Product product = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_product_list, null);
            holder = new ViewHolder();

            holder.txtActualPrice= (TextView)convertView.findViewById(R.id.actual_price);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.title);
            holder.txtSubTitle = (TextView)convertView.findViewById(R.id.sizeunit);
            holder.txtSellingPrice = (TextView)convertView.findViewById(R.id.selling_price);
            holder.txtAvailability = (TextView)convertView.findViewById(R.id.availability);
            holder.imageView = (ImageView)convertView.findViewById(R.id.icon);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTitle.setText(product.name);
        holder.txtSubTitle.setText(product.size + product.unit);
        holder.txtActualPrice.setText(product.price);
        holder.txtSellingPrice.setText(product.promoPrice);
        holder.txtAvailability.setText("" + product.availability);


        convertView.setTag(holder);

        return convertView;
    }

}