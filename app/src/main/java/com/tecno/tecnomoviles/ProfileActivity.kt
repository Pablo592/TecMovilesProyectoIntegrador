package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.tecno.tecnomoviles.databinding.ProfileBinding
import utils.fragments.HeaderFragment

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HeaderFragment>(R.id.fragment_header)
            }

            binding = ProfileBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.editDataButton.setOnClickListener() {
                startActivity(Intent(this, EditDataUser::class.java))
            }
        }
    }
}