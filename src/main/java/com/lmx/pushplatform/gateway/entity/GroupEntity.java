package com.lmx.pushplatform.gateway.entity;

import lombok.Data;
import lombok.experimental.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 群组
 */
@Entity
@Table(name = "t_group")
@Data
@Builder
public class GroupEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String groupName;
    private String userId;//群主
    private int state;
    private Date createTime, updateTime;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, updatable = false, insertable = false)
    private UserEntity userEntity;

    @OneToMany(targetEntity = GroupMembersEntity.class, cascade = CascadeType.ALL,
            mappedBy = "id")
    private Set<GroupMembersEntity> groupMembersEntitySet;
}
