package com.example.simplewordle

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    var guess = ""
    var attempts = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val submit = findViewById<Button>(R.id.Guess)

        submit.setOnClickListener{
            try{
                guess = (findViewById<View>(R.id.guessInput) as EditText).text.toString().uppercase()
                (findViewById<View>(R.id.guessInput) as EditText).setText("")
                when (attempts){
                    0 -> {
                        findViewById<TextView>(R.id.guess1).text = guess
                        findViewById<TextView>(R.id.result1).text = checkGuess(guess)
                        attempts+=1
                        findViewById<TextView>(R.id.resultsText).text = "${3-attempts} guesses left"
                    }
                    1 -> {
                        findViewById<TextView>(R.id.guess2).text = guess
                        findViewById<TextView>(R.id.result2).text = checkGuess(guess)
                        attempts+=1
                        findViewById<TextView>(R.id.resultsText).text = "${3-attempts} guesses left"
                    }
                    2 -> {
                        findViewById<TextView>(R.id.guess3).text = guess
                        findViewById<TextView>(R.id.result3).text = checkGuess(guess)
                        attempts+=1
                        findViewById<TextView>(R.id.resultsText).text = "${3-attempts} guesses left"
                        findViewById<TextView>(R.id.messageText).text = "You are out of guesses! The answer was ${wordToGuess}"
                        submit.isClickable = false
                        submit.setBackgroundColor(Color.GRAY)
                    }
                }
            }catch (t: Throwable){
                android.widget.Toast.makeText(this, "INPUT ERROR", Toast.LENGTH_SHORT).show()
            }
            //used this line in testing to see answer
            //findViewById<TextView>(R.id.guess1).text = wordToGuess
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}

