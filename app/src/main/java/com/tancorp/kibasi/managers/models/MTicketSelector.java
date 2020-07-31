package com.tancorp.kibasi.managers.models;

public class MTicketSelector
{
    private int _busPhoto;
    private String _passengerName;
    private String _confirmationNumber;
    private String _SeatNumber;

    public MTicketSelector()
    {

    }

    public MTicketSelector(int busPhoto, String passengerName, String confirmationNumber, String seatNumber)
    {
        _busPhoto = busPhoto;
        _passengerName = passengerName;
        _confirmationNumber = confirmationNumber;
        _SeatNumber = seatNumber;
    }

    public int getBusPhoto()
    {
        return _busPhoto;
    }

    public void setBusPhoto(int busPhoto)
    {
        _busPhoto = busPhoto;
    }

    public String getPassengerName()
    {
        return _passengerName;
    }

    public void setPassengerName(String passengerName)
    {
        _passengerName = passengerName;
    }

    public String getConfirmationNumber()
    {
        return _confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber)
    {
        _confirmationNumber = confirmationNumber;
    }

    public String getSeatNumber()
    {
        return _SeatNumber;
    }

    public void setSeatNumber(String seatNumber)
    {
        _SeatNumber = seatNumber;
    }
}
