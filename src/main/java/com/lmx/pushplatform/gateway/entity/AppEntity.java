package com.lmx.pushplatform.gateway.entity;

import lombok.Data;
import lombok.experimental.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_app")
@Data
@Builder
public class AppEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String appName,appKey,appSecret;
    private String developer;
    private Date createTime, updateTime;
    @ManyToOne
    @JoinColumn(name = "developer", nullable = false, updatable = false, insertable = false)
    private DeveloperEntity developerEntity;

    @OneToMany(targetEntity = UserEntity.class, cascade = CascadeType.ALL,
            mappedBy = "appName")
    private Set<UserEntity> userEntitySet;
}
