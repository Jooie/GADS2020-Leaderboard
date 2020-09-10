package com.joana.achou.gads2020leaderboard.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.joana.achou.gads2020leaderboard.R
import com.joana.achou.gads2020leaderboard.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_confirmation.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ConfirmationFragment : DialogFragment() {
    private val retrofit = ServiceBuilder.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_confirmation, container, false)


        return root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val outerLayout = LayoutInflater.from(getContext())
            .inflate(R.layout.fragment_confirmation, null) as LinearLayout

        arguments.let {
            val firstName = it?.getString(FIRST_NAME)
            val lastName = it?.getString(LAST_NAME)
            val email = it?.getString(EMAIL)
            val link = it?.getString(LINK)

            val builder = AlertDialog.Builder(requireContext())
            builder.setView(outerLayout)

            outerLayout.buttonCancel.setOnClickListener {
                dismiss()
            }

            outerLayout.buttonConfirm.setOnClickListener {
                outerLayout.progressBar.visibility = View.VISIBLE
                retrofit.submitProject(firstName!!,lastName!!,email!!,link!!).enqueue(
                    object : Callback<Void> {
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            outerLayout.progressBar.visibility = View.GONE
                            outerLayout.confirmAlert.visibility = View.GONE

                            outerLayout.failedAlert.visibility = View.VISIBLE

                        }

                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            outerLayout.progressBar.visibility = View.GONE
                            outerLayout.confirmAlert.visibility = View.GONE

                            outerLayout.successAlert.visibility = View.VISIBLE
                        }

                    }
                )

            }

            return builder.create()
        }

    }


    override fun onResume() {
        val width = resources.getDimensionPixelSize(R.dimen.popup_width)
        val height = resources.getDimensionPixelSize(R.dimen.popup_height)
        dialog!!.window!!.setLayout(width, height)
        // Call super onResume after sizing
        super.onResume()
    }

    companion object {
        private const val FIRST_NAME = "firstname"
        private const val LAST_NAME = "lastname"
        private const val EMAIL = "email"
        private const val LINK = "link"
        @JvmStatic
        fun newInstance(firstName: String,lastName:String,email:String,link:String) =
            ConfirmationFragment().apply {

                arguments = Bundle().apply {
                    putString(FIRST_NAME,firstName)
                    putString(LAST_NAME,lastName)
                    putString(EMAIL,email)
                    putString(LINK,link)
                }
            }
    }


}