package es.iessaladillo.pedrojoya.stroop.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.fragment_dashboard.*


class MainActivity : AppCompatActivity(), OnToolbarAvailableListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

    }


    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }

    val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }


    override fun onToolbarCreated(toolbar: Toolbar) {
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onToolbarDestroyed() {
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}


