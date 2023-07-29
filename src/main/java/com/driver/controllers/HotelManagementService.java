package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementService {



    public String addHotel(Hotel hotel) {
        HotelManagementRepository hotelManagementRepository=new HotelManagementRepository();
       return hotelManagementRepository.addHotel(hotel.getHotelName(), hotel);
    }

    public Integer addUser(User user) {
        HotelManagementRepository hotelManagementRepository=new HotelManagementRepository();
        return hotelManagementRepository.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        HotelManagementRepository hotelManagementRepository=new HotelManagementRepository();
        return  hotelManagementRepository.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        HotelManagementRepository hotelManagementRepository=new HotelManagementRepository();

       return hotelManagementRepository.bookARoom(booking);
    }


    public int getBookings(Integer aadharCard) {
        HotelManagementRepository hotelManagementRepository=new HotelManagementRepository();

        return hotelManagementRepository.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        HotelManagementRepository hotelManagementRepository=new HotelManagementRepository();
        return hotelManagementRepository.updateFacilities(newFacilities, hotelName);

    }
}
