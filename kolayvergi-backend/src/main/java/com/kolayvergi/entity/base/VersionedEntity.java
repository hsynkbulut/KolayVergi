package com.kolayvergi.entity.base;


import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class VersionedEntity extends BaseEntity {

    @Version
    private Long version;

}
