package api.com.tecmhaicky.primeiraapijava.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import api.com.tecmhaicky.primeiraapijava.Models.Cliente;
import api.com.tecmhaicky.primeiraapijava.Models.Pessoa;
import api.com.tecmhaicky.primeiraapijava.Servico.servico;
import jakarta.validation.Valid;


@RestController
public class controller {
    
    @Autowired
    private servico servico;

    // Api/Pessoas - [CRUD]
        // Create [C]
        @PostMapping("/api/pessoas")
        public ResponseEntity<?> cadastrarPessoa(@RequestBody Pessoa novaPessoa) {
            return servico.cadastro(novaPessoa);
        }

        // Read [R]
            // Retorna a pessoa pelo id
            @GetMapping("/api/pessoas/{id}")
            public ResponseEntity<?> readPessoa(@PathVariable int id){
                return servico.selecionaPessoa(id);
            }
            
            // Retornar todas as pessoas cadastradas
            @GetMapping("/api/pessoas")
            public ResponseEntity<?> listarPessoas(){
                return servico.listaPessoas();
            }

            // Retorna todas as pessoas pela idade
            @GetMapping("/api/pessoas/idade/{idade}")
            public ResponseEntity<?> listarPessoasIdade(@PathVariable int idade){
                return servico.selecionaIdade(idade);
            }

            // Retorna a quantidade de registros
            @GetMapping("/api/count")
            public Long contarPessoas(){
                return servico.countPessoas();
            }

            // Retorna todas as pessoas com os nomes ordenados
            @GetMapping("/api/pessoas/ordernomes/{ordenador}/{order}")
            public ResponseEntity<?> ordenarNomes(@PathVariable String ordenador, @PathVariable String order){
                return servico.selecionaPessoasOrdenadas(ordenador, order);
            }
            
            // Retorna as pessoas com nome igual, do mais velho para o mais novo
            @GetMapping("/api/pessoas/nomeidadedesc/{nome}")
            public ResponseEntity<?> nomeIdadeDesc(@PathVariable String nome){
                return servico.buscaNome(nome);
            }

            // Retorna todas as pessoas com que contém o terno passado = LIKE
            @GetMapping("/api/pessoas/nomecontem/{termo}")
            public ResponseEntity<?> listaNomesLike(@PathVariable String termo){
                return servico.buscaContainig(termo);
            }
            
            // Soma das idades de todos
            @GetMapping("/api/pessoas/somaIdades")
            public int somaIdades(){
                return servico.somaIdadesTodos();
            }

            // Traz maior ou igual a idade informada
            @GetMapping("/api/pessoas/idadeMI/{idade}")
            public ResponseEntity<?> idadeMaiorIgual(@PathVariable int idade){
                return servico.pessoasIdadeMaiorIgual(idade);
            }

        // Update [U]
        @PutMapping("/api/pessoas")
        public ResponseEntity<?> putPessoa(@RequestBody Pessoa p){
            return servico.editar(p);
        }
        
        // Delete [D]
        @DeleteMapping("/api/pessoas/{id}")
        public ResponseEntity<?> deletePessoa(@PathVariable int id){
            return servico.deletar(id);
        }

    // Rota Principal    
    @GetMapping("/")
    public String mensagem() {
        return "Hello - Yo Soy la Raiz";
    }

    // // Rota Bem-vindos, nome!
    // @GetMapping("/bv/{nome}")
    // public String bv(@PathVariable String nome) {
    //     return "Seja bem vindo, " + nome + "!";
    // }

    // // Rota com POST
    // @PostMapping("/pessoa")
    // public Pessoa pessoa(@RequestBody Pessoa p) {
    //     return p;
    // }

    // // Respondendo com status
    // @GetMapping("/status")
    // public ResponseEntity<?> RotaStatus() {
    //     return new ResponseEntity<>("Cadastro criado", HttpStatus.CREATED);
    // }

    // Cadastra via JPA
    @PostMapping("/cadastrar")
    public void cadastrarNovaPessoa(@Valid @RequestBody Cliente obj) {

    }
}
