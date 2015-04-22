package com.sample.project.adapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sample.project.R;
import com.sample.project.database.DatabaseHelper;
import com.sample.project.volley.VolleySingleton;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SampleAdapter extends BaseAdapter {

	private Context ctxt;
	private Cursor cursor;

	public SampleAdapter(Context context, Cursor cursor) {
		this.ctxt = context;
		this.cursor = cursor;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) ctxt
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item, parent, false);
		}
		VolleySingleton instanceOfVolley = VolleySingleton.newInstance(ctxt);
		ImageLoader im = instanceOfVolley.getImageLoader();
		String data = cursor.getString(cursor
				.getColumnIndex(DatabaseHelper.COLUMN_DATA));
		if (cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TYPE))
				.equals("1")) {
			NetworkImageView nImgView = (NetworkImageView) convertView
					.findViewById(R.id.img_view);
			nImgView.setImageUrl(data, im);
		}
		else {
			TextView txt = (TextView) convertView.findViewById(R.id.txt_view);
			txt.setText(data);
		}
		return convertView;
	}

}
