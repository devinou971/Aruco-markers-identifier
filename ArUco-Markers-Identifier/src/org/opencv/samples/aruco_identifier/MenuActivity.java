package org.opencv.samples.aruco_identifier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import org.opencv.objdetect.Objdetect;

public class MenuActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    private Button loadButton;
    private int selectedDictId;
    private String selectedDictName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_view);

        radioGroup = (RadioGroup) findViewById(R.id.dictionary_radio_group);
        radioGroup.setOnCheckedChangeListener(this);

        loadButton = (Button) findViewById(R.id.load_button);
        loadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra("dictionaryId", selectedDictId);
        intent.putExtra("dictionaryName", selectedDictName);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(intent);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        final int x4_4_50 = R.id.radio_button_4x4_50;

        if(i== R.id.radio_button_4x4_50){
            selectedDictId = Objdetect.DICT_4X4_50;
            selectedDictName = "DICT_4X4_50";
        }
        else if (i== R.id.radio_button_4x4_100) {
            selectedDictId = Objdetect.DICT_4X4_100;
            selectedDictName = "DICT_4X4_100";
        }
        else if (i== R.id.radio_button_4x4_250) {
            selectedDictId = Objdetect.DICT_4X4_250;
            selectedDictName = "DICT_4X4_250";
        }
        else if (i== R.id.radio_button_4x4_1000) {
            selectedDictId = Objdetect.DICT_4X4_1000;
            selectedDictName = "DICT_4X4_1000";
        }
        else if (i== R.id.radio_button_5x5_50) {
            selectedDictId = Objdetect.DICT_5X5_50;
            selectedDictName = "DICT_5X5_50";
        }
        else if (i== R.id.radio_button_5x5_100) {
            selectedDictId = Objdetect.DICT_5X5_100;
            selectedDictName = "DICT_5X5_100";
        }
        else if (i== R.id.radio_button_5x5_250) {
            selectedDictId = Objdetect.DICT_5X5_250;
            selectedDictName = "DICT_5X5_250";
        }
        else if (i== R.id.radio_button_5x5_1000) {
            selectedDictId = Objdetect.DICT_5X5_1000;
            selectedDictName = "DICT_5X5_1000";
        }
        else if (i== R.id.radio_button_6x6_50) {
            selectedDictId = Objdetect.DICT_6X6_50;
            selectedDictName = "DICT_6X6_50";
        }
        else if (i== R.id.radio_button_6x6_100) {
            selectedDictId = Objdetect.DICT_6X6_100;
            selectedDictName = "DICT_6X6_100";
        }
        else if (i== R.id.radio_button_6x6_250) {
            selectedDictId = Objdetect.DICT_6X6_250;
            selectedDictName = "DICT_6X6_250";
        }
        else if (i== R.id.radio_button_6x6_1000) {
            selectedDictId = Objdetect.DICT_6X6_1000;
            selectedDictName = "DICT_6X6_1000";
        }
        else if (i== R.id.radio_button_7x7_50) {
            selectedDictId = Objdetect.DICT_7X7_50;
            selectedDictName = "DICT_7X7_50";
        }
        else if (i== R.id.radio_button_7x7_100) {
            selectedDictId = Objdetect.DICT_7X7_100;
            selectedDictName = "DICT_7X7_100";
        }
        else if (i== R.id.radio_button_7x7_250) {
            selectedDictId = Objdetect.DICT_7X7_250;
            selectedDictName = "DICT_7X7_250";
        }
        else if (i== R.id.radio_button_7x7_1000) {
            selectedDictId = Objdetect.DICT_7X7_1000;
            selectedDictName = "DICT_7X7_1000";
        }
        else if (i== R.id.radio_button_april_tag_16h05) {
            selectedDictId = Objdetect.DICT_APRILTAG_16h5;
            selectedDictName = "DICT_APRILTAG_16h5";
        }
        else if (i== R.id.radio_button_april_tag_25h9) {
            selectedDictId = Objdetect.DICT_APRILTAG_25h9;
            selectedDictName = "DICT_APRILTAG_25h9";
        }
        else if (i== R.id.radio_button_april_tag_36h10) {
            selectedDictId = Objdetect.DICT_APRILTAG_36h10;
            selectedDictName = "DICT_APRILTAG_36h10";
        }
        else if (i== R.id.radio_button_april_tag_36h11) {
            selectedDictId = Objdetect.DICT_APRILTAG_36h11;
            selectedDictName = "DICT_APRILTAG_36h11";
        }
        else if (i== R.id.radio_button_april_tag_36h12) {
            selectedDictId = Objdetect.DICT_ARUCO_MIP_36h12;
            selectedDictName = "DICT_ARUCO_MIP_36h12";
        }
        else if (i== R.id.radio_button_original) {
            selectedDictId = Objdetect.DICT_ARUCO_ORIGINAL;
            selectedDictName = "DICT_ARUCO_ORIGINAL";
        }
    }
}
