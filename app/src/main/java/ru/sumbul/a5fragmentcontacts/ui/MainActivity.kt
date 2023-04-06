package ru.sumbul.recycleviewaston6.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.sumbul.recycleviewaston6.R
import ru.sumbul.recycleviewaston6.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val textView: TextView = findViewById(R.id.text)
//        textView.text = Datasource().loadContacts().size.toString()

    }
}