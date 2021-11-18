package com.amila.simplebank.user.entity;

import com.amila.simplebank.account.entity.AccountEntity;
import com.amila.simplebank.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column (nullable = false, unique = true)
    private String name;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false, unique = true)
    private String nin;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user_account")
    private List<AccountEntity> accountEntityList;
}
