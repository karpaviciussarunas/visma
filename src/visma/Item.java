/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visma;

import java.util.Date;

/**
 *
 * @author Sars
 */
public class Item {
private String name;
private Long code;
private Integer quantity;
Date date;
           
    
    public Item() {
    }

    public Item(String name, Long code, Integer quantity, Date date) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", code=" + code + ", quantity=" + quantity + ", date=" + date + '}';
    }

  
}
