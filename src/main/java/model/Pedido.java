package model;


import interfaces.Descuento;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private int idPedido;
    @ManyToOne
    @JoinColumn(name = "dniCliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado=null;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pedido_inventario", joinColumns = @JoinColumn(name = "idPedido"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Producto> productos;
    @ManyToOne
    @JoinColumn(name = "idDescuento")
    private Descuentos descuento;
    public Pedido() {
    }
    public Pedido(Cliente cliente, Empleado empleado, List<Producto> compra ) throws Exception {
            this.cliente = cliente;
            this.empleado = empleado;
            this.productos = compra;
    }

    // Constructor secundario que recibe un objeto de tipo Descuento
    public Pedido(Cliente cliente, Empleado empleado, List<Producto> compra, Descuentos descuento) throws Exception {
            this.cliente = cliente;
            this.empleado = empleado;
            this.productos = compra;
            this.descuento = descuento;
    }

    public Pedido(Cliente cliente, List<Producto> compra) throws Exception {
        this.cliente = cliente;
        this.productos = compra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double calcularTotalDelPedido(){
        Double precio=0.0;
        for (int i =0;i<=productos.size();i++){
            precio+=productos.get(i).getPrecioVenta();
        }
        if (descuento!=null) return descuento.aplicarDescuento(precio);
        else return precio;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
