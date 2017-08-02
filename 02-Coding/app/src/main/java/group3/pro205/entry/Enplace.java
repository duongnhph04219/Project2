package group3.pro205.entry;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class Enplace {


    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public ArrayList<Data> data;
    public ArrayList<Enplace.Data> getData() {
        return data;
    }
    public static class Pivot {
        @SerializedName("place_id")
        public int place_id;
        @SerializedName("image_id")
        public int image_id;
    }

    public static class Images {
        @SerializedName("id")
        public int id;
        @SerializedName("url")
        public String url;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("o_height")
        public int o_height;
        @SerializedName("o_width")
        public int o_width;
        @SerializedName("o_size")
        public String o_size;
        @SerializedName("domination_color")
        public String domination_color;
        @SerializedName("pivot")
        public Pivot pivot;

        public int getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getO_height() {
            return o_height;
        }

        public int getO_width() {
            return o_width;
        }

        public String getO_size() {
            return o_size;
        }

        public String getDomination_color() {
            return domination_color;
        }

        public Pivot getPivot() {
            return pivot;
        }
    }



    public static class Cover {


        @SerializedName("id")
        public int id;
        @SerializedName("url")
        public String url;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("o_height")
        public int o_height;
        @SerializedName("o_width")
        public int o_width;
        @SerializedName("o_size")
        public String o_size;
        @SerializedName("domination_color")
        public String domination_color;
        @SerializedName("pivot")
        public Pivot pivot;


        public int getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getO_height() {
            return o_height;
        }

        public int getO_width() {
            return o_width;
        }

        public String getO_size() {
            return o_size;
        }

        public String getDomination_color() {
            return domination_color;
        }

        public Pivot getPivot() {
            return pivot;
        }
    }

    public static class Tags {
    }

    public static class Category {
        @SerializedName("id")
        public int id;
        @SerializedName("name_vi")
        public String name_vi;
        @SerializedName("name_en")
        public String name_en;
        @SerializedName("icon_id")
        public int icon_id;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("icon_small_id")
        public int icon_small_id;
        @SerializedName("icon")
        public String icon;
        @SerializedName("icon_small")
        public String icon_small;
    }

    public static class Place_levels {
        @SerializedName("id")
        public int id;
        @SerializedName("place_id")
        public int place_id;
        @SerializedName("level_id")
        public int level_id;
    }

    public static class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("latitude")
        public String latitude;
        @SerializedName("longitude")
        public String longitude;
        @SerializedName("name_vi")
        public String name_vi;
        @SerializedName("name_en")
        public String name_en;
        @SerializedName("short_description_vi")
        public String short_description_vi;
        @SerializedName("short_description_en")
        public String short_description_en;
        @SerializedName("description_vi")
        public String description_vi;
        @SerializedName("description_en")
        public String description_en;
        @SerializedName("how_to_go_vi")
        public String how_to_go_vi;
        @SerializedName("how_to_go_en")
        public String how_to_go_en;
        @SerializedName("enable_en")
        public int enable_en;
        @SerializedName("enable_vi")
        public int enable_vi;
        @SerializedName("category_id")
        public int category_id;
        @SerializedName("name_in_url")
        public String name_in_url;
        @SerializedName("address_vi")
        public String address_vi;
        @SerializedName("address_en")
        public String address_en;
        @SerializedName("parent_id")
        public int parent_id;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("status_vi_id")
        public int status_vi_id;
        @SerializedName("status_en_id")
        public int status_en_id;
        @SerializedName("dri_vi_id")
        public int dri_vi_id;
        @SerializedName("dri_en_id")
        public int dri_en_id;
        @SerializedName("markdown_flg")
        public String markdown_flg;
        @SerializedName("type")
        public String type;
        @SerializedName("created_by")
        public int created_by;
        @SerializedName("updated_by")
        public int updated_by;
        @SerializedName("images_count")
        public int images_count;
        @SerializedName("rating_count")
        public int rating_count;
        @SerializedName("rate_real_count")
        public int rate_real_count;
        @SerializedName("review_count")
        public int review_count;
        @SerializedName("checkin_count")
        public int checkin_count;
        @SerializedName("recommend_count")
        public int recommend_count;
        @SerializedName("report_count")
        public int report_count;
        @SerializedName("approve")
        public int approve;
        @SerializedName("images")
        public List<Images> images;
        @SerializedName("cover")
        public List<Cover> cover;
        @SerializedName("tags")
        public List<Tags> tags;
        @SerializedName("category")
        public Category category;
        @SerializedName("place_levels")
        public List<Place_levels> place_levels;

        public int getId() {
            return id;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getName_vi() {
            return name_vi;
        }

        public String getName_en() {
            return name_en;
        }

        public String getShort_description_vi() {
            return short_description_vi;
        }

        public String getShort_description_en() {
            return short_description_en;
        }

        public String getDescription_vi() {
            return description_vi;
        }

        public String getDescription_en() {
            return description_en;
        }

        public String getHow_to_go_vi() {
            return how_to_go_vi;
        }

        public String getHow_to_go_en() {
            return how_to_go_en;
        }

        public int getEnable_en() {
            return enable_en;
        }

        public int getEnable_vi() {
            return enable_vi;
        }

        public int getCategory_id() {
            return category_id;
        }

        public String getName_in_url() {
            return name_in_url;
        }

        public String getAddress_vi() {
            return address_vi;
        }

        public String getAddress_en() {
            return address_en;
        }

        public int getParent_id() {
            return parent_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getStatus_vi_id() {
            return status_vi_id;
        }

        public int getStatus_en_id() {
            return status_en_id;
        }

        public int getDri_vi_id() {
            return dri_vi_id;
        }

        public int getDri_en_id() {
            return dri_en_id;
        }

        public String getMarkdown_flg() {
            return markdown_flg;
        }

        public String getType() {
            return type;
        }

        public int getCreated_by() {
            return created_by;
        }

        public int getUpdated_by() {
            return updated_by;
        }

        public int getImages_count() {
            return images_count;
        }

        public int getRating_count() {
            return rating_count;
        }

        public int getRate_real_count() {
            return rate_real_count;
        }

        public int getReview_count() {
            return review_count;
        }

        public int getCheckin_count() {
            return checkin_count;
        }

        public int getRecommend_count() {
            return recommend_count;
        }

        public int getReport_count() {
            return report_count;
        }

        public int getApprove() {
            return approve;
        }

        public List<Images> getImages() {
            return images;
        }

        public List<Cover> getCover() {
            return cover;
        }

        public List<Tags> getTags() {
            return tags;
        }

        public Category getCategory() {
            return category;
        }

        public List<Place_levels> getPlace_levels() {
            return place_levels;
        }

    }
}
