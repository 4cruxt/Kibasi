package com.tancorp.kibasi.managers.models;

public class MExploreVerifier
{
    private String _passengerName;
    private String _payerName;
    private String _ConfirmationCode;

    public MExploreVerifier()
    {

    }

    public MExploreVerifier(String passengerName, String payerName, String confirmationCode)
    {
        _passengerName = passengerName;
        _payerName = payerName;
        _ConfirmationCode = confirmationCode;
    }

    public String getPassengerName()
    {
        return _passengerName;
    }

    public void setPassengerName(String passengerName)
    {
        _passengerName = passengerName;
    }

    public String getPayerName()
    {
        return _payerName;
    }

    public void setPayerName(String payerName)
    {
        _payerName = payerName;
    }

    public String getConfirmationCode()
    {
        return _ConfirmationCode;
    }

    public void setConfirmationCode(String confirmationCode)
    {
        _ConfirmationCode = confirmationCode;
    }
}
