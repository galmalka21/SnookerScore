package com.gamimats.snookerscore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class New_game_pop extends Activity {

    int check = 0;
    String id;
    String player_1_name = "";
    String player_2_name = "";
    final int min = 1;
    final int max = 5000000;
    final int room_num = new Random().nextInt((max - min) + 1) + min;
    String room_number = String.valueOf(room_num);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game_pop);

        String uuid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        id = uuid;


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.6));

        fill_name();
    }
    private void fill_name(){
        EditText player_1_et = findViewById(R.id.player_1_fill_txt);
        EditText player_2_et = findViewById(R.id.player_2_fill_txt);
        Button start_game_btn = findViewById(R.id.Start_btn);
        start_game_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_1_name = player_1_et.getText().toString();
                player_2_name = player_2_et.getText().toString();
                start_game();
            }
        });

    }

private void start_game(){
    if(player_1_name.length() < 2 || player_2_name.length() < 2){
        Toast.makeText(this, "Please Fill Players Name", Toast.LENGTH_SHORT).show();
    } else {
        Toast.makeText(this, "Good Luck!", Toast.LENGTH_SHORT).show();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("player_1", player_1_name);
        map.put("player_2", player_2_name);
        map.put("leader", id);
        map.put("player_1_score", check);
        map.put("player_2_score", check);
        map.put("player_1_gb_score", check);
        map.put("player_2_gb_score",check);

        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference().child("rooms").child(room_number);
        reference5.setValue(map);

        Intent intent = new Intent(New_game_pop.this,MainActivity.class);
        intent.putExtra("GAME_NUM",room_number);
        startActivity(intent);
    }

}
}
