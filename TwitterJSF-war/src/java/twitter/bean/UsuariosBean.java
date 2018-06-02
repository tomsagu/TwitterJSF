/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import twitter.ejb.UsuarioFacade;
import twitter.entity.Usuario;

/**
 *
 * @author Tomas
 */
@Named(value = "usuariosBean")
@SessionScoped
public class UsuariosBean implements Serializable{

    @EJB
    private UsuarioFacade usuarioFacade;

    private List<Usuario> listaUsuarios;
    protected Usuario usuarioSeleccionado;

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public UsuariosBean() {
    }
    
    @PostConstruct
    public void init(){
        listaUsuarios = this.usuarioFacade.findAll();
        this.usuarioSeleccionado = null;
    }
    
    public String doEntrar(Usuario usuario){
        this.usuarioSeleccionado = usuario;
        return "twitter";
    }
    
    public String doSalir(){
        this.init();
        return "index";
    }

   
    
}
