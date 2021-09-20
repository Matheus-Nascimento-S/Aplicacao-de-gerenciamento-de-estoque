package br.com.GestaoDeEstoque.App.de.gerenciamento.de.estoque.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import br.com.GestaoDeEstoque.App.de.gerenciamento.de.estoque.Model.Game;
import br.com.GestaoDeEstoque.App.de.gerenciamento.de.estoque.Repository.GameRepository;

@Service
public class GameService {
	
	GameRepository repository;

	@Autowired
	public GameService(GameRepository repository) {
	
		this.repository = repository;
	
	}
	
	public String GetAll(Model model) {
		
		model.addAttribute("games", repository.findAll());
		
		return "HomePage";
		
	}
	
	public String GetAvaibleGames(Model model) {
		
		model.addAttribute("games", repository.findByQuantidadeEstoqueGreaterThan(0));
		return "Disponiveis";
		
	}

	public String Delete(Long id, Model model) {
		
		Game game = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Jogo invalido" + id));
		
		repository.delete(game);
		
		model.addAttribute("games", repository.findAll());
		
		return "HomePage";
		
	}
	
}