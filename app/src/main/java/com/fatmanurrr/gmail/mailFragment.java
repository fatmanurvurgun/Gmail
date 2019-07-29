package com.fatmanurrr.gmail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 5.09.2018.
 */

@SuppressLint("ValidFragment")
public class mailFragment extends Fragment {

    private ArrayList<gmail> gmailItems;
    int position;
    TextView sender;
    TextView message;
    TextView subject;
    ImageView person_image;
    ImageView isfavor;

    @SuppressLint("ValidFragment")
    public mailFragment(ArrayList<gmail> gmailItems, int position)
    {
        this.gmailItems=gmailItems;
        this.position=position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.maiil_layout,container,false);
         sender= view.findViewById(R.id.sender_name);
         message= view.findViewById(R.id.message_body);
         subject=view.findViewById(R.id.message_subject);
         person_image=view.findViewById(R.id.sender_profile_picture);
         isfavor=view.findViewById(R.id.isfavor);


        //ImageView isfav= view.findViewById(R.id.favorimi);
        return  view;



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sender.setText(gmailItems.get(position).getSender());
        message.setText(gmailItems.get(position).getMessage());
        subject.setText(gmailItems.get(position).getKonu());
        isfavor.setVisibility(gmailItems.get(position).isIsfav());
        //şimdi anladım adapterdaki gibi bağlıyoruz onbind in aynısı dimi

    }
}
