package es.abatech.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Filtro implements Serializable {
    private List<Integer> categorias = new ArrayList<>();
    private List<Integer> marcas = new ArrayList<>();
    private Double precioMax;

    public List<Integer> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Integer> categorias) {
        this.categorias = categorias;
    }

    public List<Integer> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Integer> marcas) {
        this.marcas = marcas;
    }

    public Double getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(Double precioMax) {
        this.precioMax = precioMax;
    }
}
