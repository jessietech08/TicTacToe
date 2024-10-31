package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var isPlayerXTurn : Boolean = true

        val buttons = arrayOf(
            R.id.button, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9
        )

        // gets textView by id
        val textView : TextView = findViewById(R.id.textView)

        // iterates through array of buttons
        for (id in buttons) {
            val button : Button = findViewById(id)
            // activates when user clicks button
            button.setOnClickListener {
                // if the button has no text
                if (button.text.isEmpty()) {
                    if (isPlayerXTurn) {
                        button.text = "X"
                        textView.text = "Player O's turn"
                    }
                    else {
                        button.text = "0"
                        textView.text = "Player X's turn"
                    }
                    // after every button click, this line switches the turn to the other player
                    // goes back and forth
                    isPlayerXTurn = !isPlayerXTurn
                }

            }
        }

        // gets newGame by id
        val newGame : Button = findViewById(R.id.newGameButton)

        // clears text from all buttons if newGame is clicked
        newGame.setOnClickListener {
            for (id in buttons) {
                val button: Button = findViewById(id)
                button.text = ""
            }
            // change text back to Player X
            textView.text = "Player X's turn"
            // set to true
            isPlayerXTurn = true
        }
    }
}