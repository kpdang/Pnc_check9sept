package com.example.lenovo.pnc_check9sept;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {
    String hint;
    Boolean taken_hint=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

    }

    public void showHint(View view)
    {
        String p_no="Prime number";
        String p_no_definition="Any natural no. greater than 1 is called a prime no. if it has no positive divisors other than 1 and itself";
        TextView prime = (TextView) findViewById(R.id.textView3);
        TextView definition=(TextView) findViewById(R.id.textView4);
        prime.setText(p_no);
        definition.setText(p_no_definition);
        taken_hint=true;//since the user has taken a hint so make taken_hint variable as true
    }

    @Override
    public void onBackPressed() {//when back button is pressed this function will run
        Intent intent=new Intent();
        intent.putExtra("hint",taken_hint);//we are passing taken_hint from HintActivity to MainActivity
        setResult(RESULT_OK,intent);
        finish();


    }
}
