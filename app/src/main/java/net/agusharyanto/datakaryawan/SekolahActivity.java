package net.agusharyanto.datakaryawan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SekolahActivity extends AppCompatActivity {

    private Spinner spinnerKampus;
    private Button buttonBaca;
    private TextView textViewHasil;
    private RadioGroup radioGroupJurusan;
    private RadioButton radioButtonInformatika;
    private RadioButton radioButtonEkonomi;
    private CheckBox checkBoxBakso;
    private CheckBox checkBoxGado;
    private CheckBox checkBoxMieAyam;
    private Button buttonReset;
    private Button buttonSetData;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-11-21 15:45:14 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        spinnerKampus = (Spinner)findViewById( R.id.spinnerKampus );
        buttonBaca = (Button)findViewById( R.id.buttonBaca );
        textViewHasil = (TextView)findViewById( R.id.textViewHasil );
        radioGroupJurusan = (RadioGroup)findViewById( R.id.radioGroupJurusan );
        radioButtonInformatika = (RadioButton)findViewById( R.id.radioButtonInformatika );
        radioButtonEkonomi = (RadioButton)findViewById( R.id.radioButtonEkonomi );
        checkBoxBakso = (CheckBox)findViewById( R.id.checkBoxBakso );
        checkBoxGado = (CheckBox)findViewById( R.id.checkBoxGado );
        checkBoxMieAyam = (CheckBox)findViewById( R.id.checkBoxMie );
        buttonReset = (Button)findViewById( R.id.buttonReset );
        buttonSetData = (Button)findViewById( R.id.buttonSetData );


    }


    public void initEvent() {
        buttonBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bacaInput();
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetInput();

            }
        });
        buttonSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kampus);

        findViews();
        initEvent();
        initData();


    }

    private void initData(){
        List<String> kampuses = new ArrayList<String>();
        kampuses.add("Pilih Kampus");
        kampuses.add("Universitas Indonesia");
        kampuses.add("PENS");
        kampuses.add("ITB");
        kampuses.add("UGM");
        kampuses.add("UDayana");
        kampuses.add("Unpad");

        // Creating adapter for spinner
        ArrayAdapter<String > dataAdapter =
                new ArrayAdapter< String >(this,
                        android.R.layout.simple_spinner_item
                        , kampuses);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerKampus.setAdapter(dataAdapter);
    }


    private String kampus="";
    private String jurusan="";
    private String makanan="";
    private int posisiKampus=0;
    private int idjurusan=0;
    private RadioButton radioButtonJurusan;

    private void bacaInput(){
        kampus = spinnerKampus.getSelectedItem().toString();
        posisiKampus = spinnerKampus.getSelectedItemPosition();
        // Log.d("KampusActivty","desc:"+desc+" posisi:"+posisi);
        String hasil = "Kampus : "+kampus;
        int selectedId = radioGroupJurusan.getCheckedRadioButtonId();
        idjurusan=selectedId;
        radioButtonJurusan = (RadioButton) findViewById(selectedId);
        jurusan = radioButtonJurusan.getText().toString();
        //idjurusan = radioGroupJurusan.getCheckedRadioButtonId();
        hasil += "\njurusan : "+jurusan;

        makanan ="";
        if (checkBoxBakso.isChecked()){makanan+=""+checkBoxBakso.getText();}
        if (checkBoxGado.isChecked()){makanan+=","+checkBoxGado.getText();}
        if (checkBoxMieAyam.isChecked()){makanan+=","+checkBoxMieAyam.getText();}
        hasil +="\nMakanan : "+makanan;
        textViewHasil.setText(hasil);

    }

    private void resetInput(){
        spinnerKampus.setSelection(0);
        // radioButtonEkonomi.setChecked(false);
        // radioButtonInformatika.setChecked(false);
        radioGroupJurusan.clearCheck();
        checkBoxBakso.setChecked(false);
        checkBoxGado.setChecked(false);
        checkBoxMieAyam.setChecked(false);
        // radioButtonInformatika.setChecked(true);


    }

    private void setData(){

        spinnerKampus.setSelection(posisiKampus);
        radioGroupJurusan.check(idjurusan);
        // ((RadioButton) radioGroupJurusan.getChildAt(idjurusan)).setChecked(true);
        //   Log.d("TAG","radioButtonJurusan.getId():"+radioButtonJurusan.getId());
        //radioButtonJurusan.setChecked(true);
        String[] arrmakanan = makanan.split(",");
        for (int i=0; i<arrmakanan.length; i++) {
            if (arrmakanan[i].equals("Bakso")) {
                checkBoxBakso.setChecked(true);
            }
            else if (arrmakanan[i].equals("Gado gado")) {
                checkBoxGado.setChecked(true);
            }
            else if (arrmakanan[i].equals("Mie Ayam")) {
                checkBoxMieAyam.setChecked(true);
            }
        }


    }

}
