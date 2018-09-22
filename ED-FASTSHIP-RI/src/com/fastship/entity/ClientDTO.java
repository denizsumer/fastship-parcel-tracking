/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Deniz
 */
@XmlRootElement(name = "ClientDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientDTO implements Serializable
{

    @XmlElement(required = true)
    private String clientId;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String pic;
    @XmlElement(required = true)
    private String phone;
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private String usergroup;

    public ClientDTO(String clientId, String name, String pic, String phone, String address, String email, String username, String password, String usergroup)
    {
        this.clientId = clientId;
        this.name = name;
        this.pic = pic;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.usergroup = usergroup;
    }

    public ClientDTO()
    {

    }

    public String getClientId()
    {
        return clientId;
    }

    public String getName()
    {
        return name;
    }

    public String getPic()
    {
        return pic;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getAddress()
    {
        return address;
    }

    public String getEmail()
    {
        return email;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsergroup()
    {
        return usergroup;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUsergroup(String usergroup)
    {
        this.usergroup = usergroup;
    }

}
