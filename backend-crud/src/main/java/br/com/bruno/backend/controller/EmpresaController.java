package br.com.bruno.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.backend.exception.ResourceNotFoundException;
import br.com.bruno.backend.repository.EmpresaRepository;
import br.com.bruno.backend.model.Empresa;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmpresaController {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	// get all clients
	@GetMapping("/empresas")
	public List<Empresa> getAllEmpresa(){
		return empresaRepository.findAll();
	}		
	
	// create client rest api
	@PostMapping("/empresas")
	public Empresa createEmpresa(@RequestBody Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
	// get client by id rest api
	@GetMapping("/empresas/{id}")
	public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id) {
		Empresa empresa = empresaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("empresa não existe :" + id));
		return ResponseEntity.ok(empresa);
	}
	
	// update client rest api
	
	@PutMapping("/empresas/{id}")
	public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody Empresa EmpresaDetails){
		Empresa empresa = empresaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("empresa nao existe :" + id));
		
		empresa.setRazaoSocial(EmpresaDetails.getRazaoSocial());
		
		Empresa updatedEmpresa = empresaRepository.save(empresa);
		return ResponseEntity.ok(updatedEmpresa);
	}
	
	// delete client rest api
	@DeleteMapping("/empresas/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmpresa(@PathVariable Long id){
		Empresa empresa = empresaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("empresa nao existe :" + id));
		
		empresaRepository.delete(empresa);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deletado", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
