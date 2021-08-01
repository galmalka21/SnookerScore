package com.gamimats.snookerscore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class LobbyActivity extends AppCompatActivity {

    String id;
    TextView uuid_tv;
    int game_amount;
    String Game_num = "";
    boolean MaxGames = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        String uuid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        id = uuid;
        uuid_tv = findViewById(R.id.uuid);
        uuid_tv.setText(id);
        Game_num = getIntent().getStringExtra("GAME_NUM");
        MoveToRules();
        CheckForOnlineGame();
    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    private void New_Game(){
        Button new_game_btn = findViewById(R.id.new_game_btn);
        new_game_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LobbyActivity.this,New_game_pop.class);
                startActivity(intent);
            }
        });
    }
    private void CheckForOnlineGame(){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int position = 0;
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        int map_length = (int) snapshot.getChildrenCount();
                        game_amount = map_length;
                        Map<String, Object> map = (Map<String, Object>) snapshot1.getValue();
                        String leader = map.get("leader").toString();
                        String player_1_name = map.get("player_1").toString();
                        String player_2_name = map.get("player_2").toString();
                        String player_1_gb_score = map.get("player_1_gb_score").toString();
                        String player_2_gb_score = map.get("player_2_gb_score").toString();
                        if(player_1_gb_score == null){
                            player_1_gb_score = "0";
                        }
                        if(player_2_gb_score == null){
                            player_2_gb_score = "0";
                        }
                        String room_num = snapshot1.getKey();
                        if(id.equals(leader)){
                            CreateGameBtn(400 + (position*250), player_1_name + " " + "VS" + " " + player_2_name + " " + player_1_gb_score + " - " + player_2_gb_score , room_num);
                            CreateDelete(410 + (position*250),room_num);
                        }
                        position++;

                    }
                    if(game_amount == 6){
                        MaxGames = true;
                        Toast.makeText(LobbyActivity.this, "Max Games Are 6", Toast.LENGTH_SHORT).show();
                    } else {
                        MaxGames = false;
                        New_Game();
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

    }
    private void CreateGameBtn(int TopMarg,String name,String room_number) {
        RelativeLayout relativeLayout = findViewById(R.id.lobbylayout);
        Button btnShow = new Button(this);
        btnShow.setText(name);
        RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rel_btn.topMargin = TopMarg;
        rel_btn.width = 900;
        rel_btn.height = 200;
        btnShow.setLayoutParams(rel_btn);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LobbyActivity.this,MainActivity.class);
                intent.putExtra("GAME_NUM", room_number);
                startActivity(intent);
            }
        });

        // Add Button to LinearLayout
        if (relativeLayout != null) {
            relativeLayout.addView(btnShow);

        }
    }
    private void CreateDelete(int TopMarg,String room_number) {
        RelativeLayout relativeLayout = findViewById(R.id.lobbylayout);
        Button btnShow = new Button(this);
        btnShow.setBackgroundResource(R.drawable.trash);
        RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rel_btn.topMargin = TopMarg;
        rel_btn.leftMargin = 900;
        rel_btn.width = 200;
        rel_btn.height = 170;
        btnShow.setLayoutParams(rel_btn);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(room_number);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                finish();
                startActivity(getIntent());
            }
        });

        // Add Button to LinearLayout
        if (relativeLayout != null) {
            relativeLayout.addView(btnShow);

        }
    }
    private void MoveToRules(){
        ImageButton move_to_rules = findViewById(R.id.rules_btn);
        move_to_rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LobbyActivity.this,RulesActivity.class);
                startActivity(intent);
            }
        });
    }
}