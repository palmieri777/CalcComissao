package com.example.salariouber

package com.example.calcularsalario
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextSalary = findViewById<EditText>(R.id.editTextSalary)
        val editTextDistance = findViewById<EditText>(R.id.editTextDistance)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            val salary = editTextSalary.text.toString().toDoubleOrNull()
            val distance = editTextDistance.text.toString().toDoubleOrNull()

            if (salary != null && distance != null) {
                val commission = calculateCommission(salary, distance)
                val totalSalary = salary + commission
                textViewResult.text = "Salário Total: R$ ${"%.2f".format(totalSalary)}"
            } else {
                textViewResult.text = "Por favor, insira um valor válido."
            }
        }
    }

    private fun calculateCommission(salary: Double, distance: Double): Double {
        return when {
            distance < 500 -> salary * distance * 0.001 // 0.1% do salário por km
            distance in 501.0..1000.0 -> salary * distance * 0.0015 // 0.15% do salário por km
            distance in 1001.0..2000.0 -> salary * distance * 0.0025 // 0.25% do salário por km
            else -> salary * distance * 0.003 // 0.3% do salário por km
        }
    }
}