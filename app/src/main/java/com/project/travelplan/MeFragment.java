package com.project.travelplan;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MeFragment extends Fragment {
    DbHelper dbh;

    ListView MyList;
    private ArrayList<String> name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //HomeActivity homeActivity = (HomeActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_me, container, false);

//        searchView = (SearchView) view.findViewById(R.id.search_bar);

//        textView = view.findViewById(R.id.user);
//        msg = homeActivity.getUser();
//        textView.setText(msg);

        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z1.jpg", "ภาคเหนือ(วัดร่องขุน-เชียงราย) \nNorthern in thailand"));
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z2.jpg", "ภาคอีสาน(วิหารเทพวิทยาคม-นครราชสีมา) \nNortheast in thailand"));
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z3.jpg", "ภาคกลาง(วัดศรีรัตนศาสดาราม-กรุงเทพฯ) \nCentral Region in thailand"));
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z4.jpg", "ภาคใต้(เกาะกำตก-ระนอง) \nSouthern in thailand"));
        imageSlider.setImageList(slideModels, true);

        MyList = (ListView) view.findViewById(R.id.MyList);
        name = new ArrayList<String>();

        dbh = new DbHelper(getActivity());
        Cursor resDat = dbh.getAllDataV2();
        if(resDat.getCount() == 0){
            Toast.makeText(getActivity(),"ไม่มีสถานที่ท่องเที่ยวของคุณ!!!", Toast.LENGTH_SHORT).show();
        }else{
            StringBuffer dataBuff = new StringBuffer();
            while (resDat.moveToNext()){
                name.add(resDat.getString(0));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, name);
            MyList.setAdapter(adapter);
        }

        MyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

//        TravelDAO todoListDAO = new TravelDAO(getActivity());
//        TravelDAO.open();
//        ArrayList<String> myListView = todoListDAO.getAllName();
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myListView);
//        MyList.setAdapter(adapter);
//        TravelDAO.close();

        return view;
    }
}