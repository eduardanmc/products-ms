package br.com.vivo.fasttrack.mapper;

import org.springframework.stereotype.Component;

import br.com.vivo.fasttrack.controller.dto.ProdutoDto;
import br.com.vivo.fasttrack.modelo.Produto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ProductMapper extends ConfigurableMapper {

	@Override
	public void configure(MapperFactory factory) {
		factory.classMap(ProdutoDto.class, Produto.class).byDefault().register();

	}
}
