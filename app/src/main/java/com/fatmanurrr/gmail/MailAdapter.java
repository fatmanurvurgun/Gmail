package com.fatmanurrr.gmail;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 17.08.2018.
 */

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MyViewHolder> implements SwipeListener {


    private ArrayList<gmail> mailItems;
    private clickListener clickListener;


    public clickListener getClickListener()
    {
        return clickListener;
    }
    public void setClickListener(MailAdapter.clickListener clickListener)
    {
        this.clickListener=clickListener;
    }

    public MailAdapter(ArrayList<gmail> mailItems) {
        this.mailItems = mailItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.herbir_mail_satiri, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        try {
            final gmail mailitem = mailItems.get(position);
            holder.sendertxt.setText(mailitem.getSender());
            holder.messagetxt.setText(mailitem.getMessage());
            holder.favorite.setVisibility(mailitem.isIsfav());
            holder.konutxt.setText(mailitem.getKonu());
            //  visible nin değeri aslında 0 mış Invisibleninki de 4

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onClick(mailitem, position);
                    }
                }
            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public void removeItem(int position) {
        mailItems.remove(position);

    }

    public void restoreItem(gmail item, int position) {
        mailItems.add(position, item);


    }



    @Override
    public int getItemCount() {
        return mailItems.size();
    }

    @Override
    public void onSwipe(int position, int direction) {

        if (direction == ItemTouchHelper.LEFT)
            mailItems.remove(position);
        if (direction == ItemTouchHelper.RIGHT)
            mailItems.get(position).setIsfav(0);
        notifyDataSetChanged(); //bu metod adaper için kullanılır ve adaperda bir değişiklik olduğunda adaptera basılan array yada listedeki değişiklikleri ekranda (UI) günceller




    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView sendertxt, messagetxt,konutxt;
        private ImageView favorite;
        private View view;

        // private LinearLayout left_background,right_background,foreground;
        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;

            sendertxt = (TextView) itemView.findViewById(R.id.sendertxtview);
            messagetxt = (TextView) itemView.findViewById(R.id.messagetxtview);
            favorite = (ImageView) itemView.findViewById(R.id.favorite);
            konutxt= (TextView) itemView.findViewById(R.id.konutxtview);
            // left_background=(LinearLayout) itemView.findViewById(R.id.bottom_wrapper);
            //right_background=(LinearLayout) itemView.findViewById(R.id.bottom_wrapper1);
            //foreground=(LinearLayout) itemView.findViewById(R.id.foreground);


        }
    }


    public interface clickListener{
        public void onClick(gmail items,int position);
    }

}
