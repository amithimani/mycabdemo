package com.newcab.backend_application.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
    @JsonIgnore
    private Long id;
    private String licencePlate;
    private Integer seatCount;
    private Boolean convertible;
    private Integer rating;
    private String engineType;
    private String manufacturer;

    //Added to enforce constructor with argument and builder
    private CarDTO() {
    }

    public CarDTO(Long id, String licencePlate, Integer seatCount, Boolean convertible, Integer rating, String engineType, String manufacturer) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
    }

    public static CarDTO.CarDTOBuilder newBuilder() {
        return new CarDTO.CarDTOBuilder();
    }

    @JsonProperty
    public Long getId() {
        return id;
    }


    public String getLicencePlate() {
        return licencePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public Integer getRating() {
        return rating;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "CarDTOBuilder{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", seatCount=" + seatCount +
                ", convertible=" + convertible +
                ", rating=" + rating +
                ", engineType=" + engineType +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }

    public static class CarDTOBuilder {
        private Long id;
        private String licencePlate;
        private Integer seatCount;
        private Boolean convertible;
        private Integer rating;
        private String engineType;
        private String manufacturer;


        public CarDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicencePlate(String licencePlate) {
            this.licencePlate = licencePlate;
            return this;
        }

        public CarDTOBuilder setSeatCount(Integer seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public CarDTOBuilder setConvertible(Boolean convertible) {
            this.convertible = convertible;
            return this;
        }

        public CarDTOBuilder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public CarDTOBuilder setEngineType(String engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarDTOBuilder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public CarDTO createCarDTO() {
            return new CarDTO(id, licencePlate, seatCount, convertible, rating, engineType, manufacturer);
        }
    }
}