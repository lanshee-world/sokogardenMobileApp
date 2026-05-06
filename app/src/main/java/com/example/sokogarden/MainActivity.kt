package com.example.sokogarden

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

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

        // 1. Find all buttons including the new Logout button
        val signinButton = findViewById<Button>(R.id.signinBtn)
        val signupButton = findViewById<Button>(R.id.signupBtn)
        val logoutButton = findViewById<Button>(R.id.logoutBtn) // From the improved XML
        val aboutButton = findViewById<Button>(R.id.aboutBtn)
        val lumineButton = findViewById<Button>(R.id.btnOpenLink)

        // 2. Check Login Status using SharedPreferences
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // User is logged in: Hide Join/Signin, Show Logout
            signupButton.visibility = View.GONE
            signinButton.visibility = View.GONE
            logoutButton.visibility = View.VISIBLE
        } else {
            // Guest mode: Show Join/Signin, Hide Logout
            signupButton.visibility = View.VISIBLE
            signinButton.visibility = View.VISIBLE
            logoutButton.visibility = View.GONE
        }

        // 3. Setup Click Listeners
        signupButton.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }

        signinButton.setOnClickListener {
            startActivity(Intent(this, Signin::class.java))
        }

        logoutButton.setOnClickListener {
            // Clear login state
            val editor = sharedPref.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            // Refresh the activity to update UI
            finish()
            startActivity(intent)
        }

        aboutButton.setOnClickListener {
            startActivity(Intent(this, About::class.java))
        }

        lumineButton.setOnClickListener {
            val url = "https://lumine-x8gk.onrender.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        // 4. Load Products
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val url = "https://keyafidel.alwaysdata.net/api/get_products"
        val helper = ApiHelper(applicationContext)
        helper.loadProducts(url, recyclerView, progressbar)
    }
}