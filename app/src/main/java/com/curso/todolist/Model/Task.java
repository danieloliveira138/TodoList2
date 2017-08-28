package com.curso.todolist.Model;

import android.os.Parcel;

import java.io.Serializable;
import java.util.Calendar;

public class Task implements /*Parcelable,*/ Serializable {
    //    public static final Parcelable.Creator<Task>
//            CREATOR = new Parcelable.Creator<Task>() {
//
//        public Task createFromParcel(Parcel in) {
//            return new Task(in);
//        }
//
//        public Task[] newArray(int size) {
//            return new Task[size];
//        }
//    };
    private final String date;
    private long id;
    private int index;
    private int done;
    private String title;
    private String resume;

    public Task(String title, String resume) {
        id = 0;
        done = 0;
        this.title = title;
        this.resume = resume;
        date = TaskDate();
    }

    public Task(int id, int index, String title, String resume) {
        this.id = id;
        this.index = index;
        this.done = 0;
        this.title = title;
        this.resume = resume;
        date = TaskDate();
    }

    public Task(int id, int index, String title, String resume, String date, int done) {
        this.id = id;
        this.index = index;
        this.done = done;
        this.title = title;
        this.resume = resume;
        this.date = date;
    }

    public Task() {
        id = 0;
        done = 0;
        this.title = "Nova Tarefa";
        this.resume = "";
        date = TaskDate();
    }

    private Task(Parcel from) {
        id = from.readInt();
        index = from.readInt();
        title = from.readString();
        resume = from.readString();
        date = from.readString();
        done = from.readInt();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getDate() {
        return date;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    //    @TargetApi(Build.VERSION_CODES.N)
    private String TaskDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return String.format("%d:%d\t\t%d/%d/%d", hour, minute, day, month, year);
    }
//    @Override
//    public int describeContents() {
//        return 0;
//    }

//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeLong(id);
//        parcel.writeInt(index);
//        parcel.writeString(title);
//        parcel.writeString(resume);
//        parcel.writeString(date);
//        parcel.writeInt(done);
//    }
}
