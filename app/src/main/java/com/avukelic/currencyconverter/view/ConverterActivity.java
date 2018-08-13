package com.avukelic.currencyconverter.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.avukelic.currencyconverter.R;
import com.avukelic.currencyconverter.presenter.ConverterPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConverterActivity extends AppCompatActivity implements ConverterContract.View {

    @BindView(R.id.first_currency)
    Spinner firstCurrency;
    @BindView(R.id.second_currency)
    Spinner secondCurrency;
    @BindView(R.id.value_input)
    EditText firstCurrencyInput;
    @BindView(R.id.result_label)
    TextView result;


    private ConverterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        initUi();
    }

    private void initUi() {
        ButterKnife.bind(this);
        presenter = new ConverterPresenter();
        presenter.setView(this);
        presenter.getExchangeRates();
        presenter.getCurrencyCodes();
    }

    private int isHrk() {
        if (firstCurrency.getSelectedItem().toString().equals(secondCurrency.getSelectedItem().toString())) {
            return 1;
        } else if (firstCurrency.getSelectedItem().toString().equals("HRK")) {
            return 2;
        } else if (secondCurrency.getSelectedItem().toString().equals("HRK")) {
            return 3;
        }
        return 4;
    }

    @OnClick(R.id.btn_submit)
    public void submitForConvert() {
        if (firstCurrencyInput.getText().toString().isEmpty() || firstCurrencyInput.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "First insert number to convert", Toast.LENGTH_SHORT).show();
        } else {
            double value = Double.parseDouble(firstCurrencyInput.getText().toString());
            switch (isHrk()) {
                case 1:
                    result.setText(firstCurrencyInput.getText().toString());
                    break;
                case 2:
                    presenter.convertFromHrk(value, secondCurrency.getSelectedItem().toString());
                    break;
                case 3:
                    presenter.convertToHrk(value, firstCurrency.getSelectedItem().toString());
                    break;
                default:
                    presenter.convert(value, firstCurrency.getSelectedItem().toString(), secondCurrency.getSelectedItem().toString());

                    break;
            }
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

    @Override
    public void displayConvertResult(double result) {
        this.result.setText(String.valueOf(result));
    }

    @Override
    public void showCurrencyCodes(List<String> codes) {
        codes.add("HRK");
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, codes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstCurrency.setAdapter(adapter);
        secondCurrency.setAdapter(adapter);
    }

    @Override
    public void showNetworkErrorMessage() {
        Toast.makeText(this, "You have some network issues", Toast.LENGTH_SHORT).show();
    }
}
