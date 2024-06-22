/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bdciclodaoad;

/**
 *
 * @author M24Y
 */
public class BDcicloDAOad {

    public static void main(String[] args) {
        CicloInterface objetoDAO = FactoriaCiclos.getCicloDao();

        CicloInterface ciclo1 = objetoDAO.getNuevoCiclo("DPE", "DESAROLLO DE PRODUCTOS INFORMATICOS", "MEDIOS");
    }
}
