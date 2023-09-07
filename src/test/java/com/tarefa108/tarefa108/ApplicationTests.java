package com.tarefa108.tarefa108;

import com.tarefa108.tarefa108.controller.LembreteController;
import com.tarefa108.tarefa108.entity.Lembrete;
import com.tarefa108.tarefa108.entity.Pessoa;
import com.tarefa108.tarefa108.repository.LembreteRepository;
import com.tarefa108.tarefa108.service.LembreteService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ApplicationTests {

	@MockBean
	LembreteRepository repository;

	@Autowired
	private  LembreteController controller;

	@BeforeEach
	void injectData(){

		//TESTAR BUSCA POR ID
		Optional<Lembrete> lembrete = Optional.of(new Lembrete(1L,"Bla bla bla",new Pessoa(1L,"Pedro Henrique")));

		Long id = 1L;
		Mockito.when(repository.findById(id)).thenReturn(lembrete);

		//TESTAR CADASTRO
		Mockito.when(repository.save(Mockito.any(Lembrete.class))).thenAnswer(invocation -> {
			Lembrete lembreteSalvo = invocation.getArgument(0);
			lembreteSalvo.setId(2L);
			return lembreteSalvo;
		});

		//TESTAR BUSCA POR NOME
		String nome = "Pedro Henrique";
		Mockito.when(repository.listarPorNome(nome)).thenReturn(lembrete.stream().toList());

		//TESTAR BUSCA POR TODOS
		Mockito.when(repository.findAll()).thenReturn(lembrete.stream().toList());

		//EDITAR LEMBRETE
		Optional<Lembrete> lembreteDTO = Optional.of(new Lembrete(1L,"Pedrão fodão",new Pessoa(1L,"Pedro Henrique")));
		Mockito.when(repository.save(lembreteDTO.get())).thenReturn(lembrete.get());

	}

	@Test
	void testLembreteBuscarPorId() {

		var buscarPorId = controller.buscarPorId(1L);
		Long id = buscarPorId.getBody().getId().longValue();

		Assert.assertEquals(1L, id,0);
	}

	@Test
	void testLembreteCadastrar(){

		Pessoa pessoa = new Pessoa(1L,"Pedro Henrique");
		Lembrete lembrete = new Lembrete(1L,"Bla bla bla", pessoa);

		var response = controller.cadastrar(lembrete);

		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		Assert.assertNotNull(response.getBody().getId());
		Assert.assertEquals("Pedro Henrique",response.getBody().getPessoa().getNome());
	}

	@Test
	void testLembreteListarPorNome(){

		var response = controller.listarPorNome("Pedro Henrique");
		List<Lembrete> lista = response.getBody();

		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		Assert.assertEquals(1L,lista.size(), 0);
		Assert.assertEquals("Pedro Henrique",lista.get(0).getPessoa().getNome());

	}

	@Test
	void testLembreteListarTodos(){

		var response = controller.listarTodos();
		List<Lembrete> lembretes = response.getBody();

		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		Assert.assertEquals(1L, lembretes.size(), 0);
		Assert.assertEquals("Pedro Henrique", lembretes.get(0).getPessoa().getNome());
	}

	@Test
	void testLembreteEditar(){

		Pessoa pessoa = new Pessoa(1L,"Pedro Henrique");
		Lembrete lembreteBanco = new Lembrete(1L,"Pedrão é fodão", pessoa);


		var response = controller.editar(1L,lembreteBanco);

		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		Assert.assertEquals("Pedrão é fodão", lembreteBanco.getConteudo());
	}
}
