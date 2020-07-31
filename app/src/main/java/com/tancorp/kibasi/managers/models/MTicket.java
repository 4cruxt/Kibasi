package com.tancorp.kibasi.managers.models;

public class MTicket
{
    private int _busPhoto;
    private String _busName;
    private String _busNumber;
    private String _fromRegion;
    private String _toRegion;
    private String _ticketPrice;
    private String _busSeat;

    public MTicket()
    {

    }

    public MTicket(int busPhoto, String busName, String busNumber, String fromRegion, String toRegion, String ticketPrice, String busSeat)
    {
        _busPhoto = busPhoto;
        _busName = busName;
        _busNumber = busNumber;
        _fromRegion = fromRegion;
        _toRegion = toRegion;
        _ticketPrice = ticketPrice;
        _busSeat = busSeat;
    }

    public int getBusPhoto()
    {
        return _busPhoto;
    }

    public void setBusPhoto(int busPhoto)
    {
        _busPhoto = busPhoto;
    }

    public String getBusName()
    {
        return _busName;
    }

    public void setBusName(String busName)
    {
        _busName = busName;
    }

    public String getBusNumber()
    {
        return _busNumber;
    }

    public void setBusNumber(String busNumber)
    {
        _busNumber = busNumber;
    }

    public String getFromRegion()
    {
        return _fromRegion;
    }

    public void setFromRegion(String fromRegion)
    {
        _fromRegion = fromRegion;
    }

    public String getToRegion()
    {
        return _toRegion;
    }

    public void setToRegion(String toRegion)
    {
        _toRegion = toRegion;
    }

    public String getTicketPrice()
    {
        return _ticketPrice;
    }

    public void setTicketPrice(String ticketPrice)
    {
        _ticketPrice = ticketPrice;
    }

    public String getBusSeat()
    {
        return _busSeat;
    }

    public void setBusSeat(String busSeat)
    {
        _busSeat = busSeat;
    }
}
