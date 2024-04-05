package com.dailycodework.uniquehotel.service;


import com.dailycodework.uniquehotel.model.BookedRoom;

import java.util.List;

public interface IBookingService {

    List<BookedRoom> getAllBookings();



    void cancelBooking(Long bookingId);

    List<BookedRoom> getAllBookingsByRoomId(Long roomId);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    BookedRoom findByBookingConfirmationCode(String confirmationCode);

    List<BookedRoom> getBookingsByUserEmail(String email);
}
