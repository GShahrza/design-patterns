package com.company.design_patterns.behavioral;

import com.company.design_patterns.behavioral.chain_of_responsibility.AuthenticationHandler;
import com.company.design_patterns.behavioral.chain_of_responsibility.AuthorizationHandler;
import com.company.design_patterns.behavioral.chain_of_responsibility.Handler;
import com.company.design_patterns.behavioral.chain_of_responsibility.Request;
import com.company.design_patterns.behavioral.command.AppendCommand;
import com.company.design_patterns.behavioral.command.CommandHistory;
import com.company.design_patterns.behavioral.command.DeleteLastCommand;
import com.company.design_patterns.behavioral.command.TextEditor;
import com.company.design_patterns.behavioral.interpreter.RpnParser;
import com.company.design_patterns.behavioral.iterator.Playlist;
import com.company.design_patterns.behavioral.iterator.Song;
import com.company.design_patterns.behavioral.mediator.ChatRoom;
import com.company.design_patterns.behavioral.mediator.User;
import com.company.design_patterns.behavioral.memento.GameCharacter;
import com.company.design_patterns.behavioral.observer.StockExchange;
import com.company.design_patterns.behavioral.observer.StockObserver;
import com.company.design_patterns.behavioral.state.Order;
import com.company.design_patterns.behavioral.strategy.PercentageDiscount;
import com.company.design_patterns.behavioral.strategy.ShoppingCart;
import com.company.design_patterns.behavioral.template_method.CsvReport;
import com.company.design_patterns.behavioral.template_method.Sale;
import com.company.design_patterns.behavioral.visitor.Book;
import com.company.design_patterns.behavioral.visitor.Electronics;
import com.company.design_patterns.behavioral.visitor.TaxVisitor;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BehavioralPatternsTest {

    @Test
    void chainStopsAtFirstFailingHandler() {
        Handler chain = new AuthenticationHandler();
        chain.linkWith(new AuthorizationHandler());

        assertTrue(chain.handle(new Request("/orders", "t", "USER", "ip")));
        assertFalse(chain.handle(new Request("/orders", null, "USER", "ip")));
        assertFalse(chain.handle(new Request("/admin", "t", "USER", "ip")));
        assertTrue(chain.handle(new Request("/admin", "t", "ADMIN", "ip")));
    }

    @Test
    void commandUndoRedo() {
        TextEditor editor = new TextEditor();
        CommandHistory history = new CommandHistory();
        history.execute(new AppendCommand(editor, "abc"));
        history.execute(new DeleteLastCommand(editor, 2));
        assertEquals("a", editor.getText());
        assertTrue(history.undo());
        assertEquals("abc", editor.getText());
        assertTrue(history.undo());
        assertEquals("", editor.getText());
        assertFalse(history.undo());
        assertTrue(history.redo());
        assertEquals("abc", editor.getText());
    }

    @Test
    void interpreterEvaluatesExpression() {
        assertEquals(20, RpnParser.parse("x 2 + y 1 - *").interpret(Map.of("x", 3, "y", 5)));
        assertThrows(IllegalArgumentException.class, () -> RpnParser.parse("1 2"));
    }

    @Test
    void iteratorsTraverseInDifferentOrders() {
        Playlist playlist = new Playlist();
        playlist.add(new Song("A", "x"));
        playlist.add(new Song("B", "x"));
        playlist.add(new Song("C", "x"));

        List<String> forward = new ArrayList<>();
        playlist.forEach(s -> forward.add(s.title()));
        assertEquals(List.of("A", "B", "C"), forward);

        List<String> reverse = new ArrayList<>();
        playlist.reverseIterator().forEachRemaining(s -> reverse.add(s.title()));
        assertEquals(List.of("C", "B", "A"), reverse);

        List<String> shuffled = new ArrayList<>();
        Iterator<Song> it = playlist.shuffleIterator(1);
        it.forEachRemaining(s -> shuffled.add(s.title()));
        assertEquals(3, shuffled.size());
        assertTrue(shuffled.containsAll(forward));
    }

    @Test
    void mediatorDeliversToOthersOnly() {
        ChatRoom room = new ChatRoom();
        User a = new User("A", room);
        User b = new User("B", room);
        a.send("hi");
        assertTrue(b.getInbox().contains("A: hi"));
        assertFalse(a.getInbox().contains("A: hi"));
    }

    @Test
    void mementoRestoresState() {
        GameCharacter hero = new GameCharacter();
        GameCharacter.Memento save = hero.save();
        hero.fight(500);
        assertTrue(hero.isDead());
        hero.restore(save);
        assertFalse(hero.isDead());
    }

    @Test
    void observerIsNotifiedUntilUnsubscribed() {
        StockExchange exchange = new StockExchange();
        List<BigDecimal> seen = new ArrayList<>();
        StockObserver observer = (symbol, oldP, newP) -> seen.add(newP);
        exchange.subscribe(observer);
        exchange.updatePrice("X", BigDecimal.ONE);
        exchange.updatePrice("X", BigDecimal.ONE); // dəyişiklik yoxdur -> bildiriş yoxdur
        exchange.unsubscribe(observer);
        exchange.updatePrice("X", BigDecimal.TEN);
        assertEquals(List.of(BigDecimal.ONE), seen);
    }

    @Test
    void stateAllowsOnlyValidTransitions() {
        Order order = new Order("T-1");
        assertThrows(IllegalStateException.class, order::ship);
        order.pay();
        order.ship();
        assertThrows(IllegalStateException.class, order::cancel);
        order.deliver();
        assertEquals("DELIVERED", order.getStateName());
    }

    @Test
    void strategyCanBeSwappedAtRuntime() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new BigDecimal("200.00"));
        assertEquals(new BigDecimal("200.00"), cart.total());
        cart.setDiscount(new PercentageDiscount(25));
        assertEquals(new BigDecimal("150.00"), cart.total());
        cart.setDiscount(total -> BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, cart.total());
    }

    @Test
    void templateMethodKeepsSkeleton() {
        String csv = new CsvReport().generate(List.of(new Sale("Baku", "Pen", new BigDecimal("2"))));
        assertEquals("region,product,amount\nBaku,Pen,2\nTOTAL,,2\n", csv);
    }

    @Test
    void visitorAppliesPerTypeLogic() {
        TaxVisitor tax = new TaxVisitor();
        assertEquals(new BigDecimal("0.00"), new Book("b", new BigDecimal("50")).accept(tax));
        assertEquals(new BigDecimal("18.00"), new Electronics("e", new BigDecimal("100"), 1).accept(tax));
    }
}
