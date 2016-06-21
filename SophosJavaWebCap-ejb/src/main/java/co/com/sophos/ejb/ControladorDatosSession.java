/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.ejb;

import co.com.sophos.entities.Sophoscapcategories;
import java.util.ArrayList;
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
@Stateless(name = "ControladorDatosSession", mappedName = "ControladorDatosSessionBean")
@LocalBean
public class ControladorDatosSession implements IControladorDatosSessionBeanLocal {
    
    @PersistenceContext(unitName = "OraclePU")
    private EntityManager em;
    
    public List<Sophoscapcategories> listarCategorias() {
        Query query = em.createQuery("select cat from Sophoscapcategories cat");
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
    
     public void eliminarCategoria(Sophoscapcategories dto) {
          em.remove(dto);
     }
}
