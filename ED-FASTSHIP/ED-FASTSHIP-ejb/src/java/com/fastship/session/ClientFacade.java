/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.session;

import com.fastship.entity.Client;
import com.fastship.entity.ClientDTO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Deniz
 */
@Stateless
public class ClientFacade
{

    @PersistenceContext(unitName = "ED-FASTSHIP-ejbPU")
    private EntityManager em;

    public ClientFacade()
    {
    }

    private void create(Client o)
    {
        em.persist(o);
    }

    private void edit(Client o)
    {
        em.merge(o);
    }

    private void remove(Client o)
    {
        em.remove(o);
    }

    private Client find(String id)
    {
        return em.find(Client.class, id);
    }

    public boolean hasClient(String clientId)
    {
        return (find(clientId) != null);
    }

    public String createClient(ClientDTO clientDTO)
    {
        try
        {
            String clientId = getNewClientId();
            Client client = ORConv.clientDTO2DAO(clientDTO);
            if (clientDTO.getUsername() != null)
            {
                String shaPass = getSha256(clientDTO.getPassword());
                client.setPassword(shaPass);
            }
            client.setClientId(clientId);
            this.create(client);
            return clientId;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ClientFacade.createClient()");
            return null;
        }
    }

    public boolean removeClient(String clientId)
    {
        try
        {
            Client client = find(clientId);
            if (client == null)
            {
                //client not exists
                return false;
            }
            this.remove(client);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ClientFacade.removeClient()");
            return false;
        }
    }

    public boolean updateClient(ClientDTO clientDTO)
    {
        try
        {
            if (find(clientDTO.getClientId()) == null)
            {
                //client not exists
                return false;
            }
            Client client = ORConv.clientDTO2DAO(clientDTO);
            this.edit(client);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ClientFacade.updateClient()");
            return false;
        }
    }

    public ClientDTO displayClient(String clientId)
    {
        try
        {
            Client client = this.find(clientId);
            if (client == null)
            {
                //client not exists
                return null;
            }
            return ORConv.clientDAO2DTO(client);
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ClientFacade.findClient()");
            return null;
        }
    }

    public ClientDTO displayClientForUsername(String username)
    {
        try
        {
            Query query = em.createNamedQuery("Client.findByUsername").setParameter("username", username);
            Client client = (Client) query.getSingleResult();
            if (client == null)
            {
                return null;
            }
            return ORConv.clientDAO2DTO(client);
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ClientFacade.displayClientForUsername()");
            return null;
        }
    }

    public ArrayList<ClientDTO> displayAllClient()
    {
        try
        {
            ArrayList<ClientDTO> clientDTOList = null;
            Query query = em.createNamedQuery("Client.findAll");
            List<Client> clientList = (List<Client>) query.getResultList();
            if (!clientList.isEmpty())
            {
                clientDTOList = new ArrayList<>();
                for (Client p : clientList)
                {
                    clientDTOList.add(ORConv.clientDAO2DTO(p));
                }
            }
            return clientDTOList;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ClientFacade.displayAllClient()");
            return null;
        }
    }

    private String getNewClientId()
    {
        try
        {
            int totalClients = displayAllClient().size();
            int newClientIndex = totalClients + 1;
            String newClientNoIndex = String.format("%06d", newClientIndex);
            String newClientNo = "CL" + newClientNoIndex;
            System.out.println("NEW CLIENT NO: " + newClientNo);
            return newClientNo;
        }
        catch (Exception ex)
        {
            System.out.println("Ex in ClientFacade.getNewClientId()");
            return null;
        }
    }

    private String getSha256(String value)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            String encoded = bytesToHex(md.digest());
            return encoded;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    private String bytesToHex(byte[] bytes)
    {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes)
        {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
