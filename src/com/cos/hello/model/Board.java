package com.cos.hello.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Board {
private int id;
private String title;
private String content;
private int userId; //외래키 
}
