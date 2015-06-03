package com.example.android.sunshine.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Pierre on 03/06/2015.
 */
public class LocationEditTextPreference extends EditTextPreference {
    private final static int DEFAULT_MINIMUM_LOCATION_LENGTH = 2;
    private final static String TAG = LocationEditTextPreference.class.getSimpleName();
    private int mMinLength;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LocationTextEditPreference, 0, 0);

        try {
            mMinLength = a.getInteger(R.styleable.LocationTextEditPreference_minLength,
                    DEFAULT_MINIMUM_LOCATION_LENGTH);
        } finally {
            a.recycle();
        }

        Log.v(TAG, "Min length = " + mMinLength);
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);
        EditText edit = getEditText();
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Dialog dialog = getDialog();
                if (dialog instanceof AlertDialog) {
                    Button okButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                    okButton.setEnabled(s.length() >= mMinLength);
                }
            }
        });
    }
}
