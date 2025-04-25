package com.kolayvergi.entity.base;


import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Getter
@Setter
public abstract class VersionedEntity extends BaseEntity {

    @Version
    private Long version;

}
