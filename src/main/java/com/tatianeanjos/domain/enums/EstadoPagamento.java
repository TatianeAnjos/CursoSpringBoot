package com.tatianeanjos.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1,"Pendente"),
	FINALIZADO(2,"Finalizado"),
	CANCELADO(3,"Cancelado");
	
	private int codigo;
	private String descricao;
	
	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	public int getCodigo(){
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public static EstadoPagamento toEnum(Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		for(EstadoPagamento tc : EstadoPagamento.values()) {
			if(codigo.equals(tc.getCodigo())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+ codigo);
	}
}
