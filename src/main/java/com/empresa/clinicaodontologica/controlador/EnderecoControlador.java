// package com.empresa.clinicaodontologica.controlador;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;
// import com.empresa.clinicaodontologica.servico.implementacao.EnderecoServicoImplementacao;

// @RestController
// @RequestMapping("/endereco")
// public class EnderecoControlador {   
//     private final EnderecoServicoImplementacao enderecoServico;
  
//     public EnderecoControlador(EnderecoServicoImplementacao enderecoServico) {
//         this.enderecoServico = enderecoServico;
//     }

//     @PostMapping("/salvar")
//     public ResponseEntity<EnderecoEntidade> salvar(@RequestBody EnderecoEntidade endereco) {
//         EnderecoEntidade enderecoEntidade = enderecoServico.salvar(endereco);
        
//         if (enderecoEntidade == null) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//         }
        
//         return ResponseEntity.ok().body(enderecoEntidade);
//     }

//     @GetMapping("/buscar_tudo")
//     public ResponseEntity<List<EnderecoEntidade>> buscarTudo() {
//         return ResponseEntity.ok().body(enderecoServico.buscarTudo());
//     }

//     @GetMapping("/buscar/{id}")
//     public ResponseEntity<EnderecoEntidade> buscarPorId(@PathVariable Integer id) {
//         Optional<EnderecoEntidade> enderecoEntidade = enderecoServico.buscarPorId(id);

//         if (enderecoEntidade.isPresent()) {
//             return ResponseEntity.ok().body(enderecoEntidade.get());
//         } else if (enderecoEntidade.isEmpty()) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//         } 
        
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//     }

//     @PutMapping("/atualizar")
//     public ResponseEntity<EnderecoEntidade> atualizar(@RequestBody EnderecoEntidade endereco) {
//         EnderecoEntidade enderecoEntidade = enderecoServico.atualizar(endereco);
        
//         if (enderecoEntidade == null) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//         }

//         return ResponseEntity.ok().body(enderecoEntidade);
//     }  

//     @DeleteMapping("/excluir/{id}") 
//     public ResponseEntity<String> excluir(@PathVariable Integer id) {
//         if (enderecoServico.excluir(id)) {
//             return ResponseEntity.ok().body(String.format("O registro foi excluído com sucesso.", id));
//         } 

//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível excluir o registro.");
//     }
// }
