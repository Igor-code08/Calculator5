package com.example.calculator5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText
    private lateinit var textViewResult: TextView

    private var inputExpression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        editTextInput = findViewById(R.id.editTextInput)
        textViewResult = findViewById(R.id.textViewResult)

        setupKeyboard()
    }

    private fun setupKeyboard() {
        val buttons = listOf(
            Pair(R.id.button1, "1"),
            Pair(R.id.button2, "2"),
            Pair(R.id.buttonEquals, "=")
        )

        for ((id, value) in buttons) {
            findViewById<Button>(id).setOnClickListener {
                onKeyPress(value)
            }
        }
    }

    private fun onKeyPress(value: String) {
        if (value == "=") {
            calculateResult()
            return
        }

        inputExpression += value
        editTextInput.setText(inputExpression)
    }
    private fun calculateResult() {
        try {
            val result = eval(inputExpression)
            textViewResult.text = result.toString()
            inputExpression = result.toString()
            editTextInput.setText("")
        } catch (e: Exception) {
            textViewResult.text = "Ошибка"
            inputExpression = ""
            editTextInput.setText("")
        }
    }

    private fun eval(expression: String): Double {
        return expression.toDouble()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}