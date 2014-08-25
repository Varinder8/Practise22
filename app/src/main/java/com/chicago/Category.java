package com.chicago;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 8/21/2014.
 */
@Table(name = "Categories")
public class Category extends Model {
    @Column(name = "Name", index = true)
    public String name;

    // This method is optional, does not affect the foreign key creation.


    public Category SaveCategory(String categoryName){
        Category category= new Category();
        category.name=categoryName;
        category.save();
        return category;
    }
}

