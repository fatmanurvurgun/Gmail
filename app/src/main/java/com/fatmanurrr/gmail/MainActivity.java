package com.fatmanurrr.gmail;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MailAdapter madapter;
    public static ArrayList<gmail> mailList;
    private SwipeController swipeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mailList = new ArrayList<gmail>();
        prepareMails();
        madapter = new MailAdapter(mailList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(madapter);


        swipeController = new SwipeController(madapter, MainActivity.this);

        ItemTouchHelper itemtouchhelper = new ItemTouchHelper(swipeController);
        itemtouchhelper.attachToRecyclerView(recyclerView);

        madapter.setClickListener(new MailAdapter.clickListener() {
            @Override
            public void onClick(gmail items, int position) {

                ArrayList<gmail> bundlemail=mailList;
                Bundle bundle= new Bundle();
                bundle.putInt("position",position);
                bundle.putParcelableArrayList("gmails",bundlemail);
               // bundle.putParcelableArrayList("mailler", (ArrayList<? extends Parcelable>) mailList);
        //        bundle.putParcelableArrayList("mailler",mailList);
                //bundle.putString("gonderen",mailList.get(position).getSender());
                //bundle.putString("mesaj",mailList.get(position).getMessage());

                Intent intent= new Intent(MainActivity.this,MailActivity.class );
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    private void prepareMails() {

        gmail mail = new gmail("linkedin", "junior android iş ilanlarından haberdar olmak için tıklatın","size uygun iş ilanları");
        mailList.add(mail);
        mail = new gmail( "vodafone", "20.08.2018 tarihli 1 yeni ödenmemiş faturanız var","ödenmemiş fatura");
        mailList.add(mail);
        mail = new gmail("kariyer.net", "cv nizi incelemeye aldık ","cvniz incelemeye alındı");
        mailList.add(mail);
        mail = new gmail("greenpeace", "Merhaba fatmanur,\n"  +
                "Kırklareli’nin verimli tarım arazileri kömürlü termik santral tehdidi altında. Bu proje Trakya’nın doğasını tehdit eden 3 santral projesinden yalnızca biri.\n" +
                "\n" +
                "Kırklareli Ovası’nın yanı başına yapılmak istenen kömürlü termik santral projesinin Çevresel Etki Değerlendirmesi (ÇED) Halkın Katılımı Toplantısı geçtiğimiz hafta cuma günü İnece Beldesi’nde yapılacaktı. Ancak Trakya’nın çeşitli bölgelerinden gelen insanlar ellerinde tencere, tava ve pankartlarla santral istemediğini haykırdı.","fatmanur imzan gerekli");
        mailList.add(mail);


    }
}


