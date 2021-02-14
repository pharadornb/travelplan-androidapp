package com.project.travelplan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private ListView sampleList;
    private ArrayList<String> exData;
    private ArrayList<String> lat;
    private ArrayList<String> log;
    private ArrayList<String> name;
    private ProgressDialog progressDialog;

    TextView textView;
    String msg;
    private TextView mTextViewResult;
    private RequestQueue mQueue;


    private static final String BASE_URL = "http://pharadorn.lnw.mn/API/getlocation.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HomeActivity homeActivity = (HomeActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z1.jpg", "ภาคเหนือ(วัดร่องขุ่น-เชียงราย)"));
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z2.jpg", "ภาคอีสาน(วิหารเทพวิทยาคม-นครราชสีมา)"));
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z3.jpg", "ภาคกลาง(พระปรางค์วัดอรุณฯ-กรุงเทพฯ)"));
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z4.jpg", "ภาคใต้(เกาะกำตก-ระนอง)"));
        imageSlider.setImageList(slideModels, true);

        sampleList = (ListView) view.findViewById(R.id.sampleList);
        exData = new ArrayList<String>();
        lat = new ArrayList<String>();
        log = new ArrayList<String>();
        name = new ArrayList<String>();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setCancelable(false);
                progressDialog.setMessage("กำลังโหลด...");
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                //exData.add("OKKKKKKKK");
                try {
                    //exData.add("OKKKKKKKK222222222");
                    URL url = new URL("http://pharadorn.lnw.mn/API/location_api.php");

                    URLConnection urlConnection = url.openConnection();

                    HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
                    httpURLConnection.setAllowUserInteraction(false);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();

                    InputStream inputStream = null;

                    if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
                        inputStream = httpURLConnection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;

                    while ((line=reader.readLine()) != null){
                        stringBuilder.append(line + "\n");
                    }
                    inputStream.close();
                    Log.d("JSON Result", stringBuilder.toString());

                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                    JSONArray exArray = jsonObject.getJSONArray("TravelPlan");

                    for(int i=0; i<exArray.length(); i++){
                        JSONObject jsonObj = exArray.getJSONObject(i);

                        if(jsonObj.getString("location") == "null" || jsonObj.getString("name") == "null"){

                        }else{
                            exData.add("\n" + jsonObj.getString("name") + "\n @" + jsonObj.getString("location") + "\n");
                        }
                        name.add(jsonObj.getString("name"));
                        lat.add(jsonObj.getString("latitude"));
                        log.add(jsonObj.getString("logitude"));
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, exData);
                sampleList.setAdapter(myAdapter);
                progressDialog.dismiss();
            }
        }.execute();

        sampleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putString("key_lat", lat.get(position));
                bundle.putString("key_log", log.get(position));
                bundle.putString("key_name", name.get(position));

                Fragment fragment = new MapsFragment2();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });

       return view;
    }
}