package com.kolayvergi.entity.vergi;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mtv_vergisi")
@Getter
@Setter
@AllArgsConstructor
public class MtvVergisi extends Vergi {
}
