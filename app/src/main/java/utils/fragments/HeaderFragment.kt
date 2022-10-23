package utils.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tecno.tecnomoviles.HomeActivity
import com.tecno.tecnomoviles.NavigationDrawer
import com.tecno.tecnomoviles.databinding.HeaderFragmentBinding

class HeaderFragment : Fragment(){

private  var bindig: HeaderFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindig = HeaderFragmentBinding.inflate(inflater)
        return bindig?.root
    }
    override fun onDestroy() {
        bindig = null
        super.onDestroy()
    }
}