package br.com.vivo.fasttrack.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vivo.fasttrack.controller.dto.ProdutoDto;
import br.com.vivo.fasttrack.mapper.ProductMapper;
import br.com.vivo.fasttrack.modelo.Produto;
import br.com.vivo.fasttrack.repository.ProdutosRepository;

@RestController
@RequestMapping("/products")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository produtosRepository;

	@Autowired
	private ProductMapper productMapper;
	
	@GetMapping
	public List<ProdutoDto> lista(){
		
		List<Produto> produtos = produtosRepository.findAll();
		return productMapper.mapAsList(produtos, ProdutoDto.class);

	}
	
	@PostMapping
	public ResponseEntity<ProdutoDto> criar(@RequestBody @Valid ProdutoDto productDto, UriComponentsBuilder uriBuilder) {
		
	Produto produto = productMapper.map(productDto, Produto.class);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(productMapper.map(produto, ProdutoDto.class));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> buscar(@PathVariable Long id) {
		Optional<Produto> produto = produtosRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(productMapper.map(produto, ProdutoDto.class));
		}
			return ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoDto produtoDto){
		Optional<Produto> optional = produtosRepository.findById(id);
		if (optional.isPresent()) {
			optional.get().setName(produtoDto.getName());
			optional.get().setDescription(produtoDto.getDescription());
			optional.get().setPrice(produtoDto.getPrice());
			
			return ResponseEntity.ok(productMapper.map(produtosRepository.save(optional.get()), ProdutoDto.class));
		}
			return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Produto> optional = produtosRepository.findById(id);
		if (optional.isPresent()) {
			produtosRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
			return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/search")
	public List <ProdutoDto> search(@RequestParam("q") String q, @RequestParam("min_price") BigDecimal min_price, @RequestParam("max_price") BigDecimal max_price) {
		List <Produto> produtos = produtosRepository.search(q, min_price, max_price);		
		return productMapper.mapAsList(produtos, ProdutoDto.class);
	}
		
	
}
