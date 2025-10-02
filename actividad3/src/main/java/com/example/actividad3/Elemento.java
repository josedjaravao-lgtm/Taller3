package com.example.actividad3;


public class Elemento {


    private String codigo;
    private String producto;
    private String descripcion;
    private double precio;
    private int cantidad;

    public Elemento(String codigo, String producto, String descripcion, double precio, int cantidad) {
        this.codigo = codigo;
        this.producto = producto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }



    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}