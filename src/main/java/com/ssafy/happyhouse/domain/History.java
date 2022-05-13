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
public class History implements Comparable<History>{
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

    String roadName;

    int roadMainCode;

    int roadSubCode;

    double lat;

    double lng;

    int distFromSubway;

    @Override
    public int compareTo(History o) {
        return this.distFromSubway-o.distFromSubway;
    }
}
