package com.example.p_cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    LocalTime tiempoAnterior = LocalTime.now();
    LocalTime tiempoCronometro;
    boolean cronometroPausado = false;
    boolean cronometroDetenido = true;
    CronometroTask task = new CronometroTask();
    TextView cronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cronometro = findViewById(R.id.cronometro);
        Button bDetener = findViewById(R.id.boton_pausar);
        bDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cronometroPausado = true;
            }
        });
        Button bIniciar = findViewById(R.id.boton_iniciar);
        bIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cronometroPausado) {
                    cronometroPausado = false;
                } else if (cronometroDetenido) {
                    tiempoCronometro = LocalTime.of(0,0,0);
                    cronometroDetenido = false;
                    cronometroPausado = false;
                    task = new CronometroTask();
                    task.execute(tiempoCronometro);
                }
            }
        });
        Button bReiniciar = findViewById(R.id.boton_reset);
        bReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);
                cronometroDetenido = true;
                cronometroPausado = false;
                cronometro.setText("0:0:0");
            }
        });
    }

    public void actualizarCronometro() {
        tiempoCronometro = tiempoCronometro.plusSeconds(1);
        cronometro.setText(tiempoCronometro.getHour() + ":" + tiempoCronometro.getMinute() + ":" + tiempoCronometro.getSecond());
    }

    class CronometroTask extends AsyncTask<LocalTime, Void, Void> {
        @Override
        protected Void doInBackground(LocalTime... values) {
            while (!isCancelled()) {
                if (LocalTime.now().getSecond() != tiempoAnterior.getSecond() && !cronometroPausado) {
                    actualizarCronometro();
                    tiempoAnterior = LocalTime.now();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), "Finalizada ejecución", Toast.LENGTH_LONG).show();
        }
    }
}
