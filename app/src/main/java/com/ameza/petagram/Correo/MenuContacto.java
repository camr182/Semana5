package com.ameza.petagram.Correo;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.os.AsyncTask;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import android.util.Log;
import android.widget.EditText;

import com.ameza.petagram.controlador.Acciones;
import com.ameza.petagram.R;


public class MenuContacto extends AppCompatActivity {

    private Acciones accionesMenus;

    private Button buttonContacto;
    private EditText nombre;
    private EditText correo;
    private EditText cuerpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        accionesMenus = Acciones.getInstance();

        buttonContacto = (Button) findViewById(R.id.btnContacto);
        buttonContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();

            }
        });

        nombre = (EditText)findViewById(R.id.editTextNombreEmail);
        correo = (EditText)findViewById(R.id.editTextCorreo);
        cuerpo = (EditText)findViewById(R.id.editTextMensaje);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contactos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return accionesMenus.accionesMune(item, super.onOptionsItemSelected(item),this);
    }

    private void sendMessage() {
        String[] recipients = { "andresyfrandroi@gmail.com" ,correo.getText().toString() };
        SendEmailAsyncTask email = new SendEmailAsyncTask();
        email.activity = this;
        email.m = new Mail("andresyfrandroi@gmail.com", "Gato7410/");
        email.m.set_from(correo.getText().toString());
        email.m.setBody("Correo de: "+nombre.getText().toString()+"\t"+"\n"+"Email del sujeto: "+correo.getText().toString()+"\n"+"Mensaje: "+"\n"+cuerpo.getText().toString());
        email.m.set_to(recipients);
        email.m.set_subject("Correo de Petagram, por: "+nombre.getText().toString()+" Curso3 - Semana 4");
        email.execute();
    }

        public void displayMessage (String message){
            Snackbar.make(findViewById(R.id.btnContacto), message, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }


}

    class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
        Mail m;
        MenuContacto activity;

        public SendEmailAsyncTask() {}

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                if (m.send()) {
                    activity.displayMessage("E-mail enviado.");
                } else {
                    activity.displayMessage("Fallo al enviar E-mail.");
                }

                return true;
            } catch (AuthenticationFailedException e) {
                Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
                e.printStackTrace();
                activity.displayMessage("Autenticación fallida.");
                return false;
            } catch (MessagingException e) {
                Log.e(SendEmailAsyncTask.class.getName(), "Email fallido");
                e.printStackTrace();
                activity.displayMessage("Fallo al enviar E-mail.");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                activity.displayMessage("Ocurrio un error inesperado.");
                return false;
            }
        }
}
