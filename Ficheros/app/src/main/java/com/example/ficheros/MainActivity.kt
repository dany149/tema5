package com.example.ficheros

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var preferencias: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferencias = getSharedPreferences(Preferencias.DATOS, Context.MODE_PRIVATE)

        val b=findViewById<Button>(R.id.Cargar)
        val bb=findViewById<Button>(R.id.Guardar)
        val bbb=findViewById<Button>(R.id.Limpiar)
        val aa=findViewById<EditText>(R.id.Nombre)
        val aaa=findViewById<EditText>(R.id.Telefono)
        val aas=findViewById<EditText>(R.id.Email)
        val zz=findViewById<TextView>(R.id.textView)
        val zzz=findViewById<TextView>(R.id.textView2)
        val zzx=findViewById<TextView>(R.id.textView3)
        //leer
        b.setOnClickListener () {
            zz.text = preferencias.getString(Preferencias.NOMBRE, "N/D")
            zzz.text = preferencias.getString(Preferencias.TELEFONO, "N/D")
            zzx.text = preferencias.getString(Preferencias.EMAIL, "N/D")
        }
        //guardar
        bb.setOnClickListener () {
            preferencias.edit().putString(Preferencias.NOMBRE, aa.text.toString()).apply()
            preferencias.edit().putString(Preferencias.TELEFONO, aaa.text.toString()).apply()
            preferencias.edit().putString(Preferencias.EMAIL, aas.text.toString()).apply()

        }
        //borrar
        bbb.setOnClickListener () {
            preferencias.edit().remove(Preferencias.NOMBRE).apply()
            preferencias.edit().remove(Preferencias.TELEFONO).apply()
            preferencias.edit().remove(Preferencias.EMAIL).apply()
        }


        lateinit var binding: ActivityMainBinding



        fun onCreate(savedInstanceState: Bundle?) {



            if ((ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED
                        ) || (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED
                        )) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    123
                )
            }


        }

        fun Guardar(texto: String) {
            try {
                val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
                val miCarpeta = File(rutaSD, "datos")
                if (!miCarpeta.exists()) {
                    miCarpeta.mkdir()
                }
                val ficheroFisico = File(miCarpeta, "datos.txt")
                ficheroFisico.appendText("$texto\n")
            }
            catch (e: Exception) {
                Toast.makeText(this,
                    "No se ha podido guardar",
                    Toast.LENGTH_LONG).show()
            }
        }

        fun Cargar() : String {
            var texto = ""
            try {
                val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
                val miCarpeta = File(rutaSD, "datos")
                val ficheroFisico = File(miCarpeta, "datos.txt")
                val fichero = BufferedReader(
                    InputStreamReader(FileInputStream(ficheroFisico))
                )
                texto = fichero.use(BufferedReader::readText)
            }
            catch (e : Exception) {
                // Nada
            }
            return texto
        }
    }

    interface ActivityMainBinding {

    }


}