package co.com.sophos.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Session Bean implementation class ControladorAuditoriaAsync
 */
@Stateless
public class ControladorAuditoriaAsync {

    /**
     * Default constructor. 
     */
    public ControladorAuditoriaAsync() {
        // TODO Auto-generated constructor stub
    }

    @Asynchronous
	public void algo() {
		System.out.println("----------------------------------------------------------");
		System.out.println("---------------------------ENTRO--------------------------");
		System.out.println("----------------------------------------------------------");
	}

}
