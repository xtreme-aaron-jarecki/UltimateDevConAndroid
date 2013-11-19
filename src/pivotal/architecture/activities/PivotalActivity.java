package pivotal.architecture.activities;

import pivotal.architecture.PivotalApplication;
import pivotal.architecture.R;
import pivotal.architecture.adapters.PivotalCursorAdapter;
import pivotal.architecture.loaders.PivotalLoader;
import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class PivotalActivity extends Activity implements LoaderCallbacks<Cursor> {
	
	private static final int LOADER_ID = 1;
	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_pivotal);
		super.onCreate(savedInstanceState);
		mListView = (ListView) findViewById(R.id.activity_pivotal_list_view);
	}

	protected void onResume() {
		super.onResume();
		final LoaderManager loaderManager = getLoaderManager();
		loaderManager.initLoader(LOADER_ID, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Log.d(PivotalApplication.DEBUG_TAG, "LOADER_ID == id: " + (LOADER_ID == id));
		if (LOADER_ID == id)
			return new PivotalLoader(getApplicationContext());
		return null;
	}

	@Override
	public void onLoadFinished(final Loader<Cursor> loader, final Cursor cursor) {
		final PivotalCursorAdapter pivotalCursorAdapter = new PivotalCursorAdapter(getApplicationContext(), cursor);
		mListView.setAdapter(pivotalCursorAdapter);
		Log.d(PivotalApplication.DEBUG_TAG, "cursor.getCount(): " + cursor.getCount());
	}

	@Override
	public void onLoaderReset(final Loader<Cursor> loader) {
		mListView.setAdapter(null);
	};
}
