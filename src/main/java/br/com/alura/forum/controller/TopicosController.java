package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;

    @RequestMapping("/topicos")
    @ResponseBody
    public List<TopicoDto> lista() {
    	
    	List<Topico> topicos = topicoRepository.findAll();
        return TopicoDto.converter(topicos);
    }
}
