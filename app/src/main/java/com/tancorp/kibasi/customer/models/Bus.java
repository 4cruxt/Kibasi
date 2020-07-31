package com.tancorp.kibasi.customer.models;

public class Bus
{
    private String _busName;
    private String _busJourneyTime;
    private String _busTicket;
    private String _busDepartTime;
    private String _busArrivalTime;
    private int _busImage;
    private int _busSeatNumber;
    private String _busPlateNumber;

    public Bus()
    {

    }

    public Bus(String busName, String busJourneyTime, String busTicket, String busDepartTime, String busArrivalTime, String busPlateNumber, int busImage, int busSeatNumber)
    {
        _busName = busName;
        _busJourneyTime = busJourneyTime;
        _busTicket = busTicket;
        _busDepartTime = busDepartTime;
        _busArrivalTime = busArrivalTime;
        _busImage = busImage;
        _busSeatNumber = busSeatNumber;
        _busPlateNumber = busPlateNumber;
    }

    public String getBusPlateNumber()
    {
        return _busPlateNumber;
    }

    public void setBusPlateNumber(String busPlateNumber)
    {
        _busPlateNumber = busPlateNumber;
    }

    public int getBusSeatNumber()
    {
        return _busSeatNumber;
    }

    public void setBusSeatNumber(int busSeatNumber)
    {
        _busSeatNumber = busSeatNumber;
    }

    public String getBusDepartTime()
    {
        return _busDepartTime;
    }

    public void setBusDepartTime(String busDepartTime)
    {
        _busDepartTime = busDepartTime;
    }

    public String getBusArrivalTime()
    {
        return _busArrivalTime;
    }

    public void setBusArrivalTime(String busArrivalTime)
    {
        _busArrivalTime = busArrivalTime;
    }

    public String getBusTicket()
    {
        return _busTicket;
    }

    public void setBusTicket(String busTicket)
    {
        _busTicket = busTicket;
    }

    public String getBusName()
    {
        return _busName;
    }

    public void setBusName(String busName)
    {
        _busName = busName;
    }

    public String getBusJourneyTime()
    {
        return _busJourneyTime;
    }

    public void setBusJourneyTime(String busJourneyTime)
    {
        _busJourneyTime = busJourneyTime;
    }

    public int getBusImage()
    {
        return _busImage;
    }

    public void setBusImage(int busImage)
    {
        _busImage = busImage;
    }
}
