package Modelo;

import java.sql.Date;

public class Solicitud extends Entrevista {

    private String idSol, sucursal, modalidad, sexo, nacionalidad, ocupacion, id_provincia, id_provincial;
    private int id_sucursal, id_modalidad, id_estado;
    private Date fecha, fechaIngreso, fechaNacimiento;
    private String cuil, calle, nro, piso, dpto, barrio, mza, lote, localidad, cp, provincia;
    private String empleador, nombreEmpleador, calleL, nroL, pisoL, dptoL, localidadL, cpL, provinciaL, telefonoL;
    private int id_nacionalidad, id_sexo, id_ocupacion;
    private boolean PEP;
    private double ingresos;
    private int id_rechazo, id_causal, id_aclaracion;
    private String rechazo, causal, aclaracion, observaciones;

    public Solicitud() {
    }
        
    public String getNombreEmpleador() {
        return nombreEmpleador;
    }

    public void setNombreEmpleador(String nombre_empleador) {
        this.nombreEmpleador = nombre_empleador;
    }

    
    
    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    
    public String getIdSol() {
        return idSol;
    }

    public void setIdSol(String idSol) {
        this.idSol = idSol;
    }  

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public String getEmpleador() {
        return empleador;
    }

    public void setEmpleador(String emplador) {
        this.empleador = emplador;
    }
    
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getMza() {
        return mza;
    }

    public void setMza(String mza) {
        this.mza = mza;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCalleL() {
        return calleL;
    }

    public void setCalleL(String calleL) {
        this.calleL = calleL;
    }

    public String getNroL() {
        return nroL;
    }

    public void setNroL(String nroL) {
        this.nroL = nroL;
    }

    public String getPisoL() {
        return pisoL;
    }

    public void setPisoL(String pisoL) {
        this.pisoL = pisoL;
    }

    public String getDptoL() {
        return dptoL;
    }

    public void setDptoL(String dptoL) {
        this.dptoL = dptoL;
    }

    public String getLocalidadL() {
        return localidadL;
    }

    public void setLocalidadL(String localidadL) {
        this.localidadL = localidadL;
    }

    public String getCpL() {
        return cpL;
    }

    public void setCpL(String cpL) {
        this.cpL = cpL;
    }

    public String getProvinciaL() {
        return provinciaL;
    }

    public void setProvinciaL(String provinciaL) {
        this.provinciaL = provinciaL;
    }

    public String getTelefonoL() {
        return telefonoL;
    }

    public void setTelefonoL(String telefonoL) {
        this.telefonoL = telefonoL;
    }

    public int getId_nacionalidad() {
        return id_nacionalidad;
    }

    public void setId_nacionalidad(int id_nacionalidad) {
        this.id_nacionalidad = id_nacionalidad;
    }

    public int getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(int id_sexo) {
        this.id_sexo = id_sexo;
    }

    public int getId_ocupacion() {
        return id_ocupacion;
    }

    public void setId_ocupacion(int id_ocupacion) {
        this.id_ocupacion = id_ocupacion;
    }

    public boolean isPEP() {
        return PEP;
    }

    public void setPEP(boolean PEP) {
        this.PEP = PEP;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public int getId_modalidad() {
        return id_modalidad;
    }

    public void setId_modalidad(int id_modalidad) {
        this.id_modalidad = id_modalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(String id_provincia) {
        this.id_provincia = id_provincia;
    }

    public String getId_provincial() {
        return id_provincial;
    }

    public void setId_provincial(String id_provincial) {
        this.id_provincial = id_provincial;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getId_rechazo() {
        return id_rechazo;
    }

    public void setId_rechazo(int id_rechazo) {
        this.id_rechazo = id_rechazo;
    }

    public int getId_causal() {
        return id_causal;
    }

    public void setId_causal(int id_causal) {
        this.id_causal = id_causal;
    }

    public int getId_aclaracion() {
        return id_aclaracion;
    }

    public void setId_aclaracion(int id_aclaracion) {
        this.id_aclaracion = id_aclaracion;
    }

    public String getRechazo() {
        return rechazo;
    }

    public void setRechazo(String rechazo) {
        this.rechazo = rechazo;
    }

    public String getCausal() {
        return causal;
    }

    public void setCausal(String causal) {
        this.causal = causal;
    }

    public String getAclaracion() {
        return aclaracion;
    }

    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
    
    
}
