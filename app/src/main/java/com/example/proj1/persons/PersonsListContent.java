package com.example.proj1.persons;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PersonsListContent {

    private static final int COUNT=6;
    /**
     * An array of sample (dummy) items.
     */
    public static final List<Person> ITEMS = new ArrayList<Person>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Person> ITEM_MAP = new HashMap<String, Person>();

    static {
        // Add some sample items.
        for (int i = 1; i <= 1/*COUNT*/; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Person item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void removeItem(int position) {
        ITEMS.remove(position);
        ITEM_MAP.remove(position);

    }

    private static Person createDummyItem(int position) {
        return new Person(String.valueOf(position), "Gal", "Anonim","01-01-2000","123456789");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Person implements Parcelable {
        public final String id;
        public final String name;
        public final String surname;
        public final String date;
        public final String phoneNumber;
        public final String picPath;

        public Person(String id, String name, String surname, String date, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.phoneNumber = phoneNumber;
            this.date = date.trim();

            Random rand=new Random();
            int number=rand.nextInt(12)+1;

            this.picPath = "avatar_"+number;
        }

        protected Person(Parcel in) {
            id = in.readString();
            name = in.readString();
            surname = in.readString();
            date = in.readString();
            phoneNumber = in.readString();
            picPath = in.readString();
        }

        public static final Creator<Person> CREATOR = new Creator<Person>() {
            @Override
            public Person createFromParcel(Parcel in) {
                return new Person(in);
            }

            @Override
            public Person[] newArray(int size) {
                return new Person[size];
            }
        };

        @Override
        public String toString() {
            return id+" "+name+" "+surname;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(surname);
            dest.writeString(date);
            dest.writeString(phoneNumber);
            dest.writeString(picPath);
        }
    }
}
