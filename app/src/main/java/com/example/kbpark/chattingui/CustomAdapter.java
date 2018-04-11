package com.example.kbpark.chattingui;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KBPark on 2016. 5. 28..
 */
public class CustomAdapter extends BaseAdapter
{
    Context context;
    EditText inputText;

    CustomAdapter(Context context, EditText inputText)
    {
        this.context = context;
        this.inputText = inputText;

        m_List = new ArrayList();
    }

    public class ListContents
    {
        String msg;
        int type;

        ListContents(String _msg,int _type)
        {
            msg = _msg;
            type = _type;
        }
    }

    private ArrayList m_List;


    // 외부에서 아이템 추가 요청 시 사용
    public void add(String _msg,int _type)
    {
        m_List.add(new ListContents(_msg,_type));
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        m_List.remove(_position);
    }

    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final int pos = position;
        final Context context = parent.getContext();

        TextView        text    = null;
        CustomHolder    holder  = null;
        LinearLayout    layout  = null;
        View            viewRight = null;
        View            viewLeft = null;



        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null )
        {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_chatitem, parent, false);

            layout    = (LinearLayout) convertView.findViewById(R.id.layout);
            text    = (TextView) convertView.findViewById(R.id.text);
            viewRight    = (View) convertView.findViewById(R.id.imageViewright);
            viewLeft    = (View) convertView.findViewById(R.id.imageViewleft);


            // 홀더 생성 및 Tag로 등록
            holder = new CustomHolder();
            holder.m_TextView   = text;
            holder.layout = layout;
            holder.viewRight = viewRight;
            holder.viewLeft = viewLeft;
            convertView.setTag(holder);
        }
        else
        {
            holder  = (CustomHolder) convertView.getTag();
            text    = holder.m_TextView;
            layout  = holder.layout;
            viewRight = holder.viewRight;
            viewLeft = holder.viewLeft;
        }

        // Text 등록
        text.setText(((ListContents)m_List.get(position)).msg);

        if( ((ListContents)m_List.get(position)).type == 0 )
        {
            text.setBackgroundResource(R.drawable.inbox2);
            layout.setGravity(Gravity.LEFT);
            viewRight.setVisibility(View.GONE);
            viewLeft.setVisibility(View.GONE);

        }else if(((ListContents)m_List.get(position)).type == 1)
        {
            text.setBackgroundResource(R.drawable.outbox2);
            layout.setGravity(Gravity.RIGHT);
            viewRight.setVisibility(View.GONE);
            viewLeft.setVisibility(View.GONE);

        }else if(((ListContents)m_List.get(position)).type == 2)
        {
            text.setBackgroundResource(R.drawable.datebg);
            layout.setGravity(Gravity.CENTER);
            viewRight.setVisibility(View.VISIBLE);
            viewLeft.setVisibility(View.VISIBLE);
        }


        // 리스트 아이템을 터치 했을 때 이벤트 발생
        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 터치 시 해당 아이템 이름 출력
                ((MainActivity) context).hideSoftInputWindow(inputText);
            }
        });

/*
        // 리스트 아이템을 길게 터치 했을때 이벤트 발생
        convertView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                // 터치 시 해당 아이템 이름 출력
                Toast.makeText(context, "리스트 롱 클릭 : "+m_List.get(pos), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
*/
        return convertView;

    }


    private class CustomHolder
    {
        TextView m_TextView;
        LinearLayout layout;
        View viewRight;
        View viewLeft;
    }

}