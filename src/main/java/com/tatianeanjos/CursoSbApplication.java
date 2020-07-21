package com.tatianeanjos;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class CursoSbApplication implements CommandLineRunner{

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");

		Produto p1 = new Produto (null,"Computador",2000.00);
		Produto p2 = new Produto (null,"Impressora",800.00);
		Produto p3 = new Produto (null,"Mouse",80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
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
