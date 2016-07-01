/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.ejb;

import co.com.sophos.entidades.Sophoscapacitations;
import co.com.sophos.entidades.Sophosemployeestudent;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cristian.ordonez
 */
@Stateless(name = "ControladorDatosCapacitaciones", mappedName = "ControladorDatosCapacitacionesBean")
public class ControladorDatosCapacitaciones implements IControladorDatosCapacitacionesLocal {

    @PersistenceContext(unitName = "OraclePU")
    private EntityManager em;

    @Override
    public List<Sophoscapacitations> listarCapacitaciones() throws Exception {
        try {
            Query query = em.createNamedQuery("Sophoscapacitations.findAll");
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void registrarCapacitacion(Sophoscapacitations sophoscapacitations) throws Exception {
        try {
            em.persist(sophoscapacitations);
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void modificarCapacitacion(Sophoscapacitations sophoscapacitations) throws Exception {
        try {
            sophoscapacitations = em.find(Sophoscapacitations.class, sophoscapacitations.getCapId());
            em.merge(sophoscapacitations);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void eliminarCapacitacion(Sophoscapacitations sophoscapacitations) throws Exception {

        try {
            sophoscapacitations = em.find(Sophoscapacitations.class, sophoscapacitations.getCapId());
            em.remove(sophoscapacitations);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean existeCapacitacionAsociada(Sophosemployeestudent est, Sophoscapacitations cap) {
        String sb = "select distinct tp.EMPEST_ID,tp.CAP_ID from SOPHOSCAPXEMP tp where tp.EMPEST_ID = ? and tp.CAP_ID = ?";
        List<BigDecimal> li = em.createNativeQuery(sb).setParameter(1, est.getEmpestId()).setParameter(2, cap.getCapId()).getResultList();
        return li.size() >= 1;
    }

    @Override
    public List<Sophoscapacitations> listarCapacitacionesNoAsociadasAEstudiante(Long idStudent) throws Exception {
        try {
            StringBuilder sb = new StringBuilder("select cap.CAP_ID from SOPHOSCAPACITATIONS cap where cap.CAP_ID ");
            sb.append("not in (select cap.CAP_ID from sophoscapacitations cap ");
            sb.append("inner join SOPHOSCAPXEMP tp on tp.CAP_ID = cap.CAP_ID ");
            sb.append("inner join SOPHOSEMPLOYEESTUDENT est on tp.EMPEST_ID = est.EMPEST_ID ");
            sb.append("where est.EMPEST_ID = ?) ");

            Query query = em.createNativeQuery(sb.toString()).setParameter(1, idStudent);
            List<BigDecimal> objects = query.getResultList();
            return obtenerEntidadesCapacitacion(objects);

        } catch (Exception e) {
            throw e;
        }
    }

    private List<Sophoscapacitations> obtenerEntidadesCapacitacion(List<BigDecimal> objects) throws Exception {
        if (!objects.isEmpty()) {
            StringBuilder sb = new StringBuilder("select cap from Sophoscapacitations cap where cap.capId IN (");
            for (int i = 0; i < objects.size(); i++) {
                sb = (objects.size() - 1) == i ? sb.append(objects.get(i)).append(")") : sb.append(objects.get(i)).append(",");
            }
            return em.createQuery(sb.toString()).getResultList();
        } else {
            return null;
        }
    }

    @Override
    public Sophoscapacitations find(Long idCap) throws Exception {
        return em.find(Sophoscapacitations.class, idCap);
    }

    @Override
    public void registrarAsignacionCapacitacion(Sophosemployeestudent est, Sophoscapacitations cap) throws Exception {
        String cadena = "insert into SOPHOSCAPXEMP tp (tp.EMPEST_ID,tp.CAP_ID) values (?,?)";
        em.createNativeQuery(cadena).setParameter(1, est.getEmpestId()).setParameter(2, cap.getCapId()).executeUpdate();
    }
    
    @Override
    public void truncarAsignaciones(){
        em.createNativeQuery("truncate table SOPHOSCAPXEMP").executeUpdate();
    }

    @Override
    public List<Sophoscapacitations> listarCapacitacionesPorIdEst(Long idEst) throws Exception {
        StringBuilder sb = new StringBuilder("select cap.CAP_ID from sophoscapacitations cap ");
        sb.append("inner join SOPHOSCAPXEMP tp on tp.CAP_ID = cap.CAP_ID ");
        sb.append("inner join SOPHOSEMPLOYEESTUDENT est on tp.EMPEST_ID = est.EMPEST_ID ");
        sb.append("where est.EMPEST_ID = ?");
        List<BigDecimal> objects = em.createNativeQuery(sb.toString()).setParameter(1, idEst).getResultList();
        return obtenerEntidadesCapacitacion(objects);
    }

}
