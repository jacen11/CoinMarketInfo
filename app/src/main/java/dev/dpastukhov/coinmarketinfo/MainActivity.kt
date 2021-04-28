package dev.dpastukhov.coinmarketinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.dpastukhov.coinmarketinfo.databinding.ActivityMainBinding
import dev.dpastukhov.coinmarketinfo.presentation.CoinListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        supportFragmentManager.beginTransaction().replace(R.id.container, CoinListFragment()).commit()

        // DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}