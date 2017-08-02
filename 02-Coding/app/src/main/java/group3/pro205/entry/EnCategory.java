package group3.pro205.entry;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class EnCategory {

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private ArrayList<Data> data;

    public int getCode() {
        return code;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public static class Data {
        @SerializedName("id")
        private int id;
        @SerializedName("name_vi")
        private String name_vi;
        @SerializedName("name_en")
        private String name_en;
        @SerializedName("icon_id")
        private int icon_id;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("icon_small_id")
        private int icon_small_id;
        @SerializedName("icon")
        private String icon;
        @SerializedName("icon_small")
        private String icon_small;

        public Data() {
        }

        public int getId() {
            return id;
        }

        public String getName_vi() {
            return name_vi;
        }

        public String getName_en() {
            return name_en;
        }

        public int getIcon_id() {
            return icon_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getIcon_small_id() {
            return icon_small_id;
        }

        public String getIcon() {
            return icon;
        }

        public String getIcon_small() {
            return icon_small;
        }
    }
}
