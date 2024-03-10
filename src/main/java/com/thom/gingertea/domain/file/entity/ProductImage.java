package com.thom.gingertea.domain.file.entity;

import com.thom.gingertea.config.audit.Auditing;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ProductImage extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    private String path;

    private String contentType;

    private Long size;

    private String extension;

    private String originalFilename;

    private String url;
}
