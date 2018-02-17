package br.com.airescovit.clim.ui.addtask;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by murilo aires on 12/02/2018.
 */

public class NumberTextWatcher implements TextWatcher {
    private EditText editText;

    public NumberTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    String current = "";

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {

        if (!s.toString().equals(current)) {
            editText.removeTextChangedListener(this);

            String cleanString = s.toString().replaceAll("[" + Currency.getInstance(Locale.getDefault()).getSymbol() + ",.]", "");
            if (cleanString.length() == 0) {
                editText.setText("");
                current = "";
                editText.addTextChangedListener(this);
                return;
            }
            double parsed = Double.parseDouble(cleanString);
            String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));

            current = formatted;
            editText.setText(formatted);
            editText.setSelection(formatted.length());

            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
