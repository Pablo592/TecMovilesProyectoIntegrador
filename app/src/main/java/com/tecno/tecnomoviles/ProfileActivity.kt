package com.tecno.tecnomoviles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.MyApplication
import com.tecno.tecnomoviles.databinding.ProfileBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.user.User
import utils.fragments.HeaderFragment

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ProfileBinding
    val userLiveData = MutableLiveData<User>()

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

            getProfileForDatabase()

            userLiveData.observe(this, Observer{
                binding.userNameText.text = it.name
                binding.userEmailText.text = it.email
                binding.userUsernameText.text = it.username
    //            binding.userImage.setImageURI(Uri.parse(it.profilePhotoUrl))
            })
            binding.editDataButton.setOnClickListener() {
                startActivity(Intent(this, EditDataUser::class.java))
            }
        }
        MyApplication.preferences.setActivityName("ProfileActivity")
    }
    private fun getProfileForDatabase() {
        runBlocking {
            launch {
                if (MyApplication.preferences.getUserName().isNotEmpty() || MyApplication.preferences.getUserName().isNotBlank()) {
                    userLiveData.value = MyApplication.myAppDatabase.userDao()
                        .getUser(MyApplication.preferences.getUserName())
                }
            }
        }
    }
}