package com.ablecisi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  private Integer Id;
  private String number;
  private String password;
  private String name;
  private Integer gender;
  private LocalDate birthday;
  private String phone;
  private Integer classId;
  private String image;
}
