package com.tancorp.kibasi.managers.models;

public class MPMid
{
    private int _midPhoto;
    private String _midName;
    private String _midPhone;
    private String _midBus;

    public MPMid(int midPhoto, String midName, String midPhone, String midBus)
    {
        _midPhoto = midPhoto;
        _midName = midName;
        _midPhone = midPhone;
        _midBus = midBus;
    }

    public MPMid()
    {

    }

    public int getMidPhoto()
    {
        return _midPhoto;
    }

    public void setMidPhoto(int midPhoto)
    {
        _midPhoto = midPhoto;
    }

    public String getMidName()
    {
        return _midName;
    }

    public void setMidName(String midName)
    {
        _midName = midName;
    }

    public String getMidPhone()
    {
        return _midPhone;
    }

    public void setMidPhone(String midPhone)
    {
        _midPhone = midPhone;
    }

    public String getMidBus()
    {
        return _midBus;
    }

    public void setMidBus(String midBus)
    {
        _midBus = midBus;
    }
}
