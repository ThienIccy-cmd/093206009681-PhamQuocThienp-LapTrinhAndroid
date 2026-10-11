package com.example.thuchanh03

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.os.PersistableBundle

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutChinh = LinearLayout(this)
        layoutChinh.orientation = LinearLayout.VERTICAL
        layoutChinh.setPadding(100, 200, 100, 200)

        layoutChinh.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT

        )

        val border = GradientDrawable()
        border.setColor(Color.WHITE)
        border.setStroke(3, Color.BLACK)
        border.setPadding(35, 35,35,35)
        border.cornerRadius = 60f


        val title = TextView(this)
        title.text = "Thực hành 03"
        title.setTextColor(Color.BLACK)
        title.gravity = Gravity.CENTER
        title.textSize = 24f
        title.setPadding(0, 0, 0, 50)
        layoutChinh.addView(title)

        val input1 = EditText(this)
        input1.hint = "Nhap mot so bat ky..."
        input1.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        input1.background = border
        layoutChinh.addView(input1)

        val button = LinearLayout(this)
        button.orientation = LinearLayout.HORIZONTAL
        button.gravity = Gravity.CENTER
        button.setPadding(0, 50, 0, 50)

        val buttonParams = LinearLayout.LayoutParams(
            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
        ).apply {
            setMargins(10, 0, 10, 0)
        }

        val plus = Button(this)
        plus.text = "+"
        plus.textSize = 20f
        plus.setBackgroundColor(Color.RED)
        plus.setTextColor(Color.WHITE)
        button.addView(plus, buttonParams)

        val minus = Button(this)
        minus.text = "-"
        minus.textSize = 20f
        minus.setBackgroundColor(Color.RED)
        minus.setTextColor(Color.WHITE)
        button.addView(minus, buttonParams)

        val multiply = Button(this)
        multiply.text = "*"
        multiply.textSize = 20f
        multiply.setBackgroundColor(Color.RED)
        multiply.setTextColor(Color.WHITE)
        button.addView(multiply, buttonParams)

        val divide = Button(this)
        divide.text = "/"
        divide.textSize = 20f
        divide.setBackgroundColor(Color.RED)
        divide.setTextColor(Color.WHITE)
        button.addView(divide, buttonParams)

        layoutChinh.addView(button)

        val input2 = EditText(this)
        input2.hint = "Nhap mot so bat ky..."
        input2.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        input2.background = border
        layoutChinh.addView(input2)

        val result = TextView(this)
        result.text = "Ket qua: "
        result.setTextColor(Color.BLACK)
        result.gravity = Gravity.LEFT
        result.textSize = 20f
        result.setPadding(0, 30,0,0)
        layoutChinh.addView(result)

        plus.setOnClickListener {
            val num1 = input1.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = input2.text.toString().toDoubleOrNull() ?: 0.0
            val sum = num1 + num2
            result.text = "Ket qua: $sum"
        }

        minus.setOnClickListener {
            val num1 = input1.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = input2.text.toString().toDoubleOrNull() ?: 0.0
            val sum = num1 - num2
            result.text = "Ket qua: $sum"
        }

        multiply.setOnClickListener {
            val num1 = input1.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = input2.text.toString().toDoubleOrNull() ?: 0.0
            val sum = num1 * num2
            result.text = "Ket qua: $sum"
        }

        divide.setOnClickListener {
            val num1 = input1.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = input2.text.toString().toDoubleOrNull() ?: 0.0
            val sum = num1 / num2
            result.text = "Ket qua: $sum"
        }



        setContentView(layoutChinh)
    }
}