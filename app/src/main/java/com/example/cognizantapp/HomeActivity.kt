package com.example.cognizantapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class HomeActivity : AppCompatActivity() {

    lateinit var contactTextView: TextView
    lateinit var contactEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        contactEditText = findViewById(R.id.etContact)
        contactTextView = findViewById(R.id.tvContact)
        contactTextView.text = intent.extras?.getString("name")
    }

    fun clickHandler(view: View) {
        when(view.id) {
            R.id.btnContact -> {setContactText()}
            R.id.btnCancel -> {
                Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show()
                //startTimer("milk boiling", 10)
            }
        }
    }

    private fun setContactText() {
        var contact = contactEditText.text.toString()
        var intent = Intent()
        intent.putExtra("conkey",contact)
        setResult(RESULT_OK,intent)
        finish()
    }

    private fun startTimer(message: String, duration: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_LENGTH, duration)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
        if(intent.resolveActivity(packageManager) != null)
            startActivity(intent)
    }
}