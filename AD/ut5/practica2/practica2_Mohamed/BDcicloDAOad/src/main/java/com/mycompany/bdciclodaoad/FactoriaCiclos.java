/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bdciclodaoad;

/**
 *
 * @author M24Y
 */

public class FactoriaCiclos {
    public static CicloInterface getCicloDao(){
    
        return new CicloBean();
    }
}
