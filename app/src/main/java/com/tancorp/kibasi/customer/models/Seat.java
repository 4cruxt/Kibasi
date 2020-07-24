package com.tancorp.kibasi.customer.models;

public class Seat
{
    private int _seatIcon;
    private String _seatIdText;

    public Seat(int seatIcon, String seatIdText)
    {
        _seatIcon = seatIcon;
        _seatIdText = seatIdText;
    }

    public int getSeatIcon()
    {
        return _seatIcon;
    }

    public void setSeatIcon(int seatIcon)
    {
        _seatIcon = seatIcon;
    }

    public String getSeatIdText()
    {
        return _seatIdText;
    }

    public void setSeatIdText(String seatIdText)
    {
        _seatIdText = seatIdText;
    }
}
