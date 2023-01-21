package com.example.calculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.allViews
import com.example.calculator.R
import com.example.calculator.databinding.ActivityMainBinding
import java.sql.Time

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBtns()

        binding.btCLR.setOnClickListener {
            binding.tvResult.text = ""
        }

        binding.btEquals.setOnClickListener {
            binding.tvResult.text = calculate(binding.tvResult.text.toString()).toString()
        }
    }

    private fun setupBtns() {
        binding.root.allViews.toList().forEach {
            it.setOnClickListener { view ->
                binding.tvResult.append((view as Button).text)
            }
        }
    }

    private fun calculate(result: String): Double {
        var op: Char = 'e'
        var posOfOp = 0

        result.forEachIndexed { index, num ->
            if (num == '+' ||
                num == '-' ||
                num == '*' ||
                num == '/'
            ) {
                posOfOp = index
                op = num
            }
        }

        val firstNum = result.substring(0, posOfOp).toDouble()
        val scndNum = result.substring(posOfOp + 1).toDouble()

        if (op == '/' && scndNum == 0.0)
            return 0.0

        when (op) {
            '+' -> return firstNum + scndNum
            '-' -> return firstNum - scndNum
            '*' -> return firstNum * scndNum
            '/' -> return firstNum / scndNum
            else -> return result.toDouble()
        }
    }
}