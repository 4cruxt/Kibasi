package com.tancorp.kibasi.customer.models;

public class Ticket
{
    private int _busImageTicket;
    private int _dotVerifierTicket;
    private String _busNameTicket;
    private String _busNumberTicket;
    private String _busSeatTicket;
    private String _busDateTicket;

    public Ticket(int busImageTicket, int dotVerifierTicket, String busNameTicket, String busNumberTicket, String busSeatTicket, String busDateTicket)
    {
        _busImageTicket = busImageTicket;
        _dotVerifierTicket = dotVerifierTicket;
        _busNameTicket = busNameTicket;
        _busNumberTicket = busNumberTicket;
        _busSeatTicket = busSeatTicket;
        _busDateTicket = busDateTicket;
    }

    public int getDotVerifierTicket()
    {
        return _dotVerifierTicket;
    }

    public void setDotVerifierTicket(int dotVerifierTicket)
    {
        _dotVerifierTicket = dotVerifierTicket;
    }

    public String getBusNumberTicket()
    {
        return _busNumberTicket;
    }

    public void setBusNumberTicket(String busNumberTicket)
    {
        _busNumberTicket = busNumberTicket;
    }

    public String getBusSeatTicket()
    {
        return _busSeatTicket;
    }

    public void setBusSeatTicket(String busSeatTicket)
    {
        _busSeatTicket = busSeatTicket;
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
