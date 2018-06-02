/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.bean;

import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import twitter.ejb.TuitFacade;
import twitter.entity.Tuit;
import twitter.entity.Usuario;

/**
 *
 * @author Tomas
 */
@Named(value = "twitterBean")
@RequestScoped
public class TwitterBean {

    @EJB
    private TuitFacade tuitFacade;

    public List<Tuit> getListaTuitsSeguidos() {
        return listaTuitsSeguidos;
    }

    public void setListaTuitsSeguidos(List<Tuit> listaTuitsSeguidos) {
        this.listaTuitsSeguidos = listaTuitsSeguidos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Tuit> getListaTuitsUsuario() {
        return listaTuitsUsuario;
    }

    public void setListaTuitsUsuario(List<Tuit> listaTuitsUsuario) {
        this.listaTuitsUsuario = listaTuitsUsuario;
    }

    @Inject
    private UsuariosBean usuariosBean;
    
    protected Usuario usuario;
    private List<Tuit> listaTuitsUsuario;
    private List<Tuit> listaTuitsSeguidos;
    
    
    
    public TwitterBean() {
    }
    
    @PostConstruct
    public void init(){
        this.usuario = usuariosBean.getUsuarioSeleccionado();
        listaTuitsUsuario = this.tuitFacade.buscarTuitsUsuario(usuario);
        listaTuitsSeguidos = this.tuitFacade.buscarTuitsSeguidos(usuario);
    }
    
    public String doRetuit(Tuit tuit){
        Tuit nuevo = tuit;
        Random r = new Random();
        Long status =  r.nextLong();
        tuit.setStatus(status);
        tuit.setUsuario(this.usuario);
        tuit.setFecha(new Date());
        this.tuitFacade.create(nuevo);
        
        return "twitter";
    }
    
}
