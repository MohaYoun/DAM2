/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persistenciabdemp;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Javier
 */
public class PersistenciaBDemp {

    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("PersistenciaBDemp");
        DeptJpaController dao=new DeptJpaController(emf);
        List<Dept> lista=dao.findDeptEntities();
        for(Dept departamento: lista){
            System.out.println("nombre del departamento: "+departamento.getDname());
        }
    }
}
