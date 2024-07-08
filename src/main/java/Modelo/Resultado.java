package Modelo;

public class Resultado {
 
private int regla, entrevista;
private boolean pass;
private String nombre_corto, descripcion, aceptacion, valor;

    public int getRegla() {
        return regla;
    }

    public void setRegla(int regla) {
        this.regla = regla;
    }

    public int getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(int entrevista) {
        this.entrevista = entrevista;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getNombre_corto() {
        return nombre_corto;
    }

    public void setNombre_corto(String nombre_corto) {
        this.nombre_corto = nombre_corto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(String aceptacion) {
        this.aceptacion = aceptacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

@Override
    public String toString() {
        return "Resultado{" + "regla=" + regla + ", valor=" + valor + ", aceptacion=" + aceptacion +
                ", nombre corto=" + nombre_corto + ", pass/fail=" + pass + ", descripcion=" + descripcion +
                ", entrevista=" + entrevista;
    }
  
    
    
}
