package com.thom.gingertea.domain.hashtag.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class HashTag {

    @Id
    @GeneratedValue
    private Long id;

    private String hashTagName;

}
