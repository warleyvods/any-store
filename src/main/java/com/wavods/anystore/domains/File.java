package com.wavods.anystore.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File {

    private String fileName;
    private String contentType;
    private Long size;
    private String url;
    private LocalDateTime createdAt;
    private Boolean principal;

}
