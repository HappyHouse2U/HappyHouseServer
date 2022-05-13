package com.ssafy.happyhouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {
    @Id
    @GeneratedValue
    Long no;

    String aptName;

    int amount;

    double area;

    int floor;

    String addressId;

    int year;

    int month;
}
