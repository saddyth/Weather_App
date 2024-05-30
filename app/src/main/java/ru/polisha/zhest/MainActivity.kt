package ru.polisha.zhest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val view = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }


        val startButton = view.findViewById<Button>(R.id.startButton)
        val endButton = view.findViewById<Button>(R.id.endButton)
        val checkBox1 = view.findViewById<CheckBox>(R.id.checkBox)

        startButton.isEnabled = false
        endButton.isEnabled = false

        val icon = view.findViewById<ImageView>(R.id.icon)
        icon.setOnClickListener{
            endButton.isVisible = !endButton.isVisible
        }

        val buttonClickToast = Toast.makeText(this, "You are ready", Toast.LENGTH_SHORT)

        endButton.setOnClickListener{
            startButton.isEnabled = !startButton.isEnabled
        }

        startButton.setOnClickListener{
            buttonClickToast.show()
        }

        checkBox1.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked){
                endButton.isEnabled = false
            } else {
                endButton.isEnabled = true
            }
        }
    }


    companion object {
        const val BASE_TAG = "### "
    }
}