package com.sample.project;

import org.json.JSONException;

import android.database.Cursor;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.sample.project.adapter.SampleAdapter;
import com.sample.project.database.DatabaseHelper;

public class MainActivity extends ActionBarActivity {

	private ListView mListView;
	private SampleAdapter mSampleAdapter;
	private Cursor mCursor;
	private DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.list);

		dbHelper = new DatabaseHelper(MainActivity.this);

		// populate the database
		try {
			dbHelper.populateDB();
		} catch (JSONException json) {
			json.printStackTrace();
		}
		
		mCursor = dbHelper.getData();
		
		mSampleAdapter = new SampleAdapter(this, mCursor);
		mListView.setAdapter(mSampleAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
