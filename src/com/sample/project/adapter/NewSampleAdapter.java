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

	public NewSampleAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		instanceOfVolley = VolleySingleton.newInstance(context);
	}

	@Override
	public void bindView(View convertView, Context arg1, Cursor cursor) {
		ImageLoader im = instanceOfVolley.getImageLoader();
		String data = cursor.getString(cursor
				.getColumnIndex(DatabaseHelper.COLUMN_DATA));
		Log.d("MESG", "her wer ar" + data);
		NetworkImageView nImgView = (NetworkImageView) convertView
				.findViewById(R.id.img_view);
		TextView txt = (TextView) convertView.findViewById(R.id.txt_view);
		
		if (cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TYPE))
				.equals("1")) {
			nImgView.setImageUrl(data, im);
			txt.setText("");
		} else {
			nImgView.setImageUrl("", im);
			txt.setText(data);
		}
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
		return inflater.inflate(R.layout.list_item, arg2, false);
	}

}
