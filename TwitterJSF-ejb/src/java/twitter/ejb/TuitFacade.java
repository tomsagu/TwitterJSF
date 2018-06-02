/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import twitter.entity.Tuit;
import twitter.entity.Usuario;

/**
 *
 * @author Tomas
 */
@Stateless
public class TuitFacade extends AbstractFacade<Tuit> {

    @PersistenceContext(unitName = "TwitterJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TuitFacade() {
        super(Tuit.class);
    }

    public List<Tuit> buscarTuitsUsuario(Usuario usuario) {
        Query q = this.em.createQuery("select t from Tuit t where t.usuario = :usuario order by t.fecha desc");
        q.setParameter("usuario", usuario);
        return q.getResultList();
    }

    public List<Tuit> buscarTuitsSeguidos(Usuario usuario) {
        List<Usuario> seguidos = usuario.getUsuarioList1();
        if (seguidos == null) {
            return null;
        } else {

            Query q = this.em.createQuery("select t from Tuit t where t.usuario IN :seguidos order by t.fecha DESC");
            q.setParameter("seguidos", seguidos);
            return q.getResultList();
        }
    }
}
