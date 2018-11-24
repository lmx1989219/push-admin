package com.lmx.pushplatform.gateway.entity;

import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_app")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"developerEntity", "userEntitySet"})
public class AppEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String appName, appKey, appSecret;
    private String developerId;
    private int state;
    private Date createTime, updateTime;
    @ManyToOne
    @JoinColumn(name = "developerId", nullable = false, updatable = false, insertable = false)
    private DeveloperEntity developerEntity;

    @OneToMany(targetEntity = UserEntity.class, cascade = CascadeType.ALL,
            mappedBy = "appEntity")
    private Set<UserEntity> userEntitySet;
}
