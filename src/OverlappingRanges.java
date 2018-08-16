import java.util.*;

/**
 * Write a function which can combine an input set of overlapping ranges.
 */
public class OverlappingRanges {

    public static class BigSet {
        public Set<Integer> set;

        public BigSet(Set<Integer> set) {
            this.set = set;
        }
    }

    public static Set<Set<Integer>> compressOverlappingSets(List<Set<Integer>> sets) {
        Map<Integer, BigSet> bigSets = new HashMap<>();

        for (Set<Integer> set : sets) {
            Set<Integer> currentIteration = new HashSet<>();
            currentIteration.addAll(set);

            BigSet thisSet = new BigSet(set);

            for (Integer x : currentIteration) {
                BigSet compareBigSet = bigSets.get(x);

                if (compareBigSet == null) {
                    bigSets.put(x, thisSet);
                } else {
                    if (compareBigSet.set != set) {
                        // can use actual equals operator b/c we are comparing references for faster run time
                        // and if the sets are equal, we don't have to do anything
                        set.addAll(compareBigSet.set);
                        compareBigSet.set = set;
                    }
                }
            }
        }

        Set<Set<Integer>> compressedSets = new HashSet<>();
        for (BigSet bSet : bigSets.values()) {
            compressedSets.add(bSet.set);
        }

        return compressedSets;
    }

    public static void main(String[] args) {
        // Testing to come soon
    }
}
