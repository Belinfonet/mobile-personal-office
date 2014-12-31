package by.belinfonet.adsl_helper;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    URL url;
    String basicAuth;
    Button submitButton;
    HttpsURLConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //находим кнопку
        submitButton = (Button)findViewById(R.id.btnLogin);
        //авторизация
        basicAuth = "Basic " + Base64.encodeToString("test13:test".getBytes(), Base64.NO_WRAP);
        //обработчик событий
        submitButton.setOnClickListener(this);

        //урл
        try {
            url = new URL("http://www.adsl.by");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (conn == null)
        try {

            conn = (HttpsURLConnection) url.openConnection();
            Toast.makeText(this, "Conn established", Toast.LENGTH_SHORT).show();
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null,null, new SecureRandom());
            conn.setSSLSocketFactory(context.getSocketFactory());
            conn.setDoOutput(true);
            /*conn.setRequestProperty("Authorization", basicAuth);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            выслать POST
            получить данные
            */
            Toast.makeText(this, conn.getContentEncoding() + " \n" +
                    conn.getContentType() , Toast.LENGTH_SHORT).show();
        }catch (Exception e){Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();}
    }
}
