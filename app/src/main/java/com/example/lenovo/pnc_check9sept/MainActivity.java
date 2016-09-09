//the background image of the app has been extracted from the internet
package com.example.lenovo.primenochecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int num;// variable to store the random no generated which will be checked for primality
    int savenum=0;//variable to store state of the num variable
    int value=0;//variable that will check if onResume() is called first time when our app is launched or after screen rotation
    int savevalue=0;//variable to store state of the value variable
    boolean taken_hint;
    boolean has_cheaten=false;//if a person has cheated then has_cheaten will be equal to true otherwise false
    boolean save_has_cheaten=false;//variable to store state of the has_cheaten variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Random rand=new Random();
        if(value==0) { //i.e, if value is zero i.e, if onResume is called when our app is launched and not after screen rotation
            value = 1;//then set value=1 so that when wev rotate screen code inside if block doesn't run
            num = rand.nextInt(1000) + 1;//generate a random no between 1 and 1000
        }
        String qstn = num +" "+ "is a prime no";
        TextView question = (TextView) findViewById(R.id.textView);
        question.setText(qstn);//display the question
        //i++;
    }

    public void checkprime1(View view) {//this function will be called when user clicks on CORRECT Button.
        int n = num;
        int k;

        for (k = 2; k < n; k++) {
            if (n % k == 0 && has_cheaten == false) {//if the number is not prime and the user has not cheated then if condition will be true
                Toast t = Toast.makeText(getApplicationContext(), "FALSE", Toast.LENGTH_SHORT);
                //if no is not prime and the user has not cheated then a toast with message FALSE will be displayed
                t.show();
                break;
            } else if (n % k == 0 && has_cheaten == true) {//if the number is not prime and the user has cheated then else if condition will be true
                Toast t = Toast.makeText(getApplicationContext(), "FALSE BUT YOU CHEATED!!!", Toast.LENGTH_SHORT);
                t.show();
                break;
            }
        }
        if (k == n && has_cheaten==false) {//if the number is prime and the user has not cheated then if condition will be true
            Toast t = Toast.makeText(getApplicationContext(), "TRUE", Toast.LENGTH_SHORT);
            //if the no is prime and the user has not cheated then a toast with message TRUE will be displayed
            t.show();
        } else if (k == n && has_cheaten == true) {//if the number is prime and the user has cheated then else if condition will be true
            Toast t = Toast.makeText(getApplicationContext(), "TRUE BUT YOU CHEATED!!!", Toast.LENGTH_SHORT);
            t.show();

        }

    }

    public void giveHint(View view)//this function will be called everytime when the user clicks on the HINT button
    {
        Intent in =new Intent(this,HintActivity.class);//defining the intent
        //Intent will start the HintActivity from MainActivity
        startActivityForResult(in,5);

    }
    public void giveAnswer(View view)//this function will be called everytime when the user clicks on the CHEAT button
    {
        Intent in=new Intent(this,CheatActivity.class);
        in.putExtra("number",num);
        startActivityForResult(in,6);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==5)
        {
            taken_hint=data.getBooleanExtra("hint",false);
            if(taken_hint) {//if hint has been taken then if will become true
                Toast t = Toast.makeText(getApplicationContext(), "You took a HINT", Toast.LENGTH_SHORT);
                t.show();
            }
            else//if hint has not been taken then else block will run
            {
                Toast t = Toast.makeText(getApplicationContext(), "You didn't take a HINT", Toast.LENGTH_SHORT);
                t.show();
            }


        }
        if(requestCode==6)
        {
            has_cheaten=data.getBooleanExtra("cheat",false);
            if(has_cheaten) {//if user has cheated then if condition will become true
                Toast t = Toast.makeText(getApplicationContext(), "You CHEATED!!!", Toast.LENGTH_SHORT);
                t.show();
            }
            else//if user has not cheated then else block will run
            {
                Toast t = Toast.makeText(getApplicationContext(), "You didn't cheat", Toast.LENGTH_SHORT);
                t.show();
            }

        }
    }



    public void nextquestion(View view) {//this function will be called everytime the user presses the NEXT button.
        //this method wil display the next question
        has_cheaten=false;//to set the cheat variable to false for the next question because user cannot cheat before the next question is generated
        Random rand = new Random();
        num = rand.nextInt(1000) + 1;
        String qstn = num + " " +"is a prime no";
        TextView question = (TextView) findViewById(R.id.textView);
        question.setText(qstn);
    }

    public void checkprime2(View view) {//this function will be called when user clicks on INCORRECT Button
        int n=num;
        int k;
        for(k=2;k<n;k++)
        {
            if(n%k==0 && has_cheaten==false)//if the number is not prime and the user has not cheated then if condition will be true
            {
                Toast t=Toast.makeText(getApplicationContext(),"TRUE",Toast.LENGTH_SHORT);
                //if no is not prime and the user has not cheated then a toast with message TRUE will be displayed
                t.show();
                break;
            }
            else if (n % k == 0 && has_cheaten == true)//if the number is not prime and the user has cheated then else if condition will be true
            {
                Toast t = Toast.makeText(getApplicationContext(), "TRUE BUT YOU CHEATED!!!", Toast.LENGTH_SHORT);
                t.show();

                break;
            }

        }
        if(k==n && has_cheaten==false)//if the number is prime and the user has not cheated then if condition will be true
        {
            Toast t=Toast.makeText(getApplicationContext(),"FALSE",Toast.LENGTH_SHORT);
            //if the no is prime and the user has not cheated then a toast with message FALSE will be displayed
            t.show();
        }
        else if (k == n && has_cheaten == true)//if the number is prime and the user has cheated then else if condition will be true
        {
            Toast t = Toast.makeText(getApplicationContext(), "FALSE BUT YOU CHEATED!!!", Toast.LENGTH_SHORT);
            t.show();

        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {//function to save the activity state
        savedInstanceState.putInt("savenum",num);
        savedInstanceState.putInt("savevalue",value);
        savedInstanceState.putBoolean("save_has_cheaten",has_cheaten);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {//function to restore the activity state
        super.onRestoreInstanceState(savedInstanceState);
        num = savedInstanceState.getInt("savenum");
        value = savedInstanceState.getInt("savevalue");
        has_cheaten = savedInstanceState.getBoolean("save_has_cheaten");

    }






}



