package api.com.tecmhaicky.primeiraapijava.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import api.com.tecmhaicky.primeiraapijava.Models.Pessoa;

public interface repositorio extends CrudRepository<Pessoa, Integer> {
    
    List<Pessoa> findAll();

    Pessoa findById(int id);

    List<Pessoa> findByIdade(int idade);

    List<Pessoa> findByOrderByNome();
    List<Pessoa> findByOrderByNomeDesc();
    List<Pessoa> findByOrderByIdade();
    List<Pessoa> findByOrderByIdadeDesc();
    List<Pessoa> findByOrderById();
    List<Pessoa> findByOrderByIdDesc();

    List<Pessoa> findByNomeOrderByIdade(String nome);

    List<Pessoa> findByNomeContaining(String termo);

    @Query(value = "SELECT SUM(idade) FROM pessoas", nativeQuery = true)
    int somaIdades();

    @Query(value = "SELECT * FROM pessoas WHERE idade >= :idade", nativeQuery = true)
    List<Pessoa> idadeMaiorIgual(int idade);

    int countById(int id);
    int countByNome(String nome);
}
