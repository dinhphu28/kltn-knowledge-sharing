package com.ndp.knowsharing.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.ndp.knowsharing.Entities.IdClasses.UserRoleId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dir_user_role")
@IdClass(UserRoleId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @Column(name = "roleid")
    private String roleId;

    @Id
    @Column(name = "userid")
    private String userId;
}
