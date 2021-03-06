package uk.co.traintrackapp.traintrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import uk.co.traintrackapp.traintrack.R;
import uk.co.traintrackapp.traintrack.StationActivity;
import uk.co.traintrackapp.traintrack.model.Station;
import uk.co.traintrackapp.traintrack.utils.Utils;

public class StationRowAdapter extends RecyclerView.Adapter<StationRowAdapter.ViewHolder> implements Filterable {

    private List<Station> rowList;
    private List<Station> origRowList;
    private String distanceUnit;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Station station;
        public TextView name;
        public TextView distance;
        public ImageView nationalRailIcon;
        public ImageView undergroundIcon;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            distance = (TextView) v.findViewById(R.id.distance);
            nationalRailIcon = (ImageView) v.findViewById(R.id.national_rail_icon);
            undergroundIcon = (ImageView) v.findViewById(R.id.underground_icon);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (station != null) {
                Intent intent = new Intent().setClass(v.getContext(),
                        StationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Utils.ARGS_STATION, station);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    public StationRowAdapter(ArrayList<Station> stations, String distanceUnit) {
        this.rowList = stations;
        this.distanceUnit = distanceUnit;
    }

    @Override
    public StationRowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_station, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Station station = rowList.get(position);
        holder.station = station;
        holder.name.setText(station.getName());

        holder.distance.setText(station.getDistanceText(distanceUnit));
        if (station.isNationalRail()) {
            holder.nationalRailIcon.setVisibility(View.VISIBLE);
        } else {
            holder.nationalRailIcon.setVisibility(View.GONE);
        }
        if (station.isUnderground()) {
            holder.undergroundIcon.setVisibility(View.VISIBLE);
        } else {
            holder.undergroundIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return rowList.size();
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                rowList = (List<Station>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Station> list = new ArrayList<>();

                if (origRowList == null) {
                    origRowList = new ArrayList<>(rowList);
                }

                if (constraint == null || constraint.length() == 0) {
                    results.count = origRowList.size();
                    results.values = origRowList;
                } else {
                    for (int i = 0; i < origRowList.size(); i++) {
                        Station station = origRowList.get(i);
                        if (station.isNameSimilarTo(constraint)) {
                            list.add(station);
                        }
                    }
                    results.count = list.size();
                    results.values = list;
                }
                return results;
            }
        };

    }

    public void refresh(ArrayList<Station> stations) {
        this.rowList = stations;
        this.notifyDataSetChanged();
    }

}
