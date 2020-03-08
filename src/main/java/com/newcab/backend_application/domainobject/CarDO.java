package com.newcab.backend_application.domainobject;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(
        name = "car",
        uniqueConstraints = @UniqueConstraint(name = "uc_carid", columnNames = {"id"})
)
public class CarDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime date_created = ZonedDateTime.now();

    @Column(nullable = false)
    private String licence_plate;

    @Column(nullable = false)
    private Integer seat_count;

    @Column(nullable = false)
    private Boolean convertible;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private String engine_type;

    @Column(nullable = false)
    private Boolean is_active = true;

    @Column(nullable = false)
    private String manufacturer;

    private CarDO() {
    }

    public CarDO(String licence_plate, Integer seat_count, Boolean convertible, Integer rating, String engine_type, String manufacturer) {
        this.licence_plate = licence_plate;
        this.seat_count = seat_count;
        this.convertible = convertible;
        this.rating = rating;
        this.engine_type = engine_type;
        this.is_active = true;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getdate_created() {
        return date_created;
    }

    public void setdate_created(ZonedDateTime date_created) {
        this.date_created = date_created;
    }

    public String getlicence_plate() {
        return licence_plate;
    }

    public void setlicence_plate(String licence_plate) {
        this.licence_plate = licence_plate;
    }

    public Integer getseat_count() {
        return seat_count;
    }

    public void setseat_count(Integer seat_count) {
        this.seat_count = seat_count;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarDO carDO = (CarDO) o;

        if (id != null ? !id.equals(carDO.id) : carDO.id != null) return false;
        if (date_created != null ? !date_created.equals(carDO.date_created) : carDO.date_created != null) return false;
        if (licence_plate != null ? !licence_plate.equals(carDO.licence_plate) : carDO.licence_plate != null)
            return false;
        if (seat_count != null ? !seat_count.equals(carDO.seat_count) : carDO.seat_count != null) return false;
        if (convertible != null ? !convertible.equals(carDO.convertible) : carDO.convertible != null) return false;
        if (rating != null ? !rating.equals(carDO.rating) : carDO.rating != null) return false;
        if (engine_type != null ? !engine_type.equals(carDO.engine_type) : carDO.engine_type != null) return false;
        if (is_active != null ? !is_active.equals(carDO.is_active) : carDO.is_active != null) return false;
        return manufacturer != null ? manufacturer.equals(carDO.manufacturer) : carDO.manufacturer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date_created != null ? date_created.hashCode() : 0);
        result = 31 * result + (licence_plate != null ? licence_plate.hashCode() : 0);
        result = 31 * result + (seat_count != null ? seat_count.hashCode() : 0);
        result = 31 * result + (convertible != null ? convertible.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (engine_type != null ? engine_type.hashCode() : 0);
        result = 31 * result + (is_active != null ? is_active.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        return result;
    }

}

