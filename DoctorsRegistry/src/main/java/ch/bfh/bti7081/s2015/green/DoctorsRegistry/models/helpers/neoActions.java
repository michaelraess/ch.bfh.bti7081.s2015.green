package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.helpers;

public interface neoActions<T> {
  
  public T findAll(int limit);
  
  public void save(T entity);
  
  public void remove(T entity);
}
