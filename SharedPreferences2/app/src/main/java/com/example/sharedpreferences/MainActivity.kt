package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var preferencias: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        preferencias = getSharedPreferences(Preferencias.DATOS, Context.MODE_PRIVATE)

        val b=findViewById<Button>(R.id.buttonLeer)
        val bb=findViewById<Button>(R.id.buttonGuardar)
        val bbb=findViewById<Button>(R.id.buttonBorrar)
        val bbc=findViewById<Button>(R.id.buttonVisualizar)
        val a=findViewById<EditText>(R.id.Id)
        val aa=findViewById<EditText>(R.id.Nombre)
        val aaa=findViewById<EditText>(R.id.Apellidos)
        val aas=findViewById<EditText>(R.id.Edad)
        val z=findViewById<TextView>(R.id.textView)
        val zz=findViewById<TextView>(R.id.textView2)
        val zzz=findViewById<TextView>(R.id.textView3)
        val zzx=findViewById<TextView>(R.id.textView4)
        //leer
        b.setOnClickListener () {
            z.text = preferencias.getString(Preferencias.ID, "N/D")
            zz.text = preferencias.getString(Preferencias.NOMBRE, "N/D")
            zzz.text = preferencias.getString(Preferencias.APELLIDOS, "N/D")
            zzx.text = preferencias.getString(Preferencias.EDAD, "N/D")
        }
        //guardar
        bb.setOnClickListener () {
            preferencias.edit().putString(Preferencias.ID, a.text.toString()).apply()
            preferencias.edit().putString(Preferencias.NOMBRE, aa.text.toString()).apply()
            preferencias.edit().putString(Preferencias.APELLIDOS, aaa.text.toString()).apply()
            preferencias.edit().putString(Preferencias.EDAD, aas.text.toString()).apply()

        }
        //borrar
        bbb.setOnClickListener () {
            preferencias.edit().remove(Preferencias.ID).apply()
            preferencias.edit().remove(Preferencias.NOMBRE).apply()
            preferencias.edit().remove(Preferencias.APELLIDOS).apply()
            preferencias.edit().remove(Preferencias.EDAD).apply()
        }
        //visualizar
        bbc.setOnClickListener () {
            z.text = preferencias.getString(Preferencias.ID, "N/D")
            zz.text = preferencias.getString(Preferencias.NOMBRE, "N/D")
            zzz.text = preferencias.getString(Preferencias.APELLIDOS, "N/D")
            zzx.text = preferencias.getString(Preferencias.EDAD, "N/D")
        }

    }
}