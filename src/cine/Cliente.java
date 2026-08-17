package cine;

public class Cliente {

    private String cedula;
    private String nombre;
    private int edad;
    private String correo;

    public Cliente(String cedula, String nombre, int edad, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", correo='" + correo + '\'' +
                '}';
    }
}
