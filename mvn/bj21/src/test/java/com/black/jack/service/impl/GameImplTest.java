package com.black.jack.service.impl;

import com.black.jack.dto.Card;
import com.black.jack.dto.Game;
import com.black.jack.dto.Player;
import com.black.jack.dto.Rank;
import com.black.jack.dto.Suit;
import com.black.jack.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameImplTest {

    @Test
    void startGame() {
        Player pl = new Player().setName("pl_1");
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        Assertions.assertNotNull(game);
        Assertions.assertNotNull(game.getPlayer());
        Assertions.assertNotNull(game.getComputer());
        Assertions.assertNotNull(game.getCardDeck());
        Assertions.assertEquals(52, game.getCardDeck().size());
    }

    @Test
    void userTurn() {
        Player pl = new Player().setName("pl_1");
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        gs.userTurn(game);
        Assertions.assertEquals(1, game.getPlayer().getHand().size());
        gs.userTurn(game);
        Assertions.assertEquals(2, game.getPlayer().getHand().size());

    }

    @RepeatedTest(50)
    void computerTurn() {
        Player pl = new Player().setName("pl_1");
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);
        gs.computerTurn(game);
        int amount = game.getComputer().getHand();

        Assertions.assertTrue(amount >= 17 && amount <= 25);
    }

    @Test
    void showHand(){
        Player pl = new Player().setName("pl_1");
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        Assertions.assertEquals(0, gs.showHand(game));

        pl.getHand().add(new Card(Rank.Ten, Suit.Clubs));
        Assertions.assertEquals(10, gs.showHand(game));
        pl.getHand().add(new Card(Rank.Five, Suit.Clubs));
        Assertions.assertEquals(15, gs.showHand(game));
    }

    @Test
    void showWinner_LOSE() {
        Player pl = new Player().setName("pl_1").setAmount(200);
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        pl.getHand().add(new Card(Rank.Ace, Suit.Clubs));
        pl.getHand().add(new Card(Rank.Ace, Suit.Clubs));

        game.getComputer().setHand(22);

        gs.showWinner(game, 100);

        Assertions.assertEquals(0,game.getPlayer().getNumberOfWinGames());
        Assertions.assertEquals(1,game.getPlayer().getNumberOfGames());
        Assertions.assertEquals(100,game.getPlayer().getAmount());
    }

    @Test
    void showWinner_DRAW() {
        Player pl = new Player().setName("pl_1").setAmount(50);
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        pl.getHand().add(new Card(Rank.Ten, Suit.Clubs));
        pl.getHand().add(new Card(Rank.Ten, Suit.Clubs));

        game.getComputer().setHand(20);

        gs.showWinner(game, 100);

        Assertions.assertEquals(0,game.getPlayer().getNumberOfWinGames());
        Assertions.assertEquals(1,game.getPlayer().getNumberOfGames());
        Assertions.assertEquals(50,game.getPlayer().getAmount());
    }

    @Test
    void showWinner_PLAYER() {
        Player pl = new Player().setName("pl_1").setAmount(100);
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        pl.getHand().add(new Card(Rank.Ten, Suit.Clubs));
        pl.getHand().add(new Card(Rank.Ten, Suit.Clubs));

        game.getComputer().setHand(18);

        gs.showWinner(game, 50);

        Assertions.assertEquals(1,game.getPlayer().getNumberOfWinGames());
        Assertions.assertEquals(1,game.getPlayer().getNumberOfGames());
        Assertions.assertEquals(150,game.getPlayer().getAmount());
    }

    @Test
    void showWinner_COMPUTER() {
        Player pl = new Player().setName("pl_1").setAmount(200);
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        pl.getHand().add(new Card(Rank.Ten, Suit.Clubs));
        pl.getHand().add(new Card(Rank.Ten, Suit.Clubs));

        game.getComputer().setHand(21);

        gs.showWinner(game, 100);

        Assertions.assertEquals(0,game.getPlayer().getNumberOfWinGames());
        Assertions.assertEquals(1,game.getPlayer().getNumberOfGames());
        Assertions.assertEquals(100,game.getPlayer().getAmount());
    }

    @Test
    void clearHand() {
        Player pl = new Player().setName("pl_1");
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        pl.getHand().add(new Card(Rank.Ten, Suit.Clubs));
        pl.getHand().add(new Card(Rank.Ten, Suit.Clubs));
        Assertions.assertEquals(2,game.getPlayer().getHand().size());

        gs.clearHand(game);

        Assertions.assertEquals(0,game.getPlayer().getHand().size());
    }

    @Test
    void updateDeck() {
        Player pl = new Player().setName("pl_1");
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        gs.userTurn(game);
        gs.userTurn(game);
        gs.userTurn(game);

        Assertions.assertEquals(49, game.getCardDeck().size());

        gs.updateDeck(game);

        Assertions.assertEquals(52, game.getCardDeck().size());
    }

    @Test
    void isOverScore() {
        Player pl = new Player().setName("pl_1").setAmount(200);
        GameService gs = new GameImpl();
        Game game = gs.startGame(pl);

        pl.getHand().add(new Card(Rank.Ace, Suit.Clubs));
        pl.getHand().add(new Card(Rank.Ace, Suit.Clubs));

        Assertions.assertTrue(gs.isOverScore(game));

        gs.clearHand(game);
        pl.getHand().add(new Card(Rank.Ace, Suit.Clubs));
        Assertions.assertFalse(gs.isOverScore(game));
    }
}