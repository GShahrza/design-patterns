package com.company.design_patterns.structural;

import com.company.design_patterns.structural.composite.Directory;
import com.company.design_patterns.structural.composite.File;
import com.company.design_patterns.structural.decorator.example1.Iphone;
import com.company.design_patterns.structural.decorator.example1.Iphone11Pro;
import com.company.design_patterns.structural.decorator.example1.Iphone11ProMax;
import com.company.design_patterns.structural.decorator.example1.Phone;
import com.company.design_patterns.structural.flyweight.TreeTypeFactory;
import com.company.design_patterns.structural.proxy.example3.ManagerProxy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class StructuralPatternsTest {

    @Test
    void compositeSumsSizesRecursively() {
        Directory root = new Directory("root")
                .add(new File("a", 10))
                .add(new Directory("sub").add(new File("b", 5)).add(new File("c", 7)));
        assertEquals(22, root.getSize());
    }

    @Test
    void decoratorsStack() {
        Phone phone = new Iphone11ProMax(new Iphone11Pro(new Iphone()));
        assertEquals("iPhone 11 Pro Max", phone.getName());
        assertEquals(3, phone.cameraCount());
        assertEquals(1099.99, phone.getPrice(), 0.001);
    }

    @Test
    void flyweightSharesInstances() {
        assertSame(TreeTypeFactory.get("Oak", "green", "oak.png"),
                TreeTypeFactory.get("Oak", "green", "oak.png"));
    }

    @Test
    void protectionProxyChecksAccess() {
        assertEquals(BigDecimal.valueOf(10000), new ManagerProxy("yusuf", "12345").getCiro());
        assertThrows(SecurityException.class, () -> new ManagerProxy("ayse", "1234").getCiro());
        assertThrows(SecurityException.class, () -> new ManagerProxy("yusuf", "wrong").getCiro());
    }
}
