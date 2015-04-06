package com.r2apps.sfa.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.r2apps.sfa.R;
import com.r2apps.sfa.dao.Product;

import java.util.List;

/**
 * Created by ravindra.kambale on 3/2/2015.
 */
public class OrderProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> items;
    public OrderProductAdapter(Context context, int resourceId,
                              List<Product> items) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;

    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtSubTitle;
        EditText editTextCount;
        EditText editTextRemark;
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
            convertView = mInflater.inflate(R.layout.row_order, null);
            holder = new ViewHolder();

            holder.txtTitle = (TextView)convertView.findViewById(R.id.txt_product_name);
            holder.txtSubTitle = (TextView)convertView.findViewById(R.id.txt_product_availability);
            holder.imageView = (ImageView)convertView.findViewById(R.id.img_product);
            holder.editTextCount = (EditText)convertView.findViewById(R.id.edt_quantity);
            holder.editTextRemark = (EditText)convertView.findViewById(R.id.edt_remarks);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }



        holder.txtTitle.setText(product.name);
        holder.txtSubTitle.setText("Availability : " + product.availability);

        convertView.setTag(holder);

        return convertView;
    }

}
