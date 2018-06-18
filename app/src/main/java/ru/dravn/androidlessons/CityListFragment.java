package ru.dravn.androidlessons;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

public class CityListFragment extends BaseFragment{

    private MainActivity mActivity;

    public static CityListFragment newInstance() {
        Bundle args = new Bundle();
        CityListFragment fragment = new CityListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private HashMap<String, String> message = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.city_list_fragment, container, false);

        mActivity = (MainActivity)getActivity();
        RecyclerView recyclerView = v.findViewById(R.id.list);

        CityAdapter adapter = new CityAdapter(mActivity, getResources().getStringArray(R.array.city));
        recyclerView.setAdapter(adapter);

        return v;
    }


    public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private String[] city;

        public CityAdapter(Context context, String[] city) {
            this.inflater = LayoutInflater.from(context);
            this.city = city;
        }

        @NonNull
        @Override
        public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.city_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final CityAdapter.ViewHolder holder, int position) {

            holder.cityName.setText(city[position]);

            holder.cityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message.put(CITY, holder.cityName.getText().toString());
                    mActivity.showWeatherFragment(message);
                }
            });
        }

        @Override
        public int getItemCount() {
            return city.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView cityName;

            ViewHolder(View view) {
                super(view);
                cityName = view.findViewById(R.id.city);

            }
        }
    }

}
