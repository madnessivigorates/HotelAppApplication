package com.Senla.Mathew.HotelApp.Models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idService")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idGuest")
    private Guest guest;

    public Service() {
    }

    public Service(String name, int price, LocalDate date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public Guest getGuest() {
        return guest;
    }

    public Long getGuestId(){
        return guest.getId();
    }

    public void successfullyAdded() {
        System.out.println("Услуга успешно добавлена!");
    }

    @Override
    public String toString() {
        return "Услуга: " + name + ", Цена: " + price + ", Дата: " + date;
    }
}
