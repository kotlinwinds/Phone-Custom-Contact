package com.pusauli.multicontactpicker

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.codinguser.android.contactpicker.ContactsPickerActivity
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val GET_PHONE_NUMBER = 3007

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)

        btnOpenPicker.setOnClickListener {
            getContact()
        }


    }

    fun getContact() {
        startActivityForResult(Intent(this, ContactsPickerActivity::class.java), GET_PHONE_NUMBER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // See which child activity is calling us back.
        when (requestCode) {
            GET_PHONE_NUMBER ->
                // This is the standard resultCode that is sent back if the
                // activity crashed or didn't doesn't supply an explicit result.
                if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "No phone number found", Toast.LENGTH_SHORT).show()
                } else {
                    val phoneNumber = data!!.extras!!.get(ContactsPickerActivity.KEY_PHONE_NUMBER) as String
                    //Do what you wish to do with phoneNumber e.g.
                    Toast.makeText(this, "Phone number found: $phoneNumber", Toast.LENGTH_SHORT).show();
                }
            else -> {
            }
        }
    }
}
