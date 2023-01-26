package com.example.calculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.allViews
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButtons()

        binding.btClr.setOnClickListener {
            binding.tvResult.text = ""
        }

        binding.btEquals.setOnClickListener {
            binding.tvResult.text = calculate(binding.tvResult.text.toString()).toString()
        }
    }

    private fun setupButtons() {
        binding.root.allViews.toList().forEach {
            it.setOnClickListener { view ->
                binding.tvResult.append((view as Button).text)
            }
        }
    }

    private fun calculate(result: String): Double {
        var operation = 'e'
        var positionOfOperation = 0

        result.forEachIndexed { index, num ->
            if (num == '+' ||
                num == '-' ||
                num == '*' ||
                num == '/'
            ) {
                positionOfOperation = index
                operation = num
            }
        }

        val firstNumber = result.substring(0, positionOfOperation).toDouble()
        val secondNumber = result.substring(positionOfOperation + 1).toDouble()

        if (operation == '/' && secondNumber == 0.0)
            return 0.0

        when (operation) {
            '+' -> return firstNumber + secondNumber
            '-' -> return firstNumber - secondNumber
            '*' -> return firstNumber * secondNumber
            '/' -> return firstNumber / secondNumber
            else -> return result.toDouble()
        }
    }
}