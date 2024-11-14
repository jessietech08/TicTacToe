package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Button>
    var isPlayerXTurn : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize buttons array
        buttons = arrayOf(
            findViewById(R.id.button),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )

        // gets textView by id
        val textView: TextView = findViewById(R.id.textView)

        // iterates through array of buttons
        for (button in buttons) {
            // activates when user clicks button
            button.setOnClickListener {
                // if the button has no text
                if (button.text.isEmpty()) {
                    button.text = if (isPlayerXTurn) "X" else "O"
                }
                if (checkWin()) {
                    textView.text = if (isPlayerXTurn) "Player X Wins!" else "Player O Wins!"
                } else {
                    isPlayerXTurn = !isPlayerXTurn
                    textView.text = "Player ${if (isPlayerXTurn) 'X' else 'O'}'s turn"
                }

            }
        }

        // gets newGame by id
        val newGame: Button = findViewById(R.id.newGameButton)

        // clears text from all buttons if newGame is clicked
        newGame.setOnClickListener {
            for (button in buttons) {
                button.text = ""
            }
            textView.text = "Player X's turn"
            isPlayerXTurn = true
        }
    }

    private fun checkWin(): Boolean
    {
        val winningCombinations = arrayOf(
            arrayOf(0, 1, 2),
            arrayOf(3, 4, 5),
            arrayOf(6, 7, 8),
            arrayOf(0, 3, 6),
            arrayOf(1, 4, 7),
            arrayOf(2, 5, 8),
            arrayOf(0, 4, 8),
            arrayOf(2, 4, 6)
        )
        for (combination in winningCombinations) {
            val a = combination[0]
            val b = combination[1]
            val c = combination[2]
            if (buttons[a].text.toString().isNotEmpty()
                    && buttons[a].text.toString() == buttons[b].text.toString()
                    && buttons[a].text.toString() == buttons[c].text.toString()) {
                return true
            }
        }
        return false
    }
}