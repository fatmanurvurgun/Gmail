package com.fatmanurrr.gmail;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.ref.SoftReference;

/**
 * Created by Lenovo on 17.08.2018.
 */

public class gmail implements Parcelable {

  //  private String receive;
    private String sender;
    private String message;
    private int isfav;
    private String konu;


    protected gmail(Parcel in) {
        sender = in.readString();
        message = in.readString();
        isfav = in.readInt();
        konu = in.readString();
    }

    public static final Creator<gmail> CREATOR = new Creator<gmail>() {
        @Override
        public gmail createFromParcel(Parcel in) {
            return new gmail(in);
        }

        @Override
        public gmail[] newArray(int size) {
            return new gmail[size];
        }
    };

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public gmail(String sender, String message, String konu)
    {
      // this.receive=receive;
        this.sender=sender;
        this.message=message;
        this.konu=konu;

        isfav = 4;
    }

   /* public String getReceive ()
    {
        return receive;
    }
    */
    public String getSender()
    {
        return sender;
    }

    public String getMessage()
    {
        return  message;
    }

  /*  public void setReceive(String receive)
    {
        this.receive=receive;
    }
    */

    public  void setSender(String sender)
    {
        this.sender=sender;
    }
    public void setMessage(String message)
    {
        this.message=message;
    }

    public int isIsfav() {
        return isfav;
    }

    public void setIsfav(int isfav) {
        this.isfav = isfav;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sender);
        parcel.writeString(message);
        parcel.writeInt(isfav);
        parcel.writeString(konu);
    }
}