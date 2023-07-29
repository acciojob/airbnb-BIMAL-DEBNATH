package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class HotelManagementRepository {
    HashMap<String,Hotel>map=new HashMap<>();
    HashMap<Integer,User>userMap=new HashMap<>();

    HashMap<String, Booking>bookingMap=new HashMap<>();


    public String addHotel(String hotelName, Hotel hotel) {

        if(!map.containsKey(hotelName)){
            map.put(hotelName,hotel);
            return "SUCCESS";
        } else if (map.containsKey(hotelName)) {
            return "FAILURE";
        }else {
            return "";
        }

    }

    public Integer addUser(User user) {
        userMap.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }



    private String lexicographicallySmaller() {
        List<String>list=new ArrayList<>();
        for(String k:map.keySet()){
            list.add(map.get(k).getHotelName());
        }

       return findMin(list);

    }
    public String getHotelWithMostFacilities() {

        boolean flag=false;
        HashMap<Integer,Integer>tm=new HashMap<>();
        int big=0;
        String hotelName="";

        for(String h:map.keySet()){
            List<Facility> fac=map.get(h).getFacilities();
            int size= fac.size();
            if(tm.get(size)>0){
                return lexicographicallySmaller();
            }

            if(size>big){
                big=size;
                hotelName=map.get(h).getHotelName();
            }
            tm.put(size, tm.getOrDefault(size, 0)+1);
        }

        if(big==0){
            return "";
        }

        return hotelName;
    }







    public static String findMin(List<String> list) {
        // If the list is empty, return null
        if (list == null || list.isEmpty()) {
            return "";
        }

        // Initialize the min string as the first element of the list
        String min = list.get(0);

        // Loop through the rest of the list and compare each element with the min string
        for (int i = 1; i < list.size(); i++) {
            String current = list.get(i);

            // If the current string is lexicographically smaller than the min string, update the min string
            if (current.compareTo(min) < 0) {
                min = current;
            }
        }

        // Return the min string
        return min;
    }




    public int bookARoom(Booking booking) {

       String corrId=String.valueOf( UUID.randomUUID());

         Booking s=new Booking(corrId,booking.getBookingAadharCard(), booking.getNoOfRooms(),booking.getBookingPersonName(), booking.getHotelName());

         bookingMap.put(corrId,s);

         int vt=map.get(booking.getHotelName()).getAvailableRooms();

         if(vt<booking.getNoOfRooms()){
             return -1;
         }

         int amount=booking.getNoOfRooms() * map.get(booking.getHotelName()).getPricePerNight();

         return amount;

    }

    public int getBookings(Integer aadharCard) {

        for(String s:bookingMap.keySet()){
            int tm =bookingMap.get(s).getBookingAadharCard();
            if(tm==aadharCard){
                String boo= bookingMap.get(s).getBookingId();
                return Integer.parseInt(boo);
            }
        }
        return 0;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        Hotel ht=map.getOrDefault(hotelName, null);
        //Hotel ht=map.get(hotelName);
        if(ht==null)return ht;

        for(Facility fn:newFacilities) {
            boolean flag=false;
            for (Facility f : ht.getFacilities()) {
               if(fn==f)flag=true;
            }


            if(!flag){
                map.get(hotelName).getFacilities().add(fn);
            }
            ht=map.get(hotelName);
        }
        return map.get(hotelName);
    }
}

