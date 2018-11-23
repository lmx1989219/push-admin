package com.lmx.pushplatform.gateway.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_user")
@Data
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String userName;
    private String appName;
    private Date createTime, updateTime;
    @ManyToOne
    @JoinColumn(name = "appName", nullable = false, updatable = false, insertable = false)
    private AppEntity appEntity;
}
