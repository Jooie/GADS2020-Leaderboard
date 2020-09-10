package com.joana.achou.gads2020leaderboard.activity

import android.content.Intent
import android.os.Bundle

import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.joana.achou.gads2020leaderboard.R
import com.joana.achou.gads2020leaderboard.adapter.TabsPagerAdapter
import com.joana.achou.gads2020leaderboard.fragments.SkillIQFragment
import com.joana.achou.gads2020leaderboard.fragments.TopLearnerFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabsAdapter =
            TabsPagerAdapter(
                this,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        tabsAdapter.addFragment(TopLearnerFragment(),"Learning Leaders")
        tabsAdapter.addFragment(SkillIQFragment(),"Skill IQ Leaders")
        viewPager.adapter = tabsAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        buttonSubmitForm.setOnClickListener {
            startActivity(Intent(this, SubmitForm::class.java))
        }


    }
}