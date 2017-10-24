package com.Hackathon6.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends Activity {

    private TextView robotGroupTitleTV;
    private RecyclerView amenityRecyclerView;

    private TextView robotGroupTitleTV2;
    private RecyclerView policiesRecyclerView;

    private TitleGridView amenityGridView;
    private TitleGridView policiesGridView;

    //    private amenityRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        amenityGridView = (TitleGridView) findViewById(R.id.id_grid_amenity);
        amenityGridView.setParams("Hotel Amenities", amenityData());

        policiesGridView = (TitleGridView) findViewById(R.id.id_grid_policy);
        policiesGridView.setParams("Hotel Policies", policiesData());
    }

    public List<Map<String, Object>> amenityData() {
        String[] hotelAmenityTitleList = new String[]{
                "Hotel Amenities",
                "Family Friendly Amenities",
                "Internet",
                "Parking",
                "Room Amenities",
                "Where to Eat",
                "Nearby Things to Do"
        };

        int[] hotelAmenityImageList = new int[]{
                R.drawable.ic_hotel,
                R.drawable.ic_hotel_swim,
                R.drawable.ic_network,
                R.drawable.ic_parking,
                R.drawable.ic_guest_room,
                R.drawable.ic_eat,
                R.drawable.ic_neaby_play
        };

        List<Map<String, Object>> models = new ArrayList<>();
        for (int i = 0; i < hotelAmenityTitleList.length; i++) {
            Map<String, Object> model = new HashMap<>();
            model.put("title", hotelAmenityTitleList[i]);
            model.put("image", hotelAmenityImageList[i]);
            models.add(model);
        }
        return models;
    }

    private List<Map<String, Object>> policiesData() {
        String[] hotelPoliciesTitleList = new String[]{
                "Check-in",
                "Check-out",
                "Payment types",
                "Children and extra beds",
                "Pets",
                "Fees",
                "Optional extras"};

        int[] hotelPoliciesImageList = new int[]{
                R.drawable.ic_check_in,
                R.drawable.ic_check_out,
                R.drawable.ic_pay_type,
                R.drawable.ic_children,
                R.drawable.ic_pets,
                R.drawable.ic_fee,
                R.drawable.ic_other_service
        };

        List<Map<String, Object>> models = new ArrayList<>();
        for (int i = 0; i < hotelPoliciesTitleList.length; i++) {
            Map<String, Object> model = new HashMap<>();
            model.put("title", hotelPoliciesTitleList[i]);
            model.put("image", hotelPoliciesImageList[i]);
            models.add(model);
        }
        return models;
    }

    public void backView(View view) {
        finish();
    }
}


