package br.com.fiap.program;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import br.com.fiap.dao.impl.GenericDao;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Pedido;

public class Main {
	public static void main(String[] args) {
		GenericDao<Cliente> clienteDao = new GenericDao<>(Cliente.class);
		GenericDao<Pedido> pedidoDao = new GenericDao<>(Pedido.class);
		for (int i = 0; i < 10; i++) {
			Set<Pedido> pedidos = new HashSet<>();
			Cliente cliente = new Cliente();
			cliente.setEmail("teste" + i + "@teste.com.br");
			cliente.setNome("Teste" + i);
			
			Pedido pedido = new Pedido();
			pedido.setCliente(cliente);
			pedido.setData(Calendar.getInstance());
			pedido.setDescricao("Teste" + i);
			pedido.setValor(109.4d);
			pedidos.add(pedido);
			cliente.setPedidos(pedidos);
			clienteDao.adicionar(cliente);
		}
		
		Cliente cliente = clienteDao.buscar(2);
		cliente.setNome("Teste12345678");
		cliente.setEmail("teste12345678@teste.com.br");
		clienteDao.atualizar(cliente);
		
		clienteDao.remover(clienteDao.buscar(3));
		
		clienteDao.listar().forEach(c -> {
			System.out.println(c);
			c.getPedidos().forEach(p -> {
				System.out.println(p);
			});
		});
		
		Pedido pedido = pedidoDao.buscar(2);
		pedido.setDescricao("Modelo");
		pedidoDao.atualizar(pedido);
		
		pedidoDao.remover(pedidoDao.buscar(5));
		
		pedidoDao.listar().forEach(p -> {
			System.out.println(p);
		});
	}
}
