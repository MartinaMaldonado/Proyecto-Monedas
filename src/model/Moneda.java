package model;

public class Moneda {
    public double valor_de_conversion;
    public String codigo_iso;
    public String nombre;
    public String simbolo;
    public String pais_o_region;

    public Moneda(double valor_de_conversion, String codigo_iso, String nombre, String simbolo, String pais_o_region){
        this.valor_de_conversion = valor_de_conversion;
        this.codigo_iso = codigo_iso;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.pais_o_region = pais_o_region;
    }
}