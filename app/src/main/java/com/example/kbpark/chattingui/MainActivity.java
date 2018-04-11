package com.example.kbpark.chattingui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity {

    ListView m_ListView;
    LinearLayout linearLayout;

    CustomAdapter m_Adapter;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (EditText) findViewById(R.id.editText);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        m_ListView = (ListView) findViewById(R.id.listView);

        m_Adapter = new CustomAdapter(this, inputText);  // 커스텀 어댑터 생성


        // 화면 터치시 키보드 숨기기.
        linearLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hideSoftInputWindow(inputText);
            }

        });

        // ListView에 어댑터 set
        m_ListView.setAdapter(m_Adapter);

    }

    public void onSendBtnClicked(View v)
    {
        m_Adapter.add(inputText.getText().toString(),1);
        inputText.setText("");
    }

    public void hideSoftInputWindow(View editView)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService (Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow
                (editView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }



}