/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.ejb;

import co.com.sophos.entidades.Sophoscapcategories;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cristian Ordoñez
 */
@Local
public interface IControladorDatosCategoriasLocal {

    List<Sophoscapcategories> listarCategorias();

    void registrarCategoria(Sophoscapcategories sophoscapcategories);

    void modificarCategoria(Sophoscapcategories sophoscapcategories);

    void eliminarCategoria(Sophoscapcategories sophoscapcategories);
}
