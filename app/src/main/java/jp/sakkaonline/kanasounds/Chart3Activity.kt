package jp.sakkaonline.kanasounds

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View

class Chart3Activity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_back_to_left -> {
            val intent = Intent(this, Chart2Activity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_go_to_right -> {
            val intent = Intent(this, Chart4Activity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart3)

        val toolbar3 = findViewById<View>(R.id.toolbar3) as Toolbar
        setSupportActionBar(toolbar3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
