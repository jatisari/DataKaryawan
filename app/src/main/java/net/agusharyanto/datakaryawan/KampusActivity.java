package net.agusharyanto.datakaryawan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KampusActivity extends AppCompatActivity {

    Spinner spinnerKampus;
    Button buttonBaca, buttonReset, buttonSetData;
    TextView textViewHasil;
    RadioGroup radioGroupJurusan;
    RadioButton radioButtonInformatika, radioButtonEkonomi;
    RadioButton radioButtonJurusan;
    CheckBox checkBoxBakso, checkBoxGado, checkBoxMieAyam;
    String hasil = "";
    String makanan = "";
    String jurusan = "";
    String kampus = "";
    int posisiKampus = 0;
    int idjurusan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kampus);
        initUI();
        initData();
        initEvent();
    }

    private void initEvent() {
        buttonBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bacaInput();
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetInput();
            }
        });
        buttonSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });
    }

    private void initUI() {
        spinnerKampus = (Spinner) findViewById(R.id.spinnerKampus);
        buttonBaca = (Button) findViewById(R.id.buttonBaca);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonSetData = (Button) findViewById(R.id.buttonSetData);
        textViewHasil = (TextView) findViewById(R.id.textViewHasil);
        radioGroupJurusan = (RadioGroup) findViewById(R.id.radioGroupJurusan);
        radioButtonInformatika = (RadioButton) findViewById(R.id.radioButtonInformatika);
        radioButtonEkonomi = (RadioButton) findViewById(R.id.radioButtonEkonomi);
        checkBoxBakso = (CheckBox) findViewById(R.id.checkBoxBakso);
        checkBoxGado = (CheckBox) findViewById(R.id.checkBoxGado);
        checkBoxMieAyam = (CheckBox) findViewById(R.id.checkBoxMie);
    }


    private void initData() {
        List<String> kampuses = new ArrayList<String>();
        kampuses.add("Pilih Kampus");
        kampuses.add("Universitas Indonesia");
        kampuses.add("PENS");
        kampuses.add("ITB");
        kampuses.add("UGM");
        kampuses.add("UDayana");
        kampuses.add("Unpad");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item
                        , kampuses);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerKampus.setAdapter(dataAdapter);
    }

    private void bacaInput() {
        kampus = spinnerKampus.getSelectedItem().toString();
        posisiKampus = spinnerKampus.getSelectedItemPosition();
        // Log.d("KampusActivty","desc:"+desc+" posisi:"+posisi);
        String hasil = "Kampus : " + kampus;
        int selectedId = radioGroupJurusan.getCheckedRadioButtonId();
        idjurusan = selectedId;
        radioButtonJurusan = (RadioButton) findViewById(selectedId);
        jurusan = radioButtonJurusan.getText().toString();
        //idjurusan = radioGroupJurusan.getCheckedRadioButtonId();
        hasil += "\njurusan : " + jurusan;

        makanan = "";
        if (checkBoxBakso.isChecked()) {
            makanan += "" + checkBoxBakso.getText();
        }
        if (checkBoxGado.isChecked()) {
            makanan += "," + checkBoxGado.getText();
        }
        if (checkBoxMieAyam.isChecked()) {
            makanan += "," + checkBoxMieAyam.getText();
        }
        hasil += "\nMakanan : " + makanan;
        textViewHasil.setText(hasil);

    }

    private void resetInput() {
        spinnerKampus.setSelection(0);
        // radioButtonEkonomi.setChecked(false);
        // radioButtonInformatika.setChecked(false);
        radioGroupJurusan.clearCheck();
        checkBoxBakso.setChecked(false);
        checkBoxGado.setChecked(false);
        checkBoxMieAyam.setChecked(false);
        // radioButtonInformatika.setChecked(true);


    }

    private void setData() {

        spinnerKampus.setSelection(posisiKampus);
        radioGroupJurusan.check(idjurusan);

        String[] arrmakanan = makanan.split(",");
        for (int i = 0; i < arrmakanan.length; i++) {
            if (arrmakanan[i].equals("Bakso")) {
                checkBoxBakso.setChecked(true);
            } else if (arrmakanan[i].equals("Gado gado")) {
                checkBoxGado.setChecked(true);
            } else if (arrmakanan[i].equals("Mie Ayam")) {
                checkBoxMieAyam.setChecked(true);
            }
        }


    }

}
