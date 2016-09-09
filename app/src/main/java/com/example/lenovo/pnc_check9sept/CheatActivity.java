package com.example.lenovo.pnc_check9sept;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    int num;
    String cheat;
    Boolean has_cheaten=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        //  String cheat =getIntent().getStringExtra("answer");
        //TextView  answer = (TextView) findViewById(R.id.textView6);
        //answer.setText(cheat);
        num =getIntent().getIntExtra("number",2);


    }

    public void showCheat(View view)
    {
        String ans="Answer";
        TextView answer = (TextView) findViewById(R.id.textView5);
        answer.setText(ans);
        String cheat;
        TextView cheat_ans = (TextView) findViewById(R.id.textView6);

        int n = num;
        int k;
        for (k = 2; k < n; k++) {
            if (n % k == 0) {
                ans=num + " " + "is not a prime number.";

                break;
            }
        }
        if (k == n) {
            cheat=num + " " + "is a prime number.";
        }
        cheat_ans.setText(ans);
        has_cheaten=true;//since the user has cheated so make has_cheaten variable as true
    }
    @Override
    public void onBackPressed() {//when back button is pressed this function will run
        Intent intent=new Intent();
        intent.putExtra("cheat",has_cheaten);//we are passing has_cheaten from CheatActivity to MainActivity
        setResult(RESULT_OK,intent);
        finish();


    }

}


