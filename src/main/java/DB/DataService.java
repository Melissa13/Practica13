package DB;

import jms.Data;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DataService extends DataBaseService<Data> {

    private static DataService instancia;

    private DataService() {
        super(Data.class);
    }

    public static DataService getInstancia(){
        if(instancia==null){
            instancia = new DataService();
        }
        return instancia;
    }

    /**
     *
     * @param id
     * @return
     */

    public List<Data> findAllById(){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Data.findAllById");
        List<Data> lista = query.getResultList();
        return lista;
    }
}
