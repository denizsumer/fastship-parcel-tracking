/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship;

import javax.ejb.EJB;
import com.fastship.session.ManagementFacadeRemote;
/**
 *
 * @author Deniz
 */
public class AppClient2
{
    @EJB
    private static ManagementFacadeRemote facade;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        GUInterface2 gui = new GUInterface2(facade);
        
    }
    
    public static ManagementFacadeRemote getFacade()
    {
        return facade;
    }
}
