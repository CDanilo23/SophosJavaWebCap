/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.web;

import co.com.sophos.ejb.IControladorDatosCapacitacionesLocal;
import co.com.sophos.entidades.Sophoscapacitations;
import co.com.sophos.entidades.Sophoscapcategories;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import co.com.sophos.ejb.IControladorDatosCategoriasLocal;
import java.util.ArrayList;

/**
 *
 * @author cristian.ordonez
 */
@ManagedBean(name = "capacitacionesController")
@ViewScoped
public class CapacitacionesController {

    private boolean flagNuevaCapacitacion;
    private boolean flagCreacionCapacitacion;
    private boolean flagCEcapacitacion;
    private boolean flagEdicionCapacitacion;
    private boolean flagDataTable;
    private List<Sophoscapacitations> listaCapacitaciones;
    private Sophoscapacitations capacitacionCurrent;
    private Sophoscapcategories categoriaCurrent;
    private BigDecimal idCategoria;

    @EJB(mappedName = "ControladorDatosCapacitacionesBean", lookup = "java:app/SophosJavaWebCap-ejb-1.0-SNAPSHOT/ControladorDatosCapacitaciones!co.com.sophos.ejb.IControladorDatosCapacitacionesLocal")
    private IControladorDatosCapacitacionesLocal ControladorDatosCapacitacionesBean;

    @EJB(mappedName = "ControladorDatosCategoriasBean", lookup = "java:app/SophosJavaWebCap-ejb-1.0-SNAPSHOT/ControladorDatosCategorias!co.com.sophos.ejb.IControladorDatosCategoriasLocal")
    private IControladorDatosCategoriasLocal ControladorDatosCategoriasBean;

    public CapacitacionesController() {
        this.categoriaCurrent = new Sophoscapcategories();
        this.capacitacionCurrent = new Sophoscapacitations();
        this.flagNuevaCapacitacion = true;
        this.flagCreacionCapacitacion = false;
        this.flagCEcapacitacion = false;
        this.flagEdicionCapacitacion = false;
        this.flagDataTable = true;
    }

    public void crearCapacitacion() {
        try {
            this.flagDataTable = true;
            Sophoscapacitations sophoscapacitations = new Sophoscapacitations();
            sophoscapacitations.setCapCategory(categoriaCurrent);
            sophoscapacitations.setCapName(capacitacionCurrent.getCapName());
            sophoscapacitations.setCapInitDate(capacitacionCurrent.getCapInitDate());
            sophoscapacitations.setCapEndDate(capacitacionCurrent.getCapEndDate());
            ControladorDatosCapacitacionesBean.registrarCapacitacion(sophoscapacitations);
            this.flagNuevaCapacitacion = true;
            this.flagEdicionCapacitacion = false;
            this.flagCEcapacitacion = false;
            this.flagCreacionCapacitacion = false;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado exitosamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al tratar de crear registro", ex.getMessage()));
        }
    }

    public List<Sophoscapacitations> getCapacitaciones() {
        List<Sophoscapacitations> li = new ArrayList<Sophoscapacitations>();
        try {
            li = this.listaCapacitaciones = ControladorDatosCapacitacionesBean.listarCapacitaciones();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al consultar las capacitaciones ", null));
        }
        return li;
    }

    public void actualizarCapacitacion() {
        try {
            ControladorDatosCapacitacionesBean.modificarCapacitacion(capacitacionCurrent);
            this.flagCEcapacitacion = false;
            this.flagNuevaCapacitacion = true;
            this.flagEdicionCapacitacion = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro modificado exitosamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al tratar de actualizar el registro", null));
        }
    }

    public void eliminarCapacitacion(Sophoscapacitations sophoscapacitations) {
        try {
            ControladorDatosCapacitacionesBean.eliminarCapacitacion(sophoscapacitations);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado exitosamente", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al tratar de eliminar el registro", null));
        }
    }

    public void prepararCreacionCapacitacion() {
        this.capacitacionCurrent = new Sophoscapacitations();
        this.flagCEcapacitacion = true;
        this.flagDataTable = false;
        this.flagCreacionCapacitacion = true;
        this.flagEdicionCapacitacion = false;
        this.flagNuevaCapacitacion = false;
    }

    public void prepararEdicionCapacitacion(Sophoscapacitations sophoscapacitations) {
        this.flagCEcapacitacion = true;
        this.capacitacionCurrent = sophoscapacitations;
        this.flagEdicionCapacitacion = true;
        this.flagCreacionCapacitacion = false;
        this.flagNuevaCapacitacion = false;
    }

    public SelectItem[] getListaCategoriaPorCapacitacion() {

        SelectItem[] items;
        List<Sophoscapcategories> categorias = ControladorDatosCategoriasBean.listarCategorias();

        int size = true ? categorias.size() + 1 : categorias.size();
        items = new SelectItem[size];
        int i = 0;
        if (true) {
            items[0] = new SelectItem("", "-seleccione uno-");
            i++;
        }
        for (Sophoscapcategories cat : categorias) {
            items[i++] = new SelectItem(cat.getCatid(), cat.getCatname());
        }
        return items;

    }

    public boolean isFlagNuevaCapacitacion() {
        return flagNuevaCapacitacion;
    }

    public void setFlagNuevaCapacitacion(boolean flagNuevaCapacitacion) {
        this.flagNuevaCapacitacion = flagNuevaCapacitacion;
    }

    public boolean isFlagCreacionCapacitacion() {
        return flagCreacionCapacitacion;
    }

    public void setFlagCreacionCapacitacion(boolean flagCreacionCapacitacion) {
        this.flagCreacionCapacitacion = flagCreacionCapacitacion;
    }

    public boolean isFlagCEcapacitacion() {
        return flagCEcapacitacion;
    }

    public void setFlagCEcapacitacion(boolean flagCEcapacitacion) {
        this.flagCEcapacitacion = flagCEcapacitacion;
    }

    public boolean isFlagEdicionCapacitacion() {
        return flagEdicionCapacitacion;
    }

    public void setFlagEdicionCapacitacion(boolean flagEdicionCapacitacion) {
        this.flagEdicionCapacitacion = flagEdicionCapacitacion;
    }

    public boolean isFlagDataTable() {
        return flagDataTable;
    }

    public void setFlagDataTable(boolean flagDataTable) {
        this.flagDataTable = flagDataTable;
    }

    public List<Sophoscapacitations> getListaCapacitaciones() {
        return listaCapacitaciones;
    }

    public void setListaCapacitaciones(List<Sophoscapacitations> listaCapacitaciones) {
        this.listaCapacitaciones = listaCapacitaciones;
    }

    public Sophoscapacitations getCapacitacionCurrent() {
        return capacitacionCurrent;
    }

    public void setCapacitacionCurrent(Sophoscapacitations capacitacionCurrent) {
        this.capacitacionCurrent = capacitacionCurrent;
    }

    public BigDecimal getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(BigDecimal idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Sophoscapcategories getCategoriaCurrent() {
        return categoriaCurrent;
    }

    public void setCategoriaCurrent(Sophoscapcategories categoriaCurrent) {
        this.categoriaCurrent = categoriaCurrent;
    }

}
