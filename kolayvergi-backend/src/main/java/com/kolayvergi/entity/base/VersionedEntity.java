package com.kolayvergi.entity.base;


import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class VersionedEntity extends BaseEntity {

    @Version
    private Long version;

}
