/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persistenciabdpersona;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author M24Y
 */
public class PersistenciaBDpersona {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaBDpersona");
        PersonasJpaController dao = new PersonasJpaController(emf);
        List<Personas> lista = dao.findPersonaEntities();
        for(Personas persona : lista){
            System.out.println("Nombre : " + persona.getNombre());
            System.out.println("Edad : " + persona.getEdad());
        }
        
        //ejecución 2 dar alta departamento
        EntityManager em = emf.createEntityManager();
        Personas unPersona = new Personas();
        unPersona.setId(5);
        unPersona.setNombre("Chetor");
        unPersona.setApellido("Martado");
        unPersona.setSalario(5000);
        unPersona.setEdad(55);
            em.getTransaction().begin();
            em.persist(unPersona);
            em.getTransaction().commit();
    }
}
