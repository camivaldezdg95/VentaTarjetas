package Modelo;

public class Usuario {
    private int id;
    private String usuario, password, nombre, email;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String password, String email, String nombre) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
    
    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", usuario=" + usuario + ", password=" + password + ", nombre=" + nombre + ", email=" + email + '}';
    }
  
    
    
}
