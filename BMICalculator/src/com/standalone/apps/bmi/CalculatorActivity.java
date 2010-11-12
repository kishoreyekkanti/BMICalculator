package com.standalone.apps.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class CalculatorActivity extends Activity {
	SeekBar weightSeekBar;
	SeekBar heightSeekBar;
	TextView weightText;
	TextView heightText;
	TextView bmiText;
	TextView bmiResultText;
	double weight = 1;
	double height = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		weightSeekBar = (SeekBar) findViewById(R.id.weight);
		weightSeekBar.setOnSeekBarChangeListener(weightSeekBarListener);

		heightSeekBar = (SeekBar) findViewById(R.id.height);
		heightSeekBar.setOnSeekBarChangeListener(heightSeekBarListener);

		weightText = (TextView) findViewById(R.id.weight_progress);
		heightText = (TextView) findViewById(R.id.height_progress);
		bmiText = (TextView) findViewById(R.id.bmi);
		bmiResultText = (TextView) findViewById(R.id.bmi_result);
	}

	SeekBar.OnSeekBarChangeListener weightSeekBarListener = new SeekBar.OnSeekBarChangeListener() {

		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromTouch) {
			weight = progress;
			weightText.setText(String.valueOf(weight));
			setUiDetails();
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
			setUiDetails();
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			setUiDetails();
		}
	};

	SeekBar.OnSeekBarChangeListener heightSeekBarListener = new SeekBar.OnSeekBarChangeListener() {

		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromTouch) {
			height = progress/10.0;
			heightText.setText(String.valueOf(height));
			setUiDetails();
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
			setUiDetails();
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			setUiDetails();
		}
	};
	
	private void setUiDetails() {
		double bmi = calculateBMI(weight, height);
		bmiText.setText("BMI::"+bmi);
		bmiResultText.setText(getBMIResult(bmi));
	}
	
	public double calculateBMI(double weight,double height){
		return weight/(height*height);
	}
	
	public String getBMIResult(double bmi){
		String bmiResult = "You have ";
		if(bmi <= 18.5){
			bmiResult += "UNDER WEIGHT!";
		}else if(bmi > 18.5 && bmi < 25){
			bmiResult += "normal weight!";
		}else if(bmi >=25 && bmi<30){
			bmiResult += "OVER WEIGHT!";
		}else{
			bmiResult += "OBESE!";
		}
		return bmiResult;
	}
}