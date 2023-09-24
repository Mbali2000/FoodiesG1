package com.foodsoncampus.foodies.activities;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.foodsoncampus.foodies.R;

public class QRScannerActivity extends AppCompatActivity {

    Button btn_scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v->{

            scanCode();
        });

    }

    private void scanCode() {

        ScanOptions options = new ScanOptions;
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled();
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {

        if(result.getContents() !=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new dialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialogInterface, int i){
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });
}
