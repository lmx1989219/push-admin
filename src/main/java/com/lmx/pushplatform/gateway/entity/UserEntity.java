package com.lmx.pushplatform.gateway.entity;

import lombok.Data;
import lombok.experimental.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_user")
@Data
@Builder
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String userName;
    private String appId;
    private int state;
    private Date createTime, updateTime;
    @ManyToOne
    @JoinColumn(name = "appId", nullable = false, updatable = false, insertable = false)
    private AppEntity appEntity;

    @OneToMany(targetEntity = UserRelatedEntity.class, cascade = CascadeType.ALL,
            mappedBy = "id")
    private Set<UserRelatedEntity> userRelatedEntitySet;

    @OneToMany(targetEntity = GroupEntity.class, cascade = CascadeType.ALL,
            mappedBy = "id")
    private Set<GroupEntity> groupEntitySet;
}
