package com.example.android.myapplication.ui.main;

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
import com.example.android.topic12.ui.main.MainViewModel;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    EditText mEditPass;

    final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        //Задаем действия для TextView после смены введенных в EditText символов:
        public void afterTextChanged(Editable s) {
            Toast toast = Toast.makeText(getContext(),
                    mEditPass.getText().toString(), Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        //Инициализируем наши элементы:
        mEditPass = (EditText) getView().findViewById(R.id.plain_text_input);

        //Настраиваем для поля ввода слушателя изменений в тексте TextChangedListener:
        mEditPass.addTextChangedListener(passwordWatcher);
    }

}
