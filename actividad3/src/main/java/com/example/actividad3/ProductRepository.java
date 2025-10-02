package com.example.actividad3;

import java.util.ArrayList;
import java.util.List;


public class ProductRepository {


    private static ProductRepository instance;

    private List<Elemento> productos;


    private ProductRepository() {
        productos = new ArrayList<>();

        productos.add(new Elemento("A001", "Laptop", "Portátil Core i7", 1200.50, 5));
        productos.add(new Elemento("B002", "Mouse", "Mouse óptico inalámbrico", 25.00, 50));
    }


    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public List<Elemento> getProductos() {
        return productos;
    }

    public void agregarProducto(Elemento producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Elemento producto) {
        productos.removeIf(p -> p.getCodigo().equals(producto.getCodigo()));
    }
}