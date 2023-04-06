package api.com.tecmhaicky.primeiraapijava.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import api.com.tecmhaicky.primeiraapijava.Models.Pessoa;
import api.com.tecmhaicky.primeiraapijava.Repositorio.repositorio;

@RestController
public class controller {
    
    @Autowired
    private repositorio acao;

    // Api/Pessoas - [CRUD]
        // Create [C]
        @PostMapping("/api/pessoas")
        public Pessoa cadastrarPessoa(@RequestBody Pessoa p){
            return acao.save(p);
        }

        // Read [R]
            // Retorna a pessoa pelo id
            @GetMapping("/api/pessoas/{id}")
            public Pessoa readPessoa(@PathVariable int id){
                return acao.findById(id);
            }
            
            // Retornar todas as pessoas cadastradas
            @GetMapping("/api/pessoas")
            public List<Pessoa> listarPessoas(){
                return acao.findAll();
            }

            // Retorna todas as pessoas pela idade
            @GetMapping("/api/pessoas/idade/{idade}")
            public List<Pessoa> listarPessoasIdade(@PathVariable int idade){
                return acao.findByIdade(idade);
            }

            // Retorna a quantidade de registros
            @GetMapping("/api/count")
            public String contarPessoas(){
                return "Existem atualmente " + acao.count() + " pessoas cadastradas";
            }

            // Retorna todas as pessoas com os nomes ordenados
            @GetMapping("/api/pessoas/ordernomes")
            public List<Pessoa> ordenarNomes(){
                return acao.findByOrderByNome();
            }
            
            // Retorna as pessoas com nome igual, do mais velho para o mais novo
            @GetMapping("/api/pessoas/nomeidadedesc/{nome}")
            public List<Pessoa> nomeIdadeDesc(@PathVariable String nome){
                return acao.findByNomeOrderByIdade(nome);
            }

            // Retorna todas as pessoas com que contém o terno passado = LIKE
            @GetMapping("/api/pessoas/nomecontem/{termo}")
            public List<Pessoa> listaNomesLike(@PathVariable String termo){
                return acao.findByNomeContaining(termo);
            }
            
            // Soma das idades de todos
            @GetMapping("/api/pessoas/somaIdades")
            public int somaIdades(){
                return acao.somaIdades();
            }

            // Traz maior ou igual a idade informada
            @GetMapping("/api/pessoas/idadeMI/{idade}")
            public List<Pessoa> idadeMaiorIgual(@PathVariable int idade){
                return acao.idadeMaiorIgual(idade);
            }

        // Update [U]
        @PutMapping("/api/pessoas")
        public Pessoa putPessoa(@RequestBody Pessoa p){
            return acao.save(p);
        }
        
        // Delete [D]
        @DeleteMapping("/api/pessoas/{id}")
        public String deletePessoa(@PathVariable int id){
            //Pessoa p = acao.findById(id);

            //acao.delete(p);
            acao.deleteById(id);

            return "Pessoa excluída";
        }

    // Rota Principal    
    @GetMapping("/")
    public String mensagem() {
        return "Hello - Yo Soy la Raiz";
    }

    // Rota Bem-vindos, nome!
    @GetMapping("/bv/{nome}")
    public String bv(@PathVariable String nome) {
        return "Seja bem vindo, " + nome + "!";
    }

    // Rota com POST
    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }
}
