package com.bonga.mnguni.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;


public class AsymmetricActivity extends AppCompatActivity {

    // BK MNGUNI
    // 21403041
    RelativeLayout layout;
    EditText edEncryptName;
    TextView tvResultEncrypted,tvResultDecrypted;
    AppCompatButton btnEncrypt,btnSymmetric,btnDecrypt;
    byte[] encodedBytes;
    String targetString;
    Key publicKey = null;
    Key privateKey = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asymmetric);
        getSupportActionBar().setTitle(R.string.title);
        layout = findViewById(R.id.layoutAsymmetric);
        btnSymmetric = findViewById(R.id.btn_symmetric);
        btnEncrypt = findViewById(R.id.abtn_encrypt);
        btnDecrypt = findViewById(R.id.abtn_decrypt);
        tvResultDecrypted = findViewById(R.id.atvDecryptResult);
        tvResultEncrypted = findViewById(R.id.atvEncryptResult);
        edEncryptName = findViewById(R.id.aedEncryptName);

        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.genKeyPair();
            publicKey = kp.getPublic();
            privateKey = kp.getPrivate();
        } catch (Exception e) {
            Log.e("Crypto", "RSA key pair error");
        }
        btnSymmetric.setOnClickListener(view -> startActivity(new Intent(this,MainActivity.class)));
        btnEncrypt.setOnClickListener(view -> {
            try{

                targetString = edEncryptName.getText().toString();
                encodedBytes = null;

                Cipher c = Cipher.getInstance("RSA");
                c.init(Cipher.ENCRYPT_MODE, privateKey);
                encodedBytes = c.doFinal(targetString.getBytes());

                tvResultEncrypted.setText(Base64.encodeToString(encodedBytes, Base64.DEFAULT));

            }catch (Exception e){

                Toast.makeText(this, "RSA encryption error", Toast.LENGTH_SHORT).show();
            }

        });

        btnDecrypt.setOnClickListener(view -> {
            try{
                byte[] decodedBytes;

                Cipher c = Cipher.getInstance("RSA");
                c.init(Cipher.DECRYPT_MODE, publicKey);
                decodedBytes = c.doFinal(encodedBytes);

                tvResultDecrypted.setText(new String(decodedBytes));

            }catch(Exception e){
                Toast.makeText(this, "RSA decryption error", Toast.LENGTH_SHORT).show();
            }

        });

    }

}