package com.android.covid19.ui.main.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid19.MainActivity;
import com.android.covid19.R;
import com.android.covid19.adaptor.MyAdapter;
import com.android.covid19.api.covid19.Covid19ServiceApi;
import com.android.covid19.model.corona.GetCoronaCountriesRequest;
import com.android.covid19.model.corona.GetCoronaCountriesResponse;
import com.android.covid19.model.covid19.GetWorldTotalRequest;
import com.android.covid19.model.covid19.GetWorldTotalResponse;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.IOException;
import java.util.ArrayList;

public class TabDashboard extends Fragment {
    final String TAG = this.getClass().getName();
    Covid19ServiceApi c = new Covid19ServiceApi();
    View rootView;
    private BarChart stackedChart;
    private GetWorldTotalResponse getWorldTotalResponse;
    private int [] colorArray = {Color.RED,Color.GREEN,Color.CYAN};
    private String [] StackLables = {"Deaths","Recovered","Confirmed"};
    BarDataSet barDataSet;
    ProgressDialog mProgressDialog;
    Covid19ServiceApi csapi = new Covid19ServiceApi();
    String error;
    String code;
    String message;
    String description;
    String network_error;
    String no_internet_connection;
    String enable_wifi_or_data_connection;
    String nostock;
    String no_stock_available_for;
    String try_something_other_then;
    String server_down;
    String try_after_sometime;
    boolean flag = false;
    MainActivity mainActivity;
    RecyclerView recyclerView;
    GetCoronaCountriesRequest getCoronaCountriesRequest = new GetCoronaCountriesRequest();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        rootView = inflater.inflate(R.layout.tab1_dashboard, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //stackedChart =  rootView.findViewById(R.id.stackbarchart);
//        stackedChart.setDrawGridBackground(false);
//        stackedChart.setDrawBarShadow(false);
        init();
       // Task task = new Task(mainActivity);
       // task.execute();

       final TabDashboardViewModel model = new ViewModelProvider(this).get(TabDashboardViewModel.class);
       final LiveData<GetCoronaCountriesResponse> getCoronaCountriesResponse =  model.GetCoronaCountriesRepository(getCoronaCountriesRequest);
        getCoronaCountriesResponse.observe(getViewLifecycleOwner(), new Observer<GetCoronaCountriesResponse>() {
            @Override
            public void onChanged(GetCoronaCountriesResponse getCoronaCountriesResponse) {
                if(getCoronaCountriesResponse!= null)
                Log.d(TAG, "TabDashboard :- " + getCoronaCountriesResponse.toString());
                Log.d(TAG, "TabDashboard" + getCoronaCountriesResponse);
            }
        });
      return rootView;
    }

    private void init() {
        code = getResources().getString(R.string.code);
        message = getResources().getString(R.string.message);
        description = getResources().getString(R.string.description);
        network_error = getResources().getString(R.string.network_error);
        no_internet_connection = getResources().getString(R.string.no_internet_connection);
        enable_wifi_or_data_connection = getResources().getString(R.string.enable_wifi_or_data_connection);
        server_down = getResources().getString(R.string.server_down);
        try_after_sometime = getResources().getString(R.string.try_after_sometime);
    }

    private ArrayList<BarEntry> BarDataValue(){
       ArrayList<BarEntry> barDataVal = new ArrayList<>();
        float x = 0.f;
        Log.d(TAG, " getWorldTotalResponse.getTotalConfirmed() " + getWorldTotalResponse.getTotalConfirmed());
       if(getWorldTotalResponse.getTotalConfirmed() != null ) {
           x = getWorldTotalResponse.getTotalConfirmed().floatValue();
           Log.d(TAG, " x :- " + x);
       }
       barDataVal.add(new BarEntry(0.f, new float[] {getWorldTotalResponse.getTotalDeaths(),getWorldTotalResponse.getTotalRecovered(),(getWorldTotalResponse.getTotalConfirmed()- getWorldTotalResponse.getTotalRecovered() - getWorldTotalResponse.getTotalDeaths())}));
           // barDataVal.add(new BarEntry(0.f, new float[] { getWorldTotalResponse.getTotalDeaths(),getWorldTotalResponse.getTotalRecovered(), getWorldTotalResponse.getTotalConfirmed()}));
        return barDataVal;
    }

    class Task extends AsyncTask {
        private final Activity activity;

        public Task(Activity activity) {
            this.activity = activity;
            if(activity!=null)
            mProgressDialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();

        }

        @Override
        protected GetWorldTotalResponse doInBackground(Object[] params) {
            GetWorldTotalRequest getWorldTotalRequest = new GetWorldTotalRequest();
            getWorldTotalResponse = (GetWorldTotalResponse) GetWorldTotal(getWorldTotalRequest);

         return getWorldTotalResponse;
        }

        @Override
        protected void onPostExecute(Object o) {

            super.onPostExecute(o);
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();
            mProgressDialog = null;

            if (flag != true) {
                barDataSet = new BarDataSet(BarDataValue(), "World Covid 19 Data");

                final ArrayList<BarDataSet> list = new ArrayList<BarDataSet>();
                list.add(barDataSet);
                list.add(barDataSet);
                list.add(barDataSet);
                list.add(barDataSet);
                list.add(barDataSet);

                MyAdapter adapter = new MyAdapter(getActivity(), list);

                adapter.setOnItemClickListener(new MyAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        // BarChart current = (BarChart) list.get(position);
                        //Snackbar.make(v, "add Action for " + current..getActionName(), Snackbar.LENGTH_LONG)
                        //        .setAction("Action", null).show();

                    }

                    @Override
                    public void onItemLongClick(int position, View v) {
                        //BarChart current = (BarChart) list.get(position);
                        //Snackbar.make(v, "add Action for long press "+ current.getActionName(), Snackbar.LENGTH_LONG)
                        //       .setAction("Action", null).show();
                    }
                });
                recyclerView.setAdapter(adapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getActivity());
                //((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setItemAnimator(new DefaultItemAnimator());


                // stackedChart.notifyDataSetChanged();
                // stackedChart.invalidate();

            }
//            else{
//
//                Task task = new Task(mainActivity);
//                    task.execute();
//            }


        }

    }


    public GetWorldTotalResponse GetWorldTotal(GetWorldTotalRequest getWorldTotalRequest){

        try {
            Log.d(TAG, "GetWorldTotalSynchronousApi Call" );
            getWorldTotalResponse = csapi.GetWorldTotalSynchronous(getWorldTotalRequest);

        } catch (IOException e) {
            e.printStackTrace();
           Log.d(TAG, String.valueOf(e)+"IOException");
            error = code + network_error + System.lineSeparator() +
                    message + no_internet_connection + System.lineSeparator() +
                    description+ enable_wifi_or_data_connection;
            Log.d(TAG,  code + network_error +
                    message + no_internet_connection+
                    description + enable_wifi_or_data_connection);
            flag = true;
        }
        if(getWorldTotalResponse == null) {
            Log.d(TAG, String.valueOf(getWorldTotalResponse) + "NULL Response");
            error = code + network_error + System.lineSeparator() +
                    message + server_down + System.lineSeparator() +
                    description+ enable_wifi_or_data_connection;
            Log.d(TAG,  code + network_error +
                    message + no_internet_connection+
                    description + try_after_sometime);
            flag = true;

        }
    return getWorldTotalResponse;

    }
}
