package com.zhumingwei.bilidiff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    File rootfile;
    File originf;
    File patchf;
    File outputf;
    File newFilef;

    TextView tv_origin_file;
    TextView tv_patch_file;
    TextView tv_new_file;
    TextView tv_target_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootfile = getFilesDir();
        originf = new File(rootfile.getAbsoluteFile() + "/origin/libapp.so");
        patchf = new File(rootfile.getAbsoluteFile() + "/patch/libapp.so");
        outputf = new File(rootfile.getAbsoluteFile() + "/target/libapp.so");
        newFilef = new File(rootfile.getAbsoluteFile() + "/target/newlibapp.so");

        tv_new_file = findViewById(R.id.tv_new_file);
        tv_origin_file = findViewById(R.id.tv_origin_file);
        tv_patch_file = findViewById(R.id.tv_patch_file);
        tv_target_file = findViewById(R.id.tv_target_file);
        prepare();
        setState();
        findViewById(R.id.calculate_patch).setOnClickListener(this);
        findViewById(R.id.calculate_diff).setOnClickListener(this);
        findViewById(R.id.tv_clear).setOnClickListener(this);
    }

    public void setState() {
        tv_origin_file.setText(fileDetail(originf));
        tv_target_file.setText(fileDetail(outputf));
        tv_patch_file.setText(fileDetail(patchf));
        tv_new_file.setText(fileDetail(newFilef));
    }

    private String fileDetail(File file) {
        String s = file.getAbsolutePath() + "\n";
        if (file.exists()) {
            s = s + file.length() + "\n";
            try {
                s = s + Util.sha256(file.getAbsolutePath()) + "\n";
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            s = s+ "null\n";
        }
        return s;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calculate_patch) {
            BSUtil.bsPatch(originf.getAbsolutePath(), patchf.getAbsolutePath(), newFilef.getAbsolutePath());
            setState();
        } else if (v.getId() == R.id.calculate_diff) {
            BSUtil.bsDiff(originf.getAbsolutePath(), patchf.getAbsolutePath(), outputf.getAbsolutePath());
            setState();
        } else if (v.getId() == R.id.tv_clear) {
            patchf.delete();
            newFilef.delete();
            setState();
        }
    }

    public void prepare() {

        File origin = new File(rootfile.getAbsoluteFile() + "/" + "origin");
        origin.mkdirs();
        File patch = new File(rootfile.getAbsoluteFile() + "/" + "patch");
        patch.mkdirs();
        File target = new File(rootfile.getAbsoluteFile() + "/" + "target");
        target.mkdirs();
        InputStream targets = null;
        InputStream olds = null;
        try {
            targets = getAssets().open("target/libapp.so");
            olds = getAssets().open("origin/libapp.so");

            File out;
            FileOutputStream patchouts;
            out = outputf;
            patchouts = new FileOutputStream(out);
            Util.copy(targets, patchouts);
            patchouts.close();

            out = originf;
            patchouts = new FileOutputStream(out);
            Util.copy(olds, patchouts);
            patchouts.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (targets != null) {
                try {
                    targets.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (olds != null) {
                try {
                    olds.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}