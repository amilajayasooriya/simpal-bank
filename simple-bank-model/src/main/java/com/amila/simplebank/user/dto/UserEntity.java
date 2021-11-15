package com.amila.simplebank.user.dto;

import com.amila.simplebank.account.dto.AccountEntity;
import com.amila.simplebank.base.dto.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
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
