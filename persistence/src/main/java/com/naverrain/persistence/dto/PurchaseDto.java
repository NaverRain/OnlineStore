package com.naverrain.persistence.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.List;

@Entity(name = "purchase")
public class PurchaseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_purchase_user")
    private UserDto userDto;

    @Column(name = "purchase_timestamp", insertable = false,
            updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseTimestamp;

    @ManyToOne
    @JoinColumn(name = "fk_purchase_purchase_status")
    private PurchaseStatusDto purchaseStatusDto;

    @ManyToMany
    @JoinTable(name = "purchase_product",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductDto> productDtos;

    public PurchaseDto(){
    }

    public PurchaseDto(Integer id, UserDto userDto, PurchaseStatusDto purchaseStatusDto){
        this.id = id;
        this.userDto = userDto;
        this.purchaseStatusDto = purchaseStatusDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    public PurchaseStatusDto getPurchaseStatusDto() {
        return this.purchaseStatusDto;
    }

    public void setPurchaseStatusDto(PurchaseStatusDto purchaseStatusDto) {
        this.purchaseStatusDto = purchaseStatusDto;
    }

    public Date getPurchaseTimestamp() {
        return purchaseTimestamp;
    }
    public void setPurchaseTimestamp(Date purchaseTimestamp) {
        this.purchaseTimestamp = purchaseTimestamp;
    }
}
