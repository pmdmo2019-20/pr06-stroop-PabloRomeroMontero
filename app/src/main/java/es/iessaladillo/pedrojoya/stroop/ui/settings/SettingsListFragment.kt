package es.iessaladillo.pedrojoya.stroop.ui.settings

import android.os.Bundle
import android.text.InputType
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import es.iessaladillo.pedrojoya.stroop.R

class SettingsListFragment: PreferenceFragmentCompat() {



    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        setupPreferences()
    }

    private fun setupPreferences() {
        val editTextPreference = preferenceManager.findPreference<EditTextPreference>(getString(R.string.prefAttempts_key))
        editTextPreference?.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }

    }





}