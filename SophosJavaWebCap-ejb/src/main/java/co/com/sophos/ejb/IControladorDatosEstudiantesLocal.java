/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.ejb;

import co.com.sophos.entidades.Sophosemployeestudent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cristian.ordonez
 */
@Local
public interface IControladorDatosEstudiantesLocal {
    public List<Sophosemployeestudent> obtenerEstudiantesEmpleadosPorDescripcion(String desc) throws Exception;
    public Sophosemployeestudent find(Long idEstudiante) throws Exception;
    public List<Sophosemployeestudent> listarEstudiantes() throws  Exception;
    public void eliminarEstudiante(Sophosemployeestudent estudiante) throws Exception;
    public void modificarEstudiante(Sophosemployeestudent estudiante) throws Exception;
    public void registrarEstudiante(Sophosemployeestudent estudiante) throws Exception;
}
