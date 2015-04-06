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
import com.r2apps.sfa.dao.StoreInfo;

import java.util.List;

/**
 * Created by ravindra.kambale on 2/27/2015.
 */
public class StoreListAdapter extends ArrayAdapter<StoreInfo> {

    private Context context;
    private List<StoreInfo> items;
    public StoreListAdapter(Context context, int resourceId,
                              List<StoreInfo> items) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;

    }

    private class ViewHolder {
        TextView txtTitle;
        TextView txtAddress;
        TextView txtOpenTill;
    }

    @Override
    public StoreInfo getItem(int position) {
        return items.get(position);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final StoreInfo storeInfo = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_store, null);
            holder = new ViewHolder();

            holder.txtTitle = (TextView)convertView.findViewById(R.id.txt_store_name);
            holder.txtAddress = (TextView)convertView.findViewById(R.id.txt_store_address);
            holder.txtOpenTill = (TextView)convertView.findViewById(R.id.txt_store_open);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }



        holder.txtTitle.setText(storeInfo.getName());
        holder.txtAddress.setText(storeInfo.getVicinity());
        //holder.txtOpenTill.setText(storeInfo);

        convertView.setTag(holder);

        return convertView;
    }

}