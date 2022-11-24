package com.tecno.tecnomoviles

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.MyApplication
import com.tecno.tecnomoviles.databinding.SplashBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.product.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import services.ProductRetrofit
import services.dataClasses.ProductDTO
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    lateinit var productService: ProductRetrofit
    lateinit var serviceResult: Call<List<ProductDTO>>
    lateinit var data: ProductDTO
    private lateinit var binding : SplashBinding

    private val NOTIFICATION_CHANNEL_ID = "my_channel"


    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        supportActionBar?.hide()

        binding = SplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainIntent = Intent(this, LoginActivity::class.java)

        Timer().schedule(timerTask {
            startActivity(mainIntent)
        }, 1500)

   /*   if (ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) !=
            PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WAKE_LOCK),
                PackageManager.GET_PERMISSIONS)
        }*/

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) !=
            PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),
                PackageManager.GET_PERMISSIONS)
        }
            productService = ProductRetrofit()
            getProductListFromServerOption2()
        //    sendWelcomeNotification()

    }


    private fun sendWelcomeNotification() {

        //Setear una acción a la notificación:
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.tech_logo)
                .setContentTitle(getString(R.string.notificacion))
                .setContentText(getString(R.string.mensaje_bienvenida))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Crear el Notification Channel para versiones de android posteriores a API 26.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.notificacion)
            val description = getString(R.string.mensaje_bienvenida)
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                name,
                NotificationManager.IMPORTANCE_LOW
            )
            notificationChannel.description = description
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(1, builder.build())
    }

    private fun getProductListFromServerOption2() {
        serviceResult = productService.productRetrofitService.getProductList()
        serviceResult.enqueue(object : Callback<List<ProductDTO>> {
            override fun onResponse(
                call: Call<List<ProductDTO>>,
                response: Response<List<ProductDTO>>
            ) {
                response.body()?.let {

                    for (j in it) {
                        getProductForDatabase(j)
                    }
                }
            }
            override fun onFailure(call: Call<List<ProductDTO>>, t: Throwable) {

            }
        })
    }

    private fun getProductForDatabase(product: ProductDTO) {
        runBlocking {
            launch {
                if (MyApplication.myAppDatabase.productDao().isEmpty(product.name) > 0) {
                    updateProducts(product,MyApplication.myAppDatabase.productDao().getProductIdByName(product.name))
                }else{
                    saveProducts(product)
                }
            }
        }
    }

    private fun saveProducts(product: ProductDTO){
        runBlocking {
            MyApplication.myAppDatabase.productDao().setProduct(
                Product(
                    name = product.name,
                    type = product.type,
                    urlPhoto = product.urlPhoto,
                    price = product.price,
                    description = product.description,
                    features = product.features,
                    trolley = product.trolley,
                    recommended = product.recommended,
                    bought = product.bought
                )
            )
        }
    }

    private fun updateProducts(product: ProductDTO, id:Int){
        runBlocking {
            MyApplication.myAppDatabase.productDao().updateProduct(
                Product(
                    id = id,
                    name = product.name,
                    type = product.type,
                    urlPhoto = product.urlPhoto,
                    price = product.price,
                    description = product.description,
                    features = product.features,
                    trolley = product.trolley,
                    recommended = product.recommended,
                    bought = product.bought
                )
            )
        }
    }
}






