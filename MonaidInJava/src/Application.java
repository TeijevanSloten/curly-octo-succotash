import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Application {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("sometext");

        Integer result = getValue(list)
                .map(String::toUpperCase)
                .map(s -> s.charAt(0))
                .map(character -> (int) character)
                .get();
        System.out.println(result);

    }

    private static Option<String> getValue(List<String> list) {
        return list.isEmpty() ? new None<>() : new Some<>(list.get(0));
    }

}

interface Option<T> {
    <U> Option<U> map(Function<T, U> consumer);
    T get();
}

class None<T> implements Option<T> {

    @Override
    public Option map(Function function) {
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
    public <U> Option<U> map(Function<T, U> function) {
        return new Some<>(function.apply(value));
    }

    @Override
    public T get() {
        return value;
    }
}