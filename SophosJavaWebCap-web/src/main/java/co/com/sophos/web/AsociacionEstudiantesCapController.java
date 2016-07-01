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
import org.primefaces.model.DualListModel;

/**
 *
 * @author cristian.ordonez
 */
@ManagedBean(name = "asociacionEstudiantesCapController")
@ViewScoped
public class AsociacionEstudiantesCapController implements Serializable {

    private static final long serialVersionUID = 1L;

    private DualListModel<Sophoscapacitations> pickListCap;
    List<Sophoscapacitations> cursosSource;
    List<Sophoscapacitations> cursosTarget;
    private boolean flagAsoc;
    private Sophosemployeestudent student;

    public AsociacionEstudiantesCapController() {
        this.cursosSource = new ArrayList<Sophoscapacitations>();
        this.cursosTarget = new ArrayList<Sophoscapacitations>();
        pickListCap = new DualListModel(cursosSource, cursosTarget);
        this.flagAsoc = false;
    }

    @EJB(mappedName = "ControladorDatosEstudiantesBean", lookup = "java:app/SophosJavaWebCap-ejb-1.0-SNAPSHOT/ControladorDatosEstudiantes!co.com.sophos.ejb.IControladorDatosEstudiantesLocal")
    IControladorDatosEstudiantesLocal controladorDatosEstudiantesBean;

    @EJB(mappedName = "ControladorDatosCapacitacionesBean", lookup = "java:app/SophosJavaWebCap-ejb-1.0-SNAPSHOT/ControladorDatosCapacitaciones!co.com.sophos.ejb.IControladorDatosCapacitacionesLocal")
    private IControladorDatosCapacitacionesLocal controladorDatosCapacitacionesBean;

    public void guardarCapacitaciones() {
        this.flagAsoc = false;
        try {
            controladorDatosCapacitacionesBean.truncarAsignaciones();
            for (Sophoscapacitations cap : pickListCap.getTarget()) {
                controladorDatosCapacitacionesBean.registrarAsignacionCapacitacion(student, cap);
            }

        } catch (Exception ex) {
            Logger.getLogger(AsociacionEstudiantesCapController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mostrarComponenteDualList(Sophosemployeestudent studentParam) {
        this.student = studentParam;
        this.flagAsoc = true;
        try {
            cursosSource = controladorDatosCapacitacionesBean.listarCapacitacionesNoAsociadasAEstudiante(student.getEmpestId());
            cursosTarget = controladorDatosCapacitacionesBean.listarCapacitacionesPorIdEst(student.getEmpestId());
            pickListCap = new DualListModel<Sophoscapacitations>(cursosSource, cursosTarget);
        } catch (Exception ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean existeCapacitacionAsociada(Sophoscapacitations cap) {
        boolean existe = false;
        try {
            existe = controladorDatosCapacitacionesBean.existeCapacitacionAsociada(student, cap);
        } catch (Exception ex) {
            Logger.getLogger(AsociacionEstudiantesCapController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    @FacesConverter(value = "spAsocEstCapConverter")
    public static class SpAsocEstCapConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            } else if (value != null && !value.equals("null")) {
                try {

                    AsociacionEstudiantesCapController controller = (AsociacionEstudiantesCapController) facesContext.getApplication().getELResolver().
                            getValue(facesContext.getELContext(), null, "asociacionEstudiantesCapController");

                    return controller.controladorDatosCapacitacionesBean.find(Long.valueOf(value));
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
            if (object instanceof Sophoscapacitations) {
                Sophoscapacitations o = (Sophoscapacitations) object;
                if (o.getCapId() == null) {
                    return null;
                }
                return getStringKey(Long.valueOf(o.getCapId().toString()));
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Sophoscapacitations.class.getName());
            }
        }
    }

    public Sophosemployeestudent getStudent() {
        return student;
    }

    public void setStudent(Sophosemployeestudent student) {
        this.student = student;
    }

    public boolean isFlagAsoc() {
        return flagAsoc;
    }

    public void setFlagAsoc(boolean flagAsoc) {
        this.flagAsoc = flagAsoc;
    }

    public DualListModel<Sophoscapacitations> getPickListCap() {
        return pickListCap;
    }

    public void setPickListCap(DualListModel<Sophoscapacitations> pickListCap) {
        this.pickListCap = pickListCap;
    }

}
