package model;
//Esta es una unica moneda como tal y dentro se almacenaran su respectivos valores
public class Moneda {
    //Propiedades
    public double valor_de_conversion;
    public String codigo_iso;
    public String nombre;
    public String simbolo;
    public String pais_o_region;

    //Contructor de la clase
    public Moneda(double valor_de_conversion, String codigo_iso, String nombre, String simbolo, String pais_o_region){
        this.valor_de_conversion = valor_de_conversion;
        this.codigo_iso = codigo_iso;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.pais_o_region = pais_o_region;
    }
}