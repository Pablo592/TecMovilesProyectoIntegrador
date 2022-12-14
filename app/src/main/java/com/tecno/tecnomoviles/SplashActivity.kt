package com.tecno.tecnomoviles

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
import java.text.SimpleDateFormat
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

        productService = ProductRetrofit()
        getProductListFromServerOption2()

        var mp = MediaPlayer()
        mp.setDataSource(this , Uri.parse("android.resource://" + this.packageName+"/"+R.raw.notification))
        mp.prepare()
        mp.start()

    }

    override fun onStop() {
        super.onStop()
        sendWelcomeNotification()
    }

    private fun sendWelcomeNotification() {

        createNotificationChannel()
        val date = Date()
        val notificationId = SimpleDateFormat("ddHHmmss",Locale.getDefault()).format(date).toInt()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_IMMUTABLE)



        val notificationBuilder = NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setSmallIcon(R.drawable.tech_logo)
        notificationBuilder.setContentTitle(getString(R.string.mensaje_bienvenida))
        notificationBuilder.setContentText(getString(R.string.notificacion))
        notificationBuilder.priority = NotificationCompat.PRIORITY_MAX

        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setContentIntent(pendingIntent)


        val notificationManagerCompact = NotificationManagerCompat.from(this)
        notificationManagerCompact.notify(notificationId,notificationBuilder.build())

    }

    private fun createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "MyNotification"
            val description = "My notification channel description"

            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID,name,importance)
            notificationChannel.description = description
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
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