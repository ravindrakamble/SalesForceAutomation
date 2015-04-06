package com.r2apps.sfa.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.r2apps.sfa.R;
import com.r2apps.sfa.dao.Retailer;

import java.util.List;

/**
 * Created by ravindra.kambale on 3/2/2015.
 */
public class RetailersAdapter extends ArrayAdapter<Retailer> {
    private Context context;
    private List<Retailer> items;
    public RetailersAdapter(Context context, int resourceId,
                              List<Retailer> items) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;

    }

    private class ViewHolder {
        TextView txtTitle;
        TextView txtSubTitle;
    }

    @Override
    public Retailer getItem(int position) {
        return items.get(position);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final Retailer product = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_retailer, null);
            holder = new ViewHolder();

            holder.txtTitle = (TextView)convertView.findViewById(R.id.txt_retailer_name);
            holder.txtSubTitle = (TextView)convertView.findViewById(R.id.txt_retailer_address);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }



        holder.txtTitle.setText(product.getName());
        holder.txtSubTitle.setText("Address : " + product.getAddress());

        convertView.setTag(holder);

        return convertView;
    }

}