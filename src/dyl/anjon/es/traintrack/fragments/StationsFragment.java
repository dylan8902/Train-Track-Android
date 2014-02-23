package dyl.anjon.es.traintrack.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import dyl.anjon.es.traintrack.R;
import dyl.anjon.es.traintrack.StationActivity;
import dyl.anjon.es.traintrack.adapters.StationRowAdapter;
import dyl.anjon.es.traintrack.db.StationDatabaseHandler;
import dyl.anjon.es.traintrack.models.Station;

public class StationsFragment extends Fragment {

	public StationsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_stations, container,
				false);

		StationDatabaseHandler db = new StationDatabaseHandler(getActivity());
		ArrayList<Station> stations = db.getAllStations();
		db.close();

		ListView list = (ListView) rootView.findViewById(R.id.list);
		final StationRowAdapter adapter = new StationRowAdapter(inflater,
				stations);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View view, int index,
					long x) {
				Station station = (Station) adapter.getItem(index);
				Intent intent = new Intent().setClass(getActivity(),
						StationActivity.class);
				intent.putExtra("id", station.getId());
				startActivity(intent);
				return;
			}

		});

		EditText search = (EditText) rootView.findViewById(R.id.search);
		search.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable arg0) {
			}

			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			public void onTextChanged(CharSequence search, int arg1, int arg2,
					int arg3) {
				adapter.getFilter().filter(search);
			}
		});

		return rootView;
	}
}
