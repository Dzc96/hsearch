package com.gdei.hsearch.entity;

import javax.persistence.*;

@Entity
@Table(name="support_address")
public class SupportAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //上一级行政单位
    @Column(name = "belong_to")
    private String belongTo;
    @Column(name = "en_Name")
    private String enName;
    @Column(name= "cn_Name")
    private String cnName;

    //行政级别 城市或者区域 city or region
    private String level;


    @Column(name = "baidu_map_lng")
    private double baiduMapLongitude;

    @Column(name = "baidu_map_lat")
    private double baiduMapLatitude;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getBaiduMapLongitude() {
        return baiduMapLongitude;
    }

    public void setBaiduMapLongitude(double baiduMapLongitude) {
        this.baiduMapLongitude = baiduMapLongitude;
    }

    public double getBaiduMapLatitude() {
        return baiduMapLatitude;
    }

    public void setBaiduMapLatitude(double baiduMapLatitude) {
        this.baiduMapLatitude = baiduMapLatitude;
    }


    /**
     * 行政级别定义，城市或者区域，例如北京市  东城区
     */
    public enum Level {
        CITY("city"),
        REGION("region");

        private String value;

        Level(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        //遍历所有枚举对象，找到对应枚举对象并返回
        public static Level of(String value) {

            for (Level level : Level.values()) { //枚举类的values()方法返回一个枚举数组
                if (level.getValue().equals(level)) {
                    return level;
                }
            }
            throw new IllegalArgumentException();
        }


    }



}
