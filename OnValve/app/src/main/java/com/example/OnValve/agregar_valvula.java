package com.example.onvalve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onvalve.Modelo.Valvula;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class agregar_valvula extends AppCompatActivity
{
    private EditText txtnombre_valvula;
    private EditText txtFabricante;
    private EditText txtSerial;
    private EditText txtCorreoElectronicoUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_valvula);

        txtnombre_valvula = findViewById(R.id.txtnombre_valvula);
        txtFabricante = findViewById(R.id.txtFabricante);
        txtSerial = findViewById(R.id.txtSerial);
        txtCorreoElectronicoUser = findViewById(R.id.txtCorreoElectronicoUser);
        inicializarFirebase();
    }

    private void inicializarFirebase()
    {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void validacion()
    {
        if(txtnombre_valvula.getText().toString().equals(""))
        {
            txtnombre_valvula.setError("Requerido");
        }
        if(txtFabricante.getText().toString().equals(""))
        {
            txtFabricante.setError("Requerido");
        }
        if(txtSerial.getText().toString().equals(""))
        {
            txtSerial.setError("Requerido");
        }
        if(txtCorreoElectronicoUser.getText().toString().equals(""))
        {
            txtCorreoElectronicoUser.setError("Requerido");
        }
    }

    public boolean DatosVacios()
    {
        if(txtnombre_valvula.getText().toString().equals(""))
        {
            return true;
        }
        if(txtFabricante.getText().toString().equals(""))
        {
            return true;
        }
        if(txtSerial.getText().toString().equals(""))
        {
            return true;
        }

        if(txtCorreoElectronicoUser.getText().toString().equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void Registrarse(View view)
    {
        String nombre = txtnombre_valvula.getText().toString();
        String fabricante = txtFabricante.getText().toString();
        String serial = txtSerial.getText().toString();
        String correoElectronicoUsuario = txtCorreoElectronicoUser.getText().toString();
        String ValvulaId = UUID.randomUUID().toString();

      if (this.DatosVacios())
        {
            validacion();
        }
        else
        {
            Valvula NewValvula = new Valvula(nombre, fabricante, serial, correoElectronicoUsuario, ValvulaId);
            databaseReference.child("Valvula").child(NewValvula.getvalvulaId()).setValue(NewValvula);

            txtnombre_valvula.setText("");
            txtFabricante.setText("");
            txtSerial.setText("");
            txtCorreoElectronicoUser.setText("");


            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void IrAtrasInicio(View view)
    {
        Intent Registro = new Intent(this, MainActivity.class);
        startActivity(Registro);
    }


}