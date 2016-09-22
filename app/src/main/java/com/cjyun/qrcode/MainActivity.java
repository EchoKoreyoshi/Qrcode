package com.cjyun.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cjyun.google.zxing.activity.CaptureActivity;
import com.cjyun.google.zxing.encoding.EncodingHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 0;

    Button scannerQRCode, generateQRCode;
    ImageView qrcodeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scannerQRCode = (Button) findViewById(R.id.qrcode_dencode);
        generateQRCode = (Button) findViewById(R.id.qrcode_encode);
        qrcodeImg = (ImageView) findViewById(R.id.qrcode_img);

        scannerQRCode.setOnClickListener(this);
        generateQRCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_dencode: //扫描
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.qrcode_encode: //生成
                try {
//                    Bitmap mBitmap = EncodingHandler.createQRCode("www.baidu.com", 300);
//                    qrcodeImg.setImageBitmap(mBitmap);
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.head);
                    Bitmap www = EncodingHandler.createQRCode("www", 600, 600, bitmap);
                    qrcodeImg.setImageBitmap(www);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toast.makeText(MainActivity.this, scanResult, Toast.LENGTH_LONG).show();
        }
    }
}

