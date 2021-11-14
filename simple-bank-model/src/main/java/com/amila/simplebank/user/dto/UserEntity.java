package com.amila.simplebank.user.dto;

import com.amila.simplebank.base.dto.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@Getter
public class UserEntity extends BaseEntity {

    @Column (nullable = false, unique = true)
    String name;

    @Column (nullable = false, unique = true)
    String email;

    @Column (nullable = false, unique = true)
    String nin;
}
