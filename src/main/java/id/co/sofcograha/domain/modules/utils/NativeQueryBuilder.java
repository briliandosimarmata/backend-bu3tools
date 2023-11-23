package id.co.sofcograha.domain.modules.utils;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeQueryBuilder<E> {

    private final EntityManager em;
    private final Class<E> domainClass;
    @Setter
    private StringBuilder queryStringBuilder;
    private Map<String, Object> queryParameters;

    public NativeQueryBuilder(EntityManager em, Class<E> domainClass) {
        this.em = em;
        this.domainClass = domainClass;
        this.queryStringBuilder = new StringBuilder();
        this.queryParameters = new HashMap<>();
    }

    public void addParameter(String key, Object value) {
        this.queryParameters.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public List<E> getResultList() {
        Query query = em.createNativeQuery(queryStringBuilder.toString(),
                domainClass);

        queryParameters.forEach(query::setParameter);

        return query.getResultList();
    }
}