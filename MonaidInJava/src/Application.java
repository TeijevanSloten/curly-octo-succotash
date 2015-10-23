import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Application {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.add("sometext");

        String o = (String) getValue(list)
                .map(String::toUpperCase)
                .map(o1 -> ((String) o1).substring(4))
                .get();
        System.out.println(o);

    }

    private static Option<String> getValue(List<String> list) {
        return list.isEmpty() ? new None<>(): new Some<>(list.get(0));
    }

}

interface Option<T> {

    Option<?> map(Function<T, ?> consumer);
    T get();
}

class None<T> implements Option<T> {

    @Override
    public Option<?> map(Function function) {
        return this;
    }

    @Override
    public T get() {
        return null;
    }
}

class Some<T> implements Option<T> {
    private T value;

    public Some(T value) {
        this.value = value;
    }

    @Override
    public Option<?> map(Function<T, ?> function) {
        return new Some<>(function.apply(value));
    }

    @Override
    public T get() {
        return value;
    }
}