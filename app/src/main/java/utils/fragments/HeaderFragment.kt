package utils.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tecno.tecnomoviles.CarritoActivity
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
        return r
    }
    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}