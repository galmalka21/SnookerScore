package com.gamimats.snookerscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        BackToMain();
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    private void BackToMain(){
        ImageButton Back_Arrow = findViewById(R.id.backarrow);
        Back_Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RulesActivity.this,LobbyActivity.class);
                startActivity(intent);
            }
        });
    }
}
//
//    private void player_2_score(){
//
//        Button Uplus1 = findViewById(R.id.player_2_1);
//        Button Uplus2 = findViewById(R.id.player_2_2);
//        Button Uplus3 = findViewById(R.id.player_2_3);
//        Button Uplus4 = findViewById(R.id.player_2_4);
//        Button Uplus5 = findViewById(R.id.player_2_5);
//        Button Uplus6 = findViewById(R.id.player_2_6);
//        Button Uplus7 = findViewById(R.id.player_2_7);
//        //Break
//        Uplus1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player_2_score = player_2_score +1;
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
//                reference.setValue(player_2_score);
//            }
//        });
//        Uplus2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player_2_score = player_2_score+2;
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
//                reference.setValue(player_2_score);
//            }
//        });
//        Uplus3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player_2_score = player_2_score+3;
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
//                reference.setValue(player_2_score);
//            }
//        });
//        Uplus4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player_2_score = player_2_score+4;
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
//                reference.setValue(player_2_score);
//            }
//        });
//        Uplus5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player_2_score = player_2_score+5;
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
//                reference.setValue(player_2_score);
//            }
//        });
//        Uplus6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player_2_score = player_2_score+6;
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
//                reference.setValue(player_2_score);
//            }
//        });
//        Uplus7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player_2_score = player_2_score+7;
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
//                reference.setValue(player_2_score);
//            }
//        });
//        refreshScore();
//    }