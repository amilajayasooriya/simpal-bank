package com.amila.simplebank.base.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "HIBERNATE_SEQUENCE")
    @Column(name = "ID", nullable = false, unique = true, updatable = false, length = 16)
    @Setter(AccessLevel.PUBLIC)
    private UUID id;

    @Version
    private short version;

    @Column(name="CREATED_DATE", updatable=false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}
