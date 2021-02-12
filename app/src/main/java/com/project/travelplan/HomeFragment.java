package com.project.travelplan;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.transition.Slide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    TextView textView;
    String msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        HomeActivity homeActivity = (HomeActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        textView = view.findViewById(R.id.user);
        msg = homeActivity.getUser();
        textView.setText(msg);

        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z1.jpg","ภาคเหนือ(วัดร่องขุน-เชียงราย) \nNorthern in thailand"));
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z2.jpg","ภาคอีสาน(วิหารเทพวิทยาคม-นครราชสีมา) \nNortheast in thailand"));
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z3.jpg","ภาคกลาง(วัดศรีรัตนศาสดาราม-กรุงเทพฯ) \nCentral Region in thailand"));
        slideModels.add(new SlideModel("http://pharadorn.lnw.mn/API/img/Z4.jpg","ภาคใต้(เกาะกำตก-ระนอง) \nSouthern in thailand"));
        imageSlider.setImageList(slideModels,true);

        return view;
    }
}