package com.standalone.apps.bmi.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;
import com.standalone.apps.bmi.CalculatorActivity;

public class CalculatorActivityTest extends ActivityInstrumentationTestCase2<CalculatorActivity>{

	private Solo solo;
	public CalculatorActivityTest(String name) {
		super("com.standalone.apps.bmi.CalculatorActivity",CalculatorActivity.class);
		setName(name);
	}

	@Override
	public void setUp() throws Exception{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testUIBindings() throws Throwable{
		solo.assertCurrentActivity("Supposed to be CalculatorActivity class", CalculatorActivity.class);
		CalculatorActivity ca = (CalculatorActivity)solo.getCurrentActivity();
		final SeekBar wightSeekBar = (SeekBar) ca.findViewById(com.standalone.apps.bmi.R.id.weight);
		final SeekBar heightSeekBar = (SeekBar) ca.findViewById(com.standalone.apps.bmi.R.id.height);
		runTestOnUiThread(new Runnable() {
			public void run() {
				wightSeekBar.incrementProgressBy(50);
				heightSeekBar.incrementProgressBy(14);
			}
		});
		assertEquals("BMICalculator", solo.getText(0).getText().toString());
		assertEquals("Weight in Kg.", solo.getText(1).getText().toString());
		assertEquals("55.0", solo.getText(2).getText().toString());
		assertEquals("Height in meters.", solo.getText(3).getText().toString());
		assertEquals("1.5", solo.getText(4).getText().toString());
		assertEquals("BMI::24.444444444444443", solo.getText(5).getText().toString());
		assertEquals("You have normal weight!", solo.getText(6).getText().toString());
	}
}
