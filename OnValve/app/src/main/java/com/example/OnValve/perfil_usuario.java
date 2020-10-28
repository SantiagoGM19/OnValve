package com.example.OnValve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ConstraintHorizontalLayout;

import android.content.Intent;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class perfil_usuario extends AppCompatActivity
{
    private HorizontalScrollView ContainerHorizontal;
    private LinearLayout LyoutGaraje;
    private TextView viewe;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuario);
        ContainerHorizontal = findViewById(R.id.ContainerHorizontal);
        LyoutGaraje = findViewById(R.id.LyoutGaraje);
    }

    public void IrRegistrarValvula(View view)
    {
        Intent main = new Intent(this, agregar_valvula.class);
        startActivity(main);
    }

    public void ActualizarGaraje(View view)
    {
        String valvula = getIntent().getExtras().getString("ValvulaCreada");
        viewe.setText(valvula);
        ContainerHorizontal.addView(viewe);
    }


}