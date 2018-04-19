package com.psyclotron.messenger.adapter;

import android.graphics.drawable.Drawable;

public class Contacts {

    private  String username;
    private Drawable userimage;
    private Boolean userstatus;

    public String getUsername() {
        return username;
    }

    public Drawable getUserimage() {
        return userimage;
    }

    public Boolean getUserstatus() {
        return userstatus;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserimage(Drawable userimage) {
        this.userimage = userimage;
    }

    public void setUserstatus(Boolean userstatus) {
        this.userstatus = userstatus;
    }

}
