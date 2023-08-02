package com.libertango.school.customer.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class CustomerClass {

    private int id;

    private String clientName;
    private List<Classes> classes;
}
