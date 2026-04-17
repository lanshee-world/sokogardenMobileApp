package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.loopj.android.http.RequestParams

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find the buttons by use of their ids
        val SigninButton = findViewById<Button>(R.id.signinBtn)
        val SignupButton = findViewById<Button>(R.id.signupBtn)

//create the intent to the two activities
        SignupButton.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java )
            startActivity(intent)
        }
//        =================================
        SigninButton.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }

//        find the recycler view and the progress bar by use o their id
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)

//        specify the Api URL endpoint for fetching the products (alwaysData)
        val url = "https://keyafidel.alwaysdata.net/api/get_products"

//import the helper class
        val helper = ApiHelper(applicationContext)

//        inside of the helper class access the function loadproducts
        helper.loadProducts(url, recyclerView, progressbar)

//        Find the about button by use of it's id and have the intent
        val aboutButton = findViewById<Button>(R.id.aboutBtn)

//        below is the intent to the About activity
        aboutButton.setOnClickListener {
            val intent = Intent(applicationContext, About::class.java)
            startActivity(intent)

        }



    }
}