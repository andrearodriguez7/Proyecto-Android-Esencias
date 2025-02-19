import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        // Inflar el layout del fragmento
        val binding = inflater.inflate(R.layout.fragment_factura, container, false)

        // Obtener el TextView del layout
        facturaTextView = binding.findViewById(R.id.facturaTextView)

        // Obtener el string de la factura desde los argumentos
        val factura = arguments?.getString("factura") ?: "Factura vacía"

        // Establecer el texto de la factura en el TextView
        facturaTextView.text = factura

        return binding
    }
}
