package com.example.combos_error;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.combos_error.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    Spinner spinner1, spinner2;
    EditText editText2;
    boolean isCorrectSpiner = false;
    boolean isCorrectEditText = false;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        setupSpinner();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkButtonMandatory(binding.buttonFirst, binding.editText2, binding.spinner1);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupSpinner() {
        spinner1 = binding.spinner1;
        spinner2 = binding.spinner2;
        editText2 = binding.editText2;

        String[] items = new String[]{"Selecciona un género", "Masculino", "Femenino"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    isCorrectSpiner = false;
                    return false;
                } else {
                    isCorrectSpiner = true;
                    return true;
                }
            }

        };

        spinner1.setAdapter(adapter);
    }

    private void checkButtonMandatory(Button button, EditText editText, Spinner spinner) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCorrectSpiner){
                    TextView errorText = (TextView)spinner.getSelectedView();
                    errorText.setTextColor(Color.RED);
                }
                // Comprobación para EditText
                if (editText.getText().toString().trim().equals("")) {
                    editText.setError("Este campo no puede estar vacío");
                    isCorrectEditText = false;
                } else {
                    isCorrectEditText = true;
                }

                if (isCorrectSpiner & isCorrectEditText) {
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);

                }

            }
        });


    }
}


