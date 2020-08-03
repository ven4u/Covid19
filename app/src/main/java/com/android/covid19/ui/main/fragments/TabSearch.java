package com.android.covid19.ui.main.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid19.MainActivity;
import com.android.covid19.R;
import com.android.covid19.adaptor.MyAdapter;
import com.android.covid19.api.covid19.Covid19ServiceApi;
import com.android.covid19.model.covid19.GetCountriesRequest;
import com.android.covid19.model.covid19.GetCountriesResponse;
import com.android.covid19.model.covid19.GetCountryTotalAllStatusRequest;
import com.android.covid19.model.covid19.GetCountryTotalAllStatusResponse;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import static java.lang.Thread.sleep;

public class TabSearch extends Fragment {
    final String TAG = this.getClass().getName();
    Covid19ServiceApi c = new Covid19ServiceApi();
    View rootView;
    private Spinner spinner;
    private BarChart stackedChart;
    private List<GetCountriesResponse> getCountriesResponse;
    private List<GetCountryTotalAllStatusResponse> getCountryTotalAllStatusResponse;
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
    String try_something_other_then;
    String server_down;
    String try_after_sometime;
    boolean flag = false;
    MainActivity mainActivity;
    RecyclerView recyclerView;
    AutoCompleteTextView textView;
    Hashtable<String, String> itemslag;
    String item;
    Button buttonAddToDashboard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        View rootView = inflater.inflate(R.layout.tab1_search, container, false);
        //spinner = rootView.findViewById(R.id.spinner);
        textView = (AutoCompleteTextView) rootView.findViewById(R.id.textView);
        buttonAddToDashboard = (Button) rootView.findViewById(R.id.buttonAddToDashboard);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerSearchView);
        init();
        //Task task = new Task(mainActivity);
        //task.execute();

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
            if(activity!=null) {
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();
            }
        }

        @Override
        protected List<GetCountriesResponse> doInBackground(Object[] params) {
            GetCountriesRequest getWorldTotalRequest = new GetCountriesRequest();
            getCountriesResponse = (List<GetCountriesResponse>) GetCountries(getWorldTotalRequest);
            return getCountriesResponse;
        }

        @Override
        protected void onPostExecute(Object o) {

            super.onPostExecute(o);
            if(activity!=null) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                mProgressDialog = null;
            }
            if (!flag) {
                List<String> items = new ArrayList<String>();
                Log.d(TAG, getCountriesResponse.toString());
                itemslag= new Hashtable<String, String>();
                for(int i = 0; i< getCountriesResponse.size(); i++){
                    items.add(getCountriesResponse.get(i).getCountry());
                    itemslag.put(getCountriesResponse.get(i).getCountry() , getCountriesResponse.get(i).getSlug());
                }
                //String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    items.sort(Comparator.comparing( String::toString ));
                }


                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        getActivity(), android.R.layout.simple_dropdown_item_1line,
                        items);


                textView.setAdapter(arrayAdapter);
                textView.setOnTouchListener(new View.OnTouchListener(){
                    @SuppressLint("ClickableViewAccessibility")
                    @Override
                    public boolean onTouch(View v, MotionEvent event){
                        textView.showDropDown();
                        return false;
                    }
                });
               textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        item =(String) parent.getItemAtPosition(position);
                        if (item != null) {
                            Log.d(TAG, "Item Selected :-" + item);
                            Log.d(TAG, "itemslag.contains(item) :-" + itemslag.contains(itemslag.get(item)));
                                if(itemslag.contains(itemslag.get(item))){
                                    Log.d(TAG, "ItamSlag :-"  );
                                    String slag = itemslag.get(item);
                                    GetCountriesDataTask getCountriesDataTask = new GetCountriesDataTask(mainActivity, slag);
                                    getCountriesDataTask.execute();
                                }




                            // do something with the studentInfo object
                        }
                    }
                });
                 //  textView.setOnItemSelectedListener();
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mainActivity,
//                        android.R.layout.simple_spinner_item, items);
//
//                spinner.setAdapter(adapter);
//
//                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view,
//                                               int position, long id) {
//                        //Log.v("item", (String) parent.getItemAtPosition(position));
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                        // TODO Auto-generated method stub
//                    }
//                });
            }
            else{
                Task task = new Task(mainActivity);
                task.execute();
            }


        }

    }


    class GetCountriesDataTask extends  AsyncTask{
        private Activity activity = new Activity();
        private String slag;
        public GetCountriesDataTask(Activity activity, String slag) {
        this.activity = activity;
        this.slag = slag;
        if(activity!=null)
            mProgressDialog = new ProgressDialog(activity);
    }

        @Override
        protected void onPreExecute() {
        super.onPreExecute();
        if(activity!=null) {
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();
        }
    }

        @Override
        protected List<GetCountryTotalAllStatusResponse> doInBackground(Object[] params) {
            GetCountryTotalAllStatusRequest getCountryTotalAllStatusRequest = new GetCountryTotalAllStatusRequest();
            getCountryTotalAllStatusResponse = (List<GetCountryTotalAllStatusResponse>) GetCountryTotalAllStatus(getCountryTotalAllStatusRequest, slag);

        return getCountryTotalAllStatusResponse;
    }

        @Override
        protected void onPostExecute(Object o) {

        super.onPostExecute(o);
        if(activity!=null) {
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        if (!flag) {
            barDataSet = new BarDataSet(BarDataValue(), "country :- "+ itemslag.get(item) );

            final ArrayList<BarDataSet> list = new ArrayList<BarDataSet>();
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
            if(buttonAddToDashboard !=null){
                buttonAddToDashboard.setVisibility(Button.VISIBLE);
            buttonAddToDashboard.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {
                    Log.d(TAG, "buttonAddToDashboarclicked for country :-"+ slag);
                    //AddCountries(slag);



                }
            });
            }
            // stackedChart.notifyDataSetChanged();
            // stackedChart.invalidate();

        }
        else{
            GetCountriesDataTask getCountriesDataTask = new GetCountriesDataTask(mainActivity, slag);
            getCountriesDataTask.execute();
        }


    }



    private ArrayList<BarEntry> BarDataValue(){
        ArrayList<BarEntry> barDataVal = new ArrayList<>();
        float x = 0.f;
        if(getCountryTotalAllStatusResponse.size()>=15) {
            for (int i = (getCountryTotalAllStatusResponse.size() - 15); i < getCountryTotalAllStatusResponse.size(); i++) {
                GetCountryTotalAllStatusResponse gctasr = getCountryTotalAllStatusResponse.get(i);
                Log.d(TAG, " GetCountryTotalAllStatusResponse.getConfirmed() " + gctasr.getConfirmed());
                if (gctasr.getConfirmed() != null) {
                    //x = gctasr.getConfirmed().floatValue();
                    //Log.d(TAG, " x :- " + x);
                }

                barDataVal.add(new BarEntry(x, new float[]{gctasr.getDeaths(), gctasr.getRecovered(), gctasr.getActive()}));
                // barDataVal.add(new BarEntry(0.f, new float[] { getWorldTotalResponse.getTotalDeaths(),getWorldTotalResponse.getTotalRecovered(), getWorldTotalResponse.getTotalConfirmed()}));
                x += 1.f;
            }
        }
        Log.d(TAG, "barDataVal.size():- " + barDataVal.size());
        return barDataVal;
    }


        private List<GetCountryTotalAllStatusResponse> GetCountryTotalAllStatus(GetCountryTotalAllStatusRequest getCountryTotalAllStatusRequest, String slag) {
            try {
                Log.d(TAG, "GetCountryTotalAllStatusSynchronous Call" );

                getCountryTotalAllStatusResponse = csapi.GetCountryTotalAllStatusSynchronous(getCountryTotalAllStatusRequest, slag );

            } catch (Exception e) {
                e.printStackTrace();

                error = code + network_error + System.lineSeparator() +
                        message + no_internet_connection + System.lineSeparator() +
                        description+ enable_wifi_or_data_connection;
                Log.d(TAG,  code + network_error +
                        message + no_internet_connection+
                        description + enable_wifi_or_data_connection);
                flag = true;
            }
            if(getCountriesResponse == null) {
                error = code + network_error + System.lineSeparator() +
                        message + server_down + System.lineSeparator() +
                        description+ enable_wifi_or_data_connection;
                Log.d(TAG,  code + network_error +
                        message + no_internet_connection+
                        description + try_after_sometime);
                flag = true;

            }
            return getCountryTotalAllStatusResponse;
        }

    }

   private List<GetCountriesResponse> GetCountries(GetCountriesRequest getCountriesRequest) {

        try {
            Log.d(TAG, "GetCountriesSynchronousApi Call" );
            getCountriesResponse = csapi.GetCountriesSynchronous(getCountriesRequest);

        } catch (IOException e) {
            e.printStackTrace();

            error = code + network_error + System.lineSeparator() +
                    message + no_internet_connection + System.lineSeparator() +
                    description+ enable_wifi_or_data_connection;
            Log.d(TAG,  code + network_error +
                    message + no_internet_connection+
                    description + enable_wifi_or_data_connection);
            flag = true;
        }
        if(getCountriesResponse == null) {
            error = code + network_error + System.lineSeparator() +
                    message + server_down + System.lineSeparator() +
                    description+ enable_wifi_or_data_connection;
            Log.d(TAG,  code + network_error +
                    message + no_internet_connection+
                    description + try_after_sometime);
            flag = true;

        }
        return getCountriesResponse;
    }



}
