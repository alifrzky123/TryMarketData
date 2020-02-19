package com.training.alif.geeksfarm.marketplace.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    @SerializedName("productId")
    int id;
    @SerializedName("productQty")
    int qty;
    @SerializedName("productName")
    String name;
    @SerializedName("productSlug")
    String slug;
    @SerializedName("productImage")
    String img;
    @SerializedName("merchant")
    Merchant merch;
    @SerializedName("category")
    Category cat;

    public int getId() {
        return id;
    }

    public int getQty() {
        return qty;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getImg() {
        return img;
    }

    public Merchant getMerch() {
        return merch;
    }

    public Category getCat() {
        return cat;
    }

    public Product(int id, int qty, String name, String slug, String img, Merchant merch, Category cat) {
        this.id = id;
        this.qty = qty;
        this.name = name;
        this.slug = slug;
        this.img = img;
        this.merch = merch;
        this.cat = cat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.qty);
        dest.writeString(this.name);
        dest.writeString(this.slug);
        dest.writeString(this.img);
        dest.writeParcelable(this.merch, flags);
        dest.writeParcelable(this.cat, flags);
    }

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.qty = in.readInt();
        this.name = in.readString();
        this.slug = in.readString();
        this.img = in.readString();
        this.merch = in.readParcelable(Merchant.class.getClassLoader());
        this.cat = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
