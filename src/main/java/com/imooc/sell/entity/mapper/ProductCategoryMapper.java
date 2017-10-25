package com.imooc.sell.entity.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imooc.sell.entity.ProductCategory;

public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);


    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);


    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_type" , property = "categoryType"),
            @Result(column = "category_name" , property = "categoryName"),
            @Result(column = "category_id" , property = "categoryId"),
            @Result(column = "create_time" , property = "createTime"),
            @Result(column = "update_time" , property = "updateTime"),
    })
    ProductCategory findByCategoryType(Integer categoryType);


    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_type" , property = "categoryType"),
            @Result(column = "category_name" , property = "categoryName"),
            @Result(column = "category_id" , property = "categoryId"),
            @Result(column = "create_time" , property = "createTime"),
            @Result(column = "update_time" , property = "updateTime"),
    })
    List<ProductCategory> findByCategoryName(String  categoryName);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);


    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);


    @Delete("delete from product_category  where category_type = #{categoryType}")
    int deleteByCategoryType(@Param("categoryType") Integer categoryType);

    ProductCategory selectByCategoryType(@Param("categoryType") Integer categoryType);
}
