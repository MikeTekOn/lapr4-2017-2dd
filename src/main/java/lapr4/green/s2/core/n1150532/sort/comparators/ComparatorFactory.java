package lapr4.green.s2.core.n1150532.sort.comparators;

import java.util.ArrayList;
import java.util.List;

/**
 * The factory responsible for building comparators.
 *
 * @author Manuel Meireles (1150532)
 */
public class ComparatorFactory {

    /**
     * The full constructor of the factory.
     */
    public ComparatorFactory() {
    }

    /**
     * It builds all available Range Row DTO Comparators.
     *
     * @return It provides a list with all RangeRowDTOComparator with a
     * different TypeComparator.
     */
    public List<RangeRowDTOComparator> buildAllRangeRowDTOComparators() {
        List<RangeRowDTOComparator> list = new ArrayList<>();
        List<TypeComparator> typeComparators = buildAllTypeComparators();
        for (TypeComparator tc : typeComparators) {
            list.add(new RangeRowDTOComparator(tc));
        }
        return list;
    }

    /**
     * It provides all available Type Comparators.
     *
     * @return It provides a list with all TypeComparator.
     */
    public List<TypeComparator> buildAllTypeComparators() {
        List<TypeComparator> list = new ArrayList<>();
        list.add(new TypeComparatorDNT());
        list.add(new TypeComparatorDTN());
        list.add(new TypeComparatorNDT());
        list.add(new TypeComparatorNTD());
        list.add(new TypeComparatorTDN());
        list.add(new TypeComparatorTND());
        return list;
    }
}
