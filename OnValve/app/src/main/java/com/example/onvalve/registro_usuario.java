package com.example.onvalve;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onvalve.Modelo.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class registro_usuario extends AppCompatActivity
{
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtCiudad;
    private EditText txtCorreoElectronico;
    private EditText txtContraseña;
    private EditText txtRepetirContraseña;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario);

        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCiudad = findViewById(R.id.txtCiudad);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtContraseña = findViewById(R.id.txtContraseña);
        txtRepetirContraseña = findViewById(R.id.txtRepetirContraseña);
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
        if(txtNombres.getText().toString().equals(""))
        {
            txtNombres.setError("Requerido");
        }
        if(txtApellidos.getText().toString().equals(""))
        {
            txtApellidos.setError("Requerido");
        }
        if(txtCiudad.getText().toString().equals(""))
        {
            txtCiudad.setError("Requerido");
        }
        if(txtCorreoElectronico.getText().toString().equals(""))
        {
            txtCorreoElectronico.setError("Requerido");
        }
        if(txtContraseña.getText().toString().equals(""))
        {
            txtContraseña.setError("Requerido");
        }
        if(txtRepetirContraseña.getText().toString().equals(""))
        {
            txtRepetirContraseña.setError("Requerido");
        }
    }

    public boolean DatosVacios()
    {
        if(txtNombres.getText().toString().equals(""))
        {
            return true;
        }
        if(txtApellidos.getText().toString().equals(""))
        {
            return true;
        }
        if(txtCiudad.getText().toString().equals(""))
        {
            return true;
        }
        if(txtCorreoElectronico.getText().toString().equals(""))
        {
            return true;
        }
        if(txtContraseña.getText().toString().equals(""))
        {
            return true;
        }
        if(txtRepetirContraseña.getText().toString().equals(""))
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
        String nombres = txtNombres.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String ciudad = txtCiudad.getText().toString();
        String correoElectronico = txtCorreoElectronico.getText().toString();
        String contraseña = txtContraseña.getText().toString();
        String repetirContraseña = txtRepetirContraseña.getText().toString();
        String UserId = UUID.randomUUID().toString();

        if(!contraseña.equals(repetirContraseña))
        {
            Toast.makeText(this, "Los campos de contrseña no son iguales", Toast.LENGTH_LONG).show();
        }
        else if (this.DatosVacios())
        {
            validacion();
        }
        else
        {
            Usuario NuevoUsuario = new Usuario(nombres, apellidos, ciudad, correoElectronico, contraseña, UserId);
            databaseReference.child("Usuario").child(NuevoUsuario.getUserId()).setValue(NuevoUsuario);

            txtNombres.setText("");
            txtApellidos.setText("");
            txtCiudad.setText("");
            txtCorreoElectronico.setText("");
            txtContraseña.setText("");
            txtRepetirContraseña.setText("");

            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void IrAtrasInicio(View view)
    {
        Intent Registro = new Intent(this, MainActivity.class);
        startActivity(Registro);
    }


}