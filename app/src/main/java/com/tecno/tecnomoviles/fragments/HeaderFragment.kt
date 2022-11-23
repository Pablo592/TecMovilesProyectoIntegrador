package com.tecno.tecnomoviles.fragments

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.MyApplication
import com.tecno.tecnomoviles.CarritoActivity
import com.tecno.tecnomoviles.HomeActivity
import com.tecno.tecnomoviles.NavigationDrawer
import com.tecno.tecnomoviles.databinding.HeaderFragmentBinding

class HeaderFragment : Fragment(){

    private  var binding: HeaderFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HeaderFragmentBinding.inflate(inflater)
        val  r = binding?.root
        binding!!.drawer.setOnClickListener(){
            startActivity(Intent(this.requireContext(), NavigationDrawer::class.java))
        }
        binding!!.chop.setOnClickListener(){
            startActivity(Intent(this.requireContext(), CarritoActivity::class.java))
        }

        var layout:String
        if (MyApplication.preferences.getActivityName().isNotEmpty() || MyApplication.preferences.getActivityName().isNotBlank()) {
            layout = MyApplication.preferences.getActivityName()

            if((layout == "ProfileActivity") || (layout == "HistorialCompra") || (layout == "CarritoActivity") || (layout == "DetailedActivity")){
                binding!!.searchIcon.visibility = View.INVISIBLE
                binding!!.searchInput.visibility = View.INVISIBLE
            }

            if(layout == "DetailedActivity"){
                binding!!.drawer.visibility = View.INVISIBLE
                binding!!.botonReturn.visibility = View.VISIBLE

                binding!!.botonReturn.setOnClickListener(){
                    startActivity(Intent(this.requireContext(), HomeActivity::class.java))
                }
            }

            if(layout == "CarritoActivity"){
                binding!!.chop.visibility = View.INVISIBLE
            }

        }

        return r
    }
    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}