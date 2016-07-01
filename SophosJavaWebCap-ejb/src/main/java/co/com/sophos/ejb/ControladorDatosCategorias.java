/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.ejb;

import co.com.sophos.entidades.Sophoscapcategories;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cristian Ordoñez
 */
@Stateless(name = "ControladorDatosCategorias", mappedName = "ControladorDatosCategoriasBean")
public class ControladorDatosCategorias implements IControladorDatosCategoriasLocal {
    
    @PersistenceContext(unitName = "OraclePU")
    private EntityManager em;
    
    @Override
    public List<Sophoscapcategories> listarCategorias() {
        Query query = em.createNamedQuery("Sophoscapcategories.findAll");
        return query.getResultList();
    }
    
    @Override
    public void registrarCategoria(Sophoscapcategories sophoscapcategories) {
        
        try {
            em.persist(sophoscapcategories);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void modificarCategoria(Sophoscapcategories sophoscapcategories) {
        em.merge(sophoscapcategories);
    }
    
    @Override
     public void eliminarCategoria(Sophoscapcategories sophoscapcategories) {
         sophoscapcategories = em.find(Sophoscapcategories.class, sophoscapcategories.getCatid());
         em.remove(sophoscapcategories);
     }
}
