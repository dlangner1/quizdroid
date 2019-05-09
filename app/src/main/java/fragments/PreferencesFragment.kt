package fragments

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import edu.us.ischool.dlangner.quizdroid.R

class PreferencesFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}