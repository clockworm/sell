package com.imooc.sell.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
