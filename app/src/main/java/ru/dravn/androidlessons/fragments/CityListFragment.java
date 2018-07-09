package ru.dravn.androidlessons.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import ru.dravn.androidlessons.App;
import ru.dravn.androidlessons.MainActivity;
import ru.dravn.androidlessons.R;
import ru.dravn.androidlessons.utils.Const;

public class CityListFragment extends BaseFragment {


    private EditText editText;
    private HashSet citiesName;
    private CityAdapter adapter;
    private HashMap<String, String> message = new HashMap<>();

    public static CityListFragment newInstance() {
        Bundle args = new Bundle();
        CityListFragment fragment = new CityListFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.city_list_fragment, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.list);
        editText = v.findViewById(R.id.edit_text);

        editText.setOnKeyListener(new View.OnKeyListener()
                                  {
                                      public boolean onKey(View v, int keyCode, KeyEvent event)
                                      {
                                          if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                                  (keyCode == KeyEvent.KEYCODE_ENTER))
                                          {
                                              citiesName.add(editText.getText().toString());
                                              adapter.notifyDataSetChanged();
                                              App.setCitiesName(citiesName);

                                              message.put(Const.CITY, editText.getText().toString());
                                              ((MainActivity) getActivity()).showWeatherFragment(message);
                                              return true;
                                          }
                                          return false;
                                      }
                                  }
        );

        citiesName = App.getCitiesName();

        adapter = new CityAdapter(getContext(), (String[]) citiesName.toArray(new String[citiesName.size()]));
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
                    message.put(Const.CITY, holder.cityName.getText().toString());
                    if (((MainActivity) getActivity()).getMyService() != null)
                        ((MainActivity) getActivity()).getMyService().setCity(message.get(Const.CITY));
                    ((MainActivity) getActivity()).showWeatherFragment(message);
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
