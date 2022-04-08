package com.bonga.mnguni.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;


public class MainActivity extends AppCompatActivity {

    // BK MNGUNI
    // 21403041

EditText edEncryptName;
TextView tvResultEncrypted,tvResultDecrypted;
AppCompatButton btnEncrypt,btnDecrypt,btnAsymmetric;
    byte[] encryptedString;
    String targetString;
    SymetricClass aes256 = new SymetricClass();
    Key publicKey = null;
    Key privateKey = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAsymmetric = findViewById(R.id.btn_asymmetric);
        btnEncrypt = findViewById(R.id.btn_encrypt);
        btnDecrypt = findViewById(R.id.btn_decrypt);
        tvResultDecrypted = findViewById(R.id.tvDecryptResult);
        tvResultEncrypted = findViewById(R.id.tvEncryptResult);
        edEncryptName = findViewById(R.id.edEncryptName);

        btnAsymmetric.setOnClickListener(view -> startActivity(new Intent(this,AsymmetricActivity.class)));
        btnEncrypt.setOnClickListener(view -> {
           try{
               targetString = edEncryptName.getText().toString();
               encryptedString = aes256.makeAes(targetString.getBytes(), Cipher.ENCRYPT_MODE);
               Log.d("Encoded string: ", new String(encryptedString));
               tvResultEncrypted.setText(new String(encryptedString));

           }catch (Exception e){
               tvResultEncrypted.setText(e.toString());
           }

        });

        btnDecrypt.setOnClickListener(view -> {
            try{
                byte[] decodedString = aes256.makeAes(encryptedString, Cipher.DECRYPT_MODE);
                Log.d("Decoded string: ", new String(decodedString));
                tvResultDecrypted.setText(new String(decodedString));

            }catch(Exception e){
                tvResultDecrypted.setText(e.toString());
            }

        });

    }


}