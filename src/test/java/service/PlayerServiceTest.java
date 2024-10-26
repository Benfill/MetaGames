package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.IPlayerDao;
import model.Player;
import service.impl.PlayerServiceImpl;

public class PlayerServiceTest {

	@Mock
	private IPlayerDao playerDao;

	@InjectMocks
	private PlayerServiceImpl playerService;

	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.openMocks(this);

		when(playerDao.readAll()).thenReturn(Arrays.asList(new Player("PlayerOne", 25)));
		when(playerDao.readById(1L)).thenReturn(new Player("PlayerOne", 25));
	}

	@Test
	public void testGetAllPlayers() {
		List<Player> players = playerService.getAll();
		assertFalse(players.isEmpty());
		assertEquals(1, players.size());
		assertEquals("PlayerOne", players.get(0).getPseudo());

		verify(playerDao, times(1)).readAll();
	}

	@Test
	public void testRegisterPlayer() {
		playerService.registerPlayer("PlayerTwo", 20);

		verify(playerDao, times(1)).insert(any(Player.class));
	}

	@Test
	public void testGetPlayerById() throws Exception {
		Player player = playerService.getPlayerById(1L);
		assertNotNull(player);
		assertEquals("PlayerOne", player.getPseudo());

		verify(playerDao, times(1)).readById(1L);
	}

	@Test
	public void testUpdatePlayer() throws Exception {

		playerService.updatePlayer(1L, "UpdatedPlayer", 26);

		verify(playerDao, times(1)).update(any(Player.class));
	}

	@Test
	public void testDeletePlayer() throws Exception {
		playerService.deletePlayer(1L);
		verify(playerDao, times(1)).delete(1L);
	}
}
