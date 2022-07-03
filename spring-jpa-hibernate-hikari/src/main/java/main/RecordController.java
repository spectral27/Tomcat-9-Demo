package main;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
public class RecordController {
	
	@Autowired
	private RecordRepository recordRepository;
	
	@GetMapping
	public ResponseEntity<?> selectAllItems() {
		return new ResponseEntity<>(recordRepository.selectAllItems(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> selectItem(@PathVariable("id") String id) {
		System.out.println(recordRepository.selectItem(id).getRecordTimestamp());
		return new ResponseEntity<>(recordRepository.selectItem(id), HttpStatus.OK);
	}
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateItem() {
		try {
			return new ResponseEntity<>(recordRepository.generateItem(), HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
