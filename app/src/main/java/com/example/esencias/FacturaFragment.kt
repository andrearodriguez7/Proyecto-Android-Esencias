import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.esencias.R

class FacturaFragment : Fragment() {

    private lateinit var facturaTextView: TextView

    // Función para crear una nueva instancia del fragmento con la factura
    companion object {
        fun newInstance(factura: String): FacturaFragment {
            val fragment = FacturaFragment()
            val args = Bundle()
            args.putString("factura", factura)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_factura, container, false)

        facturaTextView = view.findViewById(R.id.facturaTextView)
        val flechaVolver: ImageView = view.findViewById(R.id.FlechaVolver)

        val factura = arguments?.getString("factura") ?: "Factura vacía"

        facturaTextView.text = factura

        flechaVolver.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
