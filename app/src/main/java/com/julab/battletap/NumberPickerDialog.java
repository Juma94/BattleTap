package com.julab.battletap;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.ArrayList;

/**
 * Created by Abdelhamid on 14/10/2015.
 */
public class NumberPickerDialog extends Dialog
{
    public interface DialogListener
    {
        public void okButtonPressed(int n);
    }

    private ArrayList<String> list;
    private Context context;
    private NumberPicker numberPicker;

    private DialogListener readyListener;

    public NumberPickerDialog(Context context, DialogListener readyListener)
    {
        super(context);
        super.setCancelable(false);
        super.setTitle(R.string.dialog_number_sequence_title);
        this.readyListener = readyListener;
        this.context = context;
        this.list = new ArrayList<String>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.number_picker_dialog);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(10);

        Button buttonOK = (Button) findViewById(R.id.dialogOK);
        buttonOK.setOnClickListener(new android.view.View.OnClickListener()
        {
            public void onClick(View v)
            {
                int n = numberPicker.getValue();
                readyListener.okButtonPressed(n);
                NumberPickerDialog.this.dismiss();
            }
        });
    }
}