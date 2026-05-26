package al.sankevich.placeholders.configs.configurers.auto;

public interface Applicator<T> {

    AutoConfigurer apply(T t);
}
