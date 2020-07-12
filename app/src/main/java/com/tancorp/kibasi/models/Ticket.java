package com.tancorp.kibasi.models;

public class Ticket
{
    private int _busImageTicket;
    private String _busNameTicket;
    private String _busDateTicket;

    public Ticket(int busImageTicket, String busNameTicket, String busDateTicket)
    {
        _busImageTicket = busImageTicket;
        _busNameTicket = busNameTicket;
        _busDateTicket = busDateTicket;
    }

    public int getBusImageTicket()
    {
        return _busImageTicket;
    }

    public void setBusImageTicket(int busImageTicket)
    {
        _busImageTicket = busImageTicket;
    }

    public String getBusNameTicket()
    {
        return _busNameTicket;
    }

    public void setBusNameTicket(String busNameTicket)
    {
        _busNameTicket = busNameTicket;
    }

    public String getBusDateTicket()
    {
        return _busDateTicket;
    }

    public void setBusDateTicket(String busDateTicket)
    {
        _busDateTicket = busDateTicket;
    }
}
