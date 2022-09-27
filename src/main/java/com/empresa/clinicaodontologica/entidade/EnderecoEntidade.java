package com.empresa.clinicaodontologica.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Endereco")
@Data  // atalho para @ToString, @EqualsAndHashCode, @Getter, @Setter e @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // veja a observação 1
public class EnderecoEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;
    
    @NotNull
    private String cidade;

    @NotNull
    private String bairro;

    @NotNull
    private String rua;

    private String numero;

    public EnderecoEntidade(String cidade, String bairro, String rua, String numero) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

//     public EnderecoEntidade() {
//     } 

//     @Override
//     public String toString() {
//         return String.format(
//             "Endereco {idEndereco: %d, cidade: %s, bairro: %s, rua: %s, numero: %s}", 
//             idEndereco, cidade, bairro, rua, numero
//         );
//     }

//     public Integer getIdEndereco() {
//         return idEndereco;
//     }

//     public void setIdEndereco(Integer idEndereco) {
//         this.idEndereco = idEndereco;
//     }

//     public String getCidade() {
//         return cidade;
//     }

//     public void setCidade(String cidade) {
//         this.cidade = cidade;
//     }

//     public String getBairro() {
//         return bairro;
//     }

//     public void setBairro(String bairro) {
//         this.bairro = bairro;
//     }

//     public String getRua() {
//         return rua;
//     }

//     public void setRua(String rua) {
//         this.rua = rua;
//     }

//     public String getNumero() {
//         return numero;
//     }

//     public void setNumero(String numero) {
//         this.numero = numero;
//     }
}

/*
 * Observação 1:
 * Para lidar com o problema relacionado à serialização do modelo usando a API Jackson quando os atributos do modelo 
 * têm um carregamento lento definido (FetchType.LAZY), temos que dizer ao serializador para ignorar a cadeia ou lixo 
 * útil que o Hibernate adiciona às classes, para que ele possa gerenciar o carregamento lento de dados declarando a 
 * anotação: @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}).
 * 
 * Links:
 * https://dzone.com/articles/key-java-annotations-to-build-full-spring-boot-res
 */