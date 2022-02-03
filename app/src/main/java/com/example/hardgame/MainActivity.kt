package com.example.hardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hardgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataModel.text.observe(this){
            binding.tv.text = it
        }


        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, ChoiceFragment.newInstance()).commit()


        fun parserData(): List<String> =
            requireNotNull(this).application.applicationContext.assets.open("game.txt")
                .bufferedReader().use { it.readText() }.split("\n")

        dataModel.parcedData.value = parserData()

    }
}
