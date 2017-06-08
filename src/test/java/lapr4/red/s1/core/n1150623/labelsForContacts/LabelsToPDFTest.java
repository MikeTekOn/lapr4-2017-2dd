package lapr4.red.s1.core.n1150623.labelsForContacts;

import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by guima on 04/06/2017.
 */
public class LabelsToPDFTest {

    public LabelsToPDFTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    LabelList lista = new LabelList();

    @Before
    public void setUp() {

/*
        Event a = new Event("Evento1", Calendar.getInstance());
        Event b = new Event("Evento2", Calendar.getInstance());
        Event c = new Event("Evento3", Calendar.getInstance());
        Event d = new Event("Evento4", Calendar.getInstance());
        List<Event> e = new ArrayList<>();
        Label lab1 = new Label();

        lab1.fillLabel("name1", "C:\\Users\\guima\\Desktop\\default_img.png", "email1", "phoneNumber1", "phone1");
        e.add(a);
        e.add(d);
        lab1.addEvents(e);
        Label lab2 = new Label();
        lab2.fillLabel("name2", "C:\\Users\\guima\\Desktop\\default_img.png", "email2", "phoneNumber2", "phone2");
        List<Event> z = new ArrayList<>();
        z.add(b);
        z.add(d);
        lab2.addEvents(z);

        Label lab3 = new Label();
        lab3.fillLabel("name3", "default_img.png", "email3", "phoneNumber3", "phone3");
        List<Event> w = new ArrayList<Event>();
        w.add(b);
        lab3.addEvents(w);

        Label lab4 = new Label();
        lab4.fillLabel("name4", "\\default_img.png", "email4", "phoneNumber4", "phone4");
        List<Event> q = new ArrayList<>();
        q.add(a);
        q.add(c);
        lab4.addEvents(q);

        lista.addLabel(lab1);
        lista.addLabel(lab2);
        lista.addLabel(lab3);
        lista.addLabel(lab4);
        System.out.println("..........................................");
        for(Label l : lista.labels()){

            for(Event v : l.events())
            System.out.println(v.description());
            System.out.println("..........................................");
        }


        lista.choosePath("C:\\Users\\guima\\Desktop\\labels.pdf");
*/
    }

    @Test
    public void ensurePDFIsPrinted() {
        /*assertTrue(lista.exportPDF());*/

    }

}
