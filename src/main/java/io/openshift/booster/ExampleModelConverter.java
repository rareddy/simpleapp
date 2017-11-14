package io.openshift.booster;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;

import io.swagger.converter.ModelConverter;
import io.swagger.converter.ModelConverterContext;
import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.BooleanProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.StringProperty;

public class ExampleModelConverter implements ModelConverter {

	@Override
	public Property resolveProperty(Type type, ModelConverterContext context, Annotation[] annotations,
			Iterator<ModelConverter> chain) {
        if (chain.hasNext()) {
            return chain.next().resolveProperty(type, context, annotations, chain);
        }
        return null;
	}

	@Override
	public Model resolve(Type type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        ModelImpl model;
        try {
            model = new ModelImpl();
            model.setName("ExampleModel");
            model.property("name", requiredProperty(String.class));
            model.property("description", property(String.class));
        } catch (Exception ex) {
            return null;
        }
        return model;
	}
	
    protected Property property(Class<?> typeClass) throws Exception {
        Property property = null;
        if (String.class.equals(typeClass))
            property = new StringProperty();
        else if (Integer.class.equals(typeClass))
            property = new IntegerProperty();
        else if (Boolean.class.equals(typeClass))
            property = new BooleanProperty();
        else
            throw new Exception("Unsupported property type " +  typeClass); //$NON-NLS-1$
        return property;
    }

    protected Property requiredProperty(Class<?> typeClass) throws Exception {
        Property property = property(typeClass);
        property.setRequired(true);
        return property;
    }		
}