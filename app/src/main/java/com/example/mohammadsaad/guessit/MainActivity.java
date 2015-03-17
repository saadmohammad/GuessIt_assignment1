package com.example.mohammadsaad.guessit;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private int Number ;
    private int Attempts = 0;
    private TextView  attemptsCounter;
    private Random randomNumber;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       randomNumber = new Random();

        Number = randomNumber.nextInt(1000);
          attemptsCounter = (TextView) findViewById(R.id.attemptsCount);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void checkNumber(int GuessedNumber){

        Log.i("debug", "check_number");


        if ((GuessedNumber > 1000) || (GuessedNumber < 0) ){

            Toast.makeText(this, "Your guess should be in the range of 0 - 1000", Toast.LENGTH_LONG).show();
            clearTextView();


        }

        else if (GuessedNumber == Number){

            Toast.makeText(this, "Correct asnwer!", Toast.LENGTH_LONG).show();
            clearTextView();
            showNewGame();


        }

       else if ((GuessedNumber > (Number - 5) && GuessedNumber < (Number + 5) )){

            Toast.makeText(this, "Very Close!", Toast.LENGTH_LONG).show();
            clearTextView();
            incrementAttempts();

        } else if (GuessedNumber < Number ) {

            Toast.makeText(this, "Your guess is smaller", Toast.LENGTH_LONG).show();
            clearTextView();
            incrementAttempts();

        } else{

            Toast.makeText(this, "Your guess is greater", Toast.LENGTH_LONG).show();
            clearTextView();
            incrementAttempts();

        }


    }

    public void on_click(View view) {

        Log.i("debug", "on_click");
       int guessedNumber = getGuessedNumber();

        checkNumber(guessedNumber);


    }

    public int getGuessedNumber(){
        Log.i("debug", "check_getGuessedNumber");


        int number;
        String input;

        TextView guessedNumber;

        guessedNumber = (TextView) findViewById(R.id.userGuessedNumber);
        input = String.valueOf(guessedNumber.getText());
        input.trim();

        if (input == null) return 0;
        else {
            number = Integer.parseInt(input);
            return number;
        }
    }

    public void clearTextView(){

     TextView guessedNumber = (TextView) findViewById(R.id.userGuessedNumber);
     guessedNumber.setText("");


    }

    public void incrementAttempts(){

        Attempts++;
        setAttempts();

    }


    public void setAttempts(){

        attemptsCounter.setText("Number of your Attempts: " + this.Attempts);

    }


    public void new_onclick(View view) {

        attemptsCounter.setText("");
        Number = randomNumber.nextInt(1000);
        Toast.makeText(this, "New Game!", Toast.LENGTH_LONG).show();
        hideNewGame();
        Attempts = 0;
    }

    public void showNewGame(){

        Button newGame = (Button) findViewById(R.id.newGameButton);
        newGame.setVisibility(View.VISIBLE);
    }

    public void hideNewGame(){

        Button newGame = (Button) findViewById(R.id.newGameButton);
        newGame.setVisibility(View.INVISIBLE);
    }
}
