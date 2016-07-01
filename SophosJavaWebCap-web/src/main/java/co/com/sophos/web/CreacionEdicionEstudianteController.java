/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.web;

import co.com.sophos.ejb.IControladorDatosEstudiantesLocal;
import co.com.sophos.entidades.Sophosemployeestudent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author cristian.ordonez
 */
@ManagedBean(name = "creacionEdicionEstudianteController")
@ViewScoped
public class CreacionEdicionEstudianteController {
    
    private boolean flagDataTable;
    private Sophosemployeestudent estudianteCurrent;
    private boolean flagCEestudiante;
    private boolean flagCreacionEstudiante;
    private boolean flagEdicionEstudiante;
    private boolean flagNuevoEstudiante;
    private boolean flagCancelarEdicionCreacion;
    
    
    @EJB(mappedName = "ControladorDatosEstudiantesBean", lookup = "java:app/SophosJavaWebCap-ejb-1.0-SNAPSHOT/ControladorDatosEstudiantes!co.com.sophos.ejb.IControladorDatosEstudiantesLocal")
    IControladorDatosEstudiantesLocal ControladorDatosEstudiantesBean;

    public CreacionEdicionEstudianteController() {
        
        this.estudianteCurrent = new Sophosemployeestudent();
        this.flagNuevoEstudiante = true;
        this.flagCreacionEstudiante = false;
        this.flagCEestudiante = false;
        this.flagEdicionEstudiante = false;
        this.flagDataTable = true;
    }
    
    public List<Sophosemployeestudent> getEstudiantes(){
        List<Sophosemployeestudent> li = new ArrayList<Sophosemployeestudent>();
        try {
            li = ControladorDatosEstudiantesBean.listarEstudiantes();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Error al consultar estudiantes"));
        }
        return li;
    }
    
    public void modificarEstudiante() {
        try {
            estudianteCurrent.setEmpestEmployeeshortname(estudianteCurrent.getEmpestEmployeeshortname().toUpperCase());
            ControladorDatosEstudiantesBean.modificarEstudiante(estudianteCurrent);
            this.flagCEestudiante = false;
            this.flagNuevoEstudiante = true;
            this.flagEdicionEstudiante = false;
            this.flagCancelarEdicionCreacion = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro modificado exitosamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al tratar de actualizar el registro", null));
        }
    }
    
    public void crearEstudiante() {
        try {
            Sophosemployeestudent nuevoEstudiante = new Sophosemployeestudent();
            nuevoEstudiante.setEmpestEmployeeid(estudianteCurrent.getEmpestEmployeeid());
            nuevoEstudiante.setEmpestEmployeeshortname(estudianteCurrent.getEmpestEmployeeshortname().toUpperCase());
            nuevoEstudiante.setCargo(estudianteCurrent.getCargo());
            nuevoEstudiante.setEmail(estudianteCurrent.getEmail());
            nuevoEstudiante.setTelefono(estudianteCurrent.getTelefono());
            if(nuevoEstudiante.getEmpestEmployeeid()!=null){
                ControladorDatosEstudiantesBean.registrarEstudiante(nuevoEstudiante);
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "El campo Codigo no puede ser vacio "));
            }
            this.flagDataTable = true;
            this.flagNuevoEstudiante = true;
            this.flagEdicionEstudiante = false;
            this.flagCEestudiante = false;
            this.flagCreacionEstudiante = false;
            this.flagCancelarEdicionCreacion = false;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado exitosamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al tratar de crear registro", ex.getMessage()));
        }
    }
    
    public void prepararCreacionEstudiante() {
        this.estudianteCurrent = new Sophosemployeestudent();
        this.flagCEestudiante = true;
        this.flagCancelarEdicionCreacion = true;
        this.flagCreacionEstudiante = true;
        this.flagDataTable = false;
        this.flagEdicionEstudiante = false;
        this.flagNuevoEstudiante = false;
    }

    public void prepararEdicionEstudiante(Sophosemployeestudent current) {
        this.flagCEestudiante = true;
        this.estudianteCurrent = current;
        this.flagCancelarEdicionCreacion = true;
        this.flagEdicionEstudiante = true;
        this.flagCreacionEstudiante = false;
        this.flagNuevoEstudiante = false;
    }
    
    public void cancelarEdicionCreacion(){
        this.estudianteCurrent = new Sophosemployeestudent();
        this.flagDataTable = true;
        this.flagNuevoEstudiante = true;
        this.flagCancelarEdicionCreacion = false;
        this.flagCEestudiante = false;
        this.flagCreacionEstudiante = false;
        this.flagEdicionEstudiante = false;
    }
    
    public void eliminarEstudiante(Sophosemployeestudent estudiante){
        try {
            ControladorDatosEstudiantesBean.eliminarEstudiante(estudiante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Registro eliminado exitosamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Error al tratar de eliminar el registro"));
        }
    }

    public boolean isFlagDataTable() {
        return flagDataTable;
    }

    public void setFlagDataTable(boolean flagDataTable) {
        this.flagDataTable = flagDataTable;
    }

    public Sophosemployeestudent getEstudianteCurrent() {
        return estudianteCurrent;
    }

    public void setEstudianteCurrent(Sophosemployeestudent estudianteCurrent) {
        this.estudianteCurrent = estudianteCurrent;
    }

    public boolean isFlagCEestudiante() {
        return flagCEestudiante;
    }

    public void setFlagCEestudiante(boolean flagCEestudiante) {
        this.flagCEestudiante = flagCEestudiante;
    }

    public boolean isFlagCreacionEstudiante() {
        return flagCreacionEstudiante;
    }

    public void setFlagCreacionEstudiante(boolean flagCreacionEstudiante) {
        this.flagCreacionEstudiante = flagCreacionEstudiante;
    }

    public boolean isFlagEdicionEstudiante() {
        return flagEdicionEstudiante;
    }

    public void setFlagEdicionEstudiante(boolean flagEdicionEstudiante) {
        this.flagEdicionEstudiante = flagEdicionEstudiante;
    }

    public boolean isFlagNuevoEstudiante() {
        return flagNuevoEstudiante;
    }

    public void setFlagNuevoEstudiante(boolean flagNuevoEstudiante) {
        this.flagNuevoEstudiante = flagNuevoEstudiante;
    }

    public boolean isFlagCancelarEdicionCreacion() {
        return flagCancelarEdicionCreacion;
    }

    public void setFlagCancelarEdicionCreacion(boolean flagCancelarEdicionCreacion) {
        this.flagCancelarEdicionCreacion = flagCancelarEdicionCreacion;
    }
}
