package com.tecno.tecnomoviles

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.MyApplication
import com.tecno.tecnomoviles.databinding.ProfileBinding
import com.tecno.tecnomoviles.fragments.HeaderFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.user.User
import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.*


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ProfileBinding
    val userLiveData = MutableLiveData<User>()
    val IMAGE_REQUEST_CODE = 1_000;
    lateinit var user:User



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
                user = it
    //
                if(it.profilePhotoUrl != "NADA")
                    binding.userImage.setImageURI(Uri.parse(it.profilePhotoUrl))
            })
            binding.editDataButton.setOnClickListener() {
                startActivity(Intent(this, EditDataUser::class.java))
            }
            binding.editImage.setOnClickListener {
                pickImageFromGallery()
            }

        }
        MyApplication.preferences.setActivityName("ProfileActivity")
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.userImage.setImageURI(data?.data)
            user.profilePhotoUrl = data?.data.toString()
            updateUser(user)
            MyApplication.preferences.setUserPhoto(data?.data.toString())
        }
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

    private fun updateUser(user: User){
        runBlocking {
            MyApplication.myAppDatabase.userDao().updateUser(
                User(
                    id = user.id,
                    username = user.username,
                    password = user.password,
                    name = user.name,
                    email = user.email,
                    profilePhotoUrl = user.profilePhotoUrl
                )
            )
        }
    }
}