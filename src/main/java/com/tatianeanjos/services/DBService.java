package com.tatianeanjos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatianeanjos.domain.Categoria;
import com.tatianeanjos.domain.Cidade;
import com.tatianeanjos.domain.Cliente;
import com.tatianeanjos.domain.Endereco;
import com.tatianeanjos.domain.Estado;
import com.tatianeanjos.domain.ItemPedido;
import com.tatianeanjos.domain.Pagamento;
import com.tatianeanjos.domain.PagamentoComBoleto;
import com.tatianeanjos.domain.PagamentoComCartao;
import com.tatianeanjos.domain.Pedido;
import com.tatianeanjos.domain.Produto;
import com.tatianeanjos.domain.enums.EstadoPagamento;
import com.tatianeanjos.domain.enums.TipoCliente;
import com.tatianeanjos.repositories.CategoriaRepository;
import com.tatianeanjos.repositories.CidadeRepository;
import com.tatianeanjos.repositories.ClienteRepository;
import com.tatianeanjos.repositories.EnderecoRepository;
import com.tatianeanjos.repositories.EstadoRepository;
import com.tatianeanjos.repositories.ItemPedidoRepository;
import com.tatianeanjos.repositories.PagamentoRepository;
import com.tatianeanjos.repositories.PedidoRepository;
import com.tatianeanjos.repositories.ProdutoRepository;

@Service
public class DBService {


	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instatiateTesteDatabase() throws ParseException {

		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama, mesa e banho");
		Categoria cat4 = new Categoria(null,"Eletrônicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decoração");
		Categoria cat7 = new Categoria(null,"Perfumaria");


		Produto p1 = new Produto (null,"Computador",2000.00);
		Produto p2 = new Produto (null,"Impressora",800.00);
		Produto p3 = new Produto (null,"Mouse",80.00);
		Produto p4 = new Produto (null,"Mesa de escritório",300.00);
		Produto p5 = new Produto (null,"Toalha",50.00);
		Produto p6 = new Produto (null,"Colcha",200.00);
		Produto p7 = new Produto (null,"TV true color",1200.00);
		Produto p8 = new Produto (null,"Roçadeira",800.00);
		Produto p9 = new Produto (null,"Abajour",100.00);
		Produto p10 = new Produto (null,"Pendente",180.00);
		Produto p11 = new Produto (null,"Shampoo",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente (null, "Maria Silva", "maria@gmail.com","123456789",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("125124522","4581218484"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "30502011", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 8", "Centro", "0254620", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("16/07/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null,sdf.parse("16/07/2020 16:38"),cli1,e2);
		
		Pagamento pgt1 = new PagamentoComCartao(null,EstadoPagamento.FINALIZADO,ped1,6); 
		ped1.setPagamento(pgt1);
		
		Pagamento pgt2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("16/07/2020 00:00"),null);
		ped2.setPagamento(pgt2);
	
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgt1,pgt2));
		
		ItemPedido ip1 = new ItemPedido(p1,ped1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(p3,ped1,0.00,2,80.00);
		ItemPedido ip3 = new ItemPedido(p2,ped2,100.00,1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));	
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
