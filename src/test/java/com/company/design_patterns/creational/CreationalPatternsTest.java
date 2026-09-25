package com.company.design_patterns.creational;

import com.company.design_patterns.creational.abstract_factory.gui.DarkThemeFactory;
import com.company.design_patterns.creational.abstract_factory.gui.SettingsDialog;
import com.company.design_patterns.creational.builder.Product;
import com.company.design_patterns.creational.prototype.Shape;
import com.company.design_patterns.creational.prototype.ShapeCache;
import com.company.design_patterns.creational.singleton.BillPughSingleton;
import com.company.design_patterns.creational.singleton.DoubleCheckedLockingSingleton;
import com.company.design_patterns.creational.singleton.EnumSingleton;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CreationalPatternsTest {

    @Test
    void singletonsReturnSameInstance() {
        assertSame(BillPughSingleton.getInstance(), BillPughSingleton.getInstance());
        assertSame(EnumSingleton.INSTANCE, EnumSingleton.valueOf("INSTANCE"));
    }

    @Test
    void doubleCheckedLockingIsThreadSafe() throws InterruptedException {
        Set<Object> instances = ConcurrentHashMap.newKeySet();
        ExecutorService pool = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 100; i++) {
            pool.submit(() -> instances.add(DoubleCheckedLockingSingleton.getInstance()));
        }
        pool.shutdown();
        assertTrue(pool.awaitTermination(5, TimeUnit.SECONDS));
        assertEquals(1, instances.size());
    }

    @Test
    void builderValidatesRequiredFields() {
        assertThrows(NullPointerException.class, () -> Product.builder().id(1L).build());
        assertThrows(IllegalStateException.class,
                () -> Product.builder().name("x").price(new BigDecimal("-1")).build());

        Product p = Product.builder().name("Pen").price(BigDecimal.ONE).build();
        assertEquals("Pen", p.getName());
        assertTrue(p.isInStock());
    }

    @Test
    void prototypeReturnsIndependentCopies() {
        ShapeCache.loadCache();
        Shape a = ShapeCache.getShape("1");
        Shape b = ShapeCache.getShape("1");
        assertNotSame(a, b);
        a.setId("changed");
        assertEquals("1", ShapeCache.getShape("1").getId());
        assertThrows(IllegalArgumentException.class, () -> ShapeCache.getShape("404"));
    }

    @Test
    void abstractFactoryProducesConsistentFamily() {
        String rendered = new SettingsDialog(new DarkThemeFactory()).render();
        assertTrue(rendered.contains("dark checkbox"));
        assertTrue(rendered.contains("Dark Button"));
    }
}
