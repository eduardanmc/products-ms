package br.com.vivo.fasttrack.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.vivo.fasttrack.modelo.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByName(String name);
	
	@Query("SELECT p FROM Produto p WHERE (p.name LIKE :q% OR p.description LIKE :q%) OR p.price >= :min_price OR p.price <= :max_price")
	List<Produto> search (String q, BigDecimal min_price, BigDecimal max_price);
}
