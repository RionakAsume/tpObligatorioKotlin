package com.example.prueba

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // las vistas como propiedades de la clase para acceder a ellas
    private lateinit var editTextSueldoBasico: EditText
    private lateinit var editTextPresentismo: EditText
    private lateinit var editTextPasajes: EditText
    private lateinit var buttonCalcular: Button
    private lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establecer el layout XML que creamos
        setContentView(R.layout.activity_main) // el nombre consida con el xml que cree

        // Inicializar las vistas usando findViewById
        editTextSueldoBasico = findViewById(R.id.editTextSueldoBasico)
        editTextPresentismo = findViewById(R.id.editTextPresentismo)
        editTextPasajes = findViewById(R.id.editTextPasajes)
        buttonCalcular = findViewById(R.id.buttonCalcular)
        textViewResultado = findViewById(R.id.textViewResultado)

        // Configurar el OnClickListener para el botón
        buttonCalcular.setOnClickListener {
            calcularSueldoNeto()
        }
    }

    private fun calcularSueldoNeto() {
        // Obtener los valores de los EditText
        // Es importante manejar el caso en que los campos estén vacíos o no sean números válidos
        val sueldoBasicoStr = editTextSueldoBasico.text.toString()
        val presentismoStr = editTextPresentismo.text.toString()
        val pasajesStr = editTextPasajes.text.toString()

        if (sueldoBasicoStr.isEmpty() || presentismoStr.isEmpty() || pasajesStr.isEmpty()) {
            textViewResultado.text = "Por favor, complete todos los campos."
            return
        }

        // Convertir los valores a Double, manejando posibles errores de formato
        val sueldoBasico = sueldoBasicoStr.toDoubleOrNull() ?: 0.0
        val presentismo = presentismoStr.toDoubleOrNull() ?: 0.0
        val pasajes = pasajesStr.toDoubleOrNull() ?: 0.0

        // Calcular los egresos
        val jubilacion = sueldoBasico * 0.02
        val obraSocial = sueldoBasico * 0.02

        // Calcular el sueldo neto
        val sueldoNeto = (sueldoBasico + presentismo + pasajes) - (jubilacion + obraSocial)

        // Formatear el resultado como moneda y mostrarlo
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "AR")) // Para formato peso argentino, ajusta si es necesario
        textViewResultado.text = "Sueldo Neto: ${formatoMoneda.format(sueldoNeto)}"
    }
}
