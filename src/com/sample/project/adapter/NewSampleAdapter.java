package com.sample.project.adapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sample.project.R;
import com.sample.project.database.DatabaseHelper;
import com.sample.project.volley.VolleySingleton;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewSampleAdapter extends CursorAdapter {

	private LayoutInflater inflater;
	private VolleySingleton instanceOfVolley;
	private ImageLoader im;
	private Cursor cursor;
	private static final String TYPE_MSG = "0";
	private static final String TYPE_IMG = "1";

	public NewSampleAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		cursor = c;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		instanceOfVolley = VolleySingleton.newInstance(context);
		im = instanceOfVolley.getImageLoader();
	}

	@Override
	public void bindView(View convertView, Context arg1, Cursor cursor) {
			}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
		return null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("TAG", "convert view called");
		if (cursor.moveToPosition(position)) {
			String type = cursor.getString(cursor
					.getColumnIndex(DatabaseHelper.COLUMN_TYPE));
			if(type.equals(TYPE_IMG))
				return getImgView(position, convertView, parent);
			else
				return getTxtView(position, convertView, parent);
		}
		return convertView;
	}

	private View getTxtView(int position, View convertView, ViewGroup parent) {
		if (convertView == null || convertView.getTag().toString().equals(TYPE_IMG)) {
			convertView = inflater.inflate(R.layout.list_item_text, parent, false);
			convertView.setTag(TYPE_MSG);
		}
		if(cursor.moveToPosition(position)) {
			String data = cursor.getString(cursor
					.getColumnIndex(DatabaseHelper.COLUMN_DATA));
			TextView txt = (TextView) convertView.findViewById(R.id.txt_view);
			txt.setText(data);
		}
		return convertView;
	}
	
	private View getImgView(int position, View convertView, ViewGroup parent) {
		if (convertView == null || convertView.getTag().toString().equals(TYPE_MSG)) {
			convertView = inflater.inflate(R.layout.list_item_img, parent, false);
			convertView.setTag(TYPE_IMG);
		}
		if(cursor.moveToPosition(position)) {
			String data = cursor.getString(cursor
					.getColumnIndex(DatabaseHelper.COLUMN_DATA));
			NetworkImageView nImgView = (NetworkImageView) convertView
					.findViewById(R.id.img_view);
			nImgView.setImageUrl(data, im);	
		}
		return convertView;
	}
}
