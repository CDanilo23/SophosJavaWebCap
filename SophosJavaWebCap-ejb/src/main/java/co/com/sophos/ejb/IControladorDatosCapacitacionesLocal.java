/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.ejb;

import co.com.sophos.entidades.Sophoscapacitations;
import co.com.sophos.entidades.Sophosemployeestudent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cristian.ordonez
 */
@Local
public interface IControladorDatosCapacitacionesLocal {
    
    List<Sophoscapacitations> listarCapacitaciones() throws Exception;
    void registrarCapacitacion(Sophoscapacitations sophoscapacitations) throws Exception;
    void modificarCapacitacion(Sophoscapacitations sophoscapacitations) throws Exception;
    void eliminarCapacitacion(Sophoscapacitations sophoscapacitations) throws Exception;
    public List<Sophoscapacitations> listarCapacitacionesNoAsociadasAEstudiante(Long idStudent) throws Exception;
    public Sophoscapacitations find(Long idCap) throws Exception;
    public boolean existeCapacitacionAsociada(Sophosemployeestudent est, Sophoscapacitations cap) throws Exception;
    public void registrarAsignacionCapacitacion(Sophosemployeestudent est, Sophoscapacitations cap) throws Exception;
    public List<Sophoscapacitations> listarCapacitacionesPorIdEst(Long idEst) throws Exception;
    public void truncarAsignaciones() throws Exception;
}
