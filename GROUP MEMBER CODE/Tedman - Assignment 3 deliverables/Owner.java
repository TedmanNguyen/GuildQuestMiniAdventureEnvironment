import java.util.List;

public interface Owner {
    void addOwnList(Object item);

    boolean removeOwnList(Object item); 
    
    <T> List<T> getOwnedItems(Class<T> itemClass);
}
