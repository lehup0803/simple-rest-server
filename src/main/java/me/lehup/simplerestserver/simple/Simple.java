package me.lehup.simplerestserver.simple;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Simple {

    @Id
    @GeneratedValue
    private Long id;

    private String dataString;
    private Integer dataInteger;

}
