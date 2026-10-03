/*
 * Based on official OpenCV Android samples
 * Copyright (C) OpenCV contributors
 * Licensed under the Apache License, Version 2.0
 */

package org.opencv.samples.aruco_identifier;

import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.objdetect.ArucoDetector;
import org.opencv.objdetect.Dictionary;
import org.opencv.objdetect.Objdetect;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.imgproc.Imgproc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class CameraActivity extends org.opencv.android.CameraActivity implements CvCameraViewListener2, View.OnClickListener {
    private static final String TAG = "OCVSample::Activity";

    private CameraBridgeViewBase mOpenCvCameraView;
    private ArucoDetector detector;
    private int selectedDictionaryId = 0;

    public CameraActivity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);

        //! [ocv_loader_init]
        if (OpenCVLoader.initLocal()) {
            Log.i(TAG, "OpenCV loaded successfully");
        } else {
            Log.e(TAG, "OpenCV initialization failed!");
            (Toast.makeText(this, "OpenCV initialization failed!", Toast.LENGTH_LONG)).show();
            return;
        }
        //! [ocv_loader_init]

        //! [keep_screen]
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //! [keep_screen]

        setContentView(R.layout.camera_view);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.surface_view);

        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);

        mOpenCvCameraView.setCvCameraViewListener(this);

        Button changeDictionaryButton = (Button) findViewById(R.id.button_change_dictionary);
        changeDictionaryButton.setOnClickListener(this);

        String dictName = "DICT_4X4_50";
        Dictionary dict = Objdetect.getPredefinedDictionary(selectedDictionaryId);

        TextView mDictionaryTextView = (TextView) findViewById(R.id.selected_dictionary);
        mDictionaryTextView.setText(dictName);

        this.detector = new ArucoDetector(dict);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0 && resultCode == RESULT_OK)
        {
            selectedDictionaryId = data.getIntExtra("dictionaryId", 0);

            String dictName = "DICT_4X4_50";
            dictName = data.getStringExtra("dictionaryName") != null ? data.getStringExtra("dictionaryName") : "DICT_4X4_50";

            Log.i(TAG, "New selected dictionnary " + dictName);

            Dictionary dict = Objdetect.getPredefinedDictionary(selectedDictionaryId);
            this.detector = new ArucoDetector(dict);

            TextView mDictionaryTextView = (TextView) findViewById(R.id.selected_dictionary);
            mDictionaryTextView.setText(dictName);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.enableView();
    }

    @Override
    protected List<? extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(mOpenCvCameraView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
    }

    @Override
    public void onCameraViewStopped() {
    }

    @Override
    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        Mat rgbaFrame = inputFrame.rgba();
        Mat rgbFrame = new Mat();

        Imgproc.cvtColor(rgbaFrame, rgbFrame, Imgproc.COLOR_RGBA2RGB);

        List<Mat> corners = new java.util.ArrayList<Mat>();
        Mat ids = new Mat();
        this.detector.detectMarkers(rgbFrame, corners, ids);
        if(corners.size() > 0){
            Objdetect.drawDetectedMarkers(rgbFrame, corners, ids);
            Imgproc.cvtColor(rgbFrame, rgbaFrame, Imgproc.COLOR_RGB2RGBA);

        }
        rgbFrame.release();
        ids.release();
        for(Mat corner : corners) {
            corner.release();
        }

        return rgbaFrame;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("dictionaryId", this.selectedDictionaryId);
        startActivityForResult(intent, 0);
    }
}
