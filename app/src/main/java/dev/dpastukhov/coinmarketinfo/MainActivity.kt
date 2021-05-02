package dev.dpastukhov.coinmarketinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.dpastukhov.coinmarketinfo.databinding.ActivityMainBinding
import dev.dpastukhov.coinmarketinfo.presentation.CoinListFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        supportFragmentManager.beginTransaction().replace(R.id.container, CoinListFragment()).commit()

    }
}