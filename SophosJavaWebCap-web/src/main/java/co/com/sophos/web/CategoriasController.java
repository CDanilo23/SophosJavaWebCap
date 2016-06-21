package co.com.sophos.web;

import co.com.sophos.ejb.IControladorDatosSessionBeanLocal;
import co.com.sophos.entities.Sophoscapcategories;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "categoriasController")
@RequestScoped
public class CategoriasController {

    private Integer idCategoria;
    private String nomCategoria;
    private String desCategoria;
    private boolean flagEdicionCategoria;
    private boolean flagCreacionCategoria;
    private boolean flagCreacionEdicion;
    private boolean flagDataTable;
    private List<Sophoscapcategories> listaCategorias;
    private Sophoscapcategories categoriaCurrent;

    @EJB(mappedName = "ControladorDatosSessionBean", lookup = "java:app/SophosJavaWebCap-ejb-1.0-SNAPSHOT/ControladorDatosSession!co.com.sophos.ejb.IControladorDatosSessionBeanLocal")
    private IControladorDatosSessionBeanLocal ControladorDatosSessionBean;

    public CategoriasController() {
        this.categoriaCurrent = new Sophoscapcategories();
        this.flagCreacionCategoria = true;
        this.flagDataTable = true;
//		this.flagCreacionCategoria = false;
//		this.flagEdicionCategoria = false;
//		listaCategorias = new ArrayList<Sophoscapcategories>();
//		Sophoscapcategories categoria = new Sophoscapcategories();
//		categoria.setCatName("categoria 1");
//		categoria.setCatDescription("descripcion categoria 1");
//		listaCategorias.add(categoria);
//		categoria = new Sophoscapcategories();
//		categoria.setCatName("categoria 2");
//		categoria.setCatDescription("descripcion categoria 2");
//		listaCategorias.add(categoria);
//		categoria = new Sophoscapcategories();
//		categoria.setCatName("categoria 3");
//		categoria.setCatDescription("descripcion categoria 3");
//		listaCategorias.add(categoria);
    }

    public void prepararEdicionCategoria2() {
        categoriaCurrent.setCatname(nomCategoria);
        categoriaCurrent.setCatdescription(desCategoria);
        ControladorDatosSessionBean.modificarCategoria(categoriaCurrent);
    }

    public void crearCategoria() {
        this.flagDataTable = true;
        Sophoscapcategories sophoscapcategories = new Sophoscapcategories();
        sophoscapcategories.setCatname(categoriaCurrent.getCatname());
        sophoscapcategories.setCatdescription(categoriaCurrent.getCatdescription());
        ControladorDatosSessionBean.registrarCategoria(sophoscapcategories);
    }

    public void prepararCreacionCategoria() {
        this.flagCreacionEdicion = true;
        this.flagDataTable = false;
        this.flagCreacionCategoria = true;
        this.flagEdicionCategoria = false;
    }

    public void prepararEdicionCategoria(Sophoscapcategories sophoscapcategories) {
        this.flagCreacionEdicion = true;
        this.categoriaCurrent = sophoscapcategories;
        this.flagEdicionCategoria = true;
        this.flagCreacionCategoria = false;
    }

    public void eliminarCategoria(Sophoscapcategories sophoscapcategories) {
        ControladorDatosSessionBean.eliminarCategoria(sophoscapcategories);
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public String getDesCategoria() {
        return desCategoria;
    }

    public void setDesCategoria(String desCategoria) {
        this.desCategoria = desCategoria;
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

    public boolean isFlagCreacionEdicion() {
        return flagCreacionEdicion;
    }

    public void setFlagCreacionEdicion(boolean flagCreacionEdicion) {
        this.flagCreacionEdicion = flagCreacionEdicion;
    }

    public boolean isFlagDataTable() {
        return flagDataTable;
    }

    public void setFlagDataTable(boolean flagDataTable) {
        this.flagDataTable = flagDataTable;
    }

}
