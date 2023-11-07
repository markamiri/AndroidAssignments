package com.example.androidassignments

import android.widget.EditText
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.androidassignments.databinding.ActivityTestToolbarBinding
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AlertDialog



class TestToolbar : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTestToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_test_toolbar)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_test_toolbar)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu from the XML resource file
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_one -> {
                Log.d("Toolbar", "Option 1 selected")
                // Add your code for handling the selection of Option 1 here
                val message = "You selected item 1"
                Snackbar.make(findViewById(R.id.coordinatorLayout), message, Snackbar.LENGTH_SHORT)
                    .show()
                return true
            }

            R.id.action_two -> {
                // Create an AlertDialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Do you want to go back?") // Set the title
                builder.setPositiveButton("OK") { dialog, id ->
                    // User clicked OK button, finish the current activity
                    finish()
                }
                builder.setNegativeButton("Cancel") { dialog, id ->
                    // User canceled the dialog, do nothing
                }
                val dialog = builder.create()
                dialog.show()
                return true
            }

            R.id.action_three -> {
                // Create a custom dialog with your custom layout
                val dialogView = layoutInflater.inflate(R.layout.custom_dialog_layout, null)
                val newMessageEditText = dialogView.findViewById<EditText>(R.id.newMessageEditText)

                val builder = AlertDialog.Builder(this)
                builder.setView(dialogView)
                builder.setPositiveButton("OK") { dialog, id ->
                    // User clicked OK, handle the new message
                    val newMessage = newMessageEditText.text.toString()
                    Snackbar.make(findViewById(R.id.coordinatorLayout), newMessage, Snackbar.LENGTH_SHORT)
                        .show()
                }
                builder.setNegativeButton("Cancel") { dialog, id ->
                    // User canceled the dialog, do nothing
                }
                val dialog = builder.create()
                dialog.show()
                return true
            }

            R.id.action_about -> {
                // Show an "About" dialog or display a Toast
                showAboutDialog()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }


    private fun showAboutDialog() {
        val versionInfo = "Version 1.0, by Yiming Lu"
        Toast.makeText(this, versionInfo, Toast.LENGTH_SHORT).show()
    }


}