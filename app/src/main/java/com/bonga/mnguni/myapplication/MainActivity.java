package com.bonga.mnguni.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import javax.crypto.Cipher;


public class MainActivity extends AppCompatActivity {

    // BK MNGUNI
    // 21403041

EditText edEncryptName;
TextView tvResultEncrypted,tvResultDecrypted;
AppCompatButton btnEncrypt,btnDecrypt;
    byte[] encryptedString;
    String targetString;
    SymetricClass aes256 = new SymetricClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnEncrypt = findViewById(R.id.btn_encrypt);
        btnDecrypt = findViewById(R.id.btn_decrypt);
        tvResultDecrypted = findViewById(R.id.tvDecryptResult);
        tvResultEncrypted = findViewById(R.id.tvEncryptResult);
        edEncryptName = findViewById(R.id.edEncryptName);

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