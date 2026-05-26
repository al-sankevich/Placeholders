package al.sankevich.placeholders.configs.configurers.auto;

public interface BiApplicator<T, U> {

    AutoConfigurer accept(T t, U u);
}
