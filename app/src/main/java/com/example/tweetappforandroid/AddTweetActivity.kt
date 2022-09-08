package com.example.tweetappforandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class AddTweetActivity : AppCompatActivity() {
    private val launcher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
//        imageView.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_tweet)

        var titleText  = findViewById<EditText>(R.id.titleTextField)
        var context = findViewById<EditText>(R.id.contextTextField)
        var imageName = findViewById<TextView>(R.id.imageName)

        findViewById<ImageButton>(R.id.addImageButton).setOnClickListener {
//            launcher.launch(arrayOf("image/*"))
        }
        findViewById<Button>(R.id.tweetButton).setOnClickListener{
            val intent = Intent()
            intent.putExtra("title", titleText.text.toString())
            intent.putExtra("context", context.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}