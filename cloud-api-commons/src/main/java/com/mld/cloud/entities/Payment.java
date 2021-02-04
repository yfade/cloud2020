package com.mld.cloud.entities;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
