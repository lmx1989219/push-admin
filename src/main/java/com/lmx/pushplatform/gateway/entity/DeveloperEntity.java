package com.lmx.pushplatform.gateway.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_developer")
@Data
public class DeveloperEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String developer;
    private String password;
    private int state;
    private Date createTime, updateTime;
    @OneToMany(targetEntity = AppEntity.class, cascade = CascadeType.ALL,
            mappedBy = "id")
    private Set<AppEntity> appEntitySet;
}
