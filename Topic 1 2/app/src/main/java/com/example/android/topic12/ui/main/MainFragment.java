package com.example.android.topic12.ui.main;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.topic12.R;


public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    EditText mEditPass;
    boolean isRecreated = false;
    final String IS_RECREATED = "isRecreated";

    final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        //Задаем действия после смены введенных в EditText символов
        public void afterTextChanged(Editable s) {
            if (isRecreated == false) {
                Toast toast = Toast.makeText(getContext(),
                        mEditPass.getText().toString(), Toast.LENGTH_SHORT);
                toast.show();
            } else {
                isRecreated = false;
            }

        }
    };

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isRecreated = true;
        outState.putBoolean(IS_RECREATED, isRecreated);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            isRecreated = savedInstanceState.getBoolean(IS_RECREATED);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Инициализируем наши элементы:
        mEditPass = (EditText) view.findViewById(R.id.plain_text_input);

        //Настраиваем для поля ввода слушателя изменений в тексте TextChangedListener:
        mEditPass.addTextChangedListener(passwordWatcher);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

}
