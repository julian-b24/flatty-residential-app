package edu.co.icesi.flatty.view

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.DatePicker
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivityCreateComplaintBinding
import edu.co.icesi.flatty.databinding.SignUpPage1Binding
import java.text.SimpleDateFormat
import java.util.*

class CreateComplaint : AppCompatActivity() {

    private val binding: ActivityCreateComplaintBinding by lazy{
        ActivityCreateComplaintBinding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MMMM/yyyy")
        var current = formatter.format(time)

        binding.daySelected.text= current//Calendar.getInstance().get(Calendar.DATE).toString()
       // binding.monthSelected.text = Calendar.getInstance().get(Calendar.MONTH).toString()
        //binding.yearSelected.text = Calendar.getInstance().get(Calendar.YEAR).toString()

        binding.selectDate.setOnClickListener{
            binding.editTextTextMultiLine.visibility = View.INVISIBLE
            val datePicker = findViewById<DatePicker>(R.id.datePicker)
            binding.datePicker.visibility = View.VISIBLE
            val today = Calendar.getInstance()
            datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)

            ) { view, year, month, day ->
                    current = "$day/$month/$year"
                    binding.daySelected.text = current
                    val month = month + 1
                    val msg = "You Selected: $day/$month/$year"
                    Toast.makeText(this@CreateComplaint, msg, Toast.LENGTH_SHORT).show()
                    datePicker.visibility = View.INVISIBLE
                    binding.editTextTextMultiLine.visibility = View.VISIBLE
            }
        }
        binding.sendButton.setOnClickListener{
            val intent = Intent(this,CreateComplaint::class.java)
            finish()
        }
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}