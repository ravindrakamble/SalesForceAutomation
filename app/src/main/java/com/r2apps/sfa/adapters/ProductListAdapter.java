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


public class ProductListAdapter extends ArrayAdapter<Product> {

	private Context context;
	private List<Product> items;
	public ProductListAdapter(Context context, int resourceId,
			List<Product> items) {
		super(context, resourceId, items);
		this.context = context;
		this.items = items;

	}

	private class ViewHolder {
		ImageView imageView;
		TextView txtTitle;
        TextView txtSubTitle;
		TextView txtprice;
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
			convertView = mInflater.inflate(R.layout.only_product_row, null);
			holder = new ViewHolder();

			holder.txtprice= (TextView)convertView.findViewById(R.id.price);
			holder.txtTitle = (TextView)convertView.findViewById(R.id.title);
            holder.txtSubTitle = (TextView)convertView.findViewById(R.id.sizeunit);
			holder.imageView = (ImageView)convertView.findViewById(R.id.icon);
		} else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		String iStr = "p_" + product.id + "_icon";
		
		int id = context.getResources().getIdentifier(iStr, "drawable", context.getPackageName());
		if(id > 0){
			Drawable drawable = context.getResources().getDrawable(id);
			holder.imageView.setImageDrawable(drawable);
		}
		
		holder.txtTitle.setText(product.name);
        holder.txtSubTitle.setText(product.size + product.unit);
		holder.txtprice.setText(product.price);
		
		
		convertView.setTag(holder);

		return convertView;
	}
	
}