package com.joana.achou.gads2020leaderboard.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import com.google.android.material.snackbar.Snackbar
import com.joana.achou.gads2020leaderboard.R
import com.joana.achou.gads2020leaderboard.fragments.ConfirmationFragment
import com.joana.achou.gads2020leaderboard.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_submit_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmitForm : AppCompatActivity() {

    private val retrofit = ServiceBuilder.getFormInstance()
    lateinit var firstName:String
    lateinit var lastName:String
    lateinit var email:String
    lateinit var link:String
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_form)


        buttonSubmit.setOnClickListener {

            firstName = editTextFirstName.text.toString()
            lastName = editTextLastName.text.toString()
            email = editTextEmail.text.toString()
            link = editTextLink.text.toString()

           if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || link.isEmpty()) {
                val snackBar = Snackbar.make(root, "Provide all required information", Snackbar.LENGTH_LONG)
                snackBar.show()

            }else if(!email.matches(emailPattern.toRegex())){
                val snackBar = Snackbar.make(root, "Please enter a correct email address.", Snackbar.LENGTH_LONG)
                snackBar.show()
            }else{

                ConfirmationFragment.newInstance(firstName,lastName,email,link).show(supportFragmentManager,"tag")
            }
        }



    }


}