package co.com.sophos.web;

import co.com.sophos.entidades.Sophoscapcategories;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import co.com.sophos.ejb.IControladorDatosCategoriasLocal;

@ManagedBean(name = "categoriasController")
@ViewScoped
public class CategoriasController {

    private boolean flagEdicionCategoria;
    private boolean flagCreacionCategoria;
    private boolean flagCEcategoria;
    private boolean flagNuevaCategoria;
    private boolean flagDataTable;
    private List<Sophoscapcategories> listaCategorias;
    private Sophoscapcategories categoriaCurrent;

    @EJB(mappedName = "ControladorDatosCategoriasBean", lookup = "java:app/SophosJavaWebCap-ejb-1.0-SNAPSHOT/ControladorDatosCategorias!co.com.sophos.ejb.IControladorDatosCategoriasLocal")
    private IControladorDatosCategoriasLocal ControladorDatosSessionBean;

    public CategoriasController() {
        this.flagNuevaCategoria = true;
        this.flagCreacionCategoria = false;
        this.flagCEcategoria = false;
        this.flagEdicionCategoria = false;
        this.flagDataTable = true;
    }

    public void actualizarCategoria() {
        ControladorDatosSessionBean.modificarCategoria(categoriaCurrent);
        this.flagCEcategoria = false;
        this.flagNuevaCategoria = true;
        this.flagEdicionCategoria = false;
    }

    public void crearCategoria() {
        this.flagDataTable = true;
        Sophoscapcategories sophoscapcategories = new Sophoscapcategories();
        sophoscapcategories.setCatname(categoriaCurrent.getCatname());
        sophoscapcategories.setCatdescription(categoriaCurrent.getCatdescription());
        ControladorDatosSessionBean.registrarCategoria(sophoscapcategories);
        this.flagNuevaCategoria = true;
        this.flagEdicionCategoria = false;
        this.flagCreacionCategoria = false;
        this.flagCEcategoria = false;
    }

    public void prepararCreacionCategoria() {
        this.categoriaCurrent = new Sophoscapcategories();
        this.flagCEcategoria = true;
        this.flagDataTable = false;
        this.flagCreacionCategoria = true;
        this.flagEdicionCategoria = false;
        this.flagNuevaCategoria = false;
    }

    public void prepararEdicionCategoria(Sophoscapcategories sophoscapcategories) {
        this.flagCEcategoria = true;
        this.categoriaCurrent = sophoscapcategories;
        this.flagEdicionCategoria = true;
        this.flagCreacionCategoria = false;
        this.flagNuevaCategoria = false;
    }

    public void eliminarCategoria(Sophoscapcategories sophoscapcategories) {
        ControladorDatosSessionBean.eliminarCategoria(sophoscapcategories);
    }

    public List<Sophoscapcategories> getListaCategorias() {
        return ControladorDatosSessionBean.listarCategorias();
    }

    public void setListaCategorias(List<Sophoscapcategories> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public boolean isFlagEdicionCategoria() {
        return flagEdicionCategoria;
    }

    public void setFlagEdicionCategoria(boolean flagEdicionCategoria) {
        this.flagEdicionCategoria = flagEdicionCategoria;
    }

    public Sophoscapcategories getCategoriaCurrent() {
        return categoriaCurrent;
    }

    public void setCategoriaCurrent(Sophoscapcategories categoriaCurrent) {
        this.categoriaCurrent = categoriaCurrent;
    }

    public boolean isFlagCreacionCategoria() {
        return flagCreacionCategoria;
    }

    public void setFlagCreacionCategoria(boolean flagCreacionCategoria) {
        this.flagCreacionCategoria = flagCreacionCategoria;
    }

    public boolean isFlagDataTable() {
        return flagDataTable;
    }

    public void setFlagDataTable(boolean flagDataTable) {
        this.flagDataTable = flagDataTable;
    }

    public boolean isFlagCEcategoria() {
        return flagCEcategoria;
    }

    public void setFlagCEcategoria(boolean flagCEcategoria) {
        this.flagCEcategoria = flagCEcategoria;
    }

    public boolean isFlagNuevaCategoria() {
        return flagNuevaCategoria;
    }

    public void setFlagNuevaCategoria(boolean flagNuevaCategoria) {
        this.flagNuevaCategoria = flagNuevaCategoria;
    }

}
