package com.example.spinnerdemo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var spinnerWeeks: Spinner
    private lateinit var spinnerFruits: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_spinner)
        initViews()
        spinnerWeeks.handleItemSelectedFromSpinner()


        //Dynamic Spinner items
        val items = listOf("Apple","Orange","Jack","Melons")
        spinnerFruits.apply {
            setDataToSpinner(items)
            handleItemSelectedFromSpinner()
        }

    }

    private fun initViews() {
        spinnerWeeks = findViewById(R.id.weekSpinner)
        spinnerFruits = findViewById(R.id.fruitsSpinner)
    }

    private fun <T> Spinner.setDataToSpinner(items: List<T>) {
        val adapter = ArrayAdapter(
            this.context,
            R.layout.layout_item,  // custom layout for each item - TextView Should be its root
            items
        )
        this.adapter = adapter
    }

    private fun Spinner.handleItemSelectedFromSpinner() {
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //Will be called at the very beginning also ,as spinner selects the first item by default.
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = adapterView?.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "You selected $selectedItem", Toast.LENGTH_LONG)
                    .show()

            }

            //Not important as spinner always select the first item
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

}

/**
 * Steps to do - Static data spinner
 * Create items array in strings.xml
 * create spinner in xml with entries added referring to the array name created in strings.xml
 * We don,t need to create an adapter as we added the entries in xml as we know already what the items are .
 * Handle item Clicks - onItemSelectedListener  -
 *                           -Anonymous object     AdapterView.OnItemSelectedListener
 *                                               - override onItemSelected and onNothingSelected
 *                                               - get item selected using  adapterView?.getItemAtPosition(position)
 */

/**
 * Steps to do - Dynamic Spinner
 * The items are created during the run time - We don,t know what that items are gonna be.
 * So we cant pre define in strings.xml
 *
 * Create Adapter for the spinner
 *       -ArrayAdapter
 *           -context,layout id for each item which the textview must be its root view and should have an id,list of items
 *
 * Set the adapter to the spinner
 *  * Handle item Clicks - onItemSelectedListener  -
 *  *                           -Anonymous object     AdapterView.OnItemSelectedListener
 *  *                                               - override onItemSelected and onNothingSelected
 *  *                                               - get item selected using  adapterView?.getItemAtPosition(position)
 */