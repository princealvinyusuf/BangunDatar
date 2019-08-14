package com.intent.bangundatar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edt_panjang, edt_lebar, edt_tinggi;
    private TextView tv_result;
    private Button btnHitung;
    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_lebar = (EditText) findViewById(R.id.edt_lebar);
        edt_panjang = (EditText) findViewById(R.id.edt_panjang);
        edt_tinggi = (EditText) findViewById(R.id.edt_tinggi);
        btnHitung = (Button) findViewById(R.id.btn_hitung);
        btnHitung.setOnClickListener(this);
        tv_result = (TextView) findViewById(R.id.tv_hasil);

        if (savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tv_result.setText(result);
        }


    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.btn_hitung){
            String inputPanjang = edt_panjang.getText().toString().trim();
            String inputLebar = edt_lebar.getText().toString().trim();
            String inputTinggi = edt_tinggi.getText().toString().trim();

            boolean isKotakKosong= false;
            boolean isInvalidIsi = false;

            if (TextUtils.isEmpty(inputPanjang)){
                isKotakKosong = true;
                edt_panjang.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputLebar)){
                isKotakKosong = true;
                edt_lebar.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputTinggi)){
                isKotakKosong = true;
                edt_tinggi.setError("Field ini tidak boleh kosong");
            }

            Double panjang = toDouble(inputPanjang);
            Double lebar = toDouble(inputLebar);
            Double tinggi = toDouble(inputTinggi);

            if (panjang == null){
                isInvalidIsi = true;
                edt_panjang.setError("Field ini harus berupa nomor yang valid");
            }

            if (lebar == null){
                isInvalidIsi = true;
                edt_lebar.setError("Field ini harus berupa nomor yang valid");
            }

            if (tinggi == null){
                isInvalidIsi = true;
                edt_tinggi.setError("Field ini harus berupa nomor yang valid");
            }

            if (!isKotakKosong && !isInvalidIsi){
                double volume = panjang*lebar*tinggi;
                tv_result.setText(String.valueOf(volume));
            }
        }
    }

    Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        }catch (NumberFormatException e){
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putString(STATE_RESULT, tv_result.getText().toString());
    }
}
