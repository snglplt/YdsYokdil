package com.selpar.ydsandyokdil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.selpar.ydsandyokdil.fragment.Home

class MainActivity : AppCompatActivity() {
    var inputManager: InputMethodManager? = null
    var drawer: DrawerLayout? = null
    var bundlem = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val menuLeftIV = findViewById<View>(R.id.menuLeftIV) as ImageView
        drawer = findViewById<View>(R.id.drawelayout) as DrawerLayout
        menuLeftIV!!.setOnClickListener(View.OnClickListener { drawerOpen() })
        val fragobj = Home()
        fragobj.arguments = bundlem
        var fragmentmaneger = this.supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragobj)
            .commit()
    }
    fun drawerOpen() {
        try {
            inputManager!!.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            drawer!!.openDrawer(GravityCompat.START)
        }
    }
}