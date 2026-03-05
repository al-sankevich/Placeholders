package al.sankevich.placeholders.engines.source.ext;

import al.sankevich.placeholders.engines.source.SourceProcessingEngine;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

/**
 * General implementation of {@link SourceProcessingEngine} for some methods.
 */
public abstract class AbstractSourceProcessingEngine implements SourceProcessingEngine {

    @Override
    public <K, V> Map<K, V> process(
            final Map<K, V> source,
            final Function<String, Object> valuesProvider,
            final String... skippablePlaceholders
    ) {
        return process(List.of(source), valuesProvider, skippablePlaceholders).getFirst();
    }

    @Override
    @SuppressWarnings({"rawtypes"})
    public <T> List<T> process(
            final List<T> source,
            final Function<String, Object> valuesProvider,
            final String... skippablePlaceholders
    ) {
        Queue<List> listQueue = new LinkedList<>();
        Queue<Map> mapQueue = new LinkedList<>();
        listQueue.add(source);

        while (!listQueue.isEmpty()) {
            processList(listQueue.poll(), listQueue, mapQueue, valuesProvider, skippablePlaceholders);
        }

        return source;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void processList(
            final List<?> list,
            final Queue<List> listQueue,
            final Queue<Map> mapQueue,
            final Function<String, Object> valuesProvider,
            final String... skippablePlaceholders
    ) {
        if (!list.isEmpty()) {
            ListIterator<Object> iterator = (ListIterator<Object>) list.listIterator();

            while (iterator.hasNext()) {
                Object childValue = iterator.next();

                if (childValue instanceof String s) {
                    childValue = process(s, valuesProvider, skippablePlaceholders);
                    iterator.set(childValue);
                    continue;
                }

                if (childValue instanceof Map m) {
                    mapQueue.add(m);
                    processMap(mapQueue, listQueue, valuesProvider, skippablePlaceholders);
                    continue;
                }

                if (childValue instanceof List l) {
                    listQueue.add(l);
                }
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void processMap(
            final Queue<Map> mapQueue,
            final Queue<List> listQueue,
            final Function<String, Object> valuesProvider,
            final String... placeholdersToSkip
    ) {
        while (!mapQueue.isEmpty()) {

            Map<Object, Object> parentLeaf = (Map<Object, Object>) mapQueue.poll();

            for (Map.Entry<Object, Object> sourceLeaf : parentLeaf.entrySet()) {

                Object leafKey = sourceLeaf.getKey();
                Object leafValue = sourceLeaf.getValue();

                if (leafValue instanceof String s) {
                    leafValue = process(s, valuesProvider, placeholdersToSkip);
                    parentLeaf.replace(leafKey, leafValue);
                    continue;
                }

                if (leafValue instanceof Map m) {
                    mapQueue.add(m);
                    continue;
                }

                if (leafValue instanceof List l) {
                    listQueue.add(l);
                }
            }
        }
    }

    protected abstract Object process(
            String source,
            Function<String, Object> valuesProvider,
            String... skippablePlaceholders
    );

}
