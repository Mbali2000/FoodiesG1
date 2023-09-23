package com.foodsoncampus.foodies.activities;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodsoncampus.foodies.R;

public class QRScannerActivity extends AppCompatActivity {

    Button btn_scan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v-> {

            scanCode();

        });

    }

    private void scanCode() {

        ScanOptions options = new ScanOptions;
        options.setPrompt("Volumn up to flash on");
        options.setBeepEnabled();
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);

    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), esult)
}
