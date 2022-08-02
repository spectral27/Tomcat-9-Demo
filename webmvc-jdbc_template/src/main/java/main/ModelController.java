package main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/models")
public class ModelController {

	@Autowired
	private ModelRepository modelRepository;

	@GetMapping
	public List<Model> selectAllModels() {
		return modelRepository.selectAllModels();
	}

	@GetMapping("/{id}")
	public Model selectModel(@PathVariable("id") String id) {
		return modelRepository.selectModel(id);
	}
	
	@PostMapping
	public int insertModel(@RequestBody Model model) {
		return modelRepository.insertModel(model);
	}
	
	@PutMapping("/{id}")
	public int updateModel(@PathVariable("id") String id, @RequestBody Model model) {
		return modelRepository.updateModel(id, model);
	}
	
	@DeleteMapping("/{id}")
	public int deleteModel(@PathVariable("id") String id) {
		return modelRepository.deleteModel(id);
	}

}
