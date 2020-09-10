package com.joana.achou.gads2020leaderboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.joana.achou.gads2020leaderboard.R
import com.joana.achou.gads2020leaderboard.adapter.TopLearnerAdapter
import com.joana.achou.gads2020leaderboard.models.TopLearnerModel
import com.joana.achou.gads2020leaderboard.retrofit.ApiUrls
import com.joana.achou.gads2020leaderboard.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_skill_iq.view.*
import kotlinx.android.synthetic.main.fragment_top_learner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TopLearnerFragment : Fragment() {

    private val retrofit = ServiceBuilder.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_top_learner, container, false)

        root.progressBar.visibility = View.VISIBLE
        retrofit.getTopLearners().enqueue(object : Callback<List<TopLearnerModel>> {
            override fun onFailure(call: Call<List<TopLearnerModel>>, t: Throwable) {
                root.progressBar.visibility = View.GONE
                val snackBar = Snackbar.make(root, "Could not load content. Check your network", Snackbar.LENGTH_LONG)
                snackBar.show()


            }

            override fun onResponse(
                call: Call<List<TopLearnerModel>>,
                response: Response<List<TopLearnerModel>>
            ) {
                root.progressBar.visibility = View.GONE
                toplearner_Recyclerlist.layoutManager = LinearLayoutManager(context)
                val adapter = TopLearnerAdapter(context!!)
                toplearner_Recyclerlist.adapter = adapter
                adapter.loadList(response.body()!!)

            }

        }
        )

        return root
    }

}