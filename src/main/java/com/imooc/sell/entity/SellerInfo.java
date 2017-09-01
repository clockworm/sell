package com.imooc.sell.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.ToString;

/**
 * 买家
 */
@Entity
@Table(name = "seller_info")
@DynamicUpdate
@Data
@ToString
public class SellerInfo {
    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
}
