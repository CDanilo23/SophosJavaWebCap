/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.web;

import co.com.sophos.ejb.IControladorDatosCapacitacionesLocal;
import co.com.sophos.ejb.IControladorDatosEstudiantesLocal;
import co.com.sophos.entidades.Sophoscapacitations;
import co.com.sophos.entidades.Sophosemployeestudent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author cristian.ordonez
 */
@ManagedBean(name = "estudianteController")
@ViewScoped
public class EstudianteController implements Serializable {

    private static final long serialVersionUID = 1L;
    private Sophosemployeestudent est;
    private boolean flagGridInfo;

    @EJB(mappedName = "ControladorDatosEstudiantesBean", lookup = "java:app/SophosJavaWebCap-ejb-1.0-SNAPSHOT/ControladorDatosEstudiantes!co.com.sophos.ejb.IControladorDatosEstudiantesLocal")
    IControladorDatosEstudiantesLocal controladorDatosEstudiantesBean;

    @EJB(mappedName = "ControladorDatosCapacitacionesBean", lookup = "java:app/SophosJavaWebCap-ejb-1.0-SNAPSHOT/ControladorDatosCapacitaciones!co.com.sophos.ejb.IControladorDatosCapacitacionesLocal")
    private IControladorDatosCapacitacionesLocal controladorDatosCapacitacionesBean;

    public EstudianteController() {
        this.flagGridInfo = false;
        this.est = new Sophosemployeestudent();
    }

    public List<Sophosemployeestudent> encontrarEstudianteEmpleadoPorDescripcion(String desc) {
        List<Sophosemployeestudent> li = new ArrayList<Sophosemployeestudent>();
        this.flagGridInfo = true;
        try {
            li = controladorDatosEstudiantesBean.obtenerEstudiantesEmpleadosPorDescripcion(desc);
        } catch (Exception ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return li;
    }

    public List<Sophoscapacitations> getSophoscapacitationsList() {
        List<Sophoscapacitations> li = new ArrayList<Sophoscapacitations>();
        try {
            li = controladorDatosCapacitacionesBean.listarCapacitacionesPorIdEst(est.getEmpestId());
        } catch (Exception ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return li;
    }

    @FacesConverter(value = "spEstudianteConverter")
    public static class StClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            } else if (value != null && !value.equals("null")) {
                try {

                    EstudianteController controller = (EstudianteController) facesContext.getApplication().getELResolver().
                            getValue(facesContext.getELContext(), null, "estudianteController");

                    return controller.controladorDatosEstudiantesBean.find(Long.valueOf(value));
                } catch (Exception ex) {
                    Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return null;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Sophosemployeestudent) {
                Sophosemployeestudent o = (Sophosemployeestudent) object;
                if (o.getEmpestId() == null) {
                    return null;
                }
                return getStringKey(Long.valueOf(o.getEmpestId().toString()));
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Sophosemployeestudent.class.getName());
            }
        }
    }

    public Sophosemployeestudent getEst() {
        return est;
    }

    public void setEst(Sophosemployeestudent est) {
        this.est = est;
    }

    public boolean isFlagGridInfo() {
        return flagGridInfo;
    }

    public void setFlagGridInfo(boolean flagGridInfo) {
        this.flagGridInfo = flagGridInfo;
    }
}
