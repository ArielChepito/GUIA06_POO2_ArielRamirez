/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.AlumnosFacadeLocal;
import com.sv.udb.modelo.Alumnos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ariel
 */
@Named(value = "alumnosBean")
@ViewScoped
public class AlumnosBean implements Serializable{

    @EJB
    private AlumnosFacadeLocal FCDEAlum;
    
    private Alumnos objeAlum;
    private List<Alumnos> listAlum;
    private boolean guardar;

    public Alumnos getObjeAlum() {
        return objeAlum;
    }

    public void setObjeAlum(Alumnos objeAlum) {
        this.objeAlum = objeAlum;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Alumnos> getListAlum() {
        return listAlum;
    }
    
    /**
     * Creates a new instance of AlumnosBean
     */
    
    public AlumnosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeAlum = new Alumnos();
        this.guardar = true;
        this.consTodo();
    }
    public void limpForm()
    {
        System.err.println("entyra");
        this.objeAlum = new Alumnos();
        this.guardar = true;        
    }
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página  
       
        try
        {
            FCDEAlum.create(objeAlum);
            this.guardar=true;
            this.objeAlum=new Alumnos();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            
        }
        finally
        {
                      
        }
    }
    
    public void consTodo()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("POOPU");
        EntityManager em = emf.createEntityManager();
        try
        {
            this.listAlum=FCDEAlum.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            em.close();
            emf.close();            
        }
    }
    public void cons()
    {
       
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página  
       int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nombreparametro"));
        try
        {
            this.objeAlum=FCDEAlum.find(codi);          
            this.objeAlum=new Alumnos();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            
        }
        finally
        {
                      
        }
    }
     public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
       
     
        try{

            FCDEAlum.remove(this.objeAlum);
            this.guardar=true;              
                ctx.execute("setMessage('MESS_INFO', 'Atención', 'Registro eliminado')");
            
        }catch(Exception e){
            
        }
     
        
    }
}