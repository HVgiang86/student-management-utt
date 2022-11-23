package vn.edu.utt.uttqlsv.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vn.edu.utt.uttqlsv.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = object: Thread() {
            override fun run() {
                try {
                    sleep(1200)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(applicationContext, Login::class.java)
                    startActivity(intent)
                }
                super.run()
            }
        }

        timer.start()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}