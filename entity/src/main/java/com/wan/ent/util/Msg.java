package com.wan.ent.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class Msg<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

}
