package un4.eje4_2

data class Tienda(val nombre: String, val clientes: List<Cliente>){
    fun obtenerConjuntoDeClientes(): Set<Cliente> = clientes.toSet()
    fun obtenerCiudadesDeClientes(): Set<Ciudad> = clientes.map { it.ciudad }.toSet()
    fun obtenerClientesPor(ciudad: Ciudad): List<Cliente> = clientes.filter { ciudad == it.ciudad }
    fun checkTodosClientesSonDe(ciudad : Ciudad): Boolean = clientes.all { ciudad == it.ciudad }
    fun hayClientesDe(ciudad: Ciudad): Boolean = clientes.any { ciudad == it.ciudad }
    fun cuentaClientesDe(ciudad: Ciudad): Int = clientes.count { ciudad == it.ciudad }
    fun encuentraClienteDe(ciudad: Ciudad): Cliente? = clientes.find { ciudad == it.ciudad }
    fun obtenerClientesOrdenadosPorPedidos(): List<Cliente> = clientes.sortedByDescending { it.pedidos.size }
    fun obtenerClientesConPedidosSinEntregar(): Set<Cliente> = clientes.filter { it.pedidos.any { !it.estaEntregado } }.toSet()
    fun obtenerNumeroVecesProductoPedido(producto: Producto): Int = clientes.sumOf { it.pedidos.count { it.productos.contains(producto) } }

    fun obtenerProductosPedidosPorTodos(): Set<Producto> = TODO()
    fun agrupaClientesPorCiudad(): Map<Ciudad, List<Cliente>> = TODO()
    fun mapeaNombreACliente(): Map<String, Cliente> = TODO()
    fun mapeaClienteACiudad(): Map<Cliente, Ciudad> = TODO()
    fun mapeaNombreClienteACiudad(): Map<String, Ciudad> = TODO()
    fun obtenerClientesConMaxPedidos(): Cliente? = clientes.sortedByDescending { it.pedidos.size }[0]
}

data class Cliente(val nombre: String, val ciudad: Ciudad, val pedidos: List<Pedido>) {
    override fun toString() = "$nombre from ${ciudad.nombre}"

    fun obtenerProductosPedidos(): List<Producto> = pedidos.flatMap { it.productos.toList() }
    fun dineroGastado(): Double = pedidos.sumOf { it.productos.sumOf { it.precio } }
    fun encuentraProductoMasCaro(): Producto? = pedidos.filter { it.estaEntregado }.flatMap { it.productos.toList() }.sortedByDescending { it.precio }[0]

    fun obtenerProductoMasCaroPedido(): Producto? = obtenerProductosPedidos().sortedByDescending { it.precio }[0]
}

data class Pedido(val productos: List<Producto>, val estaEntregado: Boolean){

}

data class Producto(val nombre: String, val precio: Double) {
    override fun toString() = "'$nombre' for $precio"
}

data class Ciudad(val nombre: String) {
    override fun toString() = nombre
}

fun main(){
    val ciudad1 = Ciudad("Cádiz")
    val ciudad2 = Ciudad("San Fernando")

    val producto1 = Producto("Jamón",3.0)
    val producto2 = Producto("Leche",1.0)

    val pedido1 = Pedido(listOf(producto1),false)
    val pedido2 = Pedido(listOf(producto2),true)

    val cliente1 = Cliente("Alex",ciudad1, listOf(pedido1))
    val cliente2 = Cliente("Juan",ciudad2, listOf(pedido2,pedido1))

    val tienda1 = Tienda("Covirán", listOf(cliente1,cliente2))
    //println(cliente1.obtenerProductosPedidos())
    //println(tienda1.obtenerClientesConMaxPedidos())
    //println(cliente2.obtenerProductoMasCaroPedido())
    //println(tienda1.checkTodosClientesSonDe(Ciudad("Cádiz")))


}