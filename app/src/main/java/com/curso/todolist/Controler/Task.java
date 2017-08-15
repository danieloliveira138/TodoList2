package com.curso.todolist.Controler;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    public static final Parcelable.Creator<Task>
            CREATOR = new Parcelable.Creator<Task>() {

        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    private int id;
    private int index;
    private String title;
    private String resume;

    public Task(String title, String resume) {
        id = 0;
        this.title = title;
        this.resume = resume;
    }

    public Task(int id, int index, String title, String resume) {
        this.id = id;
        this.index = index;
        this.title = title;
        this.resume = resume;
    }

    private Task(Parcel from) {
        id = from.readInt();
        index = from.readInt();
        title = from.readString();
        resume = from.readString();
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(index);
        parcel.writeString(title);
        parcel.writeString(resume);
    }
}
