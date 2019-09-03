package com.example.footballscoreapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FootballAdapter adapter;
    private ArrayList<ModelJadwal> JadwalArraylist;
    private LinearLayout LoadingBar;
    private LinearLayout LinRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LoadingBar = (LinearLayout) findViewById(R.id.loading);
        LinRV = (LinearLayout) findViewById(R.id.LinearRV);
        JadwalArraylist = new ArrayList<>();
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray datajadwal = response.getJSONArray("events");
                            ModelJadwal  jadwal;
                            for (int i = 0; i < datajadwal.length(); i++) {
                                jadwal=new ModelJadwal();
                                JSONObject dataku=datajadwal.getJSONObject(i);
                                jadwal.setStrHomeTeam(dataku.getString("strHomeTeam"));
                                jadwal.setStrAwayTeam(dataku.getString("strAwayTeam"));
                                jadwal.setStrDate(dataku.getString("strDate"));
                                jadwal.setStrTime(dataku.getString("strTime"));
                                JadwalArraylist.add(jadwal);
                            }
                            adapter = new FootballAdapter(JadwalArraylist);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

                            recyclerView.setLayoutManager(layoutManager);

                            recyclerView.setAdapter(adapter);
                            Log.d("hasil", "jumlahdata:" + JadwalArraylist.size());

                            LoadingBar.setVisibility(View.GONE);
                            LinRV.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }
}
