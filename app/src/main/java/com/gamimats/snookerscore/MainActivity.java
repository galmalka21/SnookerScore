package com.gamimats.snookerscore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int player_1_score;
    int player_2_score;
    int player_1_fouls;
    int player_2_fouls;
    int player_1_gb_score;
    int player_2_gb_score;

    String player_1_name = "";
    String player_2_name = "";

    String Game_num = "";

    TextView player_1_name_tv;
    TextView player_2_name_tv;
    TextView player_1_gb_score_tv;
    TextView player_2_gb_score_tv;
    TextView player_1_fouls_score;
    TextView player_2_fouls_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Game_num = getIntent().getStringExtra("GAME_NUM");
        player_1_name_tv = findViewById(R.id.player_1_name);
        player_2_name_tv = findViewById(R.id.player_2_name);
        player_1_gb_score_tv = findViewById(R.id.player_1_gb_score);
        player_2_gb_score_tv = findViewById(R.id.player_2_gb_score);
        player_1_fouls_score = findViewById(R.id.fouls_score_1);
        player_2_fouls_score = findViewById(R.id.fouls_score_2);
        BackToMain();
        start_game();
        roll_dice();
        Endgame();
        Player_Fouls();
        Player_1_Reset();
        Player_2_Reset();


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
                Intent intent = new Intent(MainActivity.this,LobbyActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Player_1_CB(View v) {
        CheckBox checkBox_player_1 = findViewById(R.id.player_1_cp);
        CheckBox checkBox_player_2 = findViewById(R.id.player_2_cp);
        if(checkBox_player_1.isChecked()) {
            player_1_score();
            checkBox_player_2.setChecked(false);
        }
        else {
            popup();
        }

    }
    public void Player_2_CB(View v) {
        CheckBox checkBox_player_1 = findViewById(R.id.player_1_cp);
        CheckBox checkBox_player_2 = findViewById(R.id.player_2_cp);
        if(checkBox_player_2.isChecked()) {
            player_2_score();
            checkBox_player_1.setChecked(false);
        }
        else {
            popup();
        }

    }
    private void popup(){
        ImageButton Gplus1 = findViewById(R.id.player_1_1);
        ImageButton Gplus2 = findViewById(R.id.player_1_2);
        ImageButton Gplus3 = findViewById(R.id.player_1_3);
        ImageButton Gplus4 = findViewById(R.id.player_1_4);
        ImageButton Gplus5 = findViewById(R.id.player_1_5);
        ImageButton Gplus6 = findViewById(R.id.player_1_6);
        ImageButton Gplus7 = findViewById(R.id.player_1_7);
        Gplus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please Choose a Player", Toast.LENGTH_SHORT).show();
            }
        });
        Gplus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please Choose a Player", Toast.LENGTH_SHORT).show();
            }
        });
        Gplus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please Choose a Player", Toast.LENGTH_SHORT).show();
            }
        });
        Gplus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please Choose a Player", Toast.LENGTH_SHORT).show();
            }
        });
        Gplus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please Choose a Player", Toast.LENGTH_SHORT).show();
            }
        });
        Gplus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please Choose a Player", Toast.LENGTH_SHORT).show();
            }
        });
        Gplus7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please Choose a Player", Toast.LENGTH_SHORT).show();
            }
        });

        }
    private void Player_Fouls(){
        Button player_1_foul = findViewById(R.id.player_1_foul_btn);
        Button player_2_foul = findViewById(R.id.player_2_foul_btn);
        player_1_foul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_2_score = player_2_score+4;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
                reference.setValue(player_2_score);
                player_1_fouls = player_1_fouls+1;
                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_fouls");
                reference2.setValue(player_1_fouls);
            }
        });

        player_2_foul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_1_score = player_1_score+4;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
                reference.setValue(player_1_score);
                player_2_fouls = player_2_fouls+1;
                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_fouls");
                reference2.setValue(player_2_fouls);
            }
        });
        refreshScore();
    }
    private void roll_dice(){
        ImageButton dice = findViewById(R.id.diceimage);
        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                String player[] = {player_1_name,player_2_name};
                int index = r.nextInt(player.length - 0) + 0;
                Toast.makeText(MainActivity.this, player[index]+ " " + "Is Starting", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void start_game(){
        retrive_data();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
//        reference.setValue(player_1_score);
//        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
//        reference2.setValue(player_2_score);
    }
    private void Player_1_Reset(){
        Button greset = findViewById(R.id.player_1_reset_btn);
        greset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
                reference.setValue(0);
                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_fouls");
                reference2.setValue(0);
                player_1_score = 0;
                player_1_fouls = 0;
                refreshScore();
            }
        });
    }
    private void Player_2_Reset(){
        Button ureset = findViewById(R.id.player_2_reset_btn);
        ureset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
                reference.setValue(0);
                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_fouls");
                reference2.setValue(0);
                player_2_score = 0;
                player_2_fouls = 0;
                refreshScore();
            }
        });
        popup();
    }
    private void reset_score(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
        reference.setValue(0);
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_fouls");
        reference2.setValue(0);
        player_1_score = 0;
        player_1_fouls = 0;

        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
        reference3.setValue(0);
        DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_fouls");
        reference4.setValue(0);
        player_2_score = 0;
        player_2_fouls = 0;
        refreshScore();
    }

    private void retrive_data(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                player_1_name = value;
                player_1_name_tv.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2");
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                player_2_name = value;
                player_2_name_tv.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
        reference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                if(value == null){

                }else{
                    player_1_score = Integer.valueOf(String.valueOf(value));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
        reference4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                if(value == null){

                }else{
                    player_2_score = Integer.valueOf(String.valueOf(value));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_gb_score");
        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                if(value == null){
                    player_1_gb_score = Integer.valueOf(String.valueOf(0));
                    player_1_gb_score_tv.setText("- 0");
                } else {
                    Long value2 = snapshot.getValue(Long.class);
                    player_1_gb_score = Integer.valueOf(String.valueOf(value2));
                    player_1_gb_score_tv.setText("-" + " " + value2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reference6 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_gb_score");
        reference6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                if(value == null){
                    player_2_gb_score = Integer.valueOf(String.valueOf(0));
                    player_2_gb_score_tv.setText("- 0");
                } else {
                    Long value2 = snapshot.getValue(Long.class);
                    player_2_gb_score = Integer.valueOf(String.valueOf(value2));
                    player_2_gb_score_tv.setText("-" + " " + value2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void Endgame(){
        Button endgame_btn = findViewById(R.id.endgame_btn);
        endgame_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player_1_score > player_2_score){
                    Toast.makeText(MainActivity.this, player_1_name + " " + "Wins!", Toast.LENGTH_SHORT).show();
                    player_1_gb_score = player_1_gb_score +1;
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_gb_score");
                    reference.setValue(player_1_gb_score);
                }
                if(player_2_score > player_1_score){
                    Toast.makeText(MainActivity.this, player_2_name + " " + "Wins!", Toast.LENGTH_SHORT).show();
                    player_2_gb_score = player_2_gb_score +1;
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_gb_score");
                    reference.setValue(player_2_gb_score);
                }
                if(player_1_score == player_2_score){
                    Toast.makeText(MainActivity.this, "Its a Tie!", Toast.LENGTH_SHORT).show();

                }
                reset_score();
            }
        });

    }
    private void refreshScore(){
        TextView player_1_score = findViewById(R.id.player_1_score);
        TextView player_2_score = findViewById(R.id.player_2_score);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                player_1_score.setText(String.valueOf(value));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                player_2_score.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_fouls");
        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                player_1_fouls_score.setText(String.valueOf(value));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_fouls");
        reference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                player_2_fouls_score.setText(String.valueOf(value));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_gb_score");
        reference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                player_1_gb_score_tv.setText(String.valueOf(value));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reference6 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_gb_score");
        reference6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                player_2_gb_score_tv.setText(String.valueOf(value));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void player_1_score(){
        ImageButton Gplus1 = findViewById(R.id.player_1_1);
        ImageButton Gplus2 = findViewById(R.id.player_1_2);
        ImageButton Gplus3 = findViewById(R.id.player_1_3);
        ImageButton Gplus4 = findViewById(R.id.player_1_4);
        ImageButton Gplus5 = findViewById(R.id.player_1_5);
        ImageButton Gplus6 = findViewById(R.id.player_1_6);
        ImageButton Gplus7 = findViewById(R.id.player_1_7);
        //Break
        Gplus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_1_score = player_1_score +1;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
                reference.setValue(player_1_score);
            }
        });
        Gplus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_1_score = player_1_score+2;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
                reference.setValue(player_1_score);
            }
        });
        Gplus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_1_score = player_1_score+3;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
                reference.setValue(player_1_score);
            }
        });
        Gplus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_1_score = player_1_score+4;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
                reference.setValue(player_1_score);
            }
        });
        Gplus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_1_score = player_1_score+5;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
                reference.setValue(player_1_score);
            }
        });
        Gplus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_1_score = player_1_score+6;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
                reference.setValue(player_1_score);
            }
        });
        Gplus7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_1_score = player_1_score+7;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
                reference.setValue(player_1_score);
            }
        });
        refreshScore();
    }
    private void player_2_score(){

        ImageButton Uplus1 = findViewById(R.id.player_1_1);
        ImageButton Uplus2 = findViewById(R.id.player_1_2);
        ImageButton Uplus3 = findViewById(R.id.player_1_3);
        ImageButton Uplus4 = findViewById(R.id.player_1_4);
        ImageButton Uplus5 = findViewById(R.id.player_1_5);
        ImageButton Uplus6 = findViewById(R.id.player_1_6);
        ImageButton Uplus7 = findViewById(R.id.player_1_7);
        //Break
        Uplus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_2_score = player_2_score +1;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
                reference.setValue(player_2_score);
            }
        });
        Uplus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_2_score = player_2_score+2;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
                reference.setValue(player_2_score);
            }
        });
        Uplus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_2_score = player_2_score+3;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
                reference.setValue(player_2_score);
            }
        });
        Uplus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_2_score = player_2_score+4;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
                reference.setValue(player_2_score);
            }
        });
        Uplus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_2_score = player_2_score+5;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
                reference.setValue(player_2_score);
            }
        });
        Uplus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_2_score = player_2_score+6;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
                reference.setValue(player_2_score);
            }
        });
        Uplus7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player_2_score = player_2_score+7;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
                reference.setValue(player_2_score);
            }
        });
        refreshScore();
    }

    @Override
    protected void onStop() {
        super.onStop();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
        reference.setValue(player_1_score);
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
        reference2.setValue(player_2_score);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_1_score");
        reference.setValue(player_1_score);
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("rooms").child(Game_num).child("player_2_score");
        reference2.setValue(player_2_score);
    }
}