/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.ejb;

import co.com.sophos.entidades.Sophosemployeestudent;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cristian.ordonez
 */
@Stateless(name = "ControladorDatosEstudiantes", mappedName = "ControladorDatosEstudiantesBean")
public class ControladorDatosEstudiantes implements IControladorDatosEstudiantesLocal {
    
    @PersistenceContext(unitName = "OraclePU")
    private EntityManager em;
    
    @Override
    public List<Sophosemployeestudent> obtenerEstudiantesEmpleadosPorDescripcion(String desc) throws Exception {
        List<Sophosemployeestudent> li;
        Query query;
        try {
            if (esNumero(desc)) {
                query = em.createNamedQuery("Sophosemployeestudent.findByEmpestEmployeeid").setParameter("empestEmployeeid", desc);
                li = query.getResultList();
                return li;
                
            } else {
                query = em.createNamedQuery("Sophosemployeestudent.findByEmpestEmployeeshortname").setParameter("empestEmployeeshortname", "%" + desc.trim().toUpperCase() + "%");
                li = query.getResultList();
                return li;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public Sophosemployeestudent find(Long idEstudiante) throws Exception {
        return em.find(Sophosemployeestudent.class, idEstudiante);
    }
    
    private static boolean esNumero(String param) {
        for (char c : param.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public List<Sophosemployeestudent> listarEstudiantes() throws Exception {
        try {
            return em.createNamedQuery("Sophosemployeestudent.findAll").getResultList();
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void eliminarEstudiante(Sophosemployeestudent estudiante) throws Exception {
        try {
            estudiante = em.find(Sophosemployeestudent.class, estudiante.getEmpestId());
            em.remove(estudiante);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void modificarEstudiante(Sophosemployeestudent estudiante) throws Exception {
        try {
            em.merge(estudiante);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void registrarEstudiante(Sophosemployeestudent estudiante) throws Exception {
        try {
            em.persist(estudiante);
        } catch (Exception e) {
            throw e;
        }
    }
}
