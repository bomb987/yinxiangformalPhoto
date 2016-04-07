package com.example.yinxiang;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class PhotoActivity extends AppCompatActivity {
    private final int SYS_INTENT_REQUEST = 0XFF01;
    private final int CAMERA_INTENT_REQUEST = 0XFF02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }

    public void showCustomAlertDialog(View view) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window win = alertDialog.getWindow();

        WindowManager.LayoutParams lp = win.getAttributes();
        win.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        lp.alpha = 0.7f;
        win.setAttributes(lp);
        win.setContentView(R.layout.dialog);

        Button cancelBtn = (Button) win.findViewById(R.id.camera_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alertDialog.cancel();
            }
        });
        Button camera_phone = (Button) win.findViewById(R.id.camera_phone);
        camera_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                systemPhoto();
            }

        });
        Button camera_camera = (Button) win.findViewById(R.id.camera_camera);
        camera_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                cameraPhoto();
            }

        });
    }

    private void cameraPhoto() {
        String sdStatus = Environment.getExternalStorageState();
		/* 检测sd是否可用 */
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD卡不可用！", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, CAMERA_INTENT_REQUEST);
    }

    private void systemPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SYS_INTENT_REQUEST);
    }
}
