package activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.us.ischool.dlangner.quizdroid.R
import fragments.PreferencesFragment

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.preferences_container, PreferencesFragment())
            .commit()
    }
}