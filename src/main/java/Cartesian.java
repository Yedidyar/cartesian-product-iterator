import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



class CartesianProductIterator<T> implements Iterator<List<T>> {
    private final List<List<T>> sets;
    private final int[] indexes;
    private boolean finished;

    public CartesianProductIterator(List<List<T>> sets) {
        this.sets = sets;
        indexes = new int[sets.size()];
        finished = false;
    }

    @Override
    public boolean hasNext() {
        return !finished;
    }

    @Override
    public List<T> next() {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < sets.size(); i++) {
            List<T> set = sets.get(i);
            int index = indexes[i];
            T item = set.get(index);
            result.add(item);
        }
        incrementIndexes();
        return result;
    }

    private void incrementIndexes() {
        for (int i = indexes.length - 1; i >= 0; i--) {
            if (indexes[i] < sets.get(i).size() - 1) {
                indexes[i]++;
                break;
            } else {
                indexes[i] = 0;
                if (i == 0) {
                    finished = true;
                }
            }
        }
    }
}


public class Cartesian {
    public static void main(String[] args) {
        List<List<Integer>> sets = new ArrayList<>();
        sets.add(List.of(1, 2));
        sets.add(List.of(4, 5));
        sets.add(List.of(1, 2));

        CartesianProductIterator<Integer> iterator = new CartesianProductIterator<>(sets);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}