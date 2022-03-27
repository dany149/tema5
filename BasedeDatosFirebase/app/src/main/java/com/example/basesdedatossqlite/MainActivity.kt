package com.example.basesdedatossqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val et1=findViewById<EditText>(R.id.nick)
        val et2=findViewById<EditText>(R.id.movil)
        val et3=findViewById<EditText>(R.id.Apellido1)
        val et4=findViewById<EditText>(R.id.Apellido2)
        val et5=findViewById<EditText>(R.id.Nombre)
        val et6=findViewById<EditText>(R.id.email)
        val button2=findViewById<Button>(R.id.button2)
        val button4=findViewById<Button>(R.id.button4)
        val button3=findViewById<Button>(R.id.button3)
        val button6=findViewById<Button>(R.id.button6)
        val button5=findViewById<Button>(R.id.button5)

//visualizar
        button2.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nick", et1.getText().toString())
            registro.put("movil", et2.getText().toString())
            registro.put("Apellido1", et3.getText().toString())
            registro.put("Apellido2", et4.getText().toString())
            registro.put("Nombre", et5.getText().toString())
            registro.put("email", et6.getText().toString())
            bd.insert("contactos", null, registro)
            bd.close()
            et1.setText("")
            et2.setText("")
            et3.setText("")
            et4.setText("")
            et5.setText("")
            et6.setText("")
            Toast.makeText(this, "Se guardaron los datos de la agenda", Toast.LENGTH_SHORT).show()
        }
        //Consultar contacto por su nick
        button4.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select movil,Apellido1,Apellido2,Nombre,email from contactos where nick=${et1.text.toString()}", null)
            if (fila.moveToFirst()) {
                et2.setText(fila.getString(0))
                et3.setText(fila.getString(1))
                et4.setText(fila.getString(2))
                et5.setText(fila.getString(3))
                et6.setText(fila.getString(4))

            } else
                Toast.makeText(this, "No existe el nick",  Toast.LENGTH_SHORT).show()
            bd.close()
        }
        //Consultar contacto por su móvil

        button3.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select nick,Apellido1,Apellido2,Nombre,email from contactos where movil=${et1.text.toString()}", null)
            if (fila.moveToFirst()) {
                et1.setText(fila.getString(0))
                et3.setText(fila.getString(1))
                et4.setText(fila.getString(2))
                et5.setText(fila.getString(3))
                et6.setText(fila.getString(4))

            } else
                Toast.makeText(this, "No existe el movil",  Toast.LENGTH_SHORT).show()
            bd.close()
        }

        //Eliminar un contacto a partir de su nick

        button6.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val cant = bd.delete("contactos", "nick=${et1.text.toString()}", null)
            bd.close()
            et1.setText("")
            et2.setText("")
            et3.setText("")
            et4.setText("")
            et5.setText("")
            et6.setText("")
            if (cant == 1)
                Toast.makeText(this, "Se borró correctamente", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No existe el nick", Toast.LENGTH_SHORT).show()
        }

        //Editar los campos móvil y email
        button5.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("movil", et2.text.toString())
            registro.put("email", et6.text.toString())
            val cant = bd.update("contactos", registro, "nick=${et1.text.toString()}", null)
            bd.close()
            if (cant == 1)
                Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "no existe ", Toast.LENGTH_SHORT).show()
        }
    }
}