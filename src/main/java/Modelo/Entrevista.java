package Modelo;

import java.sql.Date;

public class Entrevista extends Prospecto {

    private int id, estado_id, canal_id;
    private double lcm, ce;  
    private Date fecha;
    private String estado, canal, vendedor, vendedor_id;
    
    public Entrevista() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public int getCanal_id() {
        return canal_id;
    }

    public void setCanal_id(int canal_id) {
        this.canal_id = canal_id;
    }

    public String getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(String vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    public double getLcm() {
        return lcm;
    }

    public void setLcm(double lcm) {
        this.lcm = lcm;
    }

    public double getCe() {
        return ce;
    }

    public void setCe(double ce) {
        this.ce = ce;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

}
