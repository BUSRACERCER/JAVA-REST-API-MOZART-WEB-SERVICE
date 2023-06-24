package com.works.demo.models;

import lombok.Data;

import java.util.List;

@Data
public class MusicCategory {
    private String baseTitle;
    private List<Item> items;

}
